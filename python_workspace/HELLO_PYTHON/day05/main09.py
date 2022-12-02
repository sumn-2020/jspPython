import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic, QtCore
import random
from random import Random
#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("main09.ui")[0]


#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    #초기화 메서드
    def __init__(self) :
        super().__init__()
        self.setupUi(self) #form_class 클래스 구동시킨다. 
        
        self.pb1.clicked.connect(self.pbFunction)
        self.pb2.clicked.connect(self.pbFunction)
        self.pb3.clicked.connect(self.pbFunction)
        self.pb4.clicked.connect(self.pbFunction)
        self.pb5.clicked.connect(self.pbFunction)
        self.pb6.clicked.connect(self.pbFunction)
        self.pb7.clicked.connect(self.pbFunction)
        self.pb8.clicked.connect(self.pbFunction)
        self.pb9.clicked.connect(self.pbFunction)
        self.pb0.clicked.connect(self.pbFunction)
        
        self.pbCall.clicked.connect(self.myCall)
        
    def myCall(self) :
        str_tel = self.le.text()
        QMessageBox.question(self, 'calling', str_tel, QMessageBox.Yes, QMessageBox.NoButton)
        #QMessageBox.question(None, 'calling', str_tel, QMessageBox.Yes, QMessageBox.NoButton)




    #pb가 눌리면 작동할 함수
    def pbFunction(self) :
        str_new = self.sender().text()
        str_old = self.le.text()
        
        self.le.setText(str_old + str_new)
        print(self.sender().text())
        

        
    


       
#메인메소드 
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()    
    
    
    
    