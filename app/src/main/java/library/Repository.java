package library;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;


public class Repository<T> {
    private final File file;
    private final Gson gson;
    private final Type mapType;

    public Repository(File file, Type mapType) {
        this.file = file;
        this.gson = GsonFactory.getGson();
        this.mapType = mapType;
    }


    public void save(Map<String, T> items) throws IOException {
        if (items.size() == 0) {
            System.out.println("null");
            return;
        }
        file.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(this.file)) {
            gson.toJson(items, writer);
        }
    }


    public Map<String, T> load() throws IOException {
        if (!this.file.exists() || this.file.length() == 0) return new TreeMap<>();

        try (Reader reader = new FileReader(this.file)) {
            Map<String, T> map = gson.fromJson(reader, mapType);
            return map != null ? map : new TreeMap<>();
        }
    }
}
