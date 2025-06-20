package library;

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

public class telaCadastroController {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private TextField senhaCadastro;

    @FXML
    private CheckBox ehFuncionario;

    @FXML
    private CheckBox ehUsuario;

    @FXML
    private TextField emailCadastro;

    @FXML
    protected Label labelNomeBiblioteca1;

    @FXML
    private TextField userCadastro;

    @FXML
    void fazerCadastro(ActionEvent event){
        String user = userCadastro.getText();
        String email = emailCadastro.getText();
        String senha = senhaCadastro.getText();
        boolean funcionario = ehFuncionario.isSelected();
        boolean usuarioComum = ehUsuario.isSelected();

        if(funcionario==true && usuarioComum==false){
            UserDatabase.addUser(new User(user, senha, email, "ADMIN"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Funcionario cadastrado com sucesso!");
            alert.showAndWait();
            System.out.println("-----Cadastro realizado com sucesso!-----");
            System.out.printf("Funcionario cadastrado: %s\nEmail: %s\nSenha: %s\n\n", user, email, senha);

            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLogin.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de cadastro e passar o nome
            telaLoginController loginController = loader.getController();
            loginController.labelNomeBiblioteca.setText(labelNomeBiblioteca1.getText());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (usuarioComum==true && funcionario==false){
            UserDatabase.addUser(new User(user, senha, email, "LEITOR"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Usuario cadastrado com sucesso!");
            alert.showAndWait();
            System.out.println("-----Cadastro realizado com sucesso!-----");
            System.out.printf("Usuario cadastrado: %s\nEmail: %s\nSenha: %s\n\n", user, email, senha);

            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLogin.fxml"));
            Parent root = loader.load();

            // Pegar o controller da tela de cadastro e passar o nome
            telaLoginController loginController = loader.getController();
            loginController.labelNomeBiblioteca.setText(labelNomeBiblioteca1.getText());

            Stage stage = App.getMainStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (funcionario==true && usuarioComum==true){
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
}
