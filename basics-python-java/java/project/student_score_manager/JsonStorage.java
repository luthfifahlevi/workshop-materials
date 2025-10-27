import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonStorage {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Type STUDENT_LIST_TYPE = new TypeToken<ArrayList<Student>>(){}.getType();

    public static void save(ArrayList<Student> students, String path) throws Exception {
        try (Writer w = new FileWriter(path)) {
            gson.toJson(students, STUDENT_LIST_TYPE, w);
        }
    }

    public static ArrayList<Student> load(String path) throws Exception {
        try (Reader r = new FileReader(path)) {
            ArrayList<Student> loaded = gson.fromJson(r, STUDENT_LIST_TYPE);
            return (loaded != null) ? loaded : new ArrayList<>();
        }
    }
}
