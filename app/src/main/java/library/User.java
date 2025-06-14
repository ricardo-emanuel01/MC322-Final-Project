import java.util.ArrayList;

public class User implements IUser{
    private String nome;
    private int codigo;
    private ArrayList<Livro> livrosEmprestados;

    public User(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.livrosEmprestados = new ArrayList<>();
    }

    public void solicitarEmprestimo(Livro livro) {
        livrosEmprestados.add(livro);
    }
    public void devolverLivro(Livro livro) {
        livrosEmprestados.remove(livro);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void setLivrosEmprestados(ArrayList<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }
}