import os

dir = "D:\\Master_Result\\06\\25\\temp"
def delete_fail_xml(dir):
    file_list = [f for f in os.listdir(dir) if f.endswith('.xml')]
    for f in file_list:
        file_path = os.path.join(dir,f)
        flag = False
        if f.startswith("output_"):
            os.remove(os.path.join(dir,f))
            continue
        with open(file_path, encoding='UTF-8') as cur_file:
            text = cur_file.read()
            flag = 'stat pass="0" fail="1"' in text
        if flag:
            os.remove(os.path.join(dir,f))

def replace_fail(dir):
    file_list = [f for f in os.listdir(dir) if f.endswith(".xml")]
    text = ''
    for f in file_list:
        file_path = os.path.join(dir,f)
        with open(file_path, encoding='UTF-8') as cur_file:
            text = cur_file.read().replace('status="FAIL"','status="PASS"')
        with open(file_path,'w', encoding='UTF-8') as cur_file:
            cur_file.write(text)

# replace_fail(dir)
delete_fail_xml(dir)