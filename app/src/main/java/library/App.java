package library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class App extends Application{

    private static Stage mainStage;
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("telaCriarBiblioteca.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);

        primaryStage.setTitle("Biblioteca");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}