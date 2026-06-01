from PySide6.QtWidgets import (
    QWidget,
    QHBoxLayout,
    QStackedWidget
)

from ui.sidebar import Sidebar

from ui.pages.dashboard_page import DashboardPage
from ui.pages.subjects_page import SubjectsPage
from ui.pages.notes_page import NotesPage
from ui.pages.quizzes_page import QuizzesPage
from ui.pages.assistant_page import AssistantPage
from ui.pages.settings_page import SettingsPage


class MainWindow(QWidget):

    def __init__(self):
        super().__init__()

        self.setObjectName("mainWindow")

        layout = QHBoxLayout(self)

        self.sidebar = Sidebar()

        self.stack = QStackedWidget()

        self.dashboard_page = DashboardPage()
        self.subjects_page = SubjectsPage()
        self.notes_page = NotesPage()
        self.quizzes_page = QuizzesPage()
        self.assistant_page = AssistantPage()
        self.settings_page = SettingsPage()

        self.stack.addWidget(self.dashboard_page)
        self.stack.addWidget(self.subjects_page)
        self.stack.addWidget(self.notes_page)
        self.stack.addWidget(self.quizzes_page)
        self.stack.addWidget(self.assistant_page)
        self.stack.addWidget(self.settings_page)

        layout.addWidget(self.sidebar)
        layout.addWidget(self.stack, 1)

        self.sidebar.dashboard_btn.clicked.connect(
            lambda: self.stack.setCurrentIndex(0)
        )

        self.sidebar.subjects_btn.clicked.connect(
            lambda: self.stack.setCurrentIndex(1)
        )

        self.sidebar.notes_btn.clicked.connect(
            lambda: self.stack.setCurrentIndex(2)
        )

        self.sidebar.quizzes_btn.clicked.connect(
            lambda: self.stack.setCurrentIndex(3)
        )

        self.sidebar.assistant_btn.clicked.connect(
            lambda: self.stack.setCurrentIndex(4)
        )

        self.sidebar.settings_btn.clicked.connect(
            lambda: self.stack.setCurrentIndex(5)
        )