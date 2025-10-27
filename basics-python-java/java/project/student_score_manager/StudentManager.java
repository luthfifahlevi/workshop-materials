import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private final Scanner input = new Scanner(System.in);
    private ArrayList<Student> students = new ArrayList<>();

    // ---------- Menu ----------
    public void menu() {
        while (true) {
            System.out.println("\n=== Student Score Manager ===");
            System.out.println("1) Tambah mahasiswa");
            System.out.println("2) Lihat semua mahasiswa");
            System.out.println("3) Tambah nilai ke mahasiswa");
            System.out.println("4) Edit nilai mahasiswa");
            System.out.println("5) Hapus nilai mahasiswa");
            System.out.println("6) Edit nama mahasiswa");
            System.out.println("7) Hapus mahasiswa");
            System.out.println("8) Statistik kelas (rata-rata & top student)");
            System.out.println("9) Simpan data (JSON)");
            System.out.println("10) Muat data (JSON)");
            System.out.println("0) Keluar");
            System.out.print("Pilih menu: ");

            String choice = input.nextLine().trim();
            try {
                switch (choice) {
                    case "1": addStudent(); break;
                    case "2": listStudents(); break;
                    case "3": addScoreToStudent(); break;
                    case "4": editStudentScore(); break;
                    case "5": deleteStudentScore(); break;
                    case "6": editStudentName(); break;
                    case "7": deleteStudent(); break;
                    case "8": classStats(); break;
                    case "9": saveJson(); break;
                    case "10": loadJson(); break;
                    case "0":
                        System.out.println("Sampai jumpa 👋");
                        return;
                    default: System.out.println("Menu tidak dikenal.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    // ---------- Helpers ----------
    private Student findByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    private double parseScore(String raw) {
        try {
            double val = Double.parseDouble(raw);
            if (val < 0 || val > 100) throw new IllegalArgumentException("Score harus 0..100");
            return val;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Masukkan angka yang valid");
        }
    }

    // ---------- Actions ----------
    private void addStudent() {
        System.out.print("Nama mahasiswa: ");
        String name = input.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Nama tidak boleh kosong."); return; }
        if (findByName(name) != null) { System.out.println("Mahasiswa sudah ada."); return; }

        Student s = new Student(name);
        while (true) {
            System.out.print("Tambah nilai (0..100) atau kosong untuk selesai: ");
            String line = input.nextLine().trim();
            if (line.isEmpty()) break;
            s.addScore(parseScore(line));
        }
        students.add(s);
        System.out.println("✅ Mahasiswa ditambahkan.");
    }

    private void listStudents() {
        if (students.isEmpty()) { System.out.println("Belum ada data."); return; }
        System.out.println("\n== Daftar Mahasiswa ==");
        for (Student s : students) {
            System.out.println("- " + s.infoLine());
        }
    }

    private void addScoreToStudent() {
        System.out.print("Nama mahasiswa: ");
        String name = input.nextLine().trim();
        Student s = findByName(name);
        if (s == null) { System.out.println("Mahasiswa tidak ditemukan."); return; }

        System.out.print("Nilai baru (0..100): ");
        String raw = input.nextLine().trim();
        s.addScore(parseScore(raw));
        System.out.println("✅ Nilai ditambahkan.");
    }

    private void editStudentScore() {
        System.out.print("Nama mahasiswa: ");
        String name = input.nextLine().trim();
        Student s = findByName(name);
        if (s == null) { System.out.println("Mahasiswa tidak ditemukan."); return; }

        List<Double> scores = s.getScores();
        if (scores.isEmpty()) { System.out.println("Mahasiswa belum memiliki nilai."); return; }
        for (int i = 0; i < scores.size(); i++) {
            System.out.printf("%d) %.2f%n", i, scores.get(i));
        }
        System.out.print("Pilih index nilai yang akan diubah: ");
        int idx = Integer.parseInt(input.nextLine().trim());

        System.out.print("Nilai baru (0..100): ");
        double newVal = parseScore(input.nextLine().trim());

        s.editScore(idx, newVal);
        System.out.println("✅ Nilai diperbarui.");
    }

    private void deleteStudentScore() {
        System.out.print("Nama mahasiswa: ");
        String name = input.nextLine().trim();
        Student s = findByName(name);
        if (s == null) { System.out.println("Mahasiswa tidak ditemukan."); return; }

        List<Double> scores = s.getScores();
        if (scores.isEmpty()) { System.out.println("Tidak ada nilai untuk dihapus."); return; }
        for (int i = 0; i < scores.size(); i++) {
            System.out.printf("%d) %.2f%n", i, scores.get(i));
        }
        System.out.print("Pilih index nilai yang akan dihapus: ");
        int idx = Integer.parseInt(input.nextLine().trim());
        s.removeScore(idx);
        System.out.println("🗑️ Nilai dihapus.");
    }

    private void editStudentName() {
        System.out.print("Nama mahasiswa (lama): ");
        String name = input.nextLine().trim();
        Student s = findByName(name);
        if (s == null) { System.out.println("Mahasiswa tidak ditemukan."); return; }

        System.out.print("Nama baru: ");
        String newName = input.nextLine().trim();
        if (newName.isEmpty()) { System.out.println("Nama baru tidak boleh kosong."); return; }
        if (findByName(newName) != null) { System.out.println("Nama baru sudah digunakan."); return; }

        s.setName(newName);
        System.out.println("✅ Nama mahasiswa diperbarui.");
    }

    private void deleteStudent() {
        System.out.print("Nama mahasiswa yang dihapus: ");
        String name = input.nextLine().trim();
        Student s = findByName(name);
        if (s == null) { System.out.println("Mahasiswa tidak ditemukan."); return; }

        students.remove(s);
        System.out.println("🗑️ Mahasiswa dihapus.");
    }

    private void classStats() {
        if (students.isEmpty()) { System.out.println("Belum ada data."); return; }

        double totalAvg = 0.0;
        for (Student s : students) totalAvg += s.getAverage();
        double classAverage = totalAvg / students.size();

        Student top = students.stream()
                .max(Comparator.comparingDouble(Student::getAverage))
                .orElse(null);

        System.out.printf("📊 Rata-rata kelas: %.2f%n", classAverage);
        if (top != null) {
            System.out.printf("🏆 Top student: %s (avg=%.2f, grade=%s)%n",
                    top.getName(), top.getAverage(), top.getGrade());
        }
    }

    private void saveJson() {
        try {
            System.out.print("Nama file (default: students.json): ");
            String path = input.nextLine().trim();
            if (path.isEmpty()) path = "students.json";
            JsonStorage.save(students, path);
            System.out.println("💾 Data disimpan ke " + path);
        } catch (Exception e) {
            System.out.println("❌ Gagal menyimpan: " + e.getMessage());
        }
    }

    private void loadJson() {
        try {
            System.out.print("Nama file (default: students.json): ");
            String path = input.nextLine().trim();
            if (path.isEmpty()) path = "students.json";
            students = JsonStorage.load(path);
            System.out.println("📂 Data dimuat. Total mahasiswa: " + students.size());
        } catch (Exception e) {
            System.out.println("❌ Gagal memuat: " + e.getMessage());
        }
    }
}
