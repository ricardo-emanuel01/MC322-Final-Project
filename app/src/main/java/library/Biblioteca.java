package library;

import java.util.ArrayList;

public class Biblioteca {
    private String nome;
    private static ArrayList<Livro> livros;
    private static ArrayList<User> usuarios;

    public Biblioteca(String nome, String endereco) {
        this.nome = nome;
        Biblioteca.livros = new ArrayList<>();
        Biblioteca.usuarios = new ArrayList<>();
    }

    public static void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public static void cadastrarUsuario(User usuario) {
        usuarios.add(usuario);
    }

    public static ArrayList<Livro> getLivros() {
        return livros;
    }

    public static ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public String getNome() {
        return nome;
    }
}
