package library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Gson factory para adicionar adaptadores de tipo
 */
public class GsonFactory {
    // TODO: Adapter para Biblioteca
    public static Gson buildGson() {
        return new GsonBuilder()
            .registerTypeAdapter(User.class, new UserDeserializer())
            .registerTypeAdapter(User.class, new UserSerializer())
            .create();
    }
}
