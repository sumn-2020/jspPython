from flask import Flask
from flask import request
import psycopg2
from flask import Flask,render_template
from day11.dao_mem import DaoMem


app = Flask(__name__)


#list 화면 출력 
@app.route('/')
@app.route('/mem_list')
def mem_list():   
    dm = DaoMem()
    list = dm.selects()
    return render_template('mem_list.html', list=list)


#detail 화면 출력
@app.route('/mem_detail')
def mem_detail():
    m_id = request.args.get('m_id', '') #파라미터 받기 e_id없으면 빈공간으로 받기
    
    dm = DaoMem()
    mem = dm.select(m_id)
    return render_template('mem_detail.html', mem = mem)
    

#edit 화면 출력 
@app.route('/mem_edit')
def mem_edit():
    m_id = request.args.get('m_id', '') # mem_list에서 받아온 파라미터 e_id없으면 빈공간으로 받기
    
    dm = DaoMem()
    mem = dm.select(m_id)
    return render_template('mem_edit.html',mem = mem)


@app.route('/mem_edit_act', methods=['POST'])
def mem_edit_act():
    m_id = request.form['m_id']
    m_nm = request.form['m_nm']
    tel = request.form['tel']
    ymd = request.form['ymd']
    
    dm = DaoMem()
    cnt = dm.update(m_id, m_nm, tel, ymd)
    return render_template('mem_edit_act.html', cnt = cnt)


#add 화면 출력 
@app.route('/mem_add')
def mem_add():   
    return render_template('mem_add.html')


#add-act 화면 출력 
@app.route('/mem_add_act', methods=['POST'])
def mem_add_act():   
    
    #파라미터를 다 받아온 후 
    m_id = request.form['m_id']
    m_nm = request.form['m_nm']
    tel = request.form['tel']
    ymd = request.form['ymd']
    
    #db에서 inser문 찾아서 실행 
    dm = DaoMem()
    cnt = dm.insert(m_id, m_nm, tel, ymd)
    return render_template('mem_add_act.html', cnt=cnt)


#delete
@app.route('/mem_delete_act', methods=['POST'])
def mem_delete_act():
    m_id = request.form['m_id']
    dm = DaoMem()
    cnt = dm.delete(m_id)
    return render_template('mem_delete_act.html', cnt = cnt)




if __name__ == '__main__':
    app.run(debug=True)
    
    
    
    
    