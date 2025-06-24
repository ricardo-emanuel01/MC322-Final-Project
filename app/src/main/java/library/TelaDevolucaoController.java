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

public class TelaDevolucaoController {

    @FXML
    private TextField campoObra;

    @FXML
    private Button botaoDevolver;

    @FXML
    private Label labelMensagem;

    @FXML
    protected Label labelNomeBiblioteca3;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoSair;

    @FXML
    void fazerDevolucao(ActionEvent event) {
        String obra = campoObra.getText().trim();
        Map<String, Emprestavel> acervo = App.getBiblioteca().getAcervo();
        Emprestimo paraDevolver = null;

        for (Emprestimo emprestimo : App.getBiblioteca().getUsuarioLogado().getEmprestimos()) {
            Emprestavel obj = acervo.get(emprestimo.objEmprestado());
            if (obj != null && obj.getTitulo().toLowerCase().contains(obra)) {
                paraDevolver = emprestimo;
                break;
            }
        }

        if (paraDevolver == null) {
            labelMensagem.setText("Obra nao existe ou não foi emprestada por você.");
            return;
        }

        App.getBiblioteca().removeEmprestimo(paraDevolver);
        // TODO: checar atraso
        labelMensagem.setText("Devolução bem sucedida");
    }

    @FXML
    void voltarParaUsuario(ActionEvent event) {
        try {
            // Fecha a janela atual
            botaoVoltar.getScene().getWindow().hide();

            // Carrega a tela de usuário
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/telaUsuario.fxml")); 
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
