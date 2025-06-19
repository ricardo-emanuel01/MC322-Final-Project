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
    private TextField autorLivro;

    @FXML
    private Button botaoCadastrarLivro;

    @FXML
    private TextField copiasLivro;

    @FXML
    private TextField edicaoLivro;

    @FXML
    protected Label labelNomeBiblioteca2;

    @FXML
    private TextField tituloLivro;

    @FXML
    private ListView<String> listaLivrosFuncionario;

    @FXML
    void cadastrarLivro(ActionEvent event){
        String titulo = tituloLivro.getText();
        String autor = autorLivro.getText();
        String edicao = edicaoLivro.getText();
        String copias = copiasLivro.getText();

        if(titulo.isEmpty() || autor.isEmpty() || edicao.isEmpty() || copias.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos!");
            alert.showAndWait();
        } else {
            // Aqui você pode adicionar o livro ao banco de dados ou lista
            
            UserDatabase.addLivro(new Livro(titulo, autor, Integer.parseInt(edicao), Integer.parseInt(copias)));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Livro");
            alert.setHeaderText(null);
            alert.setContentText("Livro cadastrado com sucesso!");
            alert.showAndWait();
            
            tituloLivro.clear();
            autorLivro.clear();
            edicaoLivro.clear();
            copiasLivro.clear();
        }
        
        listaLivrosFuncionario.setItems(FXCollections.observableArrayList(UserDatabase.getLivros().stream().map(Livro::getTitulo).toList()));

    }
}
