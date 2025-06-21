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
    private User usuarioLogado;



    /**
     * Construtor da classe Biblioteca que vai essencialmente gerir todo o programa
     * @param nome o nome escolhido para nossa Biblioteca
     * @param endereco o endereco da mesma Biblioteca
     */
    private Biblioteca(String nome) {
        this.nome = nome;
        this.acervo = new HashSet<>();
        this.usuarios = new HashSet<>();
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
    public boolean adicionaEmprestavel(Emprestavel object){
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
        if(object != null && acervo.contains(object)){
            acervo.remove(object);
            return true;
        }else{return false;}
    }

    // decide implementar um quick builder para os emprestimos para deixar mais limpo

    /**
     * Cria um record emprestimo que é atribuido a um object de classe emprestavel
     * e a um certo tipo de usuario ja logado
     * @param obj o objeto de tipo Emprestavel a ser emprestado
     */
    public boolean adicionaEmprestimo(Emprestavel obj){
        if(obj == null) { return false; }
        if(usuarioLogado == null) {return false; }

        LocalDate dataEmprestimo = LocalDate.now();
        UUID idUUID = UUID.fromString(obj.getID());
        emprestimos.add(
            new Emprestimo(
                usuarioLogado.getEmail(),
                idUUID,
                dataEmprestimo,
                dataEmprestimo.plusDays(7),
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
        // aqui vamos remover um emprestimo da lista de emprestimos e do usuario também
        if (emprestimos.contains(emprestimo)) {
            String user = emprestimo.usuario();
            ArrayList<User> filtrados = FilterCollection.filtrarUser(this.usuarios, user);
            if (filtrados.isEmpty()) {return false;}
            User usuario = filtrados.get(0);
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
            if(this.usuarioLogado == null){ return false; }
            if(!emprestimo.usuario().equals(this.usuarioLogado.getEmail())){ return false; }
            Emprestimo novoEmprestimo = Emprestimo.renovacao(emprestimo, dataRenovacao);

            // remove antigo emprestimo e adiciona novo
            String user = novoEmprestimo.usuario();
            ArrayList<User> filtrados = FilterCollection.filtrarUser(this.usuarios, user);
            if (filtrados.isEmpty()) {return false;}
            User usuario = filtrados.get(0);
            usuario.devolver(emprestimo.objEmprestado().toString());
            emprestimos.remove(emprestimo);

            // adiciona novo emprestimo
            emprestimos.add(novoEmprestimo);
            usuario.emprestar(novoEmprestimo);
            return true;
        }
        return false;
    }


    /**
     * Implemntação do registro de um usuário
     * @param user user que foi construido usando os inputs do usuario (externo)
     * @return bool caso de certo retorna true caso contrario false
     */
    public boolean adicionaUser( User user ){
        // como discutido acima vou supor que o user foi construido certinho
        if(user == null){return false;}
        this.usuarios.add(user);
        return true;
    }

    /**
     * Implemntação da remoção de um usuario por admin
     * @param emailDoUserARemover email do usuario que vamos remover
     */
    public boolean removeUser( String emailDoUserARemover ){
        ArrayList<User> filtrados = FilterCollection.filtrarUser(this.usuarios, emailDoUserARemover);
        if (filtrados.isEmpty()) return false;
        User usuario = filtrados.get(0);
        if(usuarioLogado.getEmail().equals(emailDoUserARemover)){ // verifica se o proprio usuario que deletar sua conta
            this.usuarios.remove(usuario);
            return true;
        }else if( usuarioLogado.verificaAdmin() == true){ // verifica se admin
            this.usuarios.remove(usuario);
            return true;
        }
        return false;
    }


    /**
     * Processo de login na biblioteca
     * @param emailUser supões que para o login ja foi imputado um email verificado
     * @return bool status de login
     */
    public boolean login( String emailUser, String senha){
        ArrayList<User> filtrados = FilterCollection.filtrarUser(this.usuarios, emailUser);
        if (filtrados.isEmpty()) return false;
        User usuario = filtrados.get(0);
        if (usuario.getSenha().equals(senha) == false) {return false;}
        this.usuarioLogado = usuario;
        return true;
    }

    // logout vai simplesmente settar como nenhum user logado
    /**
     * Processo de logout na biblioteca
     * @return bool status de logout
     */
    public boolean logout(){
        if(this.usuarioLogado == null){return false;}
        this.usuarioLogado = null;
        return true;
    }

}
