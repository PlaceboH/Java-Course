package com.example.geographic;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import tasks.CountryTask;
import tasks.CurrencyTask;
import tasks.PopulationTask;
import tasks.TaskInterface;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    HashMap<String, TaskInterface> myTasks;

    @FXML
    private ListView<String> taskView;
    @FXML
    private Label questionLable;
    @FXML
    private Label answerLable;
    @FXML
    private Label testTitle;
    @FXML
    private TextField inputAnswer;
    @FXML
    private MenuButton menuButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        inputAnswer.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue,
//                                String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    inputAnswer.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });

        myTasks = new HashMap<>();
        try {
            myTasks.put("Country Task", new CountryTask());
            myTasks.put("Currency Task", new CurrencyTask());
            myTasks.put("Population Task", new PopulationTask());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.createTaskListView();
    }


    public void createTaskListView(){
        for (String t : myTasks.keySet()){
            this.taskView.getItems().add(t);
        }
    }


    public void showTask(MouseEvent mouseEvent) throws IOException, InterruptedException {

        menuButton.getItems().clear();
        System.out.println("REMOVE " +  menuButton.getItems().toString());

        String selectedTaskKey = taskView.getSelectionModel().getSelectedItem();
        TaskInterface currentTask = myTasks.get(selectedTaskKey);


        testTitle.setText(currentTask.getNameOfTask());
        questionLable.setText(currentTask.getQuestion());

        for (String item : currentTask.getAllItems().keySet() ) {
            menuButton.getItems().add(new MenuItem(item));
        }
        System.out.println(menuButton.getItems().toString());

        menuButton.getItems().stream().forEach( menuItem -> {
            menuItem.setOnAction(e -> {
                menuButton.setText(menuItem.getText());
            });
        });
    }


    public void sendUserAnswer(ActionEvent actionEvent) throws IOException, InterruptedException {
        String selectedTaskKey = taskView.getSelectionModel().getSelectedItem();
        TaskInterface currentTask = myTasks.get(selectedTaskKey);
        if(menuButton.getText() != "" && !inputAnswer.getText().isEmpty()){
            answerLable.setText(currentTask.checkIsCorrectAnswer(menuButton.getText(), inputAnswer.getText()));
        }
    }



}