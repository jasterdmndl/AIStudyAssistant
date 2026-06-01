from PySide6.QtWidgets import (
    QWidget,
    QVBoxLayout,
    QPushButton,
    QLabel
)
from PySide6.QtCore import Qt


class Sidebar(QWidget):

    def __init__(self):
        super().__init__()

        self.setFixedWidth(220)

        layout = QVBoxLayout(self)

        logo = QLabel("AIStudyAssistant")
        logo.setObjectName("logo")

        layout.addWidget(logo)

        self.dashboard_btn = QPushButton("Dashboard")
        self.subjects_btn = QPushButton("Subjects")
        self.notes_btn = QPushButton("Notes")
        self.quizzes_btn = QPushButton("Quizzes")
        self.assistant_btn = QPushButton("Assistant")
        self.settings_btn = QPushButton("Settings")

        buttons = [
            self.dashboard_btn,
            self.subjects_btn,
            self.notes_btn,
            self.quizzes_btn,
            self.assistant_btn,
        ]

        for btn in buttons:
            btn.setCursor(
                Qt.PointingHandCursor
            )
            layout.addWidget(btn)

        layout.addStretch()

        layout.addWidget(self.settings_btn)