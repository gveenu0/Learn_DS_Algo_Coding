def func1(func2):
    print("In func 2")
    print(func2())
    return "As func1"


@func1
def func3():
    print("In func 3")
    return "As func 3"



def func4():
    print("In func 4")
    return "As func 4"

def func7():
    "In func 7"
    return func4()


print(func1(func7))
