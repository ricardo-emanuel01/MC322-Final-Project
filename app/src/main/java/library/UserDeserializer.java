package library;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import java.lang.reflect.Type;


/**
 * Deserializador de User
 */
public class UserDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(
        JsonElement json,
        Type typeOfT,
        JsonDeserializationContext context
    ) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String primeiroNome = jsonObject.get("primeiroNome").getAsString();
        String email = jsonObject.get("email").getAsString();
        String senha = jsonObject.get("senha").getAsString();
        String permissoes = jsonObject.get("permissoes").getAsString();

        return new User(primeiroNome, email, senha, permissoes);
    }
}
