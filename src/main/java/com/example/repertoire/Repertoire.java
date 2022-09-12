package com.example.repertoire;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Repertoire implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;

    @FXML
    private TextField enterAssignmentText;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button addAssignmentButton;
    @FXML
    VBox assignmentPane;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label welcomeUsername;
    private Label dueDate;
    private RadioButton assignment;
    private Hyperlink edit;
    private Stage stage;
    private Scene scene;
    private Singleton singleton = Singleton.getInstance();
    private Connection con = dbConnection.dbConnector();
    private String assignmentName;



    public void logOut() throws IOException {
        //Logout By Switch Back To Login Scene
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) pane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        public void addButtonFadeOut() {
        //Prevent User from Clicking the Add Button Multiple Times causing the Animation to be janky
        addAssignmentButton.setDisable(false);
        addButton.setDisable(true);

        //Add Button Translate And Fade Out
        TranslateTransition translateAdd = new TranslateTransition(Duration.millis(500), addButton);
        FadeTransition addFadeOut = new FadeTransition(Duration.millis(200), addButton);
        translateAdd.setByY(60);
        translateAdd.setDuration(Duration.millis(500));
        addFadeOut.setFromValue(1.0);
        addFadeOut.setToValue(0.0);

        TranslateTransition translateText = new TranslateTransition(Duration.millis(500), enterAssignmentText);
        FadeTransition textFadeIn = new FadeTransition(Duration.millis(200), enterAssignmentText);
        translateText.setByY(-40);
        translateText.setDelay(Duration.millis(500));
        textFadeIn.setFromValue(0.0);
        textFadeIn.setToValue(1.0);
        textFadeIn.setDelay(Duration.millis(200));

        TranslateTransition translateDate = new TranslateTransition(Duration.millis(500), datePicker);
        FadeTransition dateFadeIn = new FadeTransition(Duration.millis(200), datePicker);
        translateDate.setByY(-40);
        translateDate.setDelay(Duration.millis(500));
        dateFadeIn.setFromValue(0.0);
        dateFadeIn.setToValue(1.0);
        dateFadeIn.setDelay(Duration.millis(200));

        TranslateTransition translateAssignment = new TranslateTransition(Duration.millis(500), addAssignmentButton);
        FadeTransition assignmentFadeIn = new FadeTransition(Duration.millis(200), addAssignmentButton);
        translateAssignment.setByY(-40);
        translateAssignment.setDelay(Duration.millis(500));
        assignmentFadeIn.setFromValue(0.0);
        assignmentFadeIn.setToValue(1.0);
        assignmentFadeIn.setDelay(Duration.millis(200));

        //Play Animation
        addFadeOut.play();
        translateAdd.play();
        textFadeIn.play();
        translateText.play();
        dateFadeIn.play();
        translateDate.play();
        assignmentFadeIn.play();
        translateAssignment.play();
    }

    public void editFadeOut() {

        //Add Button Translate And Fade Out
        TranslateTransition translateAdd = new TranslateTransition(Duration.millis(500), addButton);
        FadeTransition addFadeOut = new FadeTransition(Duration.millis(200), addButton);
        translateAdd.setByY(60);
        translateAdd.setDuration(Duration.millis(500));
        addFadeOut.setFromValue(1.0);
        addFadeOut.setToValue(0.0);

        TranslateTransition translateText = new TranslateTransition(Duration.millis(500), enterAssignmentText);
        FadeTransition textFadeIn = new FadeTransition(Duration.millis(200), enterAssignmentText);
        translateText.setByY(-40);
        translateText.setDelay(Duration.millis(500));
        textFadeIn.setFromValue(0.0);
        textFadeIn.setToValue(1.0);
        textFadeIn.setDelay(Duration.millis(200));

        TranslateTransition translateDate = new TranslateTransition(Duration.millis(500), datePicker);
        FadeTransition dateFadeIn = new FadeTransition(Duration.millis(200), datePicker);
        translateDate.setByY(-40);
        translateDate.setDelay(Duration.millis(500));
        dateFadeIn.setFromValue(0.0);
        dateFadeIn.setToValue(1.0);
        dateFadeIn.setDelay(Duration.millis(200));

        TranslateTransition translateAssignment = new TranslateTransition(Duration.millis(500), editButton);
        FadeTransition assignmentFadeIn = new FadeTransition(Duration.millis(200), editButton);
        translateAssignment.setByY(-40);
        translateAssignment.setDelay(Duration.millis(500));
        assignmentFadeIn.setFromValue(0.0);
        assignmentFadeIn.setToValue(1.0);
        assignmentFadeIn.setDelay(Duration.millis(200));

        //Play Animation
        addFadeOut.play();
        translateAdd.play();
        textFadeIn.play();
        translateText.play();
        dateFadeIn.play();
        translateDate.play();
        assignmentFadeIn.play();
        translateAssignment.play();
    }




    public void addButtonFadeIn() {
        //Prevent The User From Clicking The Add Button Multiple Times
        addAssignmentButton.setDisable(true);
        addButton.setDisable(false);

        //For Clicking to Return Event, When the TextField and DatePicker have not Appeared yet don't do anything
        if(enterAssignmentText.getOpacity() == 0)
        {
            return;
        }

        //The TextField, and DatePicker Fade Out, Add Button Fade Back In
        TranslateTransition translateAdd = new TranslateTransition(Duration.millis(500), addButton);
        FadeTransition addFadeIn = new FadeTransition(Duration.millis(200), addButton);
        translateAdd.setByY(-60);
        translateAdd.setDelay(Duration.millis(200));
        addFadeIn.setDelay(Duration.millis(200));
        addFadeIn.setFromValue(0.0);
        addFadeIn.setToValue(1.0);


        TranslateTransition translateText = new TranslateTransition(Duration.millis(500), enterAssignmentText);
        FadeTransition textFadeOut = new FadeTransition(Duration.millis(200), enterAssignmentText);
        translateText.setByY(40);
        textFadeOut.setFromValue(1.0);
        textFadeOut.setToValue(0.0);

        TranslateTransition translateDate = new TranslateTransition(Duration.millis(500), datePicker);
        FadeTransition dateFadeOut = new FadeTransition(Duration.millis(200), datePicker);
        translateDate.setByY(40);
        dateFadeOut.setFromValue(1.0);
        dateFadeOut.setToValue(0.0);

        TranslateTransition translateAssignment = new TranslateTransition(Duration.millis(500), addAssignmentButton);
        FadeTransition assignmentFadeOut = new FadeTransition(Duration.millis(200), addAssignmentButton);
        translateAssignment.setByY(40);
        assignmentFadeOut.setFromValue(1.0);
        assignmentFadeOut.setToValue(0.0);

        //Play Animation
        addFadeIn.play();
        translateAdd.play();
        textFadeOut.play();
        translateText.play();
        dateFadeOut.play();
        translateDate.play();
        assignmentFadeOut.play();
        translateAssignment.play();
    }

    public void editFadeIn() {
        //Prevent The User From Clicking The Add Button Multiple Times
        addAssignmentButton.setDisable(true);
        addButton.setDisable(false);

        //For Clicking to Return Event, When the TextField and DatePicker have not Appeared yet don't do anything
        if(enterAssignmentText.getOpacity() == 0)
        {
            return;
        }


        //The TextField, and DatePicker Fade Out, Add Button Fade Back In
        TranslateTransition translateAdd = new TranslateTransition(Duration.millis(500), addButton);
        FadeTransition addFadeIn = new FadeTransition(Duration.millis(200), addButton);
        translateAdd.setByY(-60);
        translateAdd.setDelay(Duration.millis(200));
        addFadeIn.setDelay(Duration.millis(200));
        addFadeIn.setFromValue(0.0);
        addFadeIn.setToValue(1.0);


        TranslateTransition translateText = new TranslateTransition(Duration.millis(500), enterAssignmentText);
        FadeTransition textFadeOut = new FadeTransition(Duration.millis(200), enterAssignmentText);
        translateText.setByY(40);
        textFadeOut.setFromValue(1.0);
        textFadeOut.setToValue(0.0);

        TranslateTransition translateDate = new TranslateTransition(Duration.millis(500), datePicker);
        FadeTransition dateFadeOut = new FadeTransition(Duration.millis(200), datePicker);
        translateDate.setByY(40);
        dateFadeOut.setFromValue(1.0);
        dateFadeOut.setToValue(0.0);

        TranslateTransition translateAssignment = new TranslateTransition(Duration.millis(500), editButton);
        FadeTransition assignmentFadeOut = new FadeTransition(Duration.millis(200), editButton);
        translateAssignment.setByY(40);
        assignmentFadeOut.setFromValue(1.0);
        assignmentFadeOut.setToValue(0.0);

        //Play Animation
        addFadeIn.play();
        translateAdd.play();
        textFadeOut.play();
        translateText.play();
        dateFadeOut.play();
        translateDate.play();
        assignmentFadeOut.play();
        translateAssignment.play();
    }

    public String datePicker()
        {
        //Convert Date From DatePicker Into Readable Format
        LocalDate date = datePicker.getValue();
        String dayOfWeekFirst = date.getDayOfWeek().toString().substring(0, 1).toUpperCase();
        String dayOfWeekLast = date.getDayOfWeek().toString().substring(1, 3).toLowerCase();
        String monthFirst = date.getMonth().toString().substring(0, 1).toUpperCase();
        String monthLast = date.getMonth().toString().substring(1, 3).toLowerCase();
        int day = date.getDayOfMonth();
        String dateValue = dayOfWeekFirst + dayOfWeekLast + ", " + monthFirst + monthLast + " " + day;
        return dateValue;
    }
    public void addAssignment() throws SQLException
    {
        //Use Singleton To Receive data From Login Scene
        String dataBaseName = singleton.getDatabaseName();
        //If The TextField and DatePicker is Empty Do Nothing
        if(enterAssignmentText.getText().equals("") || datePicker.getValue() == null)
        {
            return;
        }
        String date = datePicker();
        //Set Up Database to Insert the Assignment Into Database
        String sql = "insert into " + dataBaseName + "Assignment (assignment, date) values (?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, enterAssignmentText.getText());
        pst.setString(2, date);
        pst.execute();
        addButtonFadeIn();
        //Update The Page
        updatePage();
        //Set The TextField and DatePicker to Empty
        enterAssignmentText.setText("");
        datePicker.setValue(null);
    }

    public void updatePage() throws SQLException {
        //Use Singleton To Receive data From Login Scene
        String dataBaseName = singleton.getDatabaseName();
        welcomeUsername.setText(singleton.getUsername());
        //Before Using the Information to Construct Dynamic Button Clear Everything
        assignmentPane.getChildren().clear();
        //Set Up Database to Construct Dynamic Button
        String sql = "select * from " + dataBaseName + "Assignment";
        PreparedStatement pst2 = con.prepareStatement(sql);
        ResultSet result = pst2.executeQuery();
        while (result.next()) {
            //Use Information to Construct a Button and Label
            String current = result.getString("assignment");
            String currentDate = result.getString("date");
            RadioButton assignment = new RadioButton();
            Label dueBy = new Label("Due By ");
            dueBy.setTextFill(Color.WHITE);
            dueBy.setTextFill(Color.WHITE);
            dueBy.setFont(Font.font("Calibri", 13));
            Label dueDate = new Label(currentDate);
            dueDate.setTextFill(Color.WHITE);
            dueDate.setFont(Font.font("Calibri", 13));
            assignment.getStyleClass().add("assignment");
            assignment.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            assignment.setText(current);
            FlowPane dueFlow = new FlowPane();
            FlowPane assignmentFlow = new FlowPane();
            dueFlow.getChildren().add(dueBy);
            dueFlow.getChildren().add(dueDate);
            Hyperlink edit = new Hyperlink("Edit");
            edit.getStyleClass().add("edit");
            edit.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            assignmentFlow.getChildren().add(assignment);
            assignmentPane.getChildren().add(dueFlow);
            assignmentPane.getChildren().add(assignment);
            assignmentPane.getChildren().add(edit);

            assignment.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        //Set Up Delete Database
                        String sql = "delete from " + dataBaseName + "Assignment where assignment = ? and date = ?";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, current);
                        pst.setString(2, currentDate);
                        assignmentPane.getChildren().remove(assignment);
                        assignmentPane.getChildren().remove(dueFlow);
                        assignmentPane.getChildren().remove(edit);
                        pst.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            edit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Prevent User From Clicking The Button Multiple Time
                        edit.setDisable(true);
                        addButton.setDisable(true);
                        //If The User Is Adding Assignment Do Nothing
                        if(addAssignmentButton.getOpacity() == 1)
                        {
                            edit.setDisable(false);
                            return;
                        }
                        editFadeOut();
                        //For MouseClick to Work When The User Is Editing Assignment
                        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                if(addAssignmentButton.getOpacity() == 1)
                                {
                                    addButtonFadeIn();
                                }

                                if(editButton.getOpacity() == 1)
                                {
                                    editFadeIn();
                                    edit.setDisable(false);
                                }
                            }
                        });
                        //Update Button Action Event
                        editButton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    //If The TextField and DatePicker is Empty Do Nothing
                                    if(enterAssignmentText.getText().equals("") || datePicker.getValue() == null)
                                    {
                                        return;
                                    }
                                    String date = datePicker();
                                        //Setup Database For the Update Function
                                        String sql = "update " + dataBaseName +"Assignment set assignment = ?, date = ? where assignment = ?";
                                        PreparedStatement pst = con.prepareStatement(sql);
                                        pst.setString(1, enterAssignmentText.getText());
                                        pst.setString(2, date);
                                        pst.setString(3, assignment.getText());
                                        pst.executeUpdate();
                                        //Set The Update Text to The Button
                                        assignment.setText(enterAssignmentText.getText());
                                        dueDate.setText(date);
                                        //Once The User Finish Update Set The TextField and DatePicker to Empty
                                        enterAssignmentText.setText("");
                                        datePicker.setValue(null);
                                        //Prevent User From clicking The Button Multiple Time
                                        addButton.setDisable(false);
                                        edit.setDisable(false);
                                    editFadeIn();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });


            //When Clicking Anywhere On The App The TextField and DatePicker Will Fade Out
            pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(addAssignmentButton.getOpacity() == 1)
                    {
                        addButtonFadeIn();
                    }

                    if(editButton.getOpacity() == 1)
                    {
                        editFadeIn();
                        edit.setDisable(false);
                    }
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize The Application
        try {
            updatePage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


