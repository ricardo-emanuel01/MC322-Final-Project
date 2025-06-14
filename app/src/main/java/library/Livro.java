public class Livro {
    private String titulo;
    private String autor;
    private int edicao;
    private int qtdCopias;
    private int qtdDisponiveis;
    
    public Livro(String titulo, String autor, int edicao){
        setAutor(autor);
        setEdicao(edicao);
        setTitulo(titulo);
    }

    public boolean disponivel(){
        if (qtdDisponiveis > 0)
            return true;
        else
            return false;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getEdicao() {
        return edicao;
    }
    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getQtdCopias() {
        return qtdCopias;
    }
    public void setQtdCopias(int qtdCopias) {
        this.qtdCopias = qtdCopias;
    }
    public int getQtdDisponiveis() {
        return qtdDisponiveis;
    }
    public void setQtdDisponiveis(int qtdDisponiveis) {
        this.qtdDisponiveis = qtdDisponiveis;
    }
}
