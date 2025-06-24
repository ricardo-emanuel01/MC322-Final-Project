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

import java.util.List;
import java.util.Map;

public class TelaEmprestimoController {

    @FXML
    private TextField campoObra;

    @FXML
    private Button botaoEmprestar;

    @FXML
    private Label labelMensagem;

    @FXML
    protected Label labelNomeBiblioteca3;

    @FXML
    private Button botaoSair;
    
    @FXML
    private Button botaoVoltar;

    // Lista de obras cadastradas na biblioteca
    private Map<String, Emprestavel> acervo = App.getBiblioteca().getAcervo();

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

    @FXML
    void fazerEmprestimo(ActionEvent event) {
        String obra = campoObra.getText().trim();

        if (obra.isEmpty()) {
            labelMensagem.setText("Por favor, informe o nome da obra.");
            return;
        }

        EmprestavelTituloFilter filter = new EmprestavelTituloFilter();
        List<Emprestavel> livros = filter.aplica(acervo, obra);
        if (livros.size() == 0) {
            labelMensagem.setText("Livro não cadastrado na biblioteca.");
            return;
        }

        Emprestavel objeto = null;
        for (Emprestavel livro : livros) {
            if (livro.getDisponibilidade()) {
                objeto = livro;
                break;
            }
        }

        if (objeto == null) {
            labelMensagem.setText("Nenhuma obra com esse nome disponível.");
            return;
        }

        App.getBiblioteca().adicionaEmprestimo(objeto);
    }
}
