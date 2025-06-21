package library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.TreeSet;
import java.util.Set;


public class Repository<T> {
    private final File file;
    private final Gson gson;

    public Repository(File file) {
        this.file = file;
        this.gson = GsonFactory.getGson();
    }


    public void save(Set<T> items) throws IOException {
        try (Writer writer = new FileWriter(this.file)) {
            gson.toJson(items, writer);
        }
    }


    public Set<T> load() throws IOException {
        if (!this.file.exists()) return new TreeSet<>();

        try (Reader reader = new FileReader(this.file)) {
            Type setType = new TypeToken<Set<T>>() {}.getType();
            return gson.fromJson(reader, setType);
        }
    }
}
