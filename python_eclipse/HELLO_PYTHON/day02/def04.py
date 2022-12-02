#function 
def addminnuldiv(a,b):
    return a+b, a-b, a*b, a/b

sum  = addminnuldiv(5, 4)

"""
sum, min, mul, div = addminnuldiv(5, 4)

print("sum", sum)
print("min", min)
print("mul", mul)
print("div", div)

"""

print("sum", sum[0]) #튜플 :배열이 아닌데 배열처럼 쓰는거