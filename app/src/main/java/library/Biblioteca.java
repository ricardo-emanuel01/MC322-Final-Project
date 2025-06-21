package library;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import org.mindrot.jbcrypt.BCrypt;


public class Biblioteca {
    private String nome;
    private Map<String, Emprestavel> acervo;
    private Map<String, User> usuarios;
    private Map<String, Emprestimo> emprestimos;
    private User usuarioLogado;
    private int diasPorEmprestimo;


    /**
     * Construtor da classe Biblioteca que vai essencialmente gerir todo o programa
     * @param nome o nome escolhido para nossa Biblioteca
     * @param endereco o endereco da mesma Biblioteca
     */
    private Biblioteca(String nome, int diasPorEmprestimo) {
        this.nome = nome;
        this.acervo = new TreeMap<>();
        this.usuarios = new TreeMap<>();
        this.emprestimos = new TreeMap<>();
        this.diasPorEmprestimo = diasPorEmprestimo;
        this.usuarioLogado = null;
    }


    // getters 
    protected String getNome() { return this.nome; }
    protected Map<String, Emprestavel> getAcervo() { return this.acervo; }
    protected Map<String, User> getUsuarios() { return this.usuarios; }
    protected Map<String, Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }


    /**
     * Adiciona um emprestável ao acervo da Biblioteca
     * @param object o objeto emprestavel a ser adicionado
     */
    public boolean adicionaEmprestavel(Emprestavel object){
        if (object == null) return false;
        if (acervo.get(object.getID()) != null) return false;

        acervo.put(object.getID(), object);
        return true; 
    }


    /**
     * Remove um emprestável ao acervo da Biblioteca
     * @param object o objeto emprestavel a ser removido
     */
    public boolean removeEmprestavel(Emprestavel object){
        if (object == null) return false;
        if (this.acervo.get(object.getID()) == null ) return false;
        if (this.emprestimos.get(object.getID()) != null) return false;

        acervo.remove(object.getID());
        return true;
    }

    // decide implementar um quick builder para os emprestimos para deixar mais limpo

    /**
     * Cria um record emprestimo que é atribuido a um object de classe emprestavel
     * e a um certo tipo de usuario ja logado
     * @param obj o objeto de tipo Emprestavel a ser emprestado
     */
    public boolean adicionaEmprestimo(Emprestavel object){
        if (object == null) return false;
        if (usuarioLogado == null) return false;
        if (this.emprestimos.get(object.getID()) != null) return false;

        LocalDate dataEmprestimo = LocalDate.now();
        emprestimos.put(
            object.getID(),
            new Emprestimo(
                usuarioLogado.getEmail(),
                object.getID(),
                dataEmprestimo,
                dataEmprestimo.plusDays(this.diasPorEmprestimo),
                dataEmprestimo
            )
        );

        return true;
    }


    /**
     * Remove um emprestimo do acervo da Biblioteca
     * @param emprestimo o emprestimo a ser removido
     */
    public boolean removeEmprestimo(Emprestimo emprestimo) {
        if (emprestimo == null) return false;
        
        // aqui vamos remover um emprestimo da lista de emprestimos e do usuario também
        String emailUsuario = emprestimo.usuario();
        User usuario = this.usuarios.get(emailUsuario);
        if (usuario == null) return false;

        usuario.devolver(emprestimo.objEmprestado());
        emprestimos.remove(emprestimo.objEmprestado());

        return true;
    }
    

    /**
     * Renova um emprestimo usando a funcao renovacao da classe Emprestimo
     * @param emprestimo o emprestimo a ser renovado
     */
    public boolean renovaEmprestimo(String emprestavel) {
        if (this.usuarioLogado == null) return false;
        if (emprestavel == null) return false;

        Emprestimo emprestimo = this.emprestimos.get(emprestavel);
        if (emprestimo == null) return false;

        if (!this.usuarioLogado.getEmail().equals(emprestimo.usuario())) {
            return false;
        }

        LocalDate hoje = LocalDate.now();
        Emprestimo emprestimoRenovado = new Emprestimo(
            usuarioLogado.getEmail(),
            emprestavel,
            emprestimo.dataEmprestimo(),
            hoje.plusDays(this.diasPorEmprestimo),
            hoje
        );

        this.usuarioLogado.renovar(emprestimoRenovado);
        this.emprestimos.remove(emprestavel);
        this.emprestimos.put(emprestavel, emprestimoRenovado);
        return true;
    }


    /**
     * Implemntação do registro de um usuário
     * @param user user que foi construido usando os inputs do usuario (externo)
     * @return bool caso de certo retorna true caso contrario false
     */
    public boolean adicionaUser(User user) {
        // como discutido acima vou supor que o user foi construido certinho
        if (user == null) return false;

        this.usuarios.put(user.getEmail(), user);
        return true;
    }

    /**
     * Implemntação da remoção de um usuario por admin
     * @param emailDoUserARemover email do usuario que vamos remover
     */
    public boolean removeUser(String email) {
        if (email == null) return false;
        if (this.usuarioLogado == null) return false;

        User usuarioParaRemover = this.usuarios.get(email);
        if (usuarioParaRemover == null) return false;
        if (usuarioParaRemover.getEmprestimos().size() > 0) return false;

        if (this.usuarioLogado.getPermissoes().equals(Permissoes.LEITOR.toString())) {
            if (!email.equals(this.usuarioLogado.getEmail())) return false;
        }

        if (email.equals(this.usuarioLogado.getEmail())) {
            this.usuarioLogado = null;
        }

        this.usuarios.remove(email);
        return true;
    }


    /**
     * Processo de login na biblioteca
     * @param emailUser supões que para o login ja foi imputado um email verificado
     * @return bool status de login
     */
    public boolean login(String emailUser, String senha){
        User usuario = this.usuarios.get(emailUser);
        if (usuario == null) return false;
        if (!BCrypt.checkpw(senha, usuario.getSenha())) return false;

        this.usuarioLogado = usuario;
        return true;
    }


    /**
     * Processo de logout na biblioteca
     * @return bool status de logout
     */
    public boolean logout(){
        if (this.usuarioLogado == null) return false;

        this.usuarioLogado = null;
        return true;
    }

}
