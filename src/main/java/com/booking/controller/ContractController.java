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
import com.booking.bean.Customer;
import com.booking.bean.Performance;
import com.booking.bean.Service;
import com.booking.config.StageManager;
import com.booking.service.BookingService;
import com.booking.service.ContractService;
import com.booking.service.CustomerService;
import com.booking.service.PerformanceService;
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
	private HBox serviceHBox;
	@FXML
	private VBox serviceVBox;
	@FXML
	private VBox showVBox;
	@FXML
	private TextField charges;
	
	
	/* Contract Table */
	@FXML
	private Label ContractID;
	@FXML
	private DatePicker BookingDate;
	@FXML
	private ComboBox<String> Purpose;
	@FXML
	private ComboBox<String> CustomerName;
	@FXML
	private ComboBox<String> paymentstatus;
	@FXML
	private Label baseprice;
	@FXML
	private Label taxamount;
	@FXML
	private Label pact;
	
	/* Booking table */
	@FXML
	private DatePicker ServiceDate;
	@FXML
	private TextField ServiceTime;
	@FXML
	private ComboBox<String> Slot;
	@FXML
	private TextField ServiceCost;
	@FXML
	private TextField ServiceUsed;
	@FXML
	private ComboBox<String> ServiceName;

	/* Performance Table */
	@FXML
	private TextField ShowName;
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
	@Autowired
	private BookingService bookingService;
	@Autowired
	private PerformanceService performanceService;
	

	private ObservableList<String> purposeList = FXCollections.observableArrayList();
	private ObservableList<String> customerList = FXCollections.observableArrayList();
	private ObservableList<String> slotList = FXCollections.observableArrayList();
	private ObservableList<String> serviceList = FXCollections.observableArrayList();
	private ObservableList<String> paymentStatusList = FXCollections.observableArrayList("Advanced" , "Final Payment");
    

	@FXML
	private void logout(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	private void dashboard(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.DASHBOARD);
	}
	@FXML
    private void customer(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.CUSTOMER);    	
    }	
	@FXML
	public void service(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.SERVICE); 		
	}	
	@FXML
	public void tax(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.TAX); 		
	}	
	@FXML
	public void contract(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.CONTRACT);	
	}	
	@FXML
	public void users(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.USER);		
	}
		
	@FXML
	public void reserve(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.RESERVE);		
	}
	
	@FXML
	public void receipt(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.RECEIPT);		
	}
	@FXML
	public void invoice(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.INVOICE);		
	}
	@FXML
	public void slot(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.SLOT);		
	}
	@FXML
	public void purpose(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.PURPOSE);		
	}

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}
	
	
		
	public void slotChange() {
		Slot.getSelectionModel().getSelectedItem();
		String time = slotService.slotTiming(Slot.getSelectionModel().getSelectedItem());
		ServiceTime.setText(time);
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
		slot.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub	

			}
			
		});

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
				baseprice.setText(service.getServicecharges());
			}
			
		});
		
		DatePicker date = new DatePicker();
		date.setId("ServiceDate");
		
		TextField ServiceTime = new TextField();
		ServiceTime.setPromptText("Time");
		
		serviceHBox.getChildren().add(date);
		serviceHBox.getChildren().add(ServiceTime);
		serviceHBox.getChildren().add(services);
		serviceHBox.getChildren().add(slot);
		serviceHBox.getChildren().add(charges);
		serviceVBox.getChildren().add(serviceHBox);

		baseprice.setText(charges.getText());
	}
	
	public void addMoreShow() {
		HBox showHBox = new HBox();
		serviceVBox.setSpacing(10);
		showHBox.setSpacing(10);
		
		TextField showName = new TextField();
		showName.setPromptText("Show name");
		
		DatePicker ShowDate = new DatePicker();
		ShowDate.setPromptText("Show Date");
		
		TextField ShowTime = new TextField();
		ShowTime.setPromptText("Show Time");
		
		TextField ShowDetails = new TextField();
		ShowDetails.setPromptText("Show Details");
		
		showHBox.getChildren().addAll(showName, ShowDate, ShowTime, ShowDetails);
		
		showVBox.getChildren().add(showHBox);
	
	}

	@FXML
	private void saveContract(ActionEvent event) {

		Contract contract = new Contract();
		contract.setBookingdate((String) BookingDate.getEditor().getText());
		contract.setPurpose(Purpose.getSelectionModel().getSelectedItem());
		contract.setBaseprice(baseprice.getText());
		contract.setTaxamount(taxamount.getText());
		contract.setPact(pact.getText());
		contract.setPaymentstatus(paymentstatus.getSelectionModel().getSelectedItem());
		
		Customer customer = customerService.findCustomer(CustomerName.getSelectionModel().getSelectedItem());
		contract.setCustomer(customer);
		
		
		Booking b = new Booking();
		b.setServicedate((String) ServiceDate.getEditor().getText());
		b.setServicecost(baseprice.getText());
		b.setServicetime(ServiceTime.getText());
		b.setSlot(Slot.getSelectionModel().getSelectedItem());
//		b.setServiceused(ServiceUsed.getText());
        b.setContract(contract);

		List<Booking> booking = new ArrayList<Booking>();
		booking.add(b);
		
		Performance p = new Performance();
		p.setShowname(ShowName.getText());
		p.setShowtime(ShowTime.getText());
		p.setShowdetails(ShowDetails.getText());
		p.setContract(contract);

		List<Performance> performance = new ArrayList<Performance>();
		performance.add(p);
		
		
		
		
		contractService.save(contract);
		bookingService.save(b);
		performanceService.save(p);
		

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
		baseprice.setText(service.getServicecharges());
		baseprice.setText(service.getServicecharges());
		Long bp = Long.parseLong(service.getServicecharges());
		Double ta = 0.18 * bp;
		Double t = bp + ta;
		taxamount.setText(String.valueOf(ta));
		pact.setText(String.valueOf(t));
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
		CustomerName.setItems(customerList);
		
		slotList.clear();
		slotList.addAll(slotService.findSlot());
		Slot.setItems(slotList);
	
		paymentstatus.setItems(paymentStatusList);
	}
}
