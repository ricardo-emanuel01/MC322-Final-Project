package library;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.gson.reflect.TypeToken;

public class App extends Application{

    private static Stage mainStage;
    private static Biblioteca biblioteca;  //Instancia unica da Biblioteca

    public static Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        File livros = new File("bd/livros.json");
        File usuarios = new File("bd/usuarios.json");
        File emprestimos = new File("bd/emprestimos.json");
        Repository<Livro> repoLivros = new Repository<>(livros, new TypeToken<TreeMap<String, Livro>>() {}.getType());
        Repository<User> repoUsuarios = new Repository<>(usuarios, new TypeToken<TreeMap<String, User>>() {}.getType());
        Repository<Emprestimo> repoEmprestimos = new Repository<>(emprestimos, new TypeToken<TreeMap<String, Emprestimo>>() {}.getType());
        Map<String, Livro> livrosMap = repoLivros.load();
        Map<String, User> usuariosMap = repoUsuarios.load();
        Map<String, Emprestimo> emprestimosMap = repoEmprestimos.load();
        Map<String, Emprestavel> emprestavelMap = new TreeMap<>();
        for (Map.Entry<String, Livro> livro : livrosMap.entrySet()) {
            emprestavelMap.put(livro.getKey(), livro.getValue());
        }
        biblioteca = new Biblioteca("", 7, emprestavelMap, usuariosMap, emprestimosMap);


        mainStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("telaCriarBiblioteca.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root, 1000, 650);
        primaryStage.setTitle("Biblioteca");
        primaryStage.setScene(tela);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(650);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }


    @Override
    public void stop() throws Exception {
        File livros = new File("bd/livros.json");
        File usuarios = new File("bd/usuarios.json");
        File emprestimos = new File("bd/emprestimos.json");
        Repository<Livro> repoLivros = new Repository<>(livros, new TypeToken<TreeMap<String, Livro>>() {}.getType());
        Repository<User> repoUsuarios = new Repository<>(usuarios, new TypeToken<TreeMap<String, User>>() {}.getType());
        Repository<Emprestimo> repoEmprestimos = new Repository<>(emprestimos, new TypeToken<TreeMap<String, Emprestimo>>() {}.getType());

        repoLivros.save(App.getBiblioteca().getLivros());
        repoUsuarios.save(App.getBiblioteca().getUsuarios());
        repoEmprestimos.save(App.getBiblioteca().getEmprestimos());
    }
}
