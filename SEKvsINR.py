import req as req
import requests as req
import time
import playsound as play
import re
high=float(input("Enter upper limit : "))
low=float(input("Enter lower limit : "))
while(True):
    content = (req.get("https://transferwise.com/gb/currency-converter/sek-to-inr-rate").text)[85061:85081]
    #start = content.find("text-success")
    ind=content.find(">")
    current=float(content[ind+1:ind+5])
    print(high,current,low)
    if(current>=high or current<=low):
        while(True):
            play.playsound("C:/Users/rdurais1/PycharmProjects/TestPy/Source/inter.mp3",True)
            time.sleep(5)
    time.sleep(20)