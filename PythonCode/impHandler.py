#import gpiod
import time
import datetime
import sqlite3
import random

INPUT_PIN = 17
IPM_PER_KWH = 800

INTERVAL = 1

#chip = gpiod.Chip("gpiochip4")
#led_line = chip.get_line(INPUT_PIN)
#led_line.request(consumer="myLed", type=gpiod.LINE_REQ_DIR_IN)  # Set the pin as input

def create_table():
    conn = sqlite3.connect('impulses_data.db')
    c = conn.cursor()
    c.execute('''CREATE TABLE IF NOT EXISTS impulses
                 (id INTEGER PRIMARY KEY AUTOINCREMENT,
                 timestamp DATETIME,
                 impulses INTEGER,
                 kwh REAL)''')
    conn.commit()
    conn.close()

def save_data_to_db(timestamp, impulses, kwh):
    conn = sqlite3.connect('impulses_data.db')
    c = conn.cursor()
    c.execute("INSERT INTO impulses (timestamp, impulses, kwh) VALUES (?, ?, ?)", (timestamp, impulses, kwh))
    conn.commit()
    conn.close()
    
def countImp():
    try:
        create_table()

        while True:
            time.sleep(1)
            impulses_in_interval = random.randint(0,1000)

            kwh = impulses_in_interval / IPM_PER_KWH

            # Saving the data
            current_time = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            save_data_to_db(current_time, impulses_in_interval, kwh)
            print("Saved data for {}: {} impulses, {} kWh".format(current_time, impulses_in_interval, kwh))

    except KeyboardInterrupt:
        pass  # This section allows the program to exit gracefully when you press Ctrl+C
    
    finally:
         pass
        #led_line.release()  # Release the GPIO line before exiting the program
    
countImp()