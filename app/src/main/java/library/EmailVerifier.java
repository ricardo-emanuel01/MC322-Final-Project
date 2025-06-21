package library;


public class EmailVerifier {
    private static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean emailValido(String email) {
        return email != null && email.matches(regexEmail);
    }
}
