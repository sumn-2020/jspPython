from flask import Flask, jsonify, render_template
from subprocess import call
from flask_socketio import SocketIO, send

app = Flask(__name__)
app.secret_key = "mysecret"

socket_io = SocketIO(app)

# websocket flask
# pip install flask-socketio
# pip install eventlet
# pip install --upgrade python-socketio==4.6.0
# pip install --upgrade python-engineio==3.13.2
# pip install --upgrade Flask-SocketIO==4.3.1



@app.route('/')
@app.route('/three')
def allready():
    return render_template('three.html')



@socket_io.on("message")
def request(message):
    print("message : "+ message) 
    mydict = dict()
    mydict['message'] = message  # 자바스크립트 mydict.message
    send(mydict, broadcast=True) #broadcast => 현재 접속한 사람들에게 송출  #broadcast vs echo
   
   


if __name__ == '__main__':
    socket_io.run(app, host="192.168.35.32", debug=True, port=9999)
    #host="0.0.0.0" => 0.0.0.0 모든 ip들을 전부 허용하겠다 (이걸 설정 안하면 127.0.0.1만 허용되고 나머지 ip는 허용 안됨)
    
    
    
    
        
    
    
    
    
    