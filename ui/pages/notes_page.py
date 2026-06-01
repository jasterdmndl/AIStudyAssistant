from PySide6.QtWidgets import (
    QWidget,
    QVBoxLayout,
    QLabel,
    QPushButton,
    QListWidget,
    QInputDialog
)

from database.db import (
    get_connection
)


class NotesPage(QWidget):

    def __init__(self):
        super().__init__()

        layout = QVBoxLayout(self)

        title = QLabel("Notes")
        title.setObjectName(
            "pageTitle"
        )

        self.add_btn = QPushButton(
            "+ New Note"
        )

        self.notes_list = QListWidget()

        layout.addWidget(title)
        layout.addWidget(self.add_btn)
        layout.addWidget(self.notes_list)

        self.add_btn.clicked.connect(
            self.create_note
        )

        self.load_notes()

    def load_notes(self):

        self.notes_list.clear()

        conn = get_connection()

        cursor = conn.cursor()

        cursor.execute(
            "SELECT * FROM notes"
        )

        notes = cursor.fetchall()

        conn.close()

        for note in notes:

            self.notes_list.addItem(
                note[1]
            )

    def create_note(self):

        title, ok = (
            QInputDialog.getText(
                self,
                "New Note",
                "Title:"
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
            (title, content)
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