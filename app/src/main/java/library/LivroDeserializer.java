package library;


import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivroDeserializer implements JsonDeserializer<Livro> {
    @Override
    public Livro deserialize(
        JsonElement json,
        Type typeOfT,
        JsonDeserializationContext context
    ) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        Livro livro;

        String id = obj.get("id").getAsString();
        String titulo = obj.get("titulo").getAsString();
        JsonElement subtituloElement = obj.get("subtitulo");
        String subtitulo;

        if (subtituloElement.isJsonNull()) subtitulo = null;
        else subtitulo = subtituloElement.getAsString();

        List<String> autores = new ArrayList<>();
        JsonArray autoresArray = obj.getAsJsonArray("autores");
        for (JsonElement elem : autoresArray) {
            autores.add(elem.getAsString());
        }

        boolean disponivel = obj.get("disponivel").getAsBoolean();
        if (!disponivel) {
            String IDUser = obj.get("IDUser").getAsString();
            String prevDev = obj.get("dataDevolucao").getAsString();
            LocalDate prevDevDate = LocalDate.parse(prevDev);
            livro = new Livro(
                id, titulo, subtitulo, autores, prevDevDate, IDUser
            );
        } else {
            livro = new Livro(id, titulo, subtitulo, autores);
        }

        return livro;
    }
}
