from PySide6.QtWidgets import (
    QWidget,
    QHBoxLayout
)

from ui.sidebar import Sidebar
from ui.pages.dashboard_page import (
    DashboardPage
)


class MainWindow(QWidget):

    def __init__(self):
        super().__init__()

        layout = QHBoxLayout(self)

        self.sidebar = Sidebar()

        self.dashboard = DashboardPage()

        layout.addWidget(
            self.sidebar
        )

        layout.addWidget(
            self.dashboard,
            1
        )