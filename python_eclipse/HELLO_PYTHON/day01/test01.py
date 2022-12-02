# 첫번째 숫자를 넣으세요 1
a = input("첫번째 숫자를 넣으세요")
# 두번째 숫자를 넣으세요 2
b = input("두번째 숫자를 넣으세요")
# 숫자의 합은 3입니다.
sum = int(a)+int(b)

#print("숫자의 합은" + str(sum)) # 문자와 숫자는 서로 합쳐질수 없음 숫자를 문자로 바꿔야함
#print("숫자의 합은",str(sum))
print("숫자의 합은 {}".format(sum))