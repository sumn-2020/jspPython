from flask import Flask
from flask import request
import psycopg2
from flask import Flask,render_template
from flask.json import jsonify
import time



app = Flask(__name__)



@app.route('/')
@app.route('/thread')
def teacher():
    return render_template('thread.html')

@app.route('/ajax_thread1', methods=['POST'])
def ajax_thread1():
    time.sleep(1) #호출은 먼저 하지만 1초 뒤에 출력됨. 결국 ajax_thread2부터 실행됨 
    a = '1'
    return jsonify(a=a)
    
@app.route('/ajax_thread2', methods=['POST'])
def ajax_thread2():
    a = '1'
    return jsonify(a=a)
    


if __name__ == '__main__':
    app.run(debug=True)
    
    
    
    
    
    
    
    
    