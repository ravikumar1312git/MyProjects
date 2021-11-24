import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import pymysql
import requests

def raise_snow_incident(timestamp, consumption, prediction, anomalyScore):
    url = 'https://gw1.qa.api.volvocars.biz/servicenow/rest/api/now/import/u_inbound_incident'
    params = {
      "u_caller_id": "****",
      "u_external_reference_id": "*******",
      "u_short_description": "This is alert incident for cpu busy metrics",
      "u_area": "office",
      "u_type": "inquiry",
      "u_work_notes": "This is work notes",
      "u_description": "timestamp:%s , metricvalue:%s, prediction:%s, anomalyscre:%s"%(timestamp,consumption,prediction,anomalyScore),
      "u_originator_partner_name": "PartnerName",
      "u_configuration_item": "",
      "impact": "1",
      "u_inbound_type":"create",
      "u_originator_partner_ticket_id": "PartnerIDNumber"
    }
    headers = {'User-Key': '******************', 'Content-Type':'application/json'}
    response = requests.post(url=url,data='',json=params,auth=('incident_New_Car_Locator', '******'),headers=headers)
    print(response.json())

def send_alert_mail(message):
    sender = 'rdurais1@volvocars.com'
    receivers = ['rdurais1@volvocars.com']
    msg = MIMEMultipart()
    msg['From'] = 'rdurais1@volvocars.com'
    msg['To'] = 'rdurais1@volvocars.com'
    msg['Subject'] = 'Anomaly Notification Mail'
    message = 'here is the email'
    msg.attach(MIMEText(message))
    smtpObj = smtplib.SMTP('mailrelay.volvocars.net',25)
    smtpObj.sendmail(sender, receivers, msg.as_string())
    print("Successfully sent email")

def find_anomaly_count():
    conn = pymysql.connect(host='localhost', user='root',password="pass",db='College',)
    cur = conn.cursor()
    cur.execute("***query to be updated***")
    count = cur.fetchall()
    return count

def eval_anomaly():
    anomaly_count = find_anomaly_count()
    if anomaly_count>3:
        send_alert_mail()
        raise_snow_incident()
    else if (anomaly_count<3):
        send_alert_mail()

if __name__=='__main__':
    eval_anomaly()

