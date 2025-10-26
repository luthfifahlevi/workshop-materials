from typing import List, Optional

from student import Student
from storage import save_to_file, load_from_file

def find_student(students: List[Student], name: str) -> Optional[Student]:
    for s in students:
        if s.name.lower() == name.lower():
            return s
    return None

def action_add_student(students: List[Student]) -> None:
    name = input("Nama mahasiswa: ").strip()
    # Validasi
    if not name:
        print("Nama tidak boleh kosong.")
        return
    if find_student(students, name):
        print("Mahasiswa sudah ada.")
        return
    
    student = Student(name)
    # TODO: buat loop input nilai dan tambahkan ke student
    while True:
        raw = input("Tambah nilai (0..100) atau kosong untuk selesai: ").strip()
        if raw == "":
            break
        try:
            student.add_score(float(raw))
        except ValueError as e:
            print(f"Input invalid: {e}")
    students.append(student)
    print(f"✅ Mahasiswa '{name}' ditambahkan.")

def action_list_students(students: List[Student]) -> None:
    # TODO: tampilkan daftar mahasiswa dengan format:
    # - Nama | scores=[..] | avg=.. | grade=..
    if not students:
        print("Belum ada data.")
        return
    print("\n== Daftar Mahasiswa ==")
    for s in students:
        print(f"- {s.name} | scores={s.scores} | avg={s.average():.2f} | grade={s.grade()}")
    print()

def action_add_score(students: List[Student]) -> None:
    # TODO: minta nama mahasiswa, cari, lalu minta nilai baru untuk ditambahkan
    name = input("Nama mahasiswa: ").strip()
    student = find_student(students, name)
    if not student:
        print("Mahasiswa tidak ditemukan.")
        return
    raw = input("Nilai baru (0..100): ").strip()
    try:
        student.add_score(float(raw))
        print("✅ Nilai ditambahkan.")
    except ValueError as e:
        print(f"Input invalid: {e}")

def action_save(students: List[Student]) -> None:
    save_to_file(students)
    print("💾 Data disimpan ke students.json")

def action_load() -> List[Student]:
    students = load_from_file()
    print(f"📂 Data dimuat. Total mahasiswa: {len(students)}")
    return students


# ------------------------------
# CLI Menu
# ------------------------------
def print_menu() -> None:
    print("""
=== Student Score Manager ===
1) Tambah mahasiswa
2) Tampilkan semua
3) Tambah nilai ke mahasiswa
4) Simpan data (JSON)
5) Muat data (JSON)
0) Keluar
""")

def main():
    # (Opsional) otomatis load data saat mulai
    students: List[Student] = []

    while True:
        print_menu()
        choice = input("Pilih menu: ").strip()

        if choice == "1":
            action_add_student(students)
        elif choice == "2":
            action_list_students(students)
        elif choice == "3":
            action_add_score(students)
        elif choice == "4":
            action_save(students)
        elif choice == "5":
            students = action_load()
        elif choice == "0":
            print("Sampai jumpa! 👋")
            break
        else:
            print("Menu tidak dikenal.")

if __name__ == "__main__":
    main()
