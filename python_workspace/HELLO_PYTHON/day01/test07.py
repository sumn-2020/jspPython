# 출력할 단수를 넣으세요 4 
# 4*1 =4
# 4*2 =8


dan = input("출력할 단수를 넣으세요")
idan = int(dan)
#print(dan, idan)
"""
print("{}*{}={}".format(idan,1,idan*1))
print("{}*{}={}".format(idan,2,idan*2))
print("{}*{}={}".format(idan,3,idan*3))
print("{}*{}={}".format(idan,4,idan*4))
print("{}*{}={}".format(idan,5,idan*5))
print("{}*{}={}".format(idan,6,idan*6))
print("{}*{}={}".format(idan,7,idan*7))
print("{}*{}={}".format(idan,8,idan*8))
print("{}*{}={}".format(idan,9,idan*9))
"""
for i in range(1,10):
    print("{}*{}={}".format(idan,i,idan*i))





