import psycopg2


col01 = '4'
col02 = '8'
col03 = '8'


conn = psycopg2.connect(host="127.0.0.1", dbname="python", user="postgres", password="python")
cur = conn.cursor()


# f => 파이썬 3.5 이상부터 돌아감 
sql = f"""
    update sample
    set 
        col02 = '{col02}',
        col03 = '{col03}'
    where col01 = '{col01}'
"""


cur.execute(sql)
print("cnt", cur.rowcount)
conn.commit()


cur.close()
conn.close()


