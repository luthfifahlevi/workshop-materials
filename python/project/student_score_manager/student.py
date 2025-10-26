from typing import List, Dict, Optional

class Student:
    def __init__(self, name: str, scores: Optional[List[float]] = None):
        self.name = name
        self.scores = scores or []

    def add_score(self, value: float) -> None:
        # TODO: validasi nilai 0..100 (raise ValueError jika invalid)
        if value < 0 or value > 100:
            raise ValueError("Score harus 0..100")
        self.scores.append(value)

    def average(self) -> float:
        # TODO: kembalikan 0 jika belum ada nilai
        if not self.scores:
            return 0.0
        return sum(self.scores) / len(self.scores)

    def grade(self) -> str:
        avg = self.average()
        # TODO: mapping grade sederhana berdasarkan average
        if avg >= 85:
            return "A"
        if avg >= 70:
            return "B"
        if avg >= 60:
            return "C"
        if avg >= 50:
            return "D"
        return "E"

    def to_dict(self) -> Dict:
        return {"name": self.name, "scores": self.scores}

    @staticmethod
    def from_dict(d: Dict) -> "Student":
        return Student(name=d["name"], scores=d.get("scores", []))
