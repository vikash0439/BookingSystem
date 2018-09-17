package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Contract;
import com.booking.bean.Rep;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@Controller
public class ContractController implements Initializable {

	@FXML
	private Label ContractID;
	
	@FXML
	private HBox serviceHBox;
	@FXML
	private VBox serviceVBox;
	
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
        	
        	
        	 
        	 contractService.save(contract);

 			Alert alert = new Alert(AlertType.INFORMATION);
 			alert.setTitle("Contract");
 			alert.setHeaderText(null);
 			alert.setContentText("Contract of   has been created of Amount: ");
 			alert.showAndWait();
//         }
	}
	
	public void serviceDetail() {
		
	
	}
	
	public void addMore() {
		System.out.println("Add more button ");
		HBox serviceHBox2 = new HBox();
		TextField charges = new TextField();
		ComboBox<String> services = new ComboBox<String>();
		serviceHBox2.getChildren().add(services);
		serviceHBox2.getChildren().add(charges);
		serviceVBox.getChildren().add(serviceHBox2);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		
	}
}
