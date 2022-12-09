import psycopg2


class DaoTeacher:
    
    #db 연동
    def __init__(self):
        self.conn = psycopg2.connect(host="127.0.0.1", dbname="python", user="postgres", password="python")
        self.cur = self.conn.cursor()
    
    
    ###################################
    
    ####selects###
    def selects(self):
        mydictionary = []
        
        
        sql = f"""
            select t_id, t_name, mobile, addr from teacher
        """

        self.cur.execute(sql)
        rows = self.cur.fetchall()
        for r in rows : 
            mydictionary.append({'t_id' : r[0], 't_name' : r[1], 'mobile' : r[2], 'addr' : r[3]})
        return mydictionary
    
    
    ###select###
    def select(self, t_id):
        
        sql = f"""
            select 
                t_id, t_name, mobile, addr
             from 
                 teacher
             where 
                 t_id = '{t_id}'
        """ 
        
        self.cur.execute(sql) #sql문 실행
        rows = self.cur.fetchall() # 데이터 한번에 읽기
        r = rows[0]
        ret = {'t_id' : r[0], 't_name' : r[1], 'mobile' : r[2], 'addr' : r[3]}
        return ret

    
    #####insert##### NEXTVAL 시퀀스 사용하기 
    def insert(self, t_name, mobile, addr):
        
        sql = f"""
            insert into teacher
                (t_id, t_name, mobile, addr)
            values
                (nextval('t_seq'), '{t_name}', '{mobile}', '{addr}')
        """
        
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount
    
    
    ###update###
    def update(self, t_id, t_name, mobile, addr):
        sql = f"""
            
            update teacher
            set 
                t_id = '{t_id}',
                t_name = '{t_name}',
                mobile = '{mobile}',
                addr = '{addr}'
            where
                t_id = '{t_id}'
        
        """
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount
    
    
    ###delete###
    def delete(self, t_id):
        
        sql = f"""
            delete from teacher
            where t_id = '{t_id}'
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
    ds = DaoTeacher()
    cnt = ds.insert('7','7', '7')
    #cnt = ds.update('3', '9', '9', '9')
    #cnt = ds.delete('4') #8이라는 id 가지고 있는 col 지우기 
    #list = ds.selects()
    #select = ds.select('3') # e_id 1을 가지고 있는 col select하기 
    print(cnt)
    
    
    
    
    