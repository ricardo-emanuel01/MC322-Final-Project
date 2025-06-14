package library;

import java.util.ArrayList;

public class User implements IUser{
    private String primeiroNome;
    private String email;
    private String senhaHashed;
    private Permissoes permissoes;
    private String IDBilioteca;

    public User(
        String primeiroNome,
        String email,
        String senha,
        String permissoes,
        String IDBilioteca
    ) {
        if (!emailValido(email)) {
            throw new IllegalArgumentException(
                "O email fornecido não é válido, tente novamente!"
            );
        }
        this.primeiroNome = primeiroNome;
        this.email = email;
        this.senhaHashed = senha;
        this.permissoes = Permissoes.valueOf(permissoes);
        this.IDBilioteca = IDBilioteca;
    }


    private boolean emailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }
}