package library;

public class Funcionario extends User implements IFuncionario {

    public Funcionario(String primeiroNome, String email, String senha, String cargo, String setor) {
        super(primeiroNome, email, senha, "ADMIN");
    }

    public void cadastrarLivro(Biblioteca biblioteca, Livro livro){
        if(livro != null && biblioteca != null){
            biblioteca.cadastrarLivro(livro);
            System.out.println("Livro cadastrado com sucesso: " + livro.getTitulo());
        }
        else
            System.out.println("Erro ao cadastrar livro.");
    }
    public void removerLivro(Biblioteca biblioteca, Livro livro){
        if(livro != null && biblioteca != null && biblioteca.getLivros().contains(livro)){
            biblioteca.getLivros().remove(livro);
            System.out.println("Livro removido com sucesso: " + livro.getTitulo());
        }
        else
            System.out.println("Erro ao remover livro");
    }
}
