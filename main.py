from PySide6.QtWidgets import (
    QApplication,
    QMainWindow,
    QLabel
)

import sys


class MainWindow(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle(
            "AI Study Assistant"
        )

        self.resize(1200, 800)

        label = QLabel(
            "Welcome to AI Study Assistant"
        )

        self.setCentralWidget(label)


app = QApplication(sys.argv)

window = MainWindow()

window.show()

app.exec()