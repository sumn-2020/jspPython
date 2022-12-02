class Xi: 
    def __init__(self):
        self.index_look = 10
    
    def corona(self):
        self.index_look -= 1
        

class Trump: 
    def __init__(self):
        self.money = 2
        
    def youfire(self):
        self.money += 1
        

class Kim:
    def __init__(self):
        self.nuclear = 20
    
    def aoji(self):
        self.nuclear += 2


class ParkInSoo(Xi, Trump, Kim):
    def __init__(self):
        #super().__init__() #super만 쓰면 세개중에 하나만 상속 받는다. 
        Xi.__init__(self)
        Trump.__init__(self)
        Kim.__init__(self)
       
    

if __name__ == '__main__': 
    pis = ParkInSoo() 
    print("index_look", pis.index_look)
    print("money", pis.money)
    print("nuclear", pis.nuclear)
    pis.corona()
    pis.youfire()
    pis.aoji()
    print("index_look", pis.index_look)
    print("money", pis.money)
    print("nuclear", pis.nuclear)
    
    
    
    
    
    
    
    
    
