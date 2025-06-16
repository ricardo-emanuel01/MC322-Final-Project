package library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.TreeSet;
import java.util.Set;


public class UserRepository {
    private final File file;
    private final Gson gson;

    public UserRepository(File file) {
        this.file = file;
        this.gson = GsonFactory.getGson();
    }

    public void saveUsers(Set<User> users) throws IOException {
        try(Writer writer = new FileWriter(this.file)) {
            gson.toJson(users, writer);
        }
    }

    public Set<User> loadUsers() throws IOException {
        if (!this.file.exists()) return new TreeSet<>();

        try (Reader reader = new FileReader(this.file)) {
            Type setType = new TypeToken<Set<User>>() {}.getType();
            return gson.fromJson(reader, setType);
        }
    }
}
