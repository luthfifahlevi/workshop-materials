import json
from typing import List

from student import Student

def save_to_file(students: List[Student], path: str = "python/project/student_score_manager/students.json") -> None:
    data = [s.to_dict() for s in students]
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

def load_from_file(path: str = "python/project/student_score_manager/students.json") -> List[Student]:
    try:
        with open(path, "r", encoding="utf-8") as f:
            data = json.load(f)
        return [Student.from_dict(x) for x in data]
    except FileNotFoundError:
        return []
