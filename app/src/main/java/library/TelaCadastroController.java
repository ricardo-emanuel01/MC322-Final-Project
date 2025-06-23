package library;

import org.mindrot.jbcrypt.BCrypt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroController {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private TextField senhaCadastro;

    @FXML
    private CheckBox adminCheck;

    @FXML
    private CheckBox usuarioCheck;

    @FXML
    private TextField emailCadastro;

    @FXML
    protected Label labelNomeBiblioteca1;

    @FXML
    private TextField userCadastro;

    @FXML
    private Button botaoSair;

    @FXML
    void fazerCadastro(ActionEvent event) {
        String user = userCadastro.getText();
        String email = emailCadastro.getText();
        String senha = senhaCadastro.getText();
        boolean admin = adminCheck.isSelected();
        boolean usuarioComum = usuarioCheck.isSelected();

        if(!User.isValidEmail(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Email inválido. Por favor, insira um email válido.");
            alert.showAndWait();
            emailCadastro.clear();
            return;
        }

        if (admin == true && usuarioComum == false) {
            String senhaHashed = BCrypt.hashpw(senha, BCrypt.gensalt());
            App.getBiblioteca().adicionaUser(new User(user, email, senhaHashed, "ADMIN"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Funcionario cadastrado com sucesso!");
            alert.showAndWait();
            System.out.println("-----Cadastro realizado com sucesso!-----");

            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLogin.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de cadastro e passar o nome
            TelaLoginController loginController = loader.getController();
            loginController.labelNomeBiblioteca.setText(labelNomeBiblioteca1.getText());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (usuarioComum == true && admin == false) {
            String senhaHashed = BCrypt.hashpw(senha, BCrypt.gensalt());
            App.getBiblioteca().adicionaUser(new User(user, email, senhaHashed, "LEITOR"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Usuario cadastrado com sucesso!");
            alert.showAndWait();
            System.out.println("-----Cadastro realizado com sucesso!-----");
            System.out.printf("Usuario cadastrado: %s\nEmail: %s\nSenha: %s\n\n", user, email, senha);

            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLogin.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de cadastro e passar o nome
            TelaLoginController loginController = loader.getController();
            loginController.labelNomeBiblioteca.setText(labelNomeBiblioteca1.getText());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (admin == true && usuarioComum == true) {
            System.out.println("Selecione apenas um tipo de usuario para cadastro.\n");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Selecione apenas um tipo de usuario para cadastro.");
            alert.showAndWait();
        }
        else {
            System.out.println("Nenhum tipo de usuario selecionado para cadastro.\n");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum tipo de usuario selecionado para cadastro.");
            alert.showAndWait();
        }
    }
    
    @FXML
    void sair(ActionEvent event) {
        // Implementar a lógica para sair da tela de usuário
        try {
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
}
