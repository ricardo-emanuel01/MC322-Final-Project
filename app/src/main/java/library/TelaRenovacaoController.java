package library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Map;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TelaRenovacaoController {

    @FXML
    private TextField campoObra;

    @FXML
    private TextField campoData;  // Data de empréstimo original

    @FXML
    private Button botaoRenovar;

    @FXML
    private Label labelMensagem;

    @FXML
    protected Label labelNomeBiblioteca3;

    @FXML
    private Button botaoSair;

    @FXML
    private Button botaoVoltar;

    @FXML
    void fazerRenovacao(ActionEvent event) {
        String obra = campoObra.getText().trim().toLowerCase();
        Map<String, Emprestavel> acervo = App.getBiblioteca().getAcervo();
        Emprestimo paraRenovar = null;

        for (Emprestimo emprestimo : App.getBiblioteca().getUsuarioLogado().getEmprestimos()) {
            Emprestavel obj = acervo.get(emprestimo.objEmprestado());
            if (obj != null && obj.getTitulo().toLowerCase().contains(obra)) {
                paraRenovar = emprestimo;
                break;
            }
        }

        if (paraRenovar == null) {
            labelMensagem.setText("Obra nao existe ou não foi emprestada por você.");
            return;
        }

        App.getBiblioteca().renovaEmprestimo(obra);
        labelMensagem.setText("Renovação feita com sucesso");
    }

    @FXML
    void voltarParaUsuario(ActionEvent event) {
        try {
            // Fecha a janela atual
            botaoVoltar.getScene().getWindow().hide();

            // Carrega a tela de usuário
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaUsuario.fxml")); 
            Parent root = loader.load();

            // Pegar o controller da tela de funcionario e passar o nome
            TelaUsuarioController usuarioController = loader.getController();
            usuarioController.labelNomeBiblioteca3.setText(App.getBiblioteca().getNome());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela do Usuário");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
