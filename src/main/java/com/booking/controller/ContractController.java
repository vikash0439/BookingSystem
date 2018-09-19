package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Booking;
import com.booking.bean.Contract;
import com.booking.bean.Performance;
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
import javafx.event.EventHandler;
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
	@FXML
	private VBox showVBox;
	@FXML
	private Label cost;
	@FXML
	private TextField charges;
	
	/* Contract Table */
	@FXML
	private DatePicker BookingDate;
	@FXML
	private ComboBox<String> Purpose;
	@FXML
	private ComboBox<String> Customer;
	/* Booking table */
	@FXML
	private DatePicker ServiceDate;
	@FXML
	private ComboBox<String> ServiceName;
	@FXML
	private ComboBox<String> Slot;
	@FXML
	private TextField ServiceCost;

	/* Performance Table */
	@FXML
	private TextField ShowName;
	@FXML
	private DatePicker ShowDate;
	@FXML
	private TextField ShowTime;
	@FXML
	private TextField ShowDetails;

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
	
	public void addMore() {
		System.out.println("Add more button ");
		HBox serviceHBox = new HBox();
		serviceHBox.setSpacing(10);
		serviceVBox.setSpacing(10);

		TextField charges = new TextField();
		charges.setPromptText("Chargers");
		charges.setId("ServiceCost");
		
		ComboBox<String> slot = new ComboBox<String>();
		slot.setPromptText("Slot");
		slot.setId("Slot");
		slot.setItems(slotList);

		ComboBox<String> services = new ComboBox<String>();
		services.setPromptText("Services");
		services.setId("ServiceName");
		services.setItems(serviceList);
		services.setOnAction( new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String ser = services.getValue();
				Service service = serviceService.getDetail(ser);
				charges.setText(service.getServicecharges());				
			}
		});
		
		DatePicker date = new DatePicker();
		date.setId("ServiceDate");
		
		serviceHBox.getChildren().add(date);
		serviceHBox.getChildren().add(services);
		serviceHBox.getChildren().add(slot);
		serviceHBox.getChildren().add(charges);
		serviceVBox.getChildren().add(serviceHBox);

		cost.setText(charges.getText());
	}
	
	public void addMoreService() {
		HBox showHBox = new HBox();
		showHBox.getChildren().add(ShowName);
		showHBox.getChildren().add(ShowDate);
		showHBox.getChildren().add(ShowTime);
		showHBox.getChildren().add(ShowDetails);
		
		showVBox.getChildren().add(showHBox);
	
	}

	@FXML
	private void saveContract(ActionEvent event) {

		Contract contract = new Contract();
		contract.setBookingdate((String) BookingDate.getEditor().getText());
		contract.setPurpose(Purpose.getSelectionModel().getSelectedItem());
		
		Booking b = new Booking();
		b.setServicedate((String) ServiceDate.getEditor().getText());
		b.setServicecost(ServiceCost.getText());
        b.setContract(contract);
        
        System.out.println("From save contract :"+(String) ServiceDate.getEditor().getText());
        ObservableList<String> s = FXCollections.observableArrayList((String)ServiceName.getEditor().getText());
		for(String service : s) {
			System.out.println("From add more services: "+service);
		}
        
		List<Booking> booking = new ArrayList<Booking>();
		booking.add(b);

		Performance p = new Performance();
		p.setShowname(ShowName.getText());
		p.setShowtime(ShowTime.getText());
		p.setShowdetails(ShowDetails.getText());
		p.setContract(contract);

		List<Performance> performance = new ArrayList<Performance>();
		performance.add(p);
		
		contract.setPerformances(performance);
		contract.setBookings(booking);
		
		contractService.save(contract);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Contract");
		alert.setHeaderText(null);
		alert.setContentText("Contract of   has been created of Amount: ");
		alert.showAndWait();

	}

	public void serviceDetail() {
		
		String ser = ServiceName.getValue();
		Service service = serviceService.getDetail(ser);
		ServiceCost.setText(service.getServicecharges());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		purposeList.clear();
		purposeList.addAll(purposeService.findPurpose());
		Purpose.setItems(purposeList);
		
		serviceList.clear();
		serviceList.addAll(serviceService.findName());
		ServiceName.setItems(serviceList);
		
		customerList.clear();
		customerList.addAll(customerService.findName());
		Customer.setItems(customerList);
		
		slotList.clear();
		slotList.addAll(slotService.findSlot());
		Slot.setItems(slotList);
	}
}
