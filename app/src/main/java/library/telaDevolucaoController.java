package library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class telaDevolucaoController {

    @FXML
    private TextField campoObra;

    @FXML
    private TextField campoData;  // aqui o usuário informa a data de empréstimo

    @FXML
    private Button botaoDevolver;

    @FXML
    private Label labelMensagem;

    private List<String> biblioteca = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public telaDevolucaoController() {
        biblioteca.add("1984");
        biblioteca.add("Dom Casmurro");
        biblioteca.add("Harry Potter");
        biblioteca.add("O Pequeno Príncipe");
    }

    @FXML
    void fazerDevolucao(ActionEvent event) {
        String obra = campoObra.getText().trim();
        String dataStr = campoData.getText().trim();

        if (!biblioteca.contains(obra)) {
            labelMensagem.setText("Livro não cadastrado na biblioteca.");
            return;
        }

        try {
            LocalDate dataEmprestimo = LocalDate.parse(dataStr, formatter);
            LocalDate hoje = LocalDate.now();

            // Verifica se a data de empréstimo não é futura
            if (dataEmprestimo.isAfter(hoje)) {
                labelMensagem.setText("A data de empréstimo não pode ser no futuro.");
                return;
            }

            long diasTotal = java.time.temporal.ChronoUnit.DAYS.between(dataEmprestimo, hoje);
            long diasDeAtraso = diasTotal - 7;  // prazo de 7 dias

            if (diasDeAtraso > 0) {
                double multa = diasDeAtraso * 3;
                labelMensagem.setText("Devolvido com atraso de " + diasDeAtraso + " dias. Multa: " + multa + " dias");
            } else {
                labelMensagem.setText("Devolução realizada dentro do prazo.");
            }
        } catch (DateTimeParseException e) {
            labelMensagem.setText("Data inválida! Use o formato dd/MM/yyyy.");
        }
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
}
