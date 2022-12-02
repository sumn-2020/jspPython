# 가위바위보를 넣으세요 가위
#나: 가위
#컴퓨터: 바위
#결과: 비김
import random
from random import Random


mine = input("가위바위보를 넣으세요")
com = ""
result = ""

rnd = random.random()

if rnd > 0.66 : 
    com = "가위"
elif rnd > 0.33: 
    com = "보"
else:
    com = "바위"

"""
if(mine == com):
    result = "비김"
elif com == "바위" and mine == "가위"  or com == "가위" and mine == "보"   or com == "보" and mine == "바위":
    result = "컴퓨터가 이김"
else:
    result = "내가이김"
"""
if com == "가위" and mine == "가위" : result = "비김"    
if com == "가위" and mine == "바위" : result = "이김"    
if com == "가위" and mine == "보" : result = "짐"    
    
if com == "바위" and mine == "가위" : result = "짐"    
if com == "바위" and mine == "바위" : result = "비김"    
if com == "바위" and mine == "보" : result = "이김"     
    
if com == "보" and mine == "가위" : result = "이김"    
if com == "보" and mine == "바위" : result = "짐"    
if com == "보" and mine == "보" : result = "비김"    
    
print("mine", mine)
print("com",com)
print("result", result)







