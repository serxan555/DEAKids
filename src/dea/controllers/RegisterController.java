package dea.controllers;

import dea.beans.User;
import dea.connection.UserRepository;
import dea.util.HashAlgoritms;
import dea.util.SendMail;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author SS.555
 */
public class RegisterController extends UserRepository implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void saveUser() throws NoSuchAlgorithmException {
        
        UUID uuid = UUID.randomUUID();
        String password = uuid.toString();
        password = password.substring(password.length() - 10);
        
        User u = new User();
        u.setName(nameField.getText());
        u.setSurname(surnameField.getText());
        u.setUsername(usernameField.getText());
        u.setEmail(emailField.getText());
        u.setPassword(HashAlgoritms.encodeSha256(password));
        

        try {
            if (!u.getUsername().equals("") && save(u) != null) {
                stage.close();
                Notifications notifications = Notifications.create()
                        .title("Save")
                        .text("USER ADDED SUCCESSFULLY")
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .hideAfter(Duration.seconds(3));
                notifications.showInformation();
                SendMail.sendMail(emailField.getText(), "Your account was successfully created", "Your account code: " + password);
                
            } else {
                usernameField.setText(null);
                Notifications notifications = Notifications.create()
                        .title("UNSUCCESSFULLY")
                        .text("INVALID USERNAME or E-MAIL")
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .hideAfter(Duration.seconds(3));
                notifications.showInformation();
            }
        } catch (Exception e) {

        }

    }
    

}
