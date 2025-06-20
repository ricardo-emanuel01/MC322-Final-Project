package library;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class telaBuscaController {

    @FXML
    private TextField campoBusca;

    @FXML
    private ListView<String> listaResultados;

    @FXML
    private Button botaoBuscar;

    private List<String> livrosCadastrados = new ArrayList<>();

    @FXML
    public void initialize() {
        // Cadastra os livros
        livrosCadastrados.add("1984");
        livrosCadastrados.add("Dom Casmurro");
        livrosCadastrados.add("Harry Potter");
        livrosCadastrados.add("O Pequeno Príncipe");
        livrosCadastrados.add("Senhor dos Anéis");

        // Exibe todos ao iniciar a tela
        listaResultados.setItems(FXCollections.observableArrayList(livrosCadastrados));
    }

    @FXML
    void fazerBusca(ActionEvent event) {
        String termo = campoBusca.getText().trim().toLowerCase();

        List<String> resultados = livrosCadastrados.stream()
                .filter(livro -> livro.toLowerCase().contains(termo))
                .collect(Collectors.toList());

        if (resultados.isEmpty()) {
            listaResultados.setItems(FXCollections.observableArrayList("Nenhum livro encontrado."));
        } else {
            listaResultados.setItems(FXCollections.observableArrayList(resultados));
        }
    }
}
