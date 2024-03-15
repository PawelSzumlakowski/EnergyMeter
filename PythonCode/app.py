from flask import Flask, render_template, jsonify
import sqlite3
import os

app = Flask(__name__, template_folder=os.path.dirname(os.path.abspath(__file__)))

def fetch_data_from_db():
    conn = sqlite3.connect('impulses_data.db')
    c = conn.cursor()
    c.execute("SELECT timestamp, impulses, kwh FROM impulses ORDER BY timestamp")
    data = c.fetchall()
    conn.close()
    
    return data
    #formatted_data = []
    #for entry in data:
    #    timestamp = entry[0]
    #    impulses = entry[1]
    #    kwh = entry[2]
    #    formatted_entry = f"Data: {timestamp}, Impulsy: {impulses}, kWh: {kwh}"
    #    formatted_data.append(formatted_entry)
    #    
    #return formatted_data

@app.route('/')
def index():
    # Wczytaj dane z bazy danych
    data = fetch_data_from_db()

    return render_template('index.html', data=data)

@app.route('/data')
def get_data():
    data = fetch_data_from_db()
    return jsonify(data)

@app.route('/<query>')
def process_x(query):
    dates_separated = query.split("%")
    
    
    return f'{dates_separated}'
#http://192.168.103.150:5000/2024-02-14_11:49:09%2024-02-21_12:34:53

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
