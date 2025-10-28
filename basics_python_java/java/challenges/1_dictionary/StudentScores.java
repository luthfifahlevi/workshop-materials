import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentScores {
    public static void main(String[] args) {
        // Membuat HashMap untuk menyimpan pasangan nama-nilai
        HashMap<String, Integer> scores = new HashMap<>();
        Scanner input = new Scanner(System.in);

        System.out.println("=== Input Data Nilai Mahasiswa ===");

        // Loop input data mahasiswa
        while (true) {
            System.out.print("Masukkan nama (atau kosong untuk selesai): ");
            String name = input.nextLine().trim();
            
            // Jika kosong, hentikan input
            if (name.isEmpty()) break;

            System.out.print("Masukkan nilai: ");
            int score = Integer.parseInt(input.nextLine().trim());

            // Simpan data ke dalam HashMap
            scores.put(name, score);
        }

        System.out.println("\n=== Daftar Nilai Mahasiswa ===");
        // Menampilkan semua data dari HashMap
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        input.close();
    }
}
