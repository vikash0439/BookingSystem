package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.booking.service.RepService;
import com.booking.service.ServiceService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Callback;

@Controller
public class ContractController implements Initializable{

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
	private VBox serviceVBox;
	@FXML
	private VBox showVBox;
	@FXML
	private TextField charges;

	/* Contract Table */
	@FXML
	private Label ContractID;
	@FXML
	private DatePicker ContractDate;
	@FXML
	private ComboBox<String> Purpose;
	@FXML
	private ComboBox<String> CustomerName;
	@FXML
	private Label baseprice;
	@FXML
	private Label taxamount;
	@FXML
	private Label pact;
	@FXML
	private ComboBox<String> repName;
	@FXML
	private ToggleGroup noc;
	@FXML
	private RadioButton NocYes;
	@FXML
	private RadioButton NocNo;

	/* Booking table */
	@FXML private DatePicker BookingDate;
	@FXML private TextField VenueName;
	@FXML private TextField SlotName;
	@FXML private TextField Price;
	@FXML private ComboBox<String> ServiceName;

	@FXML private DatePicker BookingDate2;
	@FXML private TextField VenueName2;
	@FXML private TextField SlotName2;
	@FXML private TextField Price2;
	@FXML private ComboBox<String> ServiceName2;
	
	@FXML private DatePicker BookingDate3;
	@FXML private TextField VenueName3;
	@FXML private TextField SlotName3;
	@FXML private TextField Price3;
	@FXML private ComboBox<String> ServiceName3;
	
	@FXML private DatePicker BookingDate4;
	@FXML private TextField VenueName4;
	@FXML private TextField SlotName4;
	@FXML private TextField Price4;
	@FXML private ComboBox<String> ServiceName4;
	
	@FXML private DatePicker BookingDate5;
	@FXML private TextField VenueName5;
	@FXML private TextField SlotName5;
	@FXML private TextField Price5;
	@FXML private ComboBox<String> ServiceName5;
	
	@FXML private DatePicker BookingDate6;
	@FXML private TextField VenueName6;
	@FXML private TextField SlotName6;
	@FXML private TextField Price6;
	@FXML private ComboBox<String> ServiceName6;

	/* Performance Table */
	@FXML
	private TextField ShowName;
	@FXML
	private TextField ShowTime;
	@FXML
	private TextField ShowDetails;
	@FXML
	private TextField ShowDate;

	@FXML
	HBox testDates;
	@FXML
	private TableView<Booking> services;
	@FXML
	private TableColumn<Booking, String> colBookingdate;
	@FXML
	private TableColumn<Booking, String> colServicename;
	@FXML
	private TableColumn<Booking, String> colPrice;
	@FXML
	private TableColumn<Booking, String> colSlotname;
	@FXML
	private TableColumn<Booking, String> colVenuename;
	@FXML private TableColumn<Booking, Boolean> colDelete;
	
	@FXML Button OkNextDay;
	
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired
	private ContractService contractService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private PerformanceService performanceService;
	@Autowired
	private RepService repService;
	
	/* Variables */
	final String ONE_BOOKING = "-fx-background-color: #FFFF00;";
	final String TWO_BOOKING = "-fx-background-color: #008000;";
	final String MORE_BOOKING ="-fx-background-color: #FF0000;";
	final int COUNT_DATES= 0; 
	static int i = 0;

	private ObservableList<String> purposeList = FXCollections.observableArrayList();
	private ObservableList<String> customerList = FXCollections.observableArrayList();
	private ObservableList<String> slotList = FXCollections.observableArrayList();
	private ObservableList<String> venueList = FXCollections.observableArrayList();
	private ObservableList<String> serviceList = FXCollections.observableArrayList();
	private ObservableList<String> repList = FXCollections.observableArrayList();
	private ObservableList<String> paymentStatusList = FXCollections.observableArrayList("Advanced", "Final Payment");
	ObservableList<Booking> data = FXCollections.observableArrayList();

	Contract contract = new Contract();
	
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
	public void statecode(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.STATECODE);
	}

	@FXML
	public void allcontract(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.ALLCONTRACT);
	}

	@FXML
	public void reports(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.REPORTS);
	}
	@FXML
	public void venue(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.VENUE);
	}

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}

	ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();
	ListView<LocalDate> dateList = new ListView<>(selectedDates);
	ObservableList<LocalDate> servicedates = dateList.getItems();
	String pattern = "dd-MM-yyyy";
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
	DatePicker datePicker = new DatePicker();

	Button addButton = new Button("+Add dates");
	Button removeButton = new Button("-Remove dates");
	Button nextButton = new Button("Same Services");
	Button nextButton2 = new Button("Different Services");
	Button addTable = new Button("Add Services");
	
	public void nextProceed(){
		System.out.println("Proceed ");
	}

	private void createScene(){

		ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();
		ListView<LocalDate> dateList = new ListView<>(selectedDates);
		String pattern = "dd-MM-yyyy";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

		datePicker.setShowWeekNumbers(true);
		datePicker.setOnAction(event -> System.out.println("Selected date: " + datePicker.getValue()));
		datePicker.setPromptText(pattern);
		/*
		 * datePicker.setConverter(new StringConverter<LocalDate>() {
		 * 
		 * @Override public String toString(LocalDate date) { return (date == null) ? ""
		 * : dateFormatter.format(date); }
		 * 
		 * @Override public LocalDate fromString(String string) { return ((string ==
		 * null) || string.isEmpty()) ? null : LocalDate.parse(string, dateFormatter); }
		 * });
		 */
		datePicker.setOnAction(event -> selectedDates.add(datePicker.getValue()));

		datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(DatePicker param) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						boolean alreadySelected = selectedDates.contains(item);
						setDisable(alreadySelected);
						setStyle(alreadySelected ? "-fx-background-color: #09a30f;" : "");
					}
				};
			}
		});

		// TODO: Hide text field of the date picker combo. Show dropdown directly on
		// clicking "+" button.
		// TODO: Keep dropdown of the date picker combo open until intentionally
		// clicking some other where.

		dateList.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.DELETE) {
				removeSelectedDates(selectedDates, dateList);
			}
		});
		dateList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		removeButton.disableProperty().bind(dateList.getSelectionModel().selectedItemProperty().isNull());
		addButton.setOnAction(event -> {

			Popup popup = new Popup();
			popup.getContent().add(datePicker);
			popup.setAutoHide(true);
			Window window = addButton.getScene().getWindow();
			Bounds bounds = addButton.localToScene(addButton.getBoundsInLocal());
			double x = window.getX() + bounds.getMinX();
			double y = window.getY() + bounds.getMinY() + bounds.getHeight() + 5;
			popup.show(addButton, x, y);
			datePicker.show();
		});
		removeButton.setOnAction(event -> removeSelectedDates(selectedDates, dateList));
		// testDates = new HBox(text, addButton, removeButton);
	}

	private static boolean removeSelectedDates(ObservableList<LocalDate> selectedDates, ListView<LocalDate> dateList) {
		return selectedDates.removeAll(dateList.getSelectionModel().getSelectedItems());
	}

	

	public void getAllRep() {
		String cname = CustomerName.getSelectionModel().getSelectedItem();
		Customer customer = customerService.findCustomer(cname);
		repList.clear();
		repList.addAll(repService.getRepnamebyCustomerid(customer));
		repName.setItems(repList);
	}

	public void MouseDragged(MouseEvent arg0) {
		System.out.println("In mouse dragged box");

	}

	public void addMore() {
		System.out.println("Add more button ");
		serviceHBox2.setVisible(true);
		serviceHBox3.setVisible(true);
		serviceHBox4.setVisible(true);
		serviceHBox5.setVisible(true);
		serviceHBox6.setVisible(true);
	}


	public void serviceDetail() {

		String ser = ServiceName.getValue();
		Service service = serviceService.getDetail(ser);
		Price.setText(service.getPrice());
		VenueName.setText(service.getVenue());
		SlotName.setText(service.getSlot());

	}

	public void serviceDetail2() {

		String ser2 = ServiceName2.getValue();
		Service service2 = serviceService.getDetail(ser2);
		Price2.setText(service2.getPrice());
		VenueName2.setText(service2.getVenue());
		SlotName2.setText(service2.getSlot());
	}

	public void serviceDetail3() {

		String ser3 = ServiceName3.getValue();
		Service service3 = serviceService.getDetail(ser3);
		Price3.setText(service3.getPrice());
		VenueName3.setText(service3.getVenue());
		SlotName3.setText(service3.getSlot());
	}

	public void serviceDetail4() {

		String ser4 = ServiceName4.getValue();
		Service service4 = serviceService.getDetail(ser4);
		Price4.setText(service4.getPrice());
		VenueName4.setText(service4.getVenue());
		SlotName4.setText(service4.getSlot());
	}

	public void serviceDetail5() {

		String ser5 = ServiceName5.getValue();
		Service service5 = serviceService.getDetail(ser5);
		Price5.setText(service5.getPrice());
		VenueName5.setText(service5.getVenue());
		SlotName5.setText(service5.getSlot());
	}

	public void serviceDetail6() {

		String ser6 = ServiceName6.getValue();
		Service service6 = serviceService.getDetail(ser6);
		Price6.setText(service6.getPrice());
		VenueName6.setText(service6.getVenue());
		SlotName6.setText(service6.getSlot());
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		ShowDetails.setPrefHeight(100);

		serviceList.clear();
		serviceList.addAll(serviceService.findName());
		ServiceName.setItems(serviceList);
		ServiceName2.setItems(serviceList);
		ServiceName3.setItems(serviceList);
		ServiceName4.setItems(serviceList);
		ServiceName5.setItems(serviceList);
		ServiceName6.setItems(serviceList);

		customerList.clear();
		customerList.addAll(customerService.findName());
		CustomerName.setItems(customerList);


		dateList.getItems().clear();
		data.clear();

		ContractDate.setValue(LocalDate.now());

		testDates.getChildren().addAll(addButton, removeButton, dateList, nextButton, nextButton2);
		testDates.setSpacing(15);

		datePicker.setShowWeekNumbers(true);
		datePicker.setOnAction(event -> System.out.println("Selected date: " + datePicker.getValue()));
		datePicker.setPromptText(pattern);
		/*
		 * datePicker.setConverter(new StringConverter<LocalDate>() {
		 * 
		 * @Override public String toString(LocalDate date) { return (date == null) ? ""
		 * : dateFormatter.format(date); }
		 * 
		 * @Override public LocalDate fromString(String string) { return ((string ==
		 * null) || string.isEmpty()) ? null : LocalDate.parse(string, dateFormatter); }
		 * });
		 */
		datePicker.setOnAction(event -> selectedDates.add(datePicker.getValue()));

		datePicker.setDayCellFactory(dateColorFactory);

		// TODO: Hide text field of the date picker combo. Show dropdown directly on
		// clicking "+" button.
		// TODO: Keep dropdown of the date picker combo open until intentionally
		// clicking some other where.

		dateList.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.DELETE) {
				removeSelectedDates(selectedDates, dateList);
			}
		});
		dateList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		removeButton.disableProperty().bind(dateList.getSelectionModel().selectedItemProperty().isNull());

		addButton.setOnAction(event -> {
			Popup popup = new Popup();
			popup.getContent().add(datePicker);
			popup.setAutoHide(true);
			Window window = addButton.getScene().getWindow();
			Bounds bounds = addButton.localToScene(addButton.getBoundsInLocal());
			double x = window.getX() + bounds.getMinX();
			double y = window.getY() + bounds.getMinY() + bounds.getHeight() + 5;
			popup.show(addButton, x, y);
			datePicker.show();
			System.out.println("Selected dates are : " + dateList);
		});

		removeButton.setOnAction(event -> removeSelectedDates(selectedDates, dateList));

		nextButton.setOnAction(event -> {
			ObservableList<LocalDate> servicedates = dateList.getItems();
			for (LocalDate sdate : servicedates) {
				String d = convertDate(sdate);
				System.out.print("Booking Dates : ");
				System.out.println(d);

				BookingDate.setValue(sdate);
				BookingDate2.setValue(sdate);
				BookingDate3.setValue(sdate);
				BookingDate4.setValue(sdate);
				BookingDate5.setValue(sdate);
				BookingDate6.setValue(sdate);

				// b = new Booking(d, Slot.getSelectionModel().getSelectedItem(),
				// ServiceTime.getText(), ServiceName.getValue(), ServiceCost.getText());
				//addTable.setOnAction(addServicestoTableVIew());
				addServicestoTableVIew();
			}

		});
		
		nextButton2.setOnAction(event -> {
			ObservableList<LocalDate> servicedates = dateList.getItems();
			for (LocalDate sdate : servicedates) {
				String d = convertDate(sdate);
				System.out.print("Booking Dates : ");
				System.out.println(d);

				BookingDate.setValue(sdate);
				BookingDate2.setValue(sdate);
				BookingDate3.setValue(sdate);
				BookingDate4.setValue(sdate);
				BookingDate5.setValue(sdate);
				BookingDate6.setValue(sdate);
			    			
				addServicestoTableVIew2();
			}
		});		 
	}
	/* End of initialize */

	public EventHandler<ActionEvent> addServicestoTableVIew() {

		Booking b = new Booking();
		Booking b2 = new Booking();
		Booking b3 = new Booking();
		Booking b4 = new Booking();
		Booking b5 = new Booking();
		Booking b6 = new Booking();
		
		if (emptyValidation("Service ", ServiceName.getValue().isEmpty())) {

			if (ServiceName.getValue() != null && ServiceName.getValue() != "") {
				b.setBookingdates(convertDate(BookingDate.getValue()));
				b.setSlot(SlotName.getText());
				b.setVenue(VenueName.getText());
				b.setPrice(Price.getText());
				b.setService(ServiceName.getValue());
				// b2.setServiceused(ServiceUsed.getText());
				System.out.println("Contract value here : " +contract.toString());
				b.setContract(contract);
			}

			if (ServiceName2.getValue() != null && ServiceName2.getValue() != ""){

				b2.setBookingdates(convertDate(BookingDate2.getValue()));
				b2.setSlot(SlotName2.getText());
				b2.setVenue(VenueName2.getText());
				b2.setPrice(Price2.getText());
				b2.setService(ServiceName2.getValue());
				// b2.setServiceused(ServiceUsed.getText());
				b2.setContract(contract);
			}

			if (ServiceName3.getValue() != null && ServiceName3.getValue() != "") {
				b3.setBookingdates(convertDate(BookingDate3.getValue()));
				b3.setSlot(SlotName3.getText());
				b3.setVenue(VenueName3.getText());
				b3.setPrice(Price3.getText());
				b3.setService(ServiceName3.getValue());
				// b2.setServiceused(ServiceUsed.getText());
				b3.setContract(contract);
			}

			if (ServiceName4.getValue() != null && ServiceName4.getValue() != "") {
				b4.setBookingdates(convertDate(BookingDate4.getValue()));
				b4.setSlot(SlotName4.getText());
				b4.setVenue(VenueName4.getText());
				b4.setPrice(Price4.getText());
				b4.setService(ServiceName4.getValue());
				// b2.setServiceused(ServiceUsed.getText());
				b4.setContract(contract);
			}

			if (ServiceName5.getValue() != null && ServiceName5.getValue() != "") {
				b5.setBookingdates(convertDate(BookingDate5.getValue()));
				b5.setSlot(SlotName5.getText());
				b5.setVenue(VenueName5.getText());
				b5.setPrice(Price5.getText());
				b5.setService(ServiceName5.getValue());
				// b2.setServiceused(ServiceUsed.getText());
				b5.setContract(contract);
			}

			if (ServiceName6.getValue() != null && ServiceName6.getValue() != "") {
				b6.setBookingdates(convertDate(BookingDate6.getValue()));
				b6.setSlot(SlotName6.getText());
				b6.setVenue(VenueName6.getText());
				b6.setPrice(Price6.getText());
				b6.setService(ServiceName6.getValue());
				// b2.setServiceused(ServiceUsed.getText());
				b6.setContract(contract);
			}
		}

		if (ServiceName.getValue() != null && ServiceName.getValue() != "") {
			data.add(b);
		}
		if (ServiceName2.getValue() != null && ServiceName2.getValue() != "") {
			data.add(b2);
		}
		if (ServiceName3.getValue() != null && ServiceName3.getValue() != "") {
			data.add(b3);
		}
		if (ServiceName4.getValue() != null && ServiceName4.getValue() != "") {
			data.add(b4);
		}
		if (ServiceName5.getValue() != null && ServiceName5.getValue() != "") {
			data.add(b5);
		}
		if (ServiceName6.getValue() != null && ServiceName6.getValue() != "") {
			data.add(b6);
		}
		colBookingdate.setCellValueFactory(new PropertyValueFactory<>("bookingdates"));
		colSlotname.setCellValueFactory(new PropertyValueFactory<>("slot"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colServicename.setCellValueFactory(new PropertyValueFactory<>("service"));
		colVenuename.setCellValueFactory(new PropertyValueFactory<>("venue"));
		colDelete.setCellFactory(delete);
		
		services.setEditable(true);
		services.setItems(data);
		
//  	int total = services.getItems().stream().mapToInt(Integer::intValue).sum();
		int total = 0;
		
		System.out.println("Total Amount "+total);
		
		baseprice.setText(String.valueOf(total));

		Double ta = 0.18 * total;
		Double t = total + ta;
		taxamount.setText(String.valueOf(ta));
		pact.setText(String.valueOf(t));		
		return null;
	}
	
	
	public EventHandler<ActionEvent> addServicestoTableVIew2() {
		do {
			
			System.out.println("From addServicestoTableVIew2 method: " +servicedates.get(i));
			
			BookingDate.setValue(servicedates.get(i));
			BookingDate2.setValue(servicedates.get(i));
			BookingDate3.setValue(servicedates.get(i));
			BookingDate4.setValue(servicedates.get(i));
			BookingDate5.setValue(servicedates.get(i));
			BookingDate6.setValue(servicedates.get(i));
			
			Booking b = new Booking();
			Booking b2 = new Booking();
			Booking b3 = new Booking();
			Booking b4 = new Booking();
			Booking b5 = new Booking();
			Booking b6 = new Booking();
			
			if (emptyValidation("Service ", ServiceName.getValue().isEmpty())) {

				if (ServiceName.getValue() != null && ServiceName.getValue() != "") {
					b.setBookingdates(convertDate(BookingDate.getValue()));
					b.setSlot(SlotName.getText());
					b.setService(ServiceName.getValue());
					b.setVenue(VenueName.getText());
					b.setPrice(Price.getText());
					// b2.setServiceused(ServiceUsed.getText());
					System.out.println("Contract value here : " +contract.toString());
					b.setContract(contract);
				}

				if (ServiceName2.getValue() != null && ServiceName2.getValue() != "") {

					b2.setBookingdates(convertDate(BookingDate2.getValue()));
					b2.setSlot(SlotName2.getText());
					b2.setVenue(VenueName2.getText());
					b2.setPrice(Price2.getText());
					b2.setService(ServiceName2.getValue());
					// b2.setServiceused(ServiceUsed.getText());
					b2.setContract(contract);
				}

				if (ServiceName3.getValue() != null && ServiceName3.getValue() != "") {
					b3.setBookingdates(convertDate(BookingDate3.getValue()));
					b3.setSlot(SlotName3.getText());
					b3.setVenue(VenueName3.getText());
					b3.setPrice(Price3.getText());
					b3.setService(ServiceName3.getValue());
					// b2.setServiceused(ServiceUsed.getText());
					b3.setContract(contract);
				}

				if (ServiceName4.getValue() != null && ServiceName4.getValue() != "") {
					b4.setBookingdates(convertDate(BookingDate4.getValue()));
					b4.setSlot(SlotName4.getText());
					b4.setVenue(VenueName4.getText());
					b4.setPrice(Price4.getText());
					b4.setService(ServiceName4.getValue());
					// b2.setServiceused(ServiceUsed.getText());
					b4.setContract(contract);
				}

				if (ServiceName5.getValue() != null && ServiceName5.getValue() != "") {
					b5.setBookingdates(convertDate(BookingDate5.getValue()));
					b5.setSlot(SlotName5.getText());
					b5.setVenue(VenueName5.getText());
					b5.setPrice(Price5.getText());
					b5.setService(ServiceName5.getValue());
					// b2.setServiceused(ServiceUsed.getText());
					b5.setContract(contract);
				}

				if (ServiceName6.getValue() != null && ServiceName6.getValue() != "") {
					b6.setBookingdates(convertDate(BookingDate6.getValue()));
					b6.setSlot(SlotName6.getText());
					b6.setVenue(VenueName6.getText());
					b6.setPrice(Price6.getText());
					b6.setService(ServiceName6.getValue());
					// b2.setServiceused(ServiceUsed.getText());
					b6.setContract(contract);
				}
			}	
		

		if (ServiceName.getValue() != null && ServiceName.getValue() != "") {
			data.add(b);
		}
		if (ServiceName2.getValue() != null && ServiceName2.getValue() != "") {
			data.add(b2);

		}
		if (ServiceName3.getValue() != null && ServiceName3.getValue() != "") {
			data.add(b3);
		}
		if (ServiceName4.getValue() != null && ServiceName4.getValue() != "") {
			data.add(b4);
		}
		if (ServiceName5.getValue() != null && ServiceName5.getValue() != "") {
			data.add(b5);
		}
		if (ServiceName6.getValue() != null && ServiceName6.getValue() != "") {
			data.add(b6);
		}
		
		
		colBookingdate.setCellValueFactory(new PropertyValueFactory<>("bookingdates"));
		colSlotname.setCellValueFactory(new PropertyValueFactory<>("slot"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colServicename.setCellValueFactory(new PropertyValueFactory<>("service"));
		colVenuename.setCellValueFactory(new PropertyValueFactory<>("venue"));
		colDelete.setCellFactory(delete);
		
		services.setEditable(true);
		services.setItems(data);
		
		//int total = services.getItems().stream().summingInt(Booking::getServicecost);
		int total = 0 ;
	
		
		System.out.println("Total Amount "+total);
		
		baseprice.setText(String.valueOf(total));

		Double ta = 0.18 * total;
		Double t = total + ta;
		taxamount.setText(String.valueOf(ta));
		pact.setText(String.valueOf(t));
		
		ServiceName.getSelectionModel().clearSelection();
		ServiceName2.getSelectionModel().clearSelection();
		ServiceName3.getSelectionModel().clearSelection();
		ServiceName4.getSelectionModel().clearSelection();
		ServiceName5.getSelectionModel().clearSelection();
		ServiceName6.getSelectionModel().clearSelection();
				
		Price.clear();
		Price2.clear();
		Price3.clear();
		Price4.clear();
		Price5.clear();
		Price6.clear();
		
		BookingDate.getEditor().clear();
		BookingDate2.getEditor().clear();
		BookingDate3.getEditor().clear();
		BookingDate4.getEditor().clear();
		BookingDate5.getEditor().clear();
		BookingDate6.getEditor().clear(); 
		
		SlotName.clear();
		SlotName2.clear();
		SlotName3.clear();
		SlotName4.clear();
		SlotName5.clear();
		SlotName6.clear();
		
		VenueName.clear();
		VenueName2.clear();
		VenueName3.clear();
		VenueName4.clear();
		VenueName5.clear();
		VenueName6.clear();
		
		i++;
			
		}while(servicedates.get(i) != null);
		
		 
		return null;
	}
	
	Callback<DatePicker, DateCell> dateColorFactory = new Callback<DatePicker, DateCell>() {
		public DateCell call(final DatePicker datePicker) {
			return new DateCell() {
				@Override
				public void updateItem(LocalDate item, boolean empty){
					Integer c = null;
					Map<LocalDate, Integer> getDatae = countDates();
					List<LocalDate> dates = new ArrayList<LocalDate>();
					
					for (LocalDate date : getDatae.keySet()) {
						dates.add(date);
						// search for value
						c = getDatae.get(date);
						System.out.println("Date = " + date + ", Total = " + c);
						super.updateItem(date, empty);
					}
					
					if (!empty) {
						if (dates.contains(item)) {

							int COUNT_DATES = getDatae.get(item);
							
							System.out.println("Item Value here: " + item);
							System.out.println("Item = " + item + ", Total = " + COUNT_DATES);
							
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
							
							List<String> allservice = null;
							try {
								allservice = bookingService.getAllService(simpleDateFormat.format(simpleDateFormat2.parse(item.toString())));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								
								System.out.println("Exception in all services");
							} 
							System.out.println("All Services on date :"+item+ "are : "+allservice);
							
							/* count slot(Morning & Evening) each day */
							if (COUNT_DATES == 1) {
								DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								// SimpleDateFormat DateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
								
								System.out.println("Item Date : " + item.format(DateFormat));
								System.out.println("Condition 1");
								setTooltip(new Tooltip(allservice.toString()));
								setStyle(ONE_BOOKING);

							}else if (COUNT_DATES == 2) {
								System.out.println("Condition 2");
								setTooltip(new Tooltip(allservice.toString()));
								setStyle(TWO_BOOKING);
	//							setDisable(true);
							} 
							else if (COUNT_DATES >= 2){
								System.out.println("Condition 3");
								setTooltip(new Tooltip(allservice.toString()));
								setStyle(MORE_BOOKING);
	//							setDisable(true);
							} else {
								System.out.println("Condition 4");
								setTooltip(new Tooltip("Available"));							
							}
						}
					} else {
						setTooltip(new Tooltip("No Bookings"));
						System.out.println("Condition 5");
					}
				}
			};
		}
	};
	
	Callback<TableColumn<Booking, Boolean>, TableCell<Booking, Boolean>> delete = new Callback<TableColumn<Booking, Boolean>, TableCell<Booking, Boolean>>() {
		@Override
		public TableCell<Booking, Boolean> call(final TableColumn<Booking, Boolean> param) {
			final TableCell<Booking, Boolean> cell = new TableCell<Booking, Boolean>() {
				Image imgEdit = new Image(getClass().getResourceAsStream("/images/delete.png"));
				final Button btnEdit = new Button();

				@Override
				public void updateItem(Boolean check, boolean empty) {
					super.updateItem(check, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						btnEdit.setOnAction(e -> {
							Booking b = services.getSelectionModel().getSelectedItem();
							services.getItems().remove(b);
							System.out.println("Deleted "+b.toString());
				
						});
						
						btnEdit.setStyle("-fx-background-color: transparent;");
						ImageView iv = new ImageView();
						iv.setImage(imgEdit);
						iv.setPreserveRatio(true);
						iv.setSmooth(true);
						iv.setCache(true);
						btnEdit.setGraphic(iv);
						setGraphic(btnEdit);
						setAlignment(Pos.CENTER);
						setText(null);
					}
				}
			};
			return cell;
		}
	};


	@FXML
	private void saveContract(ActionEvent event) {

		if (emptyValidation("Client", CustomerName.getSelectionModel().getSelectedItem() == null)) {
			
			Contract contract = new Contract();
			contract.setContractdate(convertDate(ContractDate.getValue()));
			Customer customer = customerService.findCustomer(CustomerName.getSelectionModel().getSelectedItem());
			contract.setCustomer(customer);
			contract.setRepname(repName.getSelectionModel().getSelectedItem());

			contractService.save(contract);

			for (Booking d : data){
				System.out.println("Contract value here d : " +contract.toString());
				d.setContract(contract);
				bookingService.save(d);
			}
			
			Performance p = new Performance();
			p.setShowdate(ShowDate.getText());
			p.setShowdetails(ShowDetails.getText());
			p.setShowname(ShowName.getText());
			p.setShowtime(ShowTime.getText());
			
			p.setContract(contract);
			performanceService.save(p);
			
		
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Contract");
			alert.setHeaderText(null);
			alert.setContentText("Contract of  " + CustomerName.getSelectionModel().getSelectedItem()
					+ " has been created of Amount: " + pact.getText() + ". Proceed to Payment..");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					i=0;
					receipt(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// ... user chose CANCEL or closed the dialog
			}
		}
	}

	public Map<LocalDate, Integer> countDates() {

		List<String> datefromBooking = bookingService.getServiceDate();
		List<LocalDate> dateList = new ArrayList<LocalDate>();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

		for (String dateString : datefromBooking) {
			try {
				simpleDateFormat2.format(simpleDateFormat.parse(dateString));
				dateList.add(LocalDate.parse(simpleDateFormat2.format(simpleDateFormat.parse(dateString))));

			}catch (ParseException e) {
				System.out.println("Date Exception");
				e.printStackTrace();
			}
		}

		System.out.println("Date Picker final service date" + dateList);

		Map<LocalDate, Integer> noOfDates = new HashMap<LocalDate, Integer>();

		System.out.println("Dates counting : " + noOfDates);

		for (int i = 0; i < dateList.size(); i++) {
			LocalDate ld = dateList.get(i);
			Integer count = noOfDates.get(ld);
			if (count != null) {
				count++;

			} else {
				count = 1;
			}
			noOfDates.put(ld, count);
		}

		System.out.println("Return from method " + noOfDates);
		return noOfDates;

	}

	/*
	 * Validations
	 */
	private boolean validate(String field, String value, String pattern){
		if (!value.isEmpty()) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(value);
			if (m.find() && m.group().equals(value)) {
				return true;
			} else {
				validationAlert(field, false);
				return false;
			}
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private boolean emptyValidation(String field, boolean empty) {
		if (!empty) {
			return true;
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private void validationAlert(String field, boolean empty) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Validation Error");
		alert.setHeaderText(null);
		if (field.equals("Client"))
			alert.setContentText("Please Select " + field);
		else {
			if (empty)
				alert.setContentText("Please Enter " + field);
			else
				alert.setContentText("Please Enter Valid " + field);
		}
		alert.showAndWait();
	}

	public String convertDate(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dtf.format(date);
	}
	
}
