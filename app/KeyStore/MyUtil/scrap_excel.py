import openpyxl as openpyxl
from selenium import webdriver
from bs4 import BeautifulSoup
import pandas as pd
from selenium.webdriver.chrome.options import Options

menu_len = 15
xl = pd.read_excel(open('video_list.xlsx', 'rb'),sheet_name='video_list')
yt_title = [[] for _ in range(menu_len)]
yt_link = [[] for _ in range(menu_len)]
yt_tag = [[] for _ in range(menu_len)]
for i in range(len(xl.index)):
    print(str(xl.loc[i,'Title']))
    print(str(xl.loc[i,'Tag']))
    if "Trie".lower() in str(xl.loc[i,'Tag']).lower() or "Trie".lower() in xl.loc[i,'Title'].lower():
        yt_title[12].append(xl.loc[i,'Title'])
        yt_link[12].append(xl.loc[i,'Link'])
        yt_tag[12].append(xl.loc[i,'Tag'])
    elif "Graph".lower() in str(xl.loc[i,'Tag']).lower() or "Graph".lower() in xl.loc[i,'Title'].lower():
        yt_title[11].append(xl.loc[i,'Title'])
        yt_link[11].append(xl.loc[i,'Link'])
        yt_tag[11].append(xl.loc[i,'Tag'])
    elif "Heap".lower() in str(xl.loc[i,'Tag']).lower() or "Heap".lower() in xl.loc[i,'Title'].lower():
        yt_title[10].append(xl.loc[i,'Title'])
        yt_link[10].append(xl.loc[i,'Link'])
        yt_tag[10].append(xl.loc[i,'Tag'])
    elif "Stack".lower() in str(xl.loc[i,'Tag']).lower() or "Stack".lower() in xl.loc[i,'Title'].lower() or "Queue".lower() in str(xl.loc[i,'Tag']).lower() or "Queue".lower() in xl.loc[i,'Title'].lower():
        yt_title[9].append(xl.loc[i,'Title'])
        yt_link[9].append(xl.loc[i,'Link'])
        yt_tag[9].append(xl.loc[i,'Tag'])
    elif "back".lower() in str(xl.loc[i,'Tag']).lower() or "back track".lower() in xl.loc[i,'Title'].lower():
        yt_title[8].append(xl.loc[i,'Title'])
        yt_link[8].append(xl.loc[i,'Link'])
        yt_tag[8].append(xl.loc[i,'Tag'])
    elif "greedy".lower() in str(xl.loc[i,'Tag']).lower() or "greedy".lower() in xl.loc[i,'Title'].lower():
        yt_title[7].append(xl.loc[i,'Title'])
        yt_link[7].append(xl.loc[i,'Link'])
        yt_tag[7].append(xl.loc[i,'Tag'])
    elif "tree".lower() in str(xl.loc[i,'Tag']).lower() or "tree".lower() in xl.loc[i,'Title'].lower():
        yt_title[5].append(xl.loc[i,'Title'])
        yt_link[5].append(xl.loc[i,'Link'])
        yt_tag[5].append(xl.loc[i,'Tag'])
    elif "linked".lower() in str(xl.loc[i,'Tag']).lower() or "linked list".lower() in xl.loc[i,'Title'].lower():
        yt_title[4].append(xl.loc[i,'Title'])
        yt_link[4].append(xl.loc[i,'Link'])
        yt_tag[4].append(xl.loc[i,'Tag'])
    elif "search".lower() in str(xl.loc[i,'Tag']).lower() or "search sort".lower() in xl.loc[i,'Title'].lower() or "sort".lower() in str(xl.loc[i,'Tag']).lower():
        yt_title[3].append(xl.loc[i,'Title'])
        yt_link[3].append(xl.loc[i,'Link'])
        yt_tag[3].append(xl.loc[i,'Tag'])
    elif "Bit".lower() in str(xl.loc[i,'Tag']).lower() or "Bit manipulation".lower() in xl.loc[i,'Title'].lower():
        yt_title[14].append(xl.loc[i,'Title'])
        yt_link[14].append(xl.loc[i,'Link'])
        yt_tag[14].append(xl.loc[i,'Tag'])
    elif "Dynamic".lower() in str(xl.loc[i,'Tag']).lower() or "Dynamic".lower() in xl.loc[i,'Title'].lower():
        yt_title[13].append(xl.loc[i,'Title'])
        yt_link[13].append(xl.loc[i,'Link'])
        yt_tag[13].append(xl.loc[i,'Tag'])
    elif "matrix".lower() in str(xl.loc[i,'Tag']).lower() or "matrix".lower() in xl.loc[i,'Title'].lower():
        yt_title[1].append(xl.loc[i,'Title'])
        yt_link[1].append(xl.loc[i,'Link'])
        yt_tag[1].append(xl.loc[i,'Tag'])
    elif "string".lower() in str(xl.loc[i,'Tag']).lower() or "string".lower() in xl.loc[i,'Title'].lower():
        yt_title[2].append(xl.loc[i,'Title'])
        yt_link[2].append(xl.loc[i,'Link'])
        yt_tag[2].append(xl.loc[i,'Tag'])
    elif "array".lower() in str(xl.loc[i,'Tag']).lower() or "array".lower() in xl.loc[i,'Title'].lower():
        yt_title[0].append(xl.loc[i,'Title'])
        yt_link[0].append(xl.loc[i,'Link'])
        yt_tag[0].append(xl.loc[i,'Tag'])
    else:
        yt_title[6].append(xl.loc[i,'Title'])
        yt_link[6].append(xl.loc[i,'Link'])
        yt_tag[6].append(xl.loc[i,'Tag'])

