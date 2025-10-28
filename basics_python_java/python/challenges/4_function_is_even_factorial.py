# Fungsi untuk memeriksa apakah sebuah bilangan genap
def is_even(num):
    return num % 2 == 0

# Tes fungsi
print(is_even(4))   # True
print(is_even(7))   # False

# Fungsi untuk menghitung faktorial
def factorial(n):
    result = 1
    for i in range(1, n + 1):
        result *= i
    return result

# Tes fungsi
print(factorial(5))  # 120
print(factorial(0))  # 1
