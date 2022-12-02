import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random
from random import Random
#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("main07.ui")[0]


#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    #초기화 메서드
    def __init__(self) :
        super().__init__()
        self.setupUi(self) #form_class 클래스 구동시킨다. 
        
        self.pb.clicked.connect(self.pbFunction)#버튼에 기능을 연결하는 코드
        self.le_mine.returnPressed.connect(self.pbFunction)#엔터 칠때 function 동작 
        
    
    #pb가 눌리면 작동할 함수
    def pbFunction(self) :
        
        mine = self.le_mine.text()
        com = ""
        result = ""
        
        rnd = random.random()
        if rnd > 0.66:
            com = " 가위"
        elif rnd > 0.33:
            com = "보"
        else:
            com = "바위"
            
            
        if com == "가위" and mine == "가위" : result = "비김"
        if com == "가위" and mine == "바위" : result = "이김"
        if com == "가위" and mine == "보" : result = "짐"
        
        if com == "바위" and mine == "가위" : result = "짐"    
        if com == "바위" and mine == "바위" : result = "비김"    
        if com == "바위" and mine == "보" : result = "이김"     
            
        if com == "보" and mine == "가위" : result = "이김"    
        if com == "보" and mine == "바위" : result = "짐"    
        if com == "보" and mine == "보" : result = "비김" 
        
        self.le_com.setText(com)         
        self.le_result.setText(result)         
       
           
       
       
       
       
       
       
#메인메소드 
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()    
    
    
    
    