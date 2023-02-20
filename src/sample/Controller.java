package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller{

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @FXML
    int playerTurn = 0, counter = 0;

    @FXML//Καθορίζει πότε θα τυπώνετε το χ και πότε ο
    private void setText(Button button){

        if(playerTurn % 2 == 0){
            //Ορίζει το χρώμα από το χ
            button.setStyle("-fx-text-fill: #2229EE");
            //τυπώνει το χ πάνω στο κουμπί
            button.setText("X");
            playerTurn = 1;
        }else {
            button.setStyle("-fx-text-fill: #EA1B1B");
            button.setText("O");
            playerTurn = 0;
        }
    }

    @FXML
    private void game(ActionEvent event) throws IOException{

        //Αναγνωρίζει πιο κουμπί πατήθηκε και ορίζει το κατάλληλο γράμμα
        if(event.getSource() == button1){
            setText(button1);
            button1.setDisable(true);
        }else if(event.getSource() == button2){
            setText(button2);
            button2.setDisable(true);
        }else if(event.getSource()== button3){
            setText(button3);
            button3.setDisable(true);
        }else if(event.getSource()== button4){
            setText(button4);
            button4.setDisable(true);
        }else if(event.getSource()== button5){
            setText(button5);
            button5.setDisable(true);
        }else if(event.getSource()== button6){
            setText(button6);
            button6.setDisable(true);
        }else if(event.getSource()== button7){
            setText(button7);
            button7.setDisable(true);
        }else if(event.getSource()== button8){
            setText(button8);
            button8.setDisable(true);
        }else if(event.getSource()== button9) {
            setText(button9);
            button9.setDisable(true);
        }

        //Κάνει ελέγχους για το αν έχει δημιουργηθεί τρίλιζα. Εαν ναι εμφανίζει τον νικητή με ανάλογο μήνυμα
        if((button1.getText() + button2.getText() + button3.getText()).equals("XXX") ||
                (button1.getText() + button2.getText() + button3.getText()).equals("OOO")){
            showMessage(event, button1);

        }else if ((button4.getText() + button5.getText() + button6.getText()).equals("XXX") ||
                (button4.getText() + button5.getText() + button6.getText()).equals("OOO")){
            showMessage(event, button4);

        }else if ((button7.getText() + button8.getText() + button9.getText()).equals("XXX") ||
                (button7.getText() + button8.getText() + button9.getText()).equals("OOO")){
            showMessage(event, button7);

        }else if ((button1.getText() + button4.getText() + button7.getText()).equals("XXX") ||
                (button1.getText() + button4.getText() + button7.getText()).equals("OOO")){
            showMessage(event, button1);

        }else if ((button2.getText() + button5.getText() + button8.getText()).equals("XXX") ||
                (button2.getText() + button5.getText() + button8.getText()).equals("OOO")){
            showMessage(event, button2);

        }else if ((button3.getText() + button6.getText() + button9.getText()).equals("XXX") ||
                (button3.getText() + button6.getText() + button9.getText()).equals("OOO")){
            showMessage(event, button3);

        }else if ((button1.getText() + button5.getText() + button9.getText()).equals("XXX") ||
                (button1.getText() + button5.getText() + button9.getText()).equals("OOO")){
            showMessage(event, button1);

        }else if ((button3.getText() + button5.getText() + button7.getText()).equals("XXX") ||
                (button3.getText() + button5.getText() + button7.getText()).equals("OOO")){
            showMessage(event, button3);
        }

        //Για να ξέρουμε πότε το παιχνίδι βγαίνει ισοπαλία
        counter++;
        if(counter == 9){
            Button draw = new Button();
            draw.setText("draw");
            showMessage(event, draw);
        }
    }

    @FXML
    private void showMessage(ActionEvent event, Button button) throws IOException{

        //Δημιουργεί τα κουμπιά στο Alert
        ButtonType playAgain = new ButtonType("Play again");
        ButtonType exit = new ButtonType("Exit");

        //Δημιουργεί το Alert
        Alert a = new Alert(Alert.AlertType.INFORMATION, "", playAgain, exit);
        a.setTitle("Game over!");

        //Για να εμφανίζει το κατάλληλο μήνυμα
        if (button.getText().equals("draw")){
            a.setHeaderText("Draw!!!");
        }else {
            a.setHeaderText(button.getText() + " wins!!!");
        }

        //Δίνει λειτουργικότητα στα κουμπιά του Alert
        Optional<ButtonType> result = a.showAndWait();
        if(!result.isPresent()) {
            Platform.exit();

        }else if(result.get() == playAgain) {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Τρίλιζα");
            primaryStage.setScene(new Scene(root, 500, 500));
            primaryStage.show();

        }else if(result.get() == exit) {
            Platform.exit();
        }
    }
}
