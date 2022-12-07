import psycopg2
from Cython.Compiler.Naming import self_cname

class DaoMem:
    
    #db 연동
    def __init__(self):
        self.conn = psycopg2.connect(host="127.0.0.1", dbname="python", user="postgres", password="python")
        self.cur = self.conn.cursor()
        
    #############################
    
    #list페이지 출력 
    def selects(self):
        mydictionary = []
        
        self.cur.execute("select m_id, m_nm, tel, ymd from member")
        
        rows = self.cur.fetchall()
        for r in rows : #rows안에 들어있는 데이터들의 r(아이템들을) 다 읽을때까지 반복 
            mydictionary.append({'m_id' : r[0], 'm_nm' : r[1], 'tel' : r[2], 'ymd' : r[3]})
        return mydictionary
    
    
    #detail 페이지 출력
    def select(self,m_id):
        
        sql = f"""
            select 
                m_id, m_nm, tel, ymd
            from 
                member
            where 
                m_id = '{m_id}'
        """
        
        self.cur.execute(sql)
        rows = self.cur.fetchall()
        r = rows[0]
        ret = {'m_id' : r[0], 'm_nm' : r[1], 'tel' : r[2], 'ymd' : r[3]}
        return ret
    
    
    
    
    
    #insert 화면 출력 
    def insert(self, m_id, m_nm, tel, ymd):
        
        sql = f"""
        
            insert into member
                (m_id, m_nm, tel, ymd)
            values
                ('{m_id}', '{m_nm}', '{tel}', '{ymd}')
        
        """
        
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount  # insert는 한줄이 추가될 수 밖에 없음 => 성공이면 return => 1

    
    
    #edit화면 출력
    def update(self, m_id, m_nm, tel, ymd):
        sql = f"""
            
            update member
            set 
                m_nm = '{m_nm}',
                tel = '{tel}',
                ymd = '{ymd}'
            where 
                m_id = '{m_id}'
        
        """
        
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount  
    
    
    #delete 
    def delete(self, m_id):
        
        sql = f"""

            delete from member
            where m_id = '{m_id}'
            
        """
        
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount
    

    
    #############################
    
    # cur conn 닫아주기 :  메모리 용량 
    def __del__(self):
        self.cur.close()
        self.conn.close()
    
    
    
 #화면에 출력 
if __name__ == '__main__':
    ds = DaoMem()
    #cnt = ds.insert('8','8','8', '8')
    #cnt = ds.update('9', '9', '9')
    #cnt = ds.delete('8') #8이라는 id 가지고 있는 col 지우기 
    #list = ds.selects()
    #select = ds.select('1') # e_id 1을 가지고 있는 col select하기 
    print(list)
       
    