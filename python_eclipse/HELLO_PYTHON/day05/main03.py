import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from random import random
#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("main03.ui")[0]


#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    #초기화 메서드
    def __init__(self) :
        super().__init__()
        self.setupUi(self) 
        self.pb.clicked.connect(self.pbFunction)
    
    #pb가 눌리면 작동할 함수
    def pbFunction(self) :

        arr45 = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45]
        
        for i in range(1000) : 
            rnd = int(random()*45)
            a = arr45[0]
            b = arr45[rnd]
            arr45[0]=b
            arr45[rnd]=a

        print(arr45[0],arr45[1],arr45[2],arr45[3],arr45[4],arr45[5])
        self.le1.setText(str(arr45[0]))
        self.le2.setText(str(arr45[1]))
        self.le3.setText(str(arr45[2]))
        self.le4.setText(str(arr45[3]))
        self.le5.setText(str(arr45[4]))
        self.le6.setText(str(arr45[5]))


       
#메인메소드 
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()    
    
    
    
    