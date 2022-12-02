import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic, QtCore
import random



#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("main0X.ui")[0]


#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    
    # global mine
    # global result


    #초기화 메서드
    def __init__(self) :
        super().__init__()
        self.setupUi(self) #form_class 클래스 구동시킨다. 
        
        
        self.pb.clicked.connect(self.pbFunction)#버튼에 기능을 연결하는 코드
        self.setComRandom()
     

    
    def setComRandom(self):
        global com #전역변수를 사용하면 지역변수처럼 안에 있어도 전역변수처럼 사용할 수 있음 
        
        arr9 = [1,2,3,4,5,6,7,8,9]
        
        for i in range(1000):
            rnd = int(random.random()*9)
            a = arr9[0]
            b = arr9[rnd]
            arr9[0] = b
            arr9[rnd] = a
        
        com  = str(arr9[0]) + "" + str(arr9[1]) + "" + str(arr9[2])
        print(com)

    

    def pbFunction(self) :
        
        mine = self.le.text() 
        result = ""

        strike = self.getStrike(mine, com)
        ball = self.getBall(mine, com)
           
        result += mine + " " + str(strike)  + "S" + str(ball) + "B"
        self.te.append(result) #textarea는 setText가 아니라 append써야됨 
        self.le.setText("") # 작성된 글 새로 초기화 

        if strike == 3: 
            msg = '정답입니다.'
            QMessageBox.question(self, '메시지', msg, QMessageBox.Yes, QMessageBox.NoButton)
        

    

      
    #스트라이크 갯수 
    def getStrike(self, mine, com):
        
        ret = 0
        
        m1 = mine[0:1]
        m2 = mine[1:2]
        m3 = mine[2:3]
        
        c1 = com[0:1]
        c2 = com[1:2]
        c3 = com[2:3] 
        
        if c1 == m1: 
            ret += 1
        
        if c2 == m2:
            ret += 1
        
        if c3 == m3:
            ret += 1
            
            
        return ret
        
    
    #ball의 갯수
    def getBall(self, mine, com):
    
        ret = 0
    
        m1 = mine[0:1]
        m2 = mine[1:2]
        m3 = mine[2:3]
        
        c1 = com[0:1]
        c2 = com[1:2]
        c3 = com[2:3] 
    
    
        if c1 == m2 or c1 == m3: 
            ret += 1
    
        if c2 == m1 or c1 == m3:
            ret += 1
    
        if c3 == m1 or c1 == m2:
            ret += 1
    
    
        return ret
   
  
      
        
    


       
#메인메소드 
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass() 
    myWindow.show()
    app.exec_()    
    
    
    
    