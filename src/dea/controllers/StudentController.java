package dea.controllers;

import dea.beans.Student;
import dea.beans.User;
import dea.connection.*;
import dea.util.HashAlgoritms;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;

/**
 *
 * @author SS.555
 */
public class StudentController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField parentField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField gmailField;
    @FXML
    private TextField gmailCodeField;
    @FXML
    private DatePicker birthdateField;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    private ComboBox<String> comboBoxGroupName;
    @FXML
    private TextField lessonDateField;
    @FXML
    private TextField lessonTimeField;
    @FXML
    private CheckBox useCheckBox;
    @FXML
    private CheckBox statusChechBox;
    @FXML
    private TextField studentNameField;
    @FXML
    private TextField paymentField;
    @FXML
    private Label labelUserPayment;
    @FXML
    private TextField USER_NAME;
    @FXML
    private TextField USER_SURNAME;
    @FXML
    private TextField USERNAMEforUSER;
    @FXML
    private TextField USER_EMAIL;
    @FXML
    private PasswordField CURRENT_PASSWORD;
    @FXML
    private PasswordField NEW_PASSWORD;
    @FXML
    private Accordion acardion;
    @FXML
    private TitledPane titlePaneStudent;
    @FXML
    private TitledPane titlePaneEditUser;
    @FXML
    private TableView<Student> studentsTableView;
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> surnameCol;
    @FXML
    private TableColumn<Student, String> parentCol;
    @FXML
    private TableColumn<Student, DatePicker> birthdateCol;
    @FXML
    private TableColumn<Student, String> phoneCol;
    @FXML
    private TableColumn<Student, String> gmailCol;
    @FXML
    private TableColumn<Student, String> gmailCodeCol;
    @FXML
    private TableColumn<Student, String> groupNameCol;
    @FXML
    private TableColumn<Student, String> statusCol;

    ObservableList<String> list = FXCollections.observableArrayList();

    private StudentRepository studentRepository;
    @FXML
    private TitledPane iki;
    @FXML
    private TitledPane bir;
    @FXML
    private TitledPane uc;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        acardion.setVisible(false);
        if (LoginRepository.staticId == 42) {
            
//            iki.setVisible(false);

//List<TitledPane> listAcardion = new ArrayList<>();
//listAcardion.add(uc);
//listAcardion.add(iki);
//listAcardion.add(bir);

            acardion.getPanes().remove(3, 4);
        }
        studentRepository = new StudentRepository();
        dateOfBirthday();
        upgradeTable();
        fillComboBox();
        fillTableForUser(LoginRepository.staticId);
        clear();
        calculateUserPayment();
        fillTextFieldForUser();
    }

    @FXML
    private void addOrUpdate(ActionEvent event) throws SQLException {
        studentRepository = new StudentRepository();
        try {
            Student selectedStudent = studentsTableView.getSelectionModel().getSelectedItem();
            Student s = new Student();

            if (selectedStudent == null) {
                s.setName(nameField.getText());
                s.setSurname(surnameField.getText());
                s.setParent(parentField.getText());
                java.sql.Date date = java.sql.Date.valueOf(birthdateField.getValue());
                s.setBirthdate(date);
                s.setPhone(phoneField.getText());
                s.setGmail(gmailField.getText());
                s.setGmailCode(gmailCodeField.getText());
                s.setGroupName(comboBoxGroupName.getValue());
                s.setLessonDate(lessonDateField.getText());
                s.setLessonTime(lessonTimeField.getText());
                if (statusChechBox.isSelected()) {
                    s.setStatus("Active");
                } else {
                    s.setStatus("Passive");
                }
                studentRepository.save(s);
                callNotification("Add Student Successful", "Add");
            } else {
                s.setId(selectedStudent.getId());
                s.setName(nameField.getText());
                s.setSurname(surnameField.getText());
                s.setParent(parentField.getText());
                java.sql.Date date = java.sql.Date.valueOf(birthdateField.getValue());
                s.setBirthdate(date);
                s.setPhone(phoneField.getText());
                s.setGmail(gmailField.getText());
                s.setGmailCode(gmailCodeField.getText());
                s.setGroupName(comboBoxGroupName.getValue());
                s.setLessonDate(lessonDateField.getText());
                s.setLessonTime(lessonTimeField.getText());
                if (statusChechBox.isSelected()) {
                    s.setStatus("Active");
                } else {
                    s.setStatus("Passive");
                }
                Long sgtId = studentRepository.isExitstSgt(selectedStudent.getId());
                if (sgtId != null) {
                    s.setPayment(Double.parseDouble(paymentField.getText()));
                    studentRepository.save(s);
                    callNotification("Update Successful", "Update");
                } else {
                    studentRepository.addSgt(s);
                    callNotification("Add Successful", "Add");
                }
            }
        } catch (NumberFormatException e) {
            callNotification("Warning", "Unsuccess");
            System.out.println("((((((((((((((((((((((((((((((");
            e.printStackTrace(System.err);
        } finally {
            clear();
            fillTableForUser(LoginRepository.staticId);
            calculateUserPayment();
        }
    }

    @FXML
    private void delete(ActionEvent event) {

        try {
            Student selectedStudent = studentsTableView.getSelectionModel().getSelectedItem();
            Student s = new Student();
            if (selectedStudent != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete");
                alert.setHeaderText("Delete Student");
                alert.setContentText("Are you sure?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/dea/files/Icon.png")));
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    studentRepository = new StudentRepository();
                    s.setId(selectedStudent.getId());
                    studentRepository.delete(s);
                }
            }
        } catch (Exception e) {
        } finally {
            clear();
            fillTableForUser(LoginRepository.staticId);
            calculateUserPayment();
        }
    }

    @FXML
    private void studentAdvancedSearch() throws SQLException {
        Student selectedStudent = studentsTableView.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            Map<String, String> map = new HashMap<>();
            if (studentNameField.getText() != null && !"".equals(studentNameField.getText())) {
                nameField.setText(studentNameField.getText());
            }
            map.put("name", nameField.getText());
            map.put("surname", surnameField.getText());
            map.put("parent", parentField.getText());
//        map.put("birthDate", birthdateField.getValue().toString());
            map.put("phone", phoneField.getText());
            map.put("gmail", gmailField.getText());
            map.put("gmailCode", gmailCodeField.getText());
            map.put("groupName", comboBoxGroupName.getValue());
            map.put("lessonDate", lessonDateField.getText());
            map.put("lessonTime", lessonTimeField.getText());
            System.out.println(map.get("groupName"));
            if (statusChechBox.isSelected()) {
                map.put("status", "Active");
            }

            if (filterAdvancedSearch(map)) {

                List<Student> students = studentRepository.studnetSearch(map);
                studentsTableView.getItems().setAll(students);
            } else {
                fillTableForUser(LoginRepository.staticId);
            }
        }
    }

    @FXML
    public void searchStudentForGroupName() throws SQLException {
        studentAdvancedSearch();
        studentRepository = new StudentRepository();
        Student s = studentRepository.searchLessons(comboBoxGroupName.getValue());
        lessonDateField.setText(s.getLessonDate());
        lessonTimeField.setText(s.getLessonTime());
    }

    public boolean filterAdvancedSearch(Map<String, String> map) {
        return map.entrySet().stream().map((entry)
                -> entry.getValue()).anyMatch((value)
                -> (!"".equals(value) && value != null));
    }

    @FXML
    private void onStudentSelect(MouseEvent event) throws IOException, SQLException {
        Student selectionStudent = studentsTableView.getSelectionModel().getSelectedItem();
        if (selectionStudent != null) {
            nameField.setText(selectionStudent.getName());
            surnameField.setText(selectionStudent.getSurname());
            parentField.setText(selectionStudent.getParent());
            birthdateField.setValue(convertDateToLocalDate(selectionStudent.getBirthdate()));
            phoneField.setText(selectionStudent.getPhone());
            gmailField.setText(selectionStudent.getGmail());
            gmailCodeField.setText(selectionStudent.getGmailCode());
//            if (studentRepository.isExitstSgt(selectionStudent.getId())) {
            comboBoxGroupName.setValue(selectionStudent.getGroupName());
            lessonDateField.setText(selectionStudent.getLessonDate());
            lessonTimeField.setText(selectionStudent.getLessonTime());
//            }
            studentNameField.setText(selectionStudent.getName());
            paymentField.setText(selectionStudent.getPayment().toString());
            System.out.println(selectionStudent.getStatus());
            if (selectionStudent.getStatus().equals("Active")) {
                statusChechBox.setSelected(true);
            } else {
                statusChechBox.setSelected(false);
            }
        } else {
            clear();
            fillTableForUser(LoginRepository.staticId);
        }
    }

    public void dateOfBirthday() {
        birthdateField.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                if (object != null) {
                    return formatter.format(object);
                }
                return null;
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    return LocalDate.parse(string, formatter);
                }
                return null;
            }
        });
    }

    private void fillTableForUser(Long u) {
        try {

            List<Student> students = studentRepository.findAllForUser(u);
            studentsTableView.getItems().setAll(students);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

    }

    public void upgradeTable() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        parentCol.setCellValueFactory(new PropertyValueFactory<>("parent"));
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        gmailCol.setCellValueFactory(new PropertyValueFactory<>("gmail"));
        gmailCodeCol.setCellValueFactory(new PropertyValueFactory<>("gmailCode"));
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void clear() {
        studentNameField.setText(null);
        nameField.setText(null);
        surnameField.setText(null);
        parentField.setText(null);
        phoneField.setText(null);
        gmailField.setText(null);
        gmailCodeField.setText(null);
        birthdateField.setValue(null);
        comboBoxGroupName.setValue(null);
        lessonDateField.setText(null);
        lessonTimeField.setText(null);
        statusChechBox.setSelected(false);
        paymentField.setText(null);
        CURRENT_PASSWORD.setText(null);
        NEW_PASSWORD.setText(null);
    }

    public LocalDate convertDateToLocalDate(Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

    public void fillComboBox() {
        comboBoxGroupName.getItems().addAll(studentRepository.allGroupname());
    }

    public void callNotification(String text, String title) {
        Notifications notifications = Notifications.create()
                .text(text)
                .title(title)
                .position(Pos.TOP_RIGHT)
                .darkStyle()
                .hideAfter(javafx.util.Duration.seconds(3));
        notifications.showInformation();
    }

    @FXML
    private void useLesson(ActionEvent event) {
        if (useCheckBox.isSelected()) {
            lessonDateField.setDisable(false);
            lessonTimeField.setDisable(false);
        } else {
            lessonDateField.setDisable(true);
            lessonTimeField.setDisable(true);
        }
    }

    public void calculateUserPayment() {
        studentRepository = new StudentRepository();
        try {
            labelUserPayment.setText("Salary: " + studentRepository.calculatePayment() + " AZN");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void fillTextFieldForUser() {
        try {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.findUser(LoginRepository.staticId);
            USER_NAME.setText(user.getName());
            USER_SURNAME.setText(user.getSurname());
            USER_EMAIL.setText(user.getEmail());
            USERNAMEforUSER.setText(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @FXML
    public void updateUserInformations() {
        try {
            UserRepository userRepository = new UserRepository();
            String currentPassword = HashAlgoritms.encodeSha256(CURRENT_PASSWORD.getText());
            User u = userRepository.findUser(LoginRepository.staticId);
            if (u.getPassword().equals(currentPassword)) {
                u.setName(USER_NAME.getText());
                u.setSurname(USER_SURNAME.getText());
                u.setEmail(USER_EMAIL.getText());
                u.setUsername(USERNAMEforUSER.getText());
                u.setPassword(HashAlgoritms.encodeSha256(NEW_PASSWORD.getText()));
                userRepository.update(u);
                callNotification("Update Succeeded", "UPDATE");
                clear();
                titlePaneEditUser.setExpanded(false);
            } else {
                callNotification("Update Unsucceeded", "WARNING");
            }
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace(System.err);
        }
        
    }
}
