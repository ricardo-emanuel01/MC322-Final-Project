package library;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

// teste

public class TelaLoginController {

    @FXML
    private Button botaoLogin;

    @FXML
    private Button botaoCadastre_se;

    @FXML
    private TextField campoPassword;

    @FXML
    private TextField campoUser;

    @FXML
    protected Label labelNomeBiblioteca;

    public void setNomeBiblioteca(String nome) {
        labelNomeBiblioteca.setText(nome);
    }

    @FXML
    void fazerLogin(ActionEvent event) {
        String email = campoUser.getText();
        String senha = campoPassword.getText();

        boolean loginOK = false;

        // Login deve ser feito com email e senha
        boolean loginBiblioteca = App.getBiblioteca().login(email, senha);
        if (loginBiblioteca){
            loginOK = true;
            System.out.println("Login efetuado com sucesso!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Login efetuado com sucesso!");
            alert.showAndWait();

            if (App.getBiblioteca().getUsuarioLogado().getPermissoes().equals("admin")){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("telaFuncionario.fxml"));
                    Parent root = loader.load();

                    // Pegar o controller da tela de funcionario e passar o nome
                    TelaFuncionarioController funcionarioController = loader.getController();
                    funcionarioController.labelNomeBiblioteca2.setText(App.getBiblioteca().getNome());

                    Stage stage = App.getMainStage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Tela Funcionario");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("telaUsuario.fxml"));
                    Parent root = loader.load();

                    // Pegar o controller da tela de usuario e passar o nome
                    TelaUsuarioController usuarioController = loader.getController();
                    usuarioController.labelNomeBiblioteca3.setText(App.getBiblioteca().getNome());
                    Stage stage = App.getMainStage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Tela Usuario");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (!loginOK) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Login");
            alert.setHeaderText(null);
            alert.setContentText("Usuário ou senha incorretos.");
            alert.showAndWait();
            System.out.println("Usuario ou senha incorretos!");
        }
    }

    @FXML
    void avancarParaCadastro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaCadastro.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de cadastro e passar o nome
            TelaCadastroController cadastroController = loader.getController();
            cadastroController.labelNomeBiblioteca1.setText(App.getBiblioteca().getNome());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
