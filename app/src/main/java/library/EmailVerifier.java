package library;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailVerifier{

    private static final String regexGmail = "^[\\w.+-]+@gmail\\.com$";
    private static final Pattern padraoGmail = Pattern.compile(regexGmail);

    public static boolean emailValido(String email) {

        if (email == null){ return false}
        Matcher matcher = padraoGmail.matcher(email);
        return matcher.matches();
    }

}
