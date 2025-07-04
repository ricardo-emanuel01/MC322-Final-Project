package library;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaFuncionarioController implements Initializable {

    @FXML
    private TextField tituloLivro;

    @FXML
    private TextField subtituloLivro;

    @FXML
    private TextField autoresLivro;

    @FXML
    private TextField idLivro;

    @FXML
    private Button botaoCadastrarLivro;

    @FXML
    protected Label labelNomeBiblioteca2;

    @FXML
    private ListView<String> listaLivrosFuncionario;

    @FXML
    private Button botaoSair;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaLivrosFuncionario.setItems(
            FXCollections.observableArrayList(
                App.getBiblioteca().getAcervo()
                    .values()
                    .stream()
                    .filter(e -> e instanceof Livro)
                    .map(e -> ((Livro) e).getTitulo())
                    .toList()
            )
        );
    }

    @FXML
    void cadastrarLivro(ActionEvent event){
        String titulo = tituloLivro.getText();
        String subtitulo = subtituloLivro.getText();
        String autores = autoresLivro.getText();
        String id = idLivro.getText();

        if (titulo.isEmpty() || autores.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos!");
            alert.showAndWait();
        } else {
            // Aqui você pode adicionar o livro ao banco de dados ou lista
            List<String> listaAutores = Arrays.stream(autores.split(",")).map(String::trim).toList();
            if (id.isEmpty()) id = UUID.randomUUID().toString();

            App.getBiblioteca().adicionaEmprestavel(new Livro(id, titulo, subtitulo, listaAutores));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Livro");
            alert.setHeaderText(null);
            alert.setContentText("Livro cadastrado com sucesso!");
            alert.showAndWait();
            
            tituloLivro.clear();
            subtituloLivro.clear();
            autoresLivro.clear();
            idLivro.clear();
        }
    }

    @FXML
    void sair(ActionEvent event) {
        // Implementar a lógica para sair da tela de usuário
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLogin.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de funcionario e passar o nome
            TelaLoginController loginController = loader.getController();
            loginController.labelNomeBiblioteca.setText(App.getBiblioteca().getNome());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
