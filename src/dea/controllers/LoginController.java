package dea.controllers;

import dea.beans.User;
import dea.connection.LoginRepository;
import dea.util.HashAlgoritms;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author SS.555
 */
public class LoginController extends LoginRepository implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label signinLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Login(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        User user = new User();
        user.setUsername(usernameField.getText());
        user.setPassword(HashAlgoritms.encodeSha256(passwordField.getText()));
        System.out.println(user.getPassword());
        if (findUser(user) != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/dea/view/StudentView.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Developia Kids");
            Image icone = new Image(getClass().getResourceAsStream("/dea/files/Icon.png"));
            stage.getIcons().add(icone);
            stage.fullScreenProperty();
            stage.setScene(scene);
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Notifications notifications = Notifications.create()
                    .text("Username or Password is invalid!")
                    .title("Worning")
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .hideAfter(Duration.seconds(3));
            notifications.showInformation();
        }
    }

    @FXML
    private void signinMethod(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dea/view/RegisterView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Regist");
        Image icone = new Image(getClass().getResourceAsStream("/dea/files/Icon.png"));
        stage.getIcons().add(icone);
        stage.setScene(new Scene(root));
        RegisterController registerController = loader.getController();
        registerController.setStage(stage);
        stage.show();

    }

}
