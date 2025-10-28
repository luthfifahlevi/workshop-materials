public class OddChecker {

    // Method 1: Mengecek apakah sebuah angka ganjil
    public static boolean is_odd(int num) {
        // Operator modulus (%) untuk memeriksa sisa bagi 2
        // Jika sisa != 0 maka angka ganjil
        return num % 2 != 0;
    }

    // Method 2 (Overloading): Mengecek apakah elemen array pada index tertentu ganjil
    public static boolean is_odd(int[] arr, int index) {
        // Cek apakah index valid (tidak keluar dari batas array)
        if (index < 0 || index >= arr.length) {
            System.out.println("❌ Index di luar batas array!");
            return false;
        }

        // Ambil nilai dari array berdasarkan index
        int value = arr[index];

        // Gunakan kembali logika dari method pertama
        return is_odd(value);
    }

    // Method main untuk pengujian
    public static void main(String[] args) {
        int[] numbers = {2, 5, 8, 11};

        System.out.println(is_odd(7));            // true  → 7 ganjil
        System.out.println(is_odd(10));           // false → 10 genap

        System.out.println(is_odd(numbers, 1));   // true  → arr[1] = 5
        System.out.println(is_odd(numbers, 2));   // false → arr[2] = 8
        System.out.println(is_odd(numbers, 5));   // ❌ Index di luar batas
    }
}