with open("array.txt", "w") as f:
    f.write("\n".join([yt_title[0][i].split("|")[0]+" === "+yt_link[0][i] for i in range(len(yt_title[0]))]))
with open("matrix.txt", "w") as f:
    f.write("\n".join([yt_title[1][i].split("|")[0]+" === "+yt_link[1][i] for i in range(len(yt_title[1]))]))
with open("string.txt", "w") as f:
    f.write("\n".join([yt_title[2][i].split("|")[0]+" === "+yt_link[2][i] for i in range(len(yt_title[2]))]))
with open("search_sort.txt", "w") as f:
    f.write("\n".join([yt_title[3][i].split("|")[0]+" === "+yt_link[3][i] for i in range(len(yt_title[3]))]))
with open("linked_list.txt", "w") as f:
    f.write("\n".join([yt_title[4][i].split("|")[0]+" === "+yt_link[4][i] for i in range(len(yt_title[4]))]))
with open("tree.txt", "w") as f:
    f.write("\n".join([yt_title[5][i].split("|")[0]+" === "+yt_link[5][i] for i in range(len(yt_title[5]))]))
with open("miscellaneous.txt", "w") as f:
    f.write("\n".join([yt_title[6][i].split("|")[0]+" === "+yt_link[6][i] for i in range(len(yt_title[6]))]))
with open("greedy.txt", "w") as f:
    f.write("\n".join([yt_title[7][i].split("|")[0]+" === "+yt_link[7][i] for i in range(len(yt_title[7]))]))
with open("back_track.txt", "w") as f:
    f.write("\n".join([yt_title[8][i].split("|")[0]+" === "+yt_link[8][i] for i in range(len(yt_title[8]))]))
with open("stack_queue.txt", "w") as f:
    f.write("\n".join([yt_title[9][i].split("|")[0]+" === "+yt_link[9][i] for i in range(len(yt_title[9]))]))
with open("heap.txt", "w") as f:
    f.write("\n".join([yt_title[10][i].split("|")[0]+" === "+yt_link[10][i] for i in range(len(yt_title[10]))]))
with open("graph.txt", "w") as f:
    f.write("\n".join([yt_title[11][i].split("|")[0]+" === "+yt_link[11][i] for i in range(len(yt_title[11]))]))
with open("trie.txt", "w") as f:
    f.write("\n".join([yt_title[12][i].split("|")[0]+" === "+yt_link[12][i] for i in range(len(yt_title[12]))]))
with open("dp.txt", "w") as f:
    f.write("\n".join([yt_title[13][i].split("|")[0]+" === "+yt_link[13][i] for i in range(len(yt_title[13]))]))
with open("bit.txt", "w") as f:
    f.write("\n".join([yt_title[14][i].split("|")[0]+" === "+yt_link[14][i] for i in range(len(yt_title[14]))]))

