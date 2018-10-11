package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private HBox serviceHBox4;
	@FXML
	private HBox serviceHBox5;
	@FXML
	private HBox serviceHBox6;
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
	@FXML
	private DatePicker ServiceDate4;
	@FXML
	private TextField ServiceTime4;
	@FXML
	private ComboBox<String> Slot4;
	@FXML
	private TextField ServiceCost4;
	@FXML
	private TextField ServiceUsed4;
	@FXML
	private ComboBox<String> ServiceName4;
	@FXML
	private DatePicker ServiceDate5;
	@FXML
	private TextField ServiceTime5;
	@FXML
	private ComboBox<String> Slot5;
	@FXML
	private TextField ServiceCost5;
	@FXML
	private TextField ServiceUsed5;
	@FXML
	private ComboBox<String> ServiceName5;
	@FXML
	private DatePicker ServiceDate6;
	@FXML
	private TextField ServiceTime6;
	@FXML
	private ComboBox<String> Slot6;
	@FXML
	private TextField ServiceCost6;
	@FXML
	private TextField ServiceUsed6;
	@FXML
	private ComboBox<String> ServiceName6;

	/* Performance Table */
	@FXML
	private TextField ShowName;
	@FXML
	private TextField ShowTime;
	@FXML
	private TextField ShowDetails;
	@FXML
	private TextField ShowName2;
	@FXML
	private TextField ShowTime2;
	@FXML
	private TextField ShowDetails2;
	@FXML
	private TextField ShowName3;
	@FXML
	private TextField ShowTime3;
	@FXML
	private TextField ShowDetails3;

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

	Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		public DateCell call(final DatePicker datePicker) {
			return new DateCell() {
				@Override
				public void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);
					List<String> datefromBooking = bookingService.getServiceDate();
					List<Date> dateList = new ArrayList<Date>();
					List<String> FinaldateList = new ArrayList<String>();

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

					for (String dateString : datefromBooking) {
						try {
							dateList.add(simpleDateFormat.parse(dateString));
						} catch (ParseException e) {
							System.out.println("Date Exception");
							e.printStackTrace();
						}
					}
					for (Date date : dateList) {
						FinaldateList.add(simpleDateFormat2.format(date));
					}
					if (!empty) {
						if (FinaldateList.contains(item)) {
							setStyle("-fx-background-color: #99e699;");
						}
					}
				}
			};
		}
	};

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

		Slot4.getSelectionModel().getSelectedItem();
		String time4 = slotService.slotTiming(Slot4.getSelectionModel().getSelectedItem());
		ServiceTime4.setText(time4);

		Slot5.getSelectionModel().getSelectedItem();
		String time5 = slotService.slotTiming(Slot5.getSelectionModel().getSelectedItem());
		ServiceTime3.setText(time5);

		Slot6.getSelectionModel().getSelectedItem();
		String time6 = slotService.slotTiming(Slot6.getSelectionModel().getSelectedItem());
		ServiceTime6.setText(time6);

	}

	public void addMore() {
		System.out.println("Add more button ");
		serviceHBox2.setVisible(true);
		serviceHBox3.setVisible(true);
		serviceHBox4.setVisible(true);
		serviceHBox5.setVisible(true);
		serviceHBox6.setVisible(true);
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
		if (ServiceName2.getValue() != null && ServiceName2.getValue() != "") {

			b2.setServicedate((String) ServiceDate2.getEditor().getText());
			b2.setServicename(ServiceName2.getValue());
			b2.setServicetime(ServiceTime2.getText());
			b2.setSlot(Slot2.getSelectionModel().getSelectedItem());
			b2.setServicecost(ServiceCost2.getText());
			// b2.setServiceused(ServiceUsed.getText());
			b2.setContract(contract);
		}

		Booking b3 = new Booking();
		if (ServiceName3.getValue() != null && ServiceName3.getValue() != "") {
			b3.setServicedate((String) ServiceDate3.getEditor().getText());
			b3.setServicename(ServiceName3.getValue());
			b3.setServicetime(ServiceTime3.getText());
			b3.setSlot(Slot3.getSelectionModel().getSelectedItem());
			b3.setServicecost(ServiceCost3.getText());
			// b2.setServiceused(ServiceUsed.getText());
			b3.setContract(contract);
		}

		Booking b4 = new Booking();
		if (ServiceName4.getValue() != null && ServiceName4.getValue() != "") {
			b4.setServicedate((String) ServiceDate4.getEditor().getText());
			b4.setServicename(ServiceName4.getValue());
			b4.setServicetime(ServiceTime4.getText());
			b4.setSlot(Slot4.getSelectionModel().getSelectedItem());
			b3.setServicecost(ServiceCost4.getText());
			// b2.setServiceused(ServiceUsed.getText());
			b3.setContract(contract);
		}

		Booking b5 = new Booking();
		if (ServiceName5.getValue() != null && ServiceName5.getValue() != "") {
			b5.setServicedate((String) ServiceDate5.getEditor().getText());
			b5.setServicename(ServiceName5.getValue());
			b5.setServicetime(ServiceTime5.getText());
			b5.setSlot(Slot5.getSelectionModel().getSelectedItem());
			b5.setServicecost(ServiceCost5.getText());
			// b2.setServiceused(ServiceUsed.getText());
			b5.setContract(contract);
		}

		Booking b6 = new Booking();
		if (ServiceName6.getValue() != null && ServiceName6.getValue() != "") {
			b6.setServicedate((String) ServiceDate6.getEditor().getText());
			b6.setServicename(ServiceName6.getValue());
			b6.setServicetime(ServiceTime6.getText());
			b6.setSlot(Slot3.getSelectionModel().getSelectedItem());
			b6.setServicecost(ServiceCost6.getText());
			// b2.setServiceused(ServiceUsed.getText());
			b6.setContract(contract);
		}

		// List<Booking> booking = new ArrayList<Booking>();
		// booking.add(b);
		// booking.add(b2);

		Performance p = new Performance();
		if (ShowName.getText() != null && ShowName.getText() != "") {
			p.setShowname(ShowName.getText());
			p.setShowtime(ShowTime.getText());
			p.setShowdetails(ShowDetails.getText());
			p.setContract(contract);
		}

		Performance p2 = new Performance();
		if (ShowName2.getText() != null && ShowName2.getText() != "") {
			p2.setShowname(ShowName2.getText());
			p2.setShowtime(ShowTime2.getText());
			p2.setShowdetails(ShowDetails2.getText());
			p2.setContract(contract);
		}

		Performance p3 = new Performance();
		if (ShowName3.getText() != null && ShowName3.getText() != "") {
			p3.setShowname(ShowName3.getText());
			p3.setShowtime(ShowTime3.getText());
			p3.setShowdetails(ShowDetails3.getText());
			p3.setContract(contract);
		}

		List<Performance> performance = new ArrayList<Performance>();
		performance.add(p);

		contractService.save(contract);
		bookingService.save(b);

		if (ServiceName2.getValue() != null && ServiceName2.getValue() != "") {
			bookingService.save(b2);
		}
		if (ServiceName3.getValue() != null && ServiceName3.getValue() != "") {
			bookingService.save(b3);
		}
		if (ServiceName4.getValue() != null && ServiceName4.getValue() != "") {
			bookingService.save(b4);
		}
		if (ServiceName5.getValue() != null && ServiceName5.getValue() != "") {
			bookingService.save(b5);
		}
		if (ServiceName6.getValue() != null && ServiceName6.getValue() != "") {
			bookingService.save(b6);
		}
		if (ShowName.getText() != null && ShowName.getText() != "") {
			performanceService.save(p);
		}
		if (ShowName2.getText() != null && ShowName2.getText() != "") {
			performanceService.save(p2);
		}
		if (ShowName3.getText() != null && ShowName3.getText() != "") {
			performanceService.save(p3);
		}
		
		

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Contract");
		alert.setHeaderText(null);
		alert.setContentText("Contract of  " + CustomerName.getSelectionModel().getSelectedItem()
				+ " has been created of Amount: " + pact.getText() + ". Proceed to Payment..");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				receipt(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// ... user chose CANCEL or closed the dialog
		}

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

		BookingDate.setValue(LocalDate.now());

		paymentstatus.setItems(paymentStatusList);

		ServiceDate.setDayCellFactory(dayCellFactory);
	}
}
