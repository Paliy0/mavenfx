package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.Arrays;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Login Form");

        GridPane myGrid = new GridPane();
        myGrid.setPadding(new Insets(10, 10, 10, 10));
        myGrid.setVgap(10); // Vertical spacing between grid items
        myGrid.setHgap(8);

        Label userLabel = new Label("Username");
        myGrid.add(userLabel, 1, 0);

        Label label = new Label("Test");
        myGrid.add(label, 4, 0);

        Label passLabel = new Label("Password");
        myGrid.add(passLabel, 1, 1);

        TextField userField = new TextField();
        userField.setPromptText("username");
        myGrid.add(userField, 2, 0);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        myGrid.add(passwordField, 2, 1);

        Button loginButton = new Button("Log in");
        myGrid.add(loginButton, 2, 2);
        loginButton.setVisible(false);

        //myGrid.getChildren().addAll(userLabel, passLabel, userField, passwordField);
        //myGrid.getChildren().add(loginButton);


        StringProperty passwordFieldProperty = passwordField.textProperty();

        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String password = passwordField.getText();

                boolean charPresent = password.matches(".*[a-zA-Z]+.*");
                boolean numPresent = password.matches("[0-9]+.*");
                boolean sPresent = password.matches(".*[!@#$%^&*()-+]");

                loginButton.setVisible(password.length() >= 8 && charPresent && numPresent && sPresent);
            }
        });

        Scene scene = new Scene(myGrid);
        window.setScene(scene);
        window.show();
    }
}
