import json
from pathlib import Path
from typing import List
from student import Student


DEFAULT_PATH = Path(__file__).parent / "students.json"


def save_to_file(students: List[Student], path: Path = DEFAULT_PATH) -> None:
    data = [s.to_dict() for s in students]
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)


def load_from_file(path: Path = DEFAULT_PATH) -> List[Student]:
    p = Path(path)
    if not p.exists():
        return []
    with open(p, "r", encoding="utf-8") as f:
        raw = json.load(f)
    return [Student.from_dict(item) for item in (raw or [])]
