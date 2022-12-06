import psycopg2


col01 = '4'


conn = psycopg2.connect(host="127.0.0.1", dbname="python", user="postgres", password="python")
cur = conn.cursor()


# f => 파이썬 3.5 이상부터 돌아감 
sql = f"""

    delete from sample 
    where col01 = '{col01}'

"""


cur.execute(sql)
print("cnt", cur.rowcount)
conn.commit()


cur.close()
conn.close()






