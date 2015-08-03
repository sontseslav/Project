package view;

import control.Controller;
import control.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InitialJFX extends Application {
    private Stage stage;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InitialJFX.class.getResource("sample.fxml"));
            BorderPane root = (BorderPane)loader.load();
            Scene scene = new Scene(root,452,219);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("JATanks");
            controller = loader.getController();
            controller.mySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 14, 2));
            controller.setInitInstance(this);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void sendDataToParent(){
        Main.setIsDatabaseSet(controller.isDatabaseSet);
        Main.setTankQuantity(controller.mySpinner.getValue());
        Platform.runLater(new Runner());
        stage.close();
    }

    public static void startInitialJFX(String[] args) {
        launch(args);
    }
}

class Runner implements Runnable{
    @Override
    public void run() {
        try {
            new JavaFXView().start(new Stage());
        }catch (Exception e){e.printStackTrace();}
    }
}