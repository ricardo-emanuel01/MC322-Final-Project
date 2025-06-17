package library;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 * Serializador de Livro para utilizar os getters
 */
public class LivroSerializer implements JsonSerializer<Livro> {
    @Override
    public JsonElement serialize(
        Livro livro, Type typeOfSrc, JsonSerializationContext context
    ) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", livro.getID());
        jsonObject.addProperty("titulo", livro.getTitulo());

        if (livro.getSubtitulo() != null) {
            jsonObject.addProperty("subtitulo", livro.getSubtitulo());
        } else {
            jsonObject.add("subtitulo", JsonNull.INSTANCE);
        }

        JsonArray autoresArray = new JsonArray();
        for (String autor : livro.getAutores()) {
            autoresArray.add(new JsonPrimitive(autor));
        }
        jsonObject.add("autores", autoresArray);

        jsonObject.addProperty("tipo", livro.getTipo());

        if (!livro.getDisponibilidade()) {
            jsonObject.addProperty("disponivel", false);
            jsonObject.addProperty("IDUser", livro.getIDUSer());
            jsonObject.addProperty("dataDevolucao", livro.getPrevisaoDevolucao());
        } else {
            jsonObject.addProperty("disponivel", true);
            jsonObject.add("IDUser", JsonNull.INSTANCE);
            jsonObject.add("dataDevolucao", JsonNull.INSTANCE);
        }

        return jsonObject;
    }
}
