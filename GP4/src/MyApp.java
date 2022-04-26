import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Vasco Gomes 19921
 * @date 02/03/2022
 */
public class MyApp extends Application {

    Horoscope h;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        MenuBar menuBar = createMenu();
        borderPane.setTop(menuBar);

        //borderPane.setCenter(new TextArea());

        getFile(primaryStage);

    }

    private void getFile(Stage primaryStage) {
        //TODO REFACTOR TO OTHER METHOD
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Escolha a Base de Dados");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = chooser.showOpenDialog(primaryStage);

        h = new Horoscope(file.getAbsolutePath());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(h.SIGN_NAMES[0], h.SIGN_NAMES);
        if(dialog.showAndWait().isPresent()) {
                                                                //If Ternário
            List<String> predictions = h.generatePredictions(dialog.showAndWait().get()/*dialog.showAndWait().isPresent() ? dialog.showAndWait().get() : "False"*/);
            System.out.println(predictions);
            //Save File
            FileChooser saveFile = new FileChooser();

            try {
                Files.write(saveFile.showSaveDialog(primaryStage).toPath(), predictions);
            } catch (IOException e) {
                System.out.println("Não Foi Possível Guardar o Ficheiro");
                e.printStackTrace();
            }
        }
    }

    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu generateMenu = new Menu();
        Menu signsMenu = new Menu();

        for (String signName : Horoscope.SIGN_NAMES) {
            MenuItem signItem = new MenuItem(signName);
            signsMenu.getItems().addAll(signItem);
            signItem.setOnAction(e -> {
                List<String> strings = h.generatePredictions(signName);
                System.out.println(strings);
            });
        }

        generateMenu.getItems().add(signsMenu);
        menuBar.getMenus().add(generateMenu);

        return menuBar;
    }
}
