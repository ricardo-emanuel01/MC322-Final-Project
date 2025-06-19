import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static List<User> users = new ArrayList<>();
    private static List<Livro> livros = new ArrayList<>();

    static {
        // Usuários pré-cadastrados (pode mudar depois)
        users.add(new User("wiczin", "1234", "wiczin@gmail.com", 0));
        users.add(new User("admin", "admin", "admin@gmail.com", 1));
        users.add(new User("teste", "senha", "teste@gmail.com", 0));
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<Livro> getLivros() {
        return livros;
    }

    public static void addLivro(Livro livro) {
        livros.add(livro);
    }
}
