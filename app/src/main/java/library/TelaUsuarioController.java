package library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

public class TelaUsuarioController {

    @FXML
    private Button botaoBuscar;

    @FXML
    private TextField campoBusca;

    @FXML
    protected Label labelNomeBiblioteca3;

    @FXML
    private ListView<String> listaResultados;

    @FXML
    private Button botaoSair;

    @FXML
    private Button botaoIrDevolucoes;

    @FXML
    private Button botaoIrEmprestimos;

    @FXML
    private Button botaoIrRenovacoes;

    /*@FXML
    public void initialize() {
        // Cadastra os livros
        /*App.getBiblioteca().cadastrarLivro(new Livro("1","1984", "a", List.of("George Orwell")));
        App.getBiblioteca().cadastrarLivro(new Livro("2","Dom Casmurro", "b", List.of("Machado de Assis")));
        
        for (Livro livro : App.getBiblioteca().getLivros()) {
            // Adiciona o livro a lista
            listaLivros.add(livro.getTitulo());
        }
        // Exibe a lista no listView
        listaResultados.setItems(FXCollections.observableArrayList(listaLivros));
    }*/

    @FXML
    void fazerBusca(ActionEvent event) {
        String titulo = campoBusca.getText().trim().toLowerCase();

        EmprestavelTituloFilter filter = new EmprestavelTituloFilter();
        List<Emprestavel> livros = filter.aplica(App.getBiblioteca().getAcervo(), titulo);

        listaResultados.setItems(FXCollections.observableArrayList(livros));

        if (resultados.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Busca");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum livro encontrado.");
            alert.showAndWait();
        }
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
