/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelpidev.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import travelpidev.entities.Plan;
import travelpidev.services.PlanService;

/**
 * FXML Controller class
 *
 * @author Wofurani
 */
public class BackPlanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDescrip;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Plan> tableView;
    @FXML
    private TableColumn<Plan, Integer> ColId;
    @FXML
    private TableColumn<Plan, String> ColName;
    @FXML
    private TableColumn<Plan, String> ColDescrip;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPlans();
        tableView.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 1) {
                txtName.setText(tableView.getSelectionModel().getSelectedItem().getName());
                txtDescrip.setText(tableView.getSelectionModel().getSelectedItem().getDesc());
            }
        });

    }

    @FXML
    public void handlerInsertAction(ActionEvent event) {
        if (event.getSource() == btnInsert) {
            insertPlan();
            showPlans();
        }

    }

    @FXML
    public void handlerUpdateAction(ActionEvent event) {
        if (event.getSource() == btnUpdate) {
            
            Plan plan = new Plan();
            plan.setId(tableView.getSelectionModel().getSelectedItem().getId());
            plan.setDesc(txtDescrip.getText());
            plan.setName(txtName.getText());
            updatePlan(plan);
            showPlans();
        }
    }

    @FXML
    public void handlerDeleteAction(ActionEvent event) {
        if (event.getSource() == btnDelete) {
            Plan plan = new Plan();
            plan.setId(tableView.getSelectionModel().getSelectedItem().getId());
            deletePlan(plan);
            showPlans();

        }

    }

    public void insertPlan() {
        System.out.println("add");
        PlanService planService = new PlanService();
        Plan plan = new Plan();
        plan.setName(txtName.getText());
        plan.setDesc(txtDescrip.getText());
        planService.add(plan);

    }

    public void updatePlan(Plan plan) {
        System.out.println("update");
        PlanService planService = new PlanService();
        
        planService.edit(plan);

    }

    public void deletePlan(Plan plan) {
        PlanService planService = new PlanService();
        planService.delete(plan);
        
    }

    public void showPlans() {
        PlanService planService = new PlanService();
        ObservableList<Plan> list = planService.getAll();
        ColId.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<Plan, String>("name"));
        ColDescrip.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tableView.setItems(list);

    }

}
