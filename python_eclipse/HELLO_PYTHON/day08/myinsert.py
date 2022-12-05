import psycopg2

conn = psycopg2.connect(host="127.0.0.1", dbname="python", user="postgres", password="python")
cur = conn.cursor()


sql = """
    insert into sample 
        (col01, col02, col03) 
    values 
        ('4','4','4')
"""


cur.execute(sql)
print("cnt", cur.rowcount)
conn.commit()


cur.close()
conn.close()