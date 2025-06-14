package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements IUser{
    private String primeiroNome;
    private String email;
    private String senhaHashed;
    private Permissoes permissoes;
    private String IDBilioteca;
    private List<Emprestimo> emprestimos;

    public User(
        String primeiroNome,
        String email,
        String senha,
        String permissoes,
        String IDBilioteca
    ) {
        this.primeiroNome = primeiroNome;
        this.email = email;
        this.senhaHashed = senha;
        this.permissoes = Permissoes.valueOf(permissoes);
        this.IDBilioteca = IDBilioteca;
        this.emprestimos = new ArrayList<Emprestimo>();
    }


    public Emprestimo emprestar(UUID IDObjeto, int duracaoEmprestimo) {
        LocalDate hoje = LocalDate.now();
        Emprestimo emprestimo = 
            new Emprestimo(
                this.email,
                IDObjeto,
                hoje,
                hoje.plusDays(duracaoEmprestimo),
                hoje
            );
        
        this.emprestimos.add(emprestimo);
        return emprestimo;
    }
}