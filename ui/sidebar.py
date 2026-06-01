from PySide6.QtWidgets import (
    QWidget,
    QVBoxLayout,
    QPushButton,
    QLabel
)


class Sidebar(QWidget):

    def __init__(self):
        super().__init__()

        layout = QVBoxLayout(self)

        logo = QLabel(
            "AIStudyAssistant"
        )

        layout.addWidget(logo)

        self.dashboard_btn = QPushButton(
            "Dashboard"
        )

        self.subjects_btn = QPushButton(
            "Subjects"
        )

        self.notes_btn = QPushButton(
            "Notes"
        )

        self.quizzes_btn = QPushButton(
            "Quizzes"
        )

        self.assistant_btn = QPushButton(
            "Assistant"
        )

        self.settings_btn = QPushButton(
            "Settings"
        )

        layout.addWidget(
            self.dashboard_btn
        )

        layout.addWidget(
            self.subjects_btn
        )

        layout.addWidget(
            self.notes_btn
        )

        layout.addWidget(
            self.quizzes_btn
        )

        layout.addWidget(
            self.assistant_btn
        )

        layout.addStretch()

        layout.addWidget(
            self.settings_btn
        )