package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Contract;
import com.booking.config.StageManager;
import com.booking.service.ContractService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Controller
public class ContractController implements Initializable {

	@FXML
	private Label ContractID;
	@FXML
	private ComboBox<String> Purpose;
	@FXML
	private TextField ShowName;
	@FXML
	private TextField ShowDetail;
	@FXML
	private TextField ShowTime;
	@FXML
	private ComboBox<String> CustomerName;
	@FXML
	private TextField RepName;
	@FXML
	private TextField RepEmail;
	@FXML
	private TextField RepMobile;
	@FXML
	private DatePicker ShowDate;
	@FXML
	private ComboBox<String> Slot;
	@FXML
	private ComboBox<String> Services;
	@FXML
	private TextField Charges;
	@FXML
	private TextField TaxAmount;
	@FXML
	private TextField Total;
	
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired
	private ContractService contractService;
	
	

	@FXML
	private void logout(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	private void dashboard(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.DASHBOARD);
	}

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	private void saveContract(ActionEvent event) {
//         if(ContractID.getText() == null || ContractID.getText() == "") {
        	 Contract contract = new Contract();
        	 contract.setPurpose(Purpose.getSelectionModel().getSelectedItem());
        	 contract.setShowname(ShowName.getText());
        	 contract.setShowdetail(ShowDetail.getText());
        	 contract.setShowtime(ShowTime.getText());
        	 contract.setCustomerName(CustomerName.getSelectionModel().getSelectedItem());
        	 contract.setRepname(RepName.getText());
        	 contract.setRepemail(RepEmail.getText());
        	 contract.setRepmobile(RepMobile.getText());
        	 contract.setShowdate(ShowDate.getValue());
        	 contract.setSlot(Slot.getSelectionModel().getSelectedItem());
        	 contract.setServices(Services.getSelectionModel().getSelectedItem());
        	 contract.setCharges(Charges.getText());
        	 contract.setTaxamount(TaxAmount.getText());
        	 contract.setTotal(Total.getText());
        	 
        	 contractService.save(contract);

 			Alert alert = new Alert(AlertType.INFORMATION);
 			alert.setTitle("Contract");
 			alert.setHeaderText(null);
 			alert.setContentText("Contract of " + CustomerName.getSelectionModel().getSelectedItem() + "  has been created of Amount: "+Total.getText());
 			alert.showAndWait();
//         }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}
