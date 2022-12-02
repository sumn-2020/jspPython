import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic, QtCore
import random
from random import Random
#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("main08.ui")[0]


#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    #초기화 메서드
    def __init__(self) :
        super().__init__()
        self.setupUi(self) #form_class 클래스 구동시킨다. 
        
        self.pb.clicked.connect(self.pbFunction)#버튼에 기능을 연결하는 코드
        #self.le_mine.returnPressed.connect(self.pbFunction)#엔터 칠때 function 동작 
        


    def drawLine(self, cnt):
        ret = ""
        
        for i in range(cnt):
            ret += "*"
        ret  += "\n"
        return ret
    
    
    #pb가 눌리면 작동할 함수
    def pbFunction(self) :
        #print("pbFunction")
        
        first = self.le_f.text()
        last = self.le_l.text()
        iFirst = int(first)
        iLast = int(last)
        
        result = ""
        
        for i in range(iFirst, iLast+1):
            result += self.drawLine(i)
            #result += self.drawLine(1)
            #result += self.drawLine(2)
        
        self.pte.setPlainText(result)
        
    


       
#메인메소드 
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()    
    
    
    
    