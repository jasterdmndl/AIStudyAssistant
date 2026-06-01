from PySide6.QtWidgets import (
    QWidget,
    QVBoxLayout,
    QLabel,
    QPushButton,
    QHBoxLayout,
    QFrame
)


class DashboardPage(QWidget):

    def __init__(self):
        super().__init__()

        main_layout = QVBoxLayout(self)

        # Header
        title = QLabel("Welcome Back")
        title.setObjectName("pageTitle")

        subtitle = QLabel(
            "Ready to continue learning?"
        )

        main_layout.addWidget(title)
        main_layout.addWidget(subtitle)

        # Quick Actions
        actions_frame = QFrame()

        actions_layout = QHBoxLayout()

        note_btn = QPushButton(
            "New Note"
        )

        quiz_btn = QPushButton(
            "New Quiz"
        )

        actions_layout.addWidget(note_btn)
        actions_layout.addWidget(quiz_btn)

        actions_frame.setLayout(
            actions_layout
        )

        main_layout.addWidget(actions_frame)

        # Recent Notes
        notes_frame = QFrame()

        notes_layout = QVBoxLayout()

        notes_title = QLabel(
            "Recent Notes"
        )

        notes_text = QLabel(
            "No notes available"
        )

        notes_layout.addWidget(
            notes_title
        )

        notes_layout.addWidget(
            notes_text
        )

        notes_frame.setLayout(
            notes_layout
        )

        main_layout.addWidget(
            notes_frame
        )

        # Statistics
        stats_frame = QFrame()

        stats_layout = QVBoxLayout()

        stats_title = QLabel(
            "Study Statistics"
        )

        notes_count = QLabel(
            "Notes: 0"
        )

        quiz_count = QLabel(
            "Quizzes: 0"
        )

        stats_layout.addWidget(
            stats_title
        )

        stats_layout.addWidget(
            notes_count
        )

        stats_layout.addWidget(
            quiz_count
        )

        stats_frame.setLayout(
            stats_layout
        )

        main_layout.addWidget(
            stats_frame
        )

        main_layout.addStretch()