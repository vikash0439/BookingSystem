package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.MonthDay;
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
import com.booking.service.RepService;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

@Controller
public class ContractController implements Initializable {

	@FXML
	private HBox serviceHBox;
	@FXML
	private HBox serviceHBox2;
	@FXML
	private HBox serviceHBox3;
	@FXML
	private HBox ShowHBox2;
	@FXML
	private HBox ShowHBox3;
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
	@FXML
	private ComboBox<String> repName;

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
	@FXML
	private DatePicker ServiceDate2;
	@FXML
	private TextField ServiceTime2;
	@FXML
	private ComboBox<String> Slot2;
	@FXML
	private TextField ServiceCost2;
	@FXML
	private TextField ServiceUsed2;
	@FXML
	private ComboBox<String> ServiceName2;
	@FXML
	private DatePicker ServiceDate3;
	@FXML
	private TextField ServiceTime3;
	@FXML
	private ComboBox<String> Slot3;
	@FXML
	private TextField ServiceCost3;
	@FXML
	private TextField ServiceUsed3;
	@FXML
	private ComboBox<String> ServiceName3;

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
	@Autowired
	private RepService repService;

	private ObservableList<String> purposeList = FXCollections.observableArrayList();
	private ObservableList<String> customerList = FXCollections.observableArrayList();
	private ObservableList<String> slotList = FXCollections.observableArrayList();
	private ObservableList<String> serviceList = FXCollections.observableArrayList();
	private ObservableList<String> repList = FXCollections.observableArrayList();
	private ObservableList<String> paymentStatusList = FXCollections.observableArrayList("Advanced", "Final Payment");
	

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

	public void getAllRep() {
		String cname = CustomerName.getSelectionModel().getSelectedItem();
		Customer customer = customerService.findCustomer(cname);

		repList.clear();
		repList.addAll(repService.getRepnamebyCustomerid(customer));
		repName.setItems(repList);

		// List<Rep> lrep = new ArrayList<>();
		// lrep = customer.getRep();
		// System.out.print("Get All Rep By customer Name: ");
		// for(Rep r : lrep){
		// System.out.println(r);
		// }
		// repList.clear();
		// repList.addAll(repService.getAllRepName());
		// repName.setItems(repList);

	}

	public void slotChange() {
		Slot.getSelectionModel().getSelectedItem();
		String time = slotService.slotTiming(Slot.getSelectionModel().getSelectedItem());
		ServiceTime.setText(time);

		Slot2.getSelectionModel().getSelectedItem();
		String time2 = slotService.slotTiming(Slot2.getSelectionModel().getSelectedItem());
		ServiceTime2.setText(time2);

		Slot3.getSelectionModel().getSelectedItem();
		String time3 = slotService.slotTiming(Slot3.getSelectionModel().getSelectedItem());
		ServiceTime3.setText(time3);
	}

	public void addMore() {
		System.out.println("Add more button ");
		serviceHBox2.setVisible(true);
		serviceHBox3.setVisible(true);
	}

	public void addMoreShow() {
		ShowHBox2.setVisible(true);
		ShowHBox3.setVisible(true);
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
		b.setServicename(ServiceName.getValue());
		b.setServicetime(ServiceTime.getText());
		b.setSlot(Slot.getSelectionModel().getSelectedItem());
		b.setServicecost(ServiceCost.getText());
		// b.setServiceused(ServiceUsed.getText());
		b.setContract(contract);

		Booking b2 = new Booking();
		b2.setServicedate((String) ServiceDate2.getEditor().getText());
		b2.setServicename(ServiceName2.getValue());
		b2.setServicetime(ServiceTime2.getText());
		b2.setSlot(Slot2.getSelectionModel().getSelectedItem());
		// b2.setServiceused(ServiceUsed.getText());
		b2.setContract(contract);

		List<Booking> booking = new ArrayList<Booking>();
		booking.add(b);
		booking.add(b2);

		Performance p = new Performance();
		p.setShowname(ShowName.getText());
		p.setShowtime(ShowTime.getText());
		p.setShowdetails(ShowDetails.getText());
		p.setContract(contract);

		List<Performance> performance = new ArrayList<Performance>();
		performance.add(p);

		contractService.save(contract);
		bookingService.save(b);
		bookingService.save(b2);
		performanceService.save(p);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Contract");
		alert.setHeaderText(null);
		alert.setContentText("Contract of  " + CustomerName.getSelectionModel().getSelectedItem()
				+ " has been created of Amount: " + pact.getText());
		alert.showAndWait();

	}

	public void serviceDetail() {

		String ser = ServiceName.getValue();
		Service service = serviceService.getDetail(ser);
		ServiceCost.setText(service.getServicecharges());


		Long bp = Long.parseLong(ServiceCost.getText());
		baseprice.setText(bp.toString());

		Double ta = 0.18 * bp;
		Double t = bp + ta;
		taxamount.setText(String.valueOf(ta));
		pact.setText(String.valueOf(t));
	}

	public void serviceDetail2() {


		String ser2 = ServiceName2.getValue();
		Service service2 = serviceService.getDetail(ser2);
		ServiceCost2.setText(service2.getServicecharges());

		Long bp = Long.parseLong(ServiceCost.getText()) + Long.parseLong(ServiceCost2.getText());
		baseprice.setText(bp.toString());

		Double ta = 0.18 * bp;
		Double t = bp + ta;
		taxamount.setText(String.valueOf(ta));
		pact.setText(String.valueOf(t));
	}

	public void serviceDetail3() {

		String ser3 = ServiceName3.getValue();
		Service service3 = serviceService.getDetail(ser3);
		ServiceCost3.setText(service3.getServicecharges());

		Long bp = Long.parseLong(ServiceCost.getText()) + Long.parseLong(ServiceCost2.getText())
				+ Long.parseLong(ServiceCost3.getText());
		baseprice.setText(bp.toString());

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
		ServiceName2.setItems(serviceList);
		ServiceName3.setItems(serviceList);

		customerList.clear();
		customerList.addAll(customerService.findName());
		CustomerName.setItems(customerList);

		slotList.clear();
		slotList.addAll(slotService.findSlot());
		Slot.setItems(slotList);
		Slot2.setItems(slotList);
		Slot3.setItems(slotList);

		paymentstatus.setItems(paymentStatusList);
	}
	 
	 
}
