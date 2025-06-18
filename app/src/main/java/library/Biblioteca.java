package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Biblioteca {
    private String nome;
    private ArrayList<Emprestavel> acervo;
    private ArrayList<IUser> usuarios;
    private ArrayList<Emprestimo> emprestimos;



    /**
     * Construtor da classe Biblioteca que vai essencialmente gerir todo o programa
     * @param nome o nome escolhido para nossa Biblioteca
     * @param endereco o endereco da mesma Biblioteca
     */
    private Biblioteca(String nome, String endereco) {
        this.nome = nome;
        this.acervo = new ArrayList<Emprestavel>();
        this.usuarios = new ArrayList<IUser>();
        this.emprestimos = new ArrayList<>();
    }


    // getters 
    protected String getNome() { return nome; }
    protected ArrayList<Emprestavel> getAcervo() { return acervo; }
    protected ArrayList<IUser> getUsuarios() { return usuarios; }
    protected ArrayList<Emprestimo> getEmprestimos() { return emprestimos; }


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
        // TODO: filter criteria para usuario
        if (emprestimos.contains(emprestimo)) {
            // Aqui DEVEMOS remover o emprestimo do usuario também
            // nao vai ter isso por enquanto
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
            emprestimos.remove(emprestimo);
            emprestimos.add(novoEmprestimo);
            return true;
        }
        return false;
    }

}