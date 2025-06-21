package library;

import java.util.ArrayList;
import java.util.List;


public class User {
    private String primeiroNome;
    private String email;
    private String senhaHashed;
    private Permissoes permissoes;
    private List<Emprestimo> emprestimos;

    public User(
        String primeiroNome,
        String email,
        String senha,
        String permissoes
    ) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }

        this.primeiroNome = primeiroNome;
        this.email = email;
        this.senhaHashed = senha;
        this.permissoes = Permissoes.valueOf(permissoes);
        this.emprestimos = new ArrayList<Emprestimo>();
    }


    // Getter utilizados pelo Gson
    public String getPrimeiroNome() {return this.primeiroNome;} 
    public String getEmail() {return this.email;}
    public String getSenha() {return this.senhaHashed;}
    public String getPermissoes() {return this.permissoes.toString();}
    public List<Emprestimo> getEmprestimos() {return this.emprestimos;}


    /**
     * Implementação do método de filtro para o padrão Criteria
     * @param item o objeto a ser testado
     * @return User o objeto encontrado ou seja pode ser User ou null
     */
    public static User aplica(User item) {
        if (item == null) {
            return null;
        }
        // Aqui poderia ser implementada uma lógica de filtro mais complexa
        return item;
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
            String objAtual = this.emprestimos.get(i).objEmprestado();
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
        this.devolver(emprestimo.objEmprestado());
        this.emprestimos.add(emprestimo);
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }
}
