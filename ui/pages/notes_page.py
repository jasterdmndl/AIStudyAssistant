from PySide6.QtWidgets import (
    QWidget,
    QHBoxLayout,
    QVBoxLayout,
    QListWidget,
    QListWidgetItem,
    QPushButton,
    QLabel,
    QLineEdit,
    QTextEdit,
    QMessageBox,
    QInputDialog,
)

from database.db import (
    get_connection,
    get_note,
    get_all_notes,
    update_note,
    delete_note,
)


class NotesPage(QWidget):

    def __init__(self):
        super().__init__()

        self.current_note_id = None

        # MAIN LAYOUT
        main_layout = QHBoxLayout(self)

        # =========================
        # LEFT PANEL
        # =========================
        left_panel = QVBoxLayout()

        title = QLabel("Notes")
        title.setObjectName("pageTitle")

        self.add_btn = QPushButton(
            "+ New Note"
        )

        self.search_input = QLineEdit()

        self.search_input.setPlaceholderText(
            "Search notes..."
        )

        self.notes_list = QListWidget()

        left_panel.addWidget(title)
        left_panel.addWidget(
            self.add_btn
        )

        left_panel.addWidget(
            self.search_input
        )

        left_panel.addWidget(
            self.notes_list
        )

        # =========================
        # RIGHT PANEL
        # =========================
        right_panel = QVBoxLayout()
        
        self.title_input = QLineEdit()
        self.modified_label = QLabel(
            "Last Modified: -"
        )
        self.title_input.setPlaceholderText(
            "Note title..."
        )

        self.content_editor = QTextEdit()
        self.content_editor.setPlaceholderText(
            "Start writing..."
        )

        self.save_btn = QPushButton(
            "Save Note"
        )

        self.delete_btn = QPushButton(
            "Delete Note"
        )

        right_panel.addWidget(
            self.title_input
        )

        right_panel.addWidget(
            self.modified_label
        )

        right_panel.addWidget(
            self.content_editor
        )

        right_panel.addWidget(
            self.save_btn
        )

        right_panel.addWidget(
            self.delete_btn
        )

        # =========================
        # ADD PANELS
        # =========================
        main_layout.addLayout(
            left_panel,
            1
        )

        main_layout.addLayout(
            right_panel,
            3
        )

        # =========================
        # SIGNALS
        # =========================
        self.add_btn.clicked.connect(
            self.create_note
        )

        self.notes_list.itemClicked.connect(
            self.load_selected_note
        )

        self.save_btn.clicked.connect(
            self.save_note
        )

        self.delete_btn.clicked.connect(
            self.remove_note
        )

        self.search_input.textChanged.connect(
            self.filter_notes
        )

        self.load_notes()

    def filter_notes(self):

        text = (
            self.search_input.text()
            .lower()
        )

        for row in range(
            self.notes_list.count()
        ):

            item = (
                self.notes_list.item(row)
            )

            item.setHidden(
                text not in item.text().lower()
            )

    # =========================
    # LOAD NOTES
    # =========================
    def load_notes(self):

        self.notes_list.clear()

        notes = get_all_notes()

        for note in notes:

            item = QListWidgetItem(
                note[1]
            )

            item.setData(
                1,
                note[0]
            )

            self.notes_list.addItem(
                item
            )

    # =========================
    # CREATE NOTE
    # =========================
    def create_note(self):

        title, ok = (
            QInputDialog.getText(
                self,
                "New Note",
                "Enter note title:"
            )
        )

        if not ok:
            return

        if not title.strip():
            return

        conn = get_connection()

        cursor = conn.cursor()

        cursor.execute(
            """
            INSERT INTO notes
            (
                title,
                content
            )
            VALUES (?, ?)
            """,
            (
                title,
                ""
            )
        )

        conn.commit()
        conn.close()

        self.load_notes()

    # =========================
    # LOAD SELECTED NOTE
    # =========================
    def load_selected_note(
        self,
        item
    ):

        note_id = item.data(1)

        note = get_note(note_id)

        if not note:
            return

        self.current_note_id = note[0]

        self.title_input.setText(
            note[1]
        )

        self.content_editor.setPlainText(
            note[2]
        )

        self.modified_label.setText(
            f"Last Modified: {note[3]}"
        )

    # =========================
    # SAVE NOTE
    # =========================
    def save_note(self):

        if not self.current_note_id:

            QMessageBox.warning(
                self,
                "No Note Selected",
                "Select a note first."
            )

            return

        update_note(
            self.current_note_id,
            self.title_input.text(),
            self.content_editor.toPlainText(),
        )

        QMessageBox.information(
            self,
            "Saved",
            "Note updated successfully."
        )

        self.load_notes()

    # =========================
    # DELETE NOTE
    # =========================
    def remove_note(self):

        if not self.current_note_id:

            QMessageBox.warning(
                self,
                "No Note Selected",
                "Select a note first."
            )

            return

        confirm = QMessageBox.question(
            self,
            "Delete Note",
            "Are you sure you want to delete this note?"
        )

        if (
            confirm
            != QMessageBox.StandardButton.Yes
        ):
            return

        delete_note(
            self.current_note_id
        )

        self.current_note_id = None

        self.title_input.clear()

        self.content_editor.clear()

        self.load_notes()