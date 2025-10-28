# Program Klasifikasi Usia

# 1. Baca input dari pengguna
usia = int(input("Masukkan usia: "))

# 2. Tentukan kategori berdasarkan kondisi
if usia < 12:
    print("Kategori: Anak-anak")
elif usia <= 17:
    print("Kategori: Remaja")
elif usia <= 59:
    print("Kategori: Dewasa")
else:
    print("Kategori: Lansia")
