from PySide6.QtWidgets import (
    QWidget,
    QVBoxLayout,
    QLabel
)


class DashboardPage(QWidget):

    def __init__(self):
        super().__init__()

        layout = QVBoxLayout(self)

        title = QLabel("Welcome Back")
        title.setObjectName("title")

        subtitle = QLabel(
            "Select a section from the sidebar."
        )

        layout.addWidget(title)
        layout.addWidget(subtitle)
        layout.addStretch()