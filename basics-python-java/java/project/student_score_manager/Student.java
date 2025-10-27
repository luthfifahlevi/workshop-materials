import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private ArrayList<Double> scores;

    public Student(String name) {
        this.name = name;
        this.scores = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String newName) { this.name = newName; }

    public List<Double> getScores() { return scores; }

    public void addScore(double score) {
        if (score < 0 || score > 100) throw new IllegalArgumentException("Score harus 0..100");
        scores.add(score);
    }

    public void editScore(int index, double newScore) {
        if (index < 0 || index >= scores.size()) throw new IndexOutOfBoundsException("Index nilai tidak valid");
        if (newScore < 0 || newScore > 100) throw new IllegalArgumentException("Score harus 0..100");
        scores.set(index, newScore);
    }

    public void removeScore(int index) {
        if (index < 0 || index >= scores.size()) throw new IndexOutOfBoundsException("Index nilai tidak valid");
        scores.remove(index);
    }

    public double getAverage() {
        if (scores.isEmpty()) return 0.0;
        double total = 0;
        for (double s : scores) total += s;
        return total / scores.size();
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 85) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "E";
    }

    public String infoLine() {
        return String.format("%s | scores=%s | avg=%.2f | grade=%s", name, scores.toString(), getAverage(), getGrade());
    }
}
