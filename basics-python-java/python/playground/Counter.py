class Counter:
    count = 0  # class variable

    def __init__(self):
        self.count = 0  # instance variable
        Counter.count += 1

c1 = Counter()
c2 = Counter()
print(c1.count)  # 0
print(c2.count)  # 0
print(Counter.count)  # 2

c2.count += 1
print(c2.count)  # 1
Counter.count += 3
print(Counter.count)  # 5


