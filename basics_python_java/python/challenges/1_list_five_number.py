# 1. Buat list berisi 5 angka
numbers = [10, 25, 7, 40, 15]
print("List awal:", numbers)

# 2. Tampilkan angka pertama dan terakhir
print("Angka pertama:", numbers[0])
print("Angka terakhir:", numbers[-1])

# 3. Tambahkan angka baru ke akhir list
numbers.append(60)
print("List setelah ditambah:", numbers)

# 4. Urutkan list secara descending
numbers.sort(reverse=True)

# 5. Tampilkan hasil akhir
print("List urut descending:", numbers)
