package library;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class telaFuncionarioController {

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
    void cadastrarLivro(ActionEvent event){
        String titulo = tituloLivro.getText();
        String subtitulo = subtituloLivro.getText();
        String autores = autoresLivro.getText();
        String id = idLivro.getText();

        if(titulo.isEmpty() || autores.isEmpty() || subtitulo.isEmpty() || id.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos!");
            alert.showAndWait();
        } else {
            // Aqui você pode adicionar o livro ao banco de dados ou lista
            
            List<String> listaAutores = Arrays.stream(autores.split(",")).map(String::trim).toList();

            Biblioteca.cadastrarLivro(new Livro(id, titulo, subtitulo, listaAutores));

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
        
        listaLivrosFuncionario.setItems(FXCollections.observableArrayList(Biblioteca.getLivros().stream().map(Livro::getTitulo).toList()));

    }
}
