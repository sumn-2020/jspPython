from flask import Flask
from flask import request
import psycopg2
from flask import Flask,render_template
from flask.json import jsonify


from flask import Flask
from flask_socketio import SocketIO





app = Flask(__name__)
app.config['SECRET_KEY'] = '1234'
socketio = SocketIO(app)




@app.route('/')
@app.route('/three')
def sessions():
    return render_template('session.html')

def messageReceived(methods=['GET', 'POST']):
    print('message was received!!!')
    
    
@socketio.on('my event')
def handle_my_custom_event(json, methods=['GET', 'POST']):
    print('received my event: ' + str(json))
    socketio.emit('my response', json, callback=messageReceived)   




if __name__ == '__main__':
    #app.run(debug=True)
    socketio.run(app, debug=True)
    
    
    
    
    
    
    
    
    