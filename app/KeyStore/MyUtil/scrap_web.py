import openpyxl as openpyxl
from selenium import webdriver
from bs4 import BeautifulSoup
import pandas as pd
from selenium.webdriver.chrome.options import Options

menu_len = 15
xl = pd.read_excel(open('yt.xlsx', 'rb'),sheet_name='yt')
yt_title = [[] for _ in range(menu_len)]
yt_link = [[] for _ in range(menu_len)]
yt_des = [[] for _ in range(menu_len)]
for i in range(len(xl.index)):
    yt_title[0].append(xl.loc[i,'Title'])
    yt_link[0].append(xl.loc[i,'Video URL'])
    des = xl.loc[i,'Description']
    if 'question link:' in str(des):
        des = des.split('question link:')[1].split('\n')[0].strip()
    elif 'question link :' in str(des):
        des = des.split('question link :')[1].split('\n')[0].strip()
    elif 'Question Link :' in str(des):
        des = des.split('Question Link :')[1].split('\n')[0].strip()
    else:
        des = ''
    des = des.split(' ')[0].strip()
    yt_des[0].append(des)

co = Options()
co.add_argument("--headless")
driver = webdriver.Chrome(options=co)
driver.implicitly_wait(10)
elms = driver.find_elements_by_xpath("//a[contains(@class,'topic-tag')]")
for i in range(len(yt_des[0])):
    if yt_des[0][i]:
        print(yt_des[0][i])
        driver.get(yt_des[0][i])
        elms = driver.find_elements_by_xpath("//a[contains(@class,'topic-tag')]")
        yt_des[0][i]=''
        for elm in elms:
            yt_des[0][i]=yt_des[0][i]+elm.get_attribute("href").split('/')[-2]+' |'
        print("==================================================")
        print(yt_des[0][i])

print(yt_des[0])
# content = driver.page_source
# soup = BeautifulSoup(content)
# for a in soup.findAll('a',href=True, attrs={'class':'_31qSD5'}):
#     yt_link=a.find('div', attrs={'class':'_3wU53n'})
#     yt_title=a.find('div', attrs={'class':'_1vC4OE _2rQ-NK'})
#     yt_tag=a.find('div', attrs={'class':'hGSR34 _2beYZw'})

# yt_link.append(yt_link.text)
# yt_title.append(yt_title.text)
# yt_tag.append(yt_tag.text)

df = pd.DataFrame({'Title':yt_link[0],'Link':yt_title[0],'Tag':yt_des[0]})
df.to_csv('video_list.csv', index=False, encoding='utf-8')
