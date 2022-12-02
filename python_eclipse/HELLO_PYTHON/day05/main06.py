import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("main06.ui")[0]


#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    #초기화 메서드
    def __init__(self) :
        super().__init__()
        self.setupUi(self) #form_class 클래스 구동시킨다. 
        self.pb.clicked.connect(self.pbFunction)#버튼에 기능을 연결하는 코드
    
    #pb가 눌리면 작동할 함수
    def pbFunction(self) :
       
        # dan = self.le.text()
        # idan = int(dan)
        # result = ""
        #
        #
        # for i in range(1,10):
        #     result += "{}*{}={}\n".format(idan,i,idan*i)
        #     print(result)
        #     self.te.setText(result)    
        #

        dan = self.le.text()
        idan = int(dan)
        result = ""
        result += f"{idan}*{1}={idan*1}\n"
        result += f"{idan}*{2}={idan*2}\n"
        result += f"{idan}*{3}={idan*3}\n"
        result += f"{idan}*{4}={idan*4}\n"
        result += f"{idan}*{5}={idan*5}\n"
        result += f"{idan}*{6}={idan*6}\n"
        result += f"{idan}*{7}={idan*7}\n"
        result += f"{idan}*{8}={idan*8}\n"
        result += f"{idan}*{9}={idan*9}\n"
        
        self.te.setText(result)



       
       
#메인메소드 
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()    
    
    
    
    