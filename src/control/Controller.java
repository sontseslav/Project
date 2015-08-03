package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import view.InitialJFX;

public class Controller {
    public boolean isDatabaseSet;
    private InitialJFX initInstance;

    @FXML
    public Spinner<Integer> mySpinner = new Spinner<>();

    @FXML
    CheckBox myCheckbox;

    @FXML
    Button startButton;

    public void startTheGame(ActionEvent act){
        initInstance.sendDataToParent();
    }

    public void setDatabase(ActionEvent act){
        isDatabaseSet = !isDatabaseSet;
    }

    public void setInitInstance(InitialJFX initInstance){
        this.initInstance = initInstance;
    }
}

