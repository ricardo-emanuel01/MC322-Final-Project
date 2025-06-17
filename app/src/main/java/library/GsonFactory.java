package library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Gson factory para adicionar adaptadores de tipo
 */
public class GsonFactory {
    // TODO: Adapter para Biblioteca
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(User.class, new UserDeserializer())
            .registerTypeAdapter(User.class, new UserSerializer())
            .registerTypeAdapter(Livro.class, new LivroDeserializer())
            .registerTypeAdapter(Livro.class, new LivroSerializer())
            .create();

    public static Gson getGson() {
        return gson;
    }
}
