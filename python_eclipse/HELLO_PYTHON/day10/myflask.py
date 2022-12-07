from flask import Flask
from flask import request
import psycopg2
from flask import Flask,render_template
from day10.dao_emp import DaoEmp

app = Flask(__name__)
 

#list 화면 출력 
@app.route('/')
@app.route('/emp_list')
def emp_list():   
    ds = DaoEmp()
    list = ds.selects()
    return render_template('emp_list.html', list=list)


#detail화면 출력 
@app.route('/emp_detail')
def emp_detail():   
    e_id = request.args.get('e_id', '') #파라미터 받기 e_id없으면 빈공간으로 받기
    print(e_id)
    
    ds = DaoEmp()
    emp = ds.select(e_id)
    return render_template('emp_detail.html',emp = emp)


#edit 화면 출력 
@app.route('/emp_edit')
def emp_edit():   
    e_id = request.args.get('e_id', '') #파라미터 받기 e_id없으면 빈공간으로 받기
    print(e_id)
    
    ds = DaoEmp()
    emp = ds.select(e_id)
    return render_template('emp_edit.html',emp = emp)


#edit 화면 출력 
@app.route('/emp_edit_act', methods=['POST'])
def emp_edit_act():   
    e_id = request.form['e_id']
    e_name = request.form['e_name']
    sex = request.form['sex']
    addr = request.form['addr']
    
    ds = DaoEmp()
    cnt = ds.update(e_id, e_name, sex, addr)
    return render_template('emp_edit_act.html', cnt = cnt)


#add 화면 출력 
@app.route('/emp_add')
def emp_add(): 
    return render_template('emp_add.html')

#emp_add_act 화면 출력 
@app.route('/emp_add_act', methods=['POST'])
def emp_add_act():   
    e_id = request.form['e_id']
    e_name = request.form['e_name']
    sex = request.form['sex']
    addr = request.form['addr']
    
    ds = DaoEmp()
    cnt = ds.insert(e_id, e_name, sex, addr)
    return render_template('emp_add_act.html', cnt = cnt)


#emp_delete 화면 출력 
@app.route('/emp_delete_act', methods=['POST'])
def emp_delete_act():   
    e_id = request.form['e_id']
    ds = DaoEmp()
    cnt = ds.delete(e_id)
    return render_template('emp_delete_act.html', cnt = cnt)


if __name__ == '__main__':
    app.run(debug=True)

    
    