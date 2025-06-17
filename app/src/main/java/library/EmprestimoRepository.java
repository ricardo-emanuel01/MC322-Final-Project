package library;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.TreeSet;
import java.util.Set;


public class EmprestimoRepository {
    private final File file;
    private final Gson gson;

    public EmprestimoRepository(File file) {
        this.file = file;
        this.gson = GsonFactory.getGson();
    }

    public void saveEmprestimos(
        Set<Emprestimo> emprestimos
    ) throws IOException {

        try (Writer writer = new FileWriter(this.file)) {
            gson.toJson(emprestimos, writer);
        }
    }

    public Set<Emprestimo> loadEmprestimos() throws IOException {
        if (!this.file.exists()) return new TreeSet<>();

        try (Reader reader = new FileReader(this.file)) {
            Type setType = new TypeToken<Set<Emprestimo>>() {}.getType();
            return gson.fromJson(reader, setType);
        }
    }
}
