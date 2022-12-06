import psycopg2

class DaoEmp:
    
    #db 연동
    def __init__(self):
        self.conn = psycopg2.connect(host="127.0.0.1", dbname="python", user="postgres", password="python")
        self.cur = self.conn.cursor()
    
    
    ###################################
    
    ####selects###
    def selects(self):
        mydictionary = []
        
        self.cur.execute("select e_id, e_name, sex, addr from emp")
        
        #fetchone() : 한줄씩읽기
        #fetchall() : 한번에읽기
        rows = self.cur.fetchall()
        for r in rows : #rows안에 들어있는 데이터들의 r(아이템들을) 다 읽을때까지 반복 
            mydictionary.append({'e_id' : r[0], 'e_name' : r[1], 'sex' : r[2], 'addr' : r[3]})
            #mydictionary배열 안에 다음과 같은 내용 집어넣기(append)
            #'e_id' : 데이터 0번째 내용
        return mydictionary
    
    
    ###select###
    def select(self, e_id):
        
        sql = f"""
            select 
                e_id, e_name, sex, addr
             from 
                 emp
             where 
                 e_id = '{e_id}'
        """ 
        
        self.cur.execute(sql) #sql문 실행
        rows = self.cur.fetchall() # 데이터 한번에 읽기
        r = rows[0]
        ret = {'e_id' : r[0], 'e_name' : r[1], 'sex' : r[2], 'addr' : r[3]}
        return ret

    
    #####insert#####
    def insert(self, e_id, e_name, sex, addr):
        
        sql = f"""
            insert into emp
                (e_id, e_name, sex, addr)
            values
                ('{e_id}', '{e_name}', '{sex}', '{addr}')
        """
        
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount
    
    
    ###update###
    def update(self, e_name, sex, addr):
        sql = f"""
            
            update emp
            set 
                e_name = '{e_name}',
                sex = '{sex}',
                addr = '{addr}'
        
        """
        self.cur.execute(sql)
        self.conn.commit()
        return self.cur.rowcount
    
    
    ###delete###
    def delete(self, e_id):
        
        sql = f"""
            delete from emp
            where e_id = '{e_id}'
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
    ds = DaoEmp()
    #cnt = ds.insert('8','8','8', '8')
    #cnt = ds.update('9', '9', '9')
    #cnt = ds.delete('8') #8이라는 id 가지고 있는 col 지우기 
    #list = ds.selects()
    select = ds.select('1') # e_id 1을 가지고 있는 col select하기 
    print(select)
    
    
    
    
    