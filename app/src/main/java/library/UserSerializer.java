package library;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;


/**
 * Serializador de User para ignorar atributo emprestimos
 */
public class UserSerializer implements JsonSerializer<User> {
    @Override
    public JsonElement serialize(
        User user, Type typeOfSrc, JsonSerializationContext context
    ) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("primeiroNome", user.getPrimeiroNome());
        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("senha", user.getSenha());
        jsonObject.addProperty("permissoes", user.getPermissoes());

        return jsonObject;
    }
}
