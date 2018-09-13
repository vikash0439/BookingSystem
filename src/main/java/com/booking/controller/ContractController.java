package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Contract;
import com.booking.bean.Service;
import com.booking.config.StageManager;
import com.booking.service.ContractService;
import com.booking.service.CustomerService;
import com.booking.service.PurposeService;
import com.booking.service.ServiceService;
import com.booking.service.SlotService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	@Autowired
	private PurposeService purposeService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SlotService slotService;
	@Autowired
	private ServiceService serviceService;
	
	private ObservableList<String> purposeList = FXCollections.observableArrayList();
	private ObservableList<String> customerList = FXCollections.observableArrayList();
	private ObservableList<String> slotList = FXCollections.observableArrayList();
	private ObservableList<String> serviceList = FXCollections.observableArrayList();
	

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
        	 contract.setCustomername(CustomerName.getSelectionModel().getSelectedItem());
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
	
	public void serviceDetail() {
		String ser = Services.getValue();
		Service service = serviceService.getDetail(ser);
		Charges.setText(service.getServicecharges());
		TaxAmount.setText("200");
		Total.setText("200000");
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		purposeList.clear();
		purposeList.addAll(purposeService.findPurpose());
		Purpose.setItems(purposeList);
		customerList.clear();
		customerList.addAll(customerService.findName());
		CustomerName.setItems(customerList);
		slotList.clear();
		slotList.addAll(slotService.findSlot());
		Slot.setItems(slotList);
		serviceList.clear();
		serviceList.addAll(serviceService.findName());
		Services.setItems(serviceList);
		
	}
}
