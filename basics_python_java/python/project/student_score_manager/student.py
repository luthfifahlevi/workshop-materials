from __future__ import annotations
from dataclasses import dataclass, field
from typing import List, Dict


@dataclass
class Student:
    name: str
    scores: List[float] = field(default_factory=list)

    # ---------- score ops ----------
    def add_score(self, value: float) -> None:
        self._validate_score(value)
        self.scores.append(float(value))

    def edit_score(self, index: int, new_value: float) -> None:
        if index < 0 or index >= len(self.scores):
            raise IndexError("Index nilai tidak valid")
        self._validate_score(new_value)
        self.scores[index] = float(new_value)

    def remove_score(self, index: int) -> None:
        if index < 0 or index >= len(self.scores):
            raise IndexError("Index nilai tidak valid")
        self.scores.pop(index)

    # ---------- derived metrics ----------
    def average(self) -> float:
        return sum(self.scores) / len(self.scores) if self.scores else 0.0

    def grade(self) -> str:
        avg = self.average()
        if avg >= 85: return "A"
        if avg >= 70: return "B"
        if avg >= 60: return "C"
        if avg >= 50: return "D"
        return "E"

    # ---------- utils ----------
    def info_line(self) -> str:
        return f"{self.name} | scores={self.scores} | avg={self.average():.2f} | grade={self.grade()}"

    def to_dict(self) -> Dict:
        return {"name": self.name, "scores": self.scores}

    @staticmethod
    def from_dict(d: Dict) -> "Student":
        return Student(name=d["name"], scores=[float(x) for x in d.get("scores", [])])

    @staticmethod
    def _validate_score(value: float) -> None:
        try:
            v = float(value)
        except Exception as e:
            raise ValueError("Nilai harus berupa angka") from e
        if v < 0 or v > 100:
            raise ValueError("Score harus 0..100")
