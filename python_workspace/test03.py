# 홀짝을 넣으세요 홀
#나:홀
#컴퓨터: 짝
#결과: 졌음
import random

print("홀짝을 넣으세요")
a = input("나 : ")
b = random.random()

#b(0.5)이상이면 짝
#b(0.5)이하면 홀
#같으면 내가 이김

if b > 0.5:
    b = "짝"
else: 
    b = "홀"

print("컴퓨터 : " + b)

if a == b :
    print("내가이김")
else:
    print("컴퓨터가이김")


