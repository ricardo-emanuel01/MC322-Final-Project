package library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class telaEmprestimoController {

    @FXML
    private TextField campoObra;

    @FXML
    private Button botaoEmprestar;

    @FXML
    private Label labelMensagem;

    // Lista de obras cadastradas na biblioteca
    private List<String> biblioteca = new ArrayList<>();

    // Formato de data
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Inicializa com alguns livros
    public telaEmprestimoController() {
        biblioteca.add("1984");
        biblioteca.add("Dom Casmurro");
        biblioteca.add("Harry Potter");
        biblioteca.add("O Pequeno Príncipe");
    }

    @FXML
    private Button botaoVoltar;

    @FXML
    void voltarParaUsuario(ActionEvent event) {
        try {
            // Fecha a janela atual
            botaoVoltar.getScene().getWindow().hide();

            // Carrega a tela de usuário
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/telaUsuario.fxml")); 
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tela do Usuário");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void fazerEmprestimo(ActionEvent event) {
        String obra = campoObra.getText().trim();

        if (obra.isEmpty()) {
            labelMensagem.setText("Por favor, informe o nome da obra.");
            return;
        }

        if (!biblioteca.contains(obra)) {
            labelMensagem.setText("Livro não cadastrado na biblioteca.");
            return;
        }

        LocalDate hoje = LocalDate.now();
        LocalDate dataDevolucao = hoje.plusDays(7);  // Prazo de 7 dias

        labelMensagem.setText("Empréstimo realizado!\nData de devolução: " + dataDevolucao.format(formatter));
    }
}
