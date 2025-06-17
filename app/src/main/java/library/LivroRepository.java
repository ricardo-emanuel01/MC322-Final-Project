package library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.TreeSet;
import java.util.Set;


// Poderá se tornar um Repository genérico com User
public class LivroRepository {
    private final File file;
    private final Gson gson;

    public LivroRepository(File file) {
        this.file = file;
        this.gson = GsonFactory.getGson();
    }

    public void saveLivros(Set<Livro> livros) throws IOException {
        try (Writer writer = new FileWriter(this.file)) {
            gson.toJson(livros, writer);
        }
    }

    public Set<Livro> loadLivros() throws IOException {
        if (!this.file.exists()) return new TreeSet<>();

        try (Reader reader = new FileReader(this.file)) {
            Type setType = new TypeToken<Set<Livro>>() {}.getType();
            return gson.fromJson(reader, setType);
        }
    }

}
