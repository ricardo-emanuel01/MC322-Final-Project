package library;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

public class Biblioteca {
    private String nome;
    private Set<Emprestavel> acervo;
    private Set<User> usuarios;
    private Set<Emprestimo> emprestimos;



    /**
     * Construtor da classe Biblioteca que vai essencialmente gerir todo o programa
     * @param nome o nome escolhido para nossa Biblioteca
     * @param endereco o endereco da mesma Biblioteca
     */
    private Biblioteca(String nome, String endereco) {
        this.nome = nome;
        this.acervo = new HashSet<Emprestavel>();
        this.usuarios = new HashSet<User>();
        this.emprestimos = new HashSet<>();
    }


    // getters 
    protected String getNome() { return nome; }
    protected Set<Emprestavel> getAcervo() { return acervo; }
    protected Set<User> getUsuarios() { return usuarios; }
    protected Set<Emprestimo> getEmprestimos() { return emprestimos; }


    /**
     * Adiciona um emprestável ao acervo da Biblioteca
     * @param object o objeto emprestavel a ser adicionado
     */
    public boolean addEmprestavel(Emprestavel object){
        if(object == null || acervo.contains(object)) {
            return false;
        }else {
            acervo.add(object);
            return true; 
        }
    }
    /**
     * Remove um emprestável ao acervo da Biblioteca
     * @param object o objeto emprestavel a ser removido
     */
    public boolean removeEmprestavel(Emprestavel object){
        if(object != null){
            acervo.add(object);
            return true;
        }else{return false;}
    }

    // decide implementar um quick builder para os emprestimos para deixar mais limpo

    /**
     * Cria um record emprestimo que é atribuido a um object de classe emprestavel
     * e a um certo tipo de usuario ja logado
     * @param user o usuario que vai emprestar o livro
     * @param obj o objeto de tipo Emprestavel a ser emprestado
     */
    public boolean adicionaEmprestimo(Emprestavel obj, User user){
        if(obj == null || user == null) {   
            LocalDate dataEmprestimo = LocalDate.now();
            UUID idUUID = UUID.fromString(obj.getID());
            emprestimos.add(
                new Emprestimo(
                    user.getEmail(),
                    idUUID,
                    dataEmprestimo,
                    dataEmprestimo.plusDays(7),
                    dataEmprestimo
            ));
            return true;    
        } return false; // talvez seja melhor lançar uma exceção generalizada por exemplo objeto nulo exception
        // excecoes customizadas sao bons exemplo de boas praticas
    }

    /**
     * Remove um emprestimo do acervo da Biblioteca
     * @param emprestimo o emprestimo a ser removido
     */
    public boolean removeEmprestimo(Emprestimo emprestimo) {
        // aqui vamos remover um emprestimo da lista de emprestimos e do usuario também
        if (emprestimos.contains(emprestimo)) {
            String user = emprestimo.usuario();
            User usuario = FilterCollection.filtrarUser(usuarios, user);
            usuario.devolver(emprestimo.objEmprestado().toString());
            emprestimos.remove(emprestimo);
            return true;
        }
        return false;
    }
    
    /**
     * Renova um emprestimo usando a funcao renovacao da classe Emprestimo
     * @param emprestimo o emprestimo a ser renovado
     */
    public boolean renovaEmprestimo(Emprestimo emprestimo, LocalDate dataRenovacao) {
        if (emprestimos.contains(emprestimo)) {
            Emprestimo novoEmprestimo = Emprestimo.renovacao(emprestimo, dataRenovacao);

            // remove antigo emprestimo e adiciona novo
            String user = emprestimo.usuario();
            User usuario = FilterCollection.filtrarUser(usuarios, user);
            usuario.devolver(emprestimo.objEmprestado().toString());
            emprestimos.remove(emprestimo);
            
            // adiciona novo emprestimo
            emprestimos.add(novoEmprestimo);
            usuario.emprestar(novoEmprestimo);
            return true;
        }
        return false;
    }

}