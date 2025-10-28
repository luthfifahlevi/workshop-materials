# Membuat class Student untuk merepresentasikan data mahasiswa
class Student:
    # Constructor (__init__) dipanggil saat objek baru dibuat
    def __init__(self, name, scores):
        self.name = name
        self.scores = scores

    # Method untuk menghitung rata-rata nilai
    def average(self):
        # sum() menjumlahkan semua elemen dalam list
        # len() menghitung jumlah elemen dalam list
        return sum(self.scores) / len(self.scores)


# Fungsi untuk mencari mahasiswa dengan rata-rata tertinggi
def top_student(students):
    # max() akan mencari nilai maksimum dalam list
    # key=lambda s: s.average() artinya: urutkan berdasarkan hasil average() tiap mahasiswa
    return max(students, key=lambda s: s.average())


# Membuat daftar (list) berisi beberapa objek Student
students = [
    Student("Luthfi", [85, 90, 88]),  # Objek/instance 1
    Student("Andi", [70, 95, 80])     # Objek/instance 2
]

# Panggil fungsi untuk mencari mahasiswa terbaik
best = top_student(students)

# Cetak hasil: nama dan rata-rata mahasiswa terbaik
# f-string memudahkan format teks dan angka (:.1f = 1 angka di belakang koma)
print(f"Top student: {best.name} ({best.average():.1f})")
