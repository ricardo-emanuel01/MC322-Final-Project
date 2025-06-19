import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class telaCriarBibliotecaController {

    @FXML
    private Button botaoCriarBiblioteca;

    @FXML
    private TextField campoNomeBiblioteca;

    @FXML
    private void avancarParaLogin() {
        try {
            String nomeBiblioteca = campoNomeBiblioteca.getText();
            if (nomeBiblioteca.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Digite um nome para a biblioteca.");
                alert.showAndWait();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLogin.fxml"));
                Parent root = loader.load();

                // Pegar o controller da tela de login e passar o nome
                telaLoginController loginController = loader.getController();
                loginController.setNomeBiblioteca(nomeBiblioteca);

                Stage stage = App.getMainStage();
                stage.setScene(new Scene(root));
                stage.setTitle("Login");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
