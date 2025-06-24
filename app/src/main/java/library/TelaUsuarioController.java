package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

public class TelaUsuarioController implements Initializable {

    @FXML
    private Button botaoBuscar;

    @FXML
    private TextField campoBusca;

    @FXML
    protected Label labelNomeBiblioteca3;

    @FXML
    private ListView<String> listaLivros;

    @FXML
    private Button botaoSair;

    @FXML
    private Button botaoIrDevolucoes;

    @FXML
    private Button botaoIrEmprestimos;

    @FXML
    private Button botaoIrRenovacoes;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaLivros.setItems(
            FXCollections.observableArrayList(
                App.getBiblioteca().getAcervo()
                    .values()
                    .stream()
                    .filter(e -> e.getDisponibilidade())
                    .map(e -> ((Livro) e).getTitulo())
                    .toList()
            )
        );
    }


    @FXML
    void fazerBusca(ActionEvent event) {
        Map<String, Emprestavel> acervo = App.getBiblioteca().getAcervo();
        String termo = campoBusca.getText().trim().toLowerCase();
        
        EmprestavelTituloFilter filter = new EmprestavelTituloFilter();
        ArrayList<Emprestavel> resultados = filter.aplica(acervo, termo);
        List<String> resultadosString = resultados
                        .stream()
                        .filter(e -> e.getDisponibilidade())
                        .map(e -> ((Livro) e).getTitulo().toLowerCase())
                        .filter(e -> e.contains(termo))
                        .toList();
                    
        if (resultadosString.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Busca");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum livro encontrado.");
            alert.showAndWait();
        }

        listaLivros.setItems(FXCollections.observableArrayList(resultadosString));
    }


    @FXML
    void sair(ActionEvent event) {
        // Implementar a lógica para sair da tela de usuário
        try {
            App.getBiblioteca().logout();
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

    @FXML
    void telaEmprestimos(ActionEvent event) {
        // Implementar a lógica para sair da tela de usuário
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaEmprestimo.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de funcionario e passar o nome
            TelaEmprestimoController emprestimoController = loader.getController();
            emprestimoController.labelNomeBiblioteca3.setText(App.getBiblioteca().getNome());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void telaDevolucoes(ActionEvent event) {
        // Implementar a lógica para sair da tela de usuário
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaDevolucao.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de funcionario e passar o nome
            TelaDevolucaoController devolucaoController = loader.getController();
            devolucaoController.labelNomeBiblioteca3.setText(App.getBiblioteca().getNome());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void telaRenovacoes(ActionEvent event) {
        // Implementar a lógica para sair da tela de usuário
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaRenovacao.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de funcionario e passar o nome
            TelaRenovacaoController renovacaoController = loader.getController();
            renovacaoController.labelNomeBiblioteca3.setText(App.getBiblioteca().getNome());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
