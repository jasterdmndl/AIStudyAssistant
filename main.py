import sys

from PySide6.QtWidgets import (
    QApplication
)

from ui.main_window import (
    MainWindow
)

from database.db import (
    initialize_database
)


app = QApplication(sys.argv)
app.setStyleSheet("""
QWidget {
    background: #F7F7F5;
    color: #1F1F1F;
    font-size: 14px;
}

#logo {
    font-size: 18px;
    font-weight: bold;
    padding: 10px;
}

QPushButton {
    border: none;
    text-align: left;
    padding: 10px 12px;
    border-radius: 8px;
    background: transparent;
}

QPushButton:hover {
    background: #EDEDED;
}

QLabel {
    background: transparent;
}
                  
#pageTitle {
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 6px;
}

QFrame {
    background: white;
    border: 1px solid #E5E5E5;
    border-radius: 10px;
    padding: 10px;
}
""")

initialize_database()

window = MainWindow()

window.resize(1200, 800)

window.show()

app.exec()