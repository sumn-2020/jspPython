from flask import Flask
from flask import request
import psycopg2
from flask import Flask,render_template
from flask.json import jsonify
from day14.dao_teacher import DaoTeacher



app = Flask(__name__)



@app.route('/')
@app.route('/teacher')
def teacher():
    return render_template('teacher.html')


@app.route('/ajax_teacher_list', methods=['POST'])
def ajax_teacher_list():
    
    data = request.get_json() #받은 파라미터 출력
    #return jsonify(data=data)
    
    dt = DaoTeacher()
    list = dt.selects()
    return jsonify(list=list)
    

#상세 
@app.route('/ajax_teacher_one', methods=['POST'])
def ajax_teacher_one():
    dict = request.get_json()
    t_id = dict['t_id']
    
    dt = DaoTeacher()
    teacher = dt.select(t_id)
    return jsonify(teacher=teacher)


#추가 
@app.route('/ajax_teacher_add', methods=['POST'])
def ajax_teacher_add():
    dict    = request.get_json()
    t_name  = dict['t_name']
    mobile  = dict['mobile']
    addr    = dict['addr']
    
    dt = DaoTeacher()
    cnt = dt.insert(t_name, mobile, addr)
    return jsonify(cnt=cnt)



#삭제
@app.route('/ajax_teacher_delete', methods=['POST'])
def ajax_delete():
    dict = request.get_json()
    t_id = dict['t_id']


    dt = DaoTeacher()
    cnt = dt.delete(t_id)
    return jsonify(cnt=cnt)


#수정
@app.route('/ajax_teacher_edit', methods=['POST'])
def ajax_teacher_edit():
    dict = request.get_json()
    t_id = dict['t_id']
    t_name = dict['t_name']
    mobile = dict['mobile']
    addr = dict['addr']
    
    de = DaoTeacher()
    cnt = de.update(t_id, t_name, mobile, addr)
    return jsonify(cnt=cnt)









if __name__ == '__main__':
    app.run(debug=True)
    
    
    
    
    
    
    
    
    