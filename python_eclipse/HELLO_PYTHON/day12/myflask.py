from flask import Flask
from flask import request
import psycopg2
from flask import Flask,render_template
from flask.json import jsonify
from day12.dao_emp import DaoEmp

app = Flask(__name__)


#mvvm


@app.route('/')
def hello():
    return render_template('hello.html')



#ajax 
# @app.route('/ajax', methods=['POST'])
# def ajax():
#     data = request.get_json() #json형태로 data를 받아옴  {'id': '1'}
#     print(data)
#     return jsonify(result = "success", result2= data)

#ajax 
@app.route('/ajax', methods=['POST'])
def ajax():
    de = DaoEmp()
    list = de.selects()
    return jsonify(list=list)

#ajax_one detail
@app.route('/ajax_one', methods=['POST'])
def ajax_one():
    dict = request.get_json()
    e_id = dict['e_id']
    #print(dict['e_id'])
    de = DaoEmp()
    emp = de.select(e_id)
    return jsonify(emp=emp)


#ajax_add 추가 
@app.route('/ajax_add', methods=['POST'])
def ajax_add():
    dict = request.get_json()
    e_id = dict['e_id']
    e_name = dict['e_name']
    sex = dict['sex']
    addr = dict['addr']
    
    de = DaoEmp()
    cnt = de.insert(e_id, e_name, sex, addr)
    return jsonify(cnt=cnt)


#ajax_edit 추가 
@app.route('/ajax_edit', methods=['POST'])
def ajax_edit():
    dict = request.get_json()
    e_id = dict['e_id']
    e_name = dict['e_name']
    sex = dict['sex']
    addr = dict['addr']
    
    de = DaoEmp()
    cnt = de.update(e_id, e_name, sex, addr)
    return jsonify(cnt=cnt)


#ajax_delete 추가 
@app.route('/ajax_delete', methods=['POST'])
def ajax_delete():
    dict = request.get_json()
    e_id = dict['e_id']

    de = DaoEmp()
    cnt = de.delete(e_id)
    return jsonify(cnt=cnt)






if __name__ == '__main__':
    app.run(debug=True)
    
    
    
    
    
    
    
    
    