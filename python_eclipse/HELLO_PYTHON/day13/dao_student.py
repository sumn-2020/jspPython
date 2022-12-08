import psycopg2


class DaoStudent:
    
    #db 연동
    def __init__(self):
        self.conn = psycopg2.connect(host="127.0.0.1", dbname="python", user="postgres", password="python")
        self.cur = self.conn.cursor()
    
    
    ###################################
    
    ####selects###
    def selects(self):
        mydictionary = []
        
        
        sql = f"""
            select s_id, s_name, mobile, address from student
        """

        self.cur.execute(sql)
        rows = self.cur.fetchall()
        for r in rows : 
            mydictionary.append({'s_id' : r[0], 's_name' : r[1], 'mobile' : r[2], 'address' : r[3]})
        return mydictionary
    
    
    ###select###
    def select(self, s_id):
        
        sql = f"""
            select 
                s_id, s_name, mobile, address
             from 
                 student
             where 
                 s_id = '{s_id}'
        """ 
        
        self.cur.execute(sql) #sql문 실행
        rows = self.cur.fetchall() # 데이터 한번에 읽기
        r = rows[0]
        ret = {'s_id' : r[0], 's_name' : r[1], 'mobile' : r[2], 'address' : r[3]}
        return ret

    
    #####insert#####
    def insert(self, s_id, s_name, mobile, address):
        
        sql = f"""
            insert into student
                (s_id, s_name, mobile, address)
            values
                ('{s_id}', '{s_name}', '{mobile}', '{address}')
        """
        
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount
    
    
    ###update###
    def update(self, s_id, s_name, mobile, address):
        sql = f"""
            
            update student
            set 
                s_name = '{s_name}',
                mobile = '{mobile}',
                address = '{address}'
            where
                s_id = '{s_id}'
        
        """
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount
    
    
    ###delete###
    def delete(self, s_id):
        
        sql = f"""
            delete from student
            where s_id = '{s_id}'
        """
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount
        
  

    ###################################
    
    # cur conn 닫아주기 :  메모리 용량 
    def __del__(self):
        self.cur.close()
        self.conn.close()
        
        
#화면에 출력 
if __name__ == '__main__':
    ds = DaoStudent()
    #cnt = ds.insert('8','8','8', '8')
    #cnt = ds.update('9', '9', '9')
    #cnt = ds.delete('8') #8이라는 id 가지고 있는 col 지우기 
    #list = ds.selects()
    select = ds.select('1') # e_id 1을 가지고 있는 col select하기 
    print(select)
    
    
    
    
    