package library;

import java.util.ArrayList;

public class Biblioteca {
    private String nome;
    private ArrayList<Livro> livros;
    private static ArrayList<User> usuarios;

    public Biblioteca(String nome, String endereco) {
        this.nome = nome;
        this.livros = new ArrayList<>();
        Biblioteca.usuarios = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarUsuario(User usuario) {
        usuarios.add(usuario);
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public static ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public String getNome() {
        return nome;
    }
}
