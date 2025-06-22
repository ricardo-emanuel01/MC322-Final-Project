package library;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class TelaCriarBibliotecaController {

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

                App.getBiblioteca().setNome(nomeBiblioteca); // Define o nome da biblioteca na instância única
                //final Biblioteca biblioteca = new Biblioteca(nomeBiblioteca, ""); // Endereço vazio por enquanto
                FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLogin.fxml"));
                Parent root = loader.load();

                // Pegar o controller da tela de login e passar o nome
                TelaLoginController loginController = loader.getController();
                loginController.setNomeBiblioteca(App.getBiblioteca().getNome());

                Stage stage = App.getMainStage();
                stage.setScene(new Scene(root));
                stage.setTitle("Login");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
