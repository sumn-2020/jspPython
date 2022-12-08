from flask import Flask
from flask import request
import psycopg2
from flask import Flask,render_template
from flask.json import jsonify
from day13.dao_student import DaoStudent


app = Flask(__name__)



@app.route('/')
def hello():
    return render_template('student.html')


#목록화면 
@app.route('/student', methods=['POST'])
def ajax():
    de = DaoStudent()
    list = de.selects()
    return jsonify(list=list)



#목록화면 
@app.route('/student_one', methods=['POST'])
def ajax_one():
    dict = request.get_json()
    s_id = dict['s_id']
    
    de = DaoStudent()
    student = de.select(s_id)
    return jsonify(student=student)



#추가화면 
@app.route('/student_add', methods=['POST'])
def ajax_add():
    dict = request.get_json()
    s_id = dict['s_id']
    s_name = dict['s_name']
    mobile = dict['mobile']
    address = dict['address']
    
    de = DaoStudent()
    cnt = de.insert(s_id, s_name, mobile, address)
    return jsonify(cnt=cnt)


#수정 추가 
@app.route('/student_edit', methods=['POST'])
def ajax_edit():
    dict = request.get_json()
    s_id = dict['s_id']
    s_name = dict['s_name']
    mobile = dict['mobile']
    address = dict['address']
    
    de = DaoStudent()
    cnt = de.update(s_id, s_name, mobile, address)
    return jsonify(cnt=cnt)


#ajax_delete 추가 
@app.route('/student_delete', methods=['POST'])
def ajax_delete():
    dict = request.get_json()
    s_id = dict['s_id']


    de = DaoStudent()
    cnt = de.delete(s_id)
    return jsonify(cnt=cnt)




if __name__ == '__main__':
    app.run(debug=True)
    
    
    
    
    
    
    
    
    