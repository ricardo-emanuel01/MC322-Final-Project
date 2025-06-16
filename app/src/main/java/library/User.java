package library;

import java.util.ArrayList;
import java.util.List;


public class User {
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


    /**
     * Utilizado para criar um emprestimo
     * @param emprestimo o record do emprestimo gerenciado pela Biblioteca
     */
    public void emprestar(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }


    /**
     * Utilizado para remover um emprestimo do sistema ao ser devolvido
     * Remove o emprestimo com o ID do objeto em emprestimo
     * @param IDObjeto ID do objeto a ter emprestimo excluido
     * @return false caso o objeto nao esteja nos emprestimos do usuario
     */
    public boolean devolver(String IDObjeto) {
        for (int i = 0; i < this.emprestimos.size(); ++i) {
            String objAtual = this.emprestimos.get(i).objEmprestado().toString();
            if (IDObjeto.equals(objAtual)) {
                this.emprestimos.remove(i);
                return true;
            }
        }

        return false;
    }


    /**
     * Utilizado para renovar um emprestimo
     * @param emprestimo o record do novo emprestimo
     */
    public void renovar(Emprestimo emprestimo) {
        // TODO: Renovações apenas no dia da devolução
        // Lógica do todo acima deve estar na Biblioteca
        this.devolver(emprestimo.objEmprestado().toString());
        this.emprestimos.add(emprestimo);
    }
}