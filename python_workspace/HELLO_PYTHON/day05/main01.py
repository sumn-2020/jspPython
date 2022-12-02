import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("main01.ui")[0]


#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    #초기화 메서드
    def __init__(self) :
        super().__init__()
        self.setupUi(self) #form_class 클래스 구동시킨다. 
        self.pb.clicked.connect(self.pbFunction)#버튼에 기능을 연결하는 코드
    
    #pb가 눌리면 작동할 함수
    def pbFunction(self) :
        self.lbl.setText("Good Evening")
        print(self.lbl); # ui파일에서 name = lbl : 글씨 
        
       
#메인메소드 
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()    
    
    
    
    