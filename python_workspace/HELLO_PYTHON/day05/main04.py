import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random
from random import Random
#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("main04.ui")[0]


#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    #초기화 메서드
    def __init__(self) :
        super().__init__()
        self.setupUi(self) #form_class 클래스 구동시킨다. 
        self.pb.clicked.connect(self.pbFunction)#버튼에 기능을 연결하는 코드
    
    #pb가 눌리면 작동할 함수
    def pbFunction(self) :
        
        
        #선언부 
        mine = self.leMine.text()
        com = ""
        result = ""
        
        
        #persistent layer
        rnd = random.random()
        #rnd = random() #from이 있으면 이렇게 가져와야됨 
        
        if rnd > 0.5 :
            com = "홀"
        else:
            com = "짝"
            
        if mine == com : 
            result = "이김"
        else:
            result = "졌음"
        
        
        #presentation layer    
        self.leCom.setText(com)
        self.leResult.setText(result)
            
       
            
           
       
       
       
       
       
       
#메인메소드 
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()    
    
    
    
    