import psycopg2


conn = psycopg2.connect(host="127.0.0.1", dbname="python", user="postgres", password="python")
cur = conn.cursor()
cur.execute("select col01, col02, col03 from sample")
rows = cur.fetchall()


#파이썬 cursor() = 자바 statment와 동급
for i in rows: 
    print(i)


cur.close()
conn.close()



