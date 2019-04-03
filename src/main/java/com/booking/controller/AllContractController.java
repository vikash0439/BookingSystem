package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Booking;
import com.booking.bean.Contract;
import com.booking.bean.Performance;
import com.booking.bean.Service;
import com.booking.config.StageManager;
import com.booking.service.BookingService;
import com.booking.service.ContractService;
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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

@Controller
public class AllContractController implements Initializable {
	
	@FXML
	private HBox serviceHBox;

	@FXML
	private ComboBox<?> ContractID;
	@FXML
	private Label receipt;
	@FXML
	private Label invoice;

	@FXML
	private Label bookingdate;
	@FXML
	private Label contractid;
	@FXML
	private Label purpose;
	@FXML
	private Label baseprice;
	@FXML
	private Label taxamount;
	@FXML
	private Label pact;
	@FXML
	private Label client;
	@FXML
	private Label repname;
	@FXML
	private Label repemail;
	@FXML
	private Label repmobile;
	@FXML
	private ToggleGroup noc;
	@FXML
	private RadioButton NocYes;
	@FXML
	private RadioButton NocNo;
	
    @FXML
    private TableView<Performance> showtable;
    @FXML
	private TableColumn<Performance, String> colShowname;
    @FXML
	private TableColumn<Performance, String> colShowtime;
    @FXML
	private TableColumn<Performance, String> colShowdetails;
	@FXML
	private TableColumn<Performance, String> colPerformanceid;
	@FXML
	private TableColumn<Performance, String> colShowdate;

	@FXML
	private TableView<Booking> bookingtable;
	@FXML
	private TableColumn<Booking, Long> colBookingid;
	@FXML
	private TableColumn<Booking, String> colBooked;
	@FXML
	private TableColumn<Booking, String> colBookingdate;
	@FXML
	private TableColumn<Booking, String> colDoc;
	@FXML
	private TableColumn<Booking, String> colServicename;
	@FXML
	private TableColumn<Booking, String> colVenuename; 
	@FXML
	private TableColumn<Booking, String> colSlotname; 
	@FXML
	private TableColumn<Booking, String> colPrice; 
	@FXML
	private TableColumn<Booking, Boolean> colDelete;

	/* Booking table */
	@FXML
	private DatePicker ServiceDate;
	@FXML
	private TextField StartTime;
	@FXML
	private TextField EndTime;
	@FXML
	private ComboBox<String> Slot;
	@FXML
	private TextField ServiceCost;
	@FXML
	private TextField ServiceUsed;
	@FXML
	private ComboBox<String> ServiceName;
	@FXML
	private TextField Price;
	@FXML
	private TextField SlotName;
	@FXML
	private TextField VenueName;

	@Autowired
	private RepService repService;
	@Autowired
	private ContractService contractService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private SlotService slotService;
	@Lazy
	@Autowired
	private StageManager stageManager;

	private ObservableList cIDList = FXCollections.observableArrayList();
	private ObservableList contractbookingList = FXCollections.observableArrayList();
	private ObservableList contractshowList = FXCollections.observableArrayList();
	private ObservableList<String> slotList = FXCollections.observableArrayList();
	private ObservableList<String> serviceList = FXCollections.observableArrayList();

	@FXML
	private void logout(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void reset(ActionEvent event) {

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
	
	public void update() {
		String noc = NocYes.isSelected() ? "Yes" : "No";
		System.out.println(noc +" "+Long.parseLong((String) ContractID.getEditor().getText()));
		contractService.updateNoc(Long.parseLong((String) ContractID.getEditor().getText()), noc);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Contract");	
		alert.setContentText("Noc updated!");
		alert.show();
		
	}

	public void slotChange() {
		Slot.getSelectionModel().getSelectedItem();
		com.booking.bean.Slot time = slotService.slotTiming(Slot.getSelectionModel().getSelectedItem());
		StartTime.setText(time.getStarttime());
		EndTime.setText(time.getEndtime());
	}

	public void serviceDetail() {

		String ser = ServiceName.getValue();
		Service service = serviceService.getDetail(ser);
		ServiceCost.setText(service.getPrice());

		Long bp = Long.parseLong(baseprice.getText()) + Long.parseLong(ServiceCost.getText());
		baseprice.setText(bp.toString());


		Double ta = 0.18 * bp;
		Double t = bp + ta;
		taxamount.setText(String.valueOf(ta));
		pact.setText(String.valueOf(t));
		
		
		contractService.updateCost(Long.parseLong((String) ContractID.getEditor().getText()), bp.toString(), String.valueOf(ta), String.valueOf(t));
		
	}
	
	public void addMore() {
		System.out.println("Add more button ");
		
		Booking b = new Booking();
		b.setBookingdates(convertDate(ServiceDate.getValue()));
		b.setService(ServiceName.getValue());
		b.setSlot(Slot.getSelectionModel().getSelectedItem());
//		b.setDateofcancel(dateofcancel);
		Contract contract = contractService.find(Long.parseLong((String) ContractID.getEditor().getText()));
		b.setContract(contract);
		
		bookingService.save(b);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Contract");
		alert.setHeaderText("Booking updated");
		alert.setContentText("New booking added in the contract Id : " +ContractID.getEditor().getText());
		
		ServiceDate.getEditor().clear();
		ServiceName.getSelectionModel().clearSelection();
		StartTime.clear();
		EndTime.clear();
		Slot.getSelectionModel().clearSelection();
		ServiceCost.clear();
		
		contractbookingList.clear();
		contractbookingList.addAll(contract.getBookings());
		bookingtable.setItems(contractbookingList);
		
	}

	@FXML
	void getContractID(ActionEvent event) {
		
		serviceHBox.setVisible(true);

		Contract contract = contractService.find(Long.parseLong((String) ContractID.getEditor().getText()));

		/* Booking table */
		System.out.println("Booking table size: " + contract.getBookings().size());

		colBookingid.setCellValueFactory(new PropertyValueFactory<Booking, Long>("bookingid"));
		colBooked.setCellValueFactory(new PropertyValueFactory<Booking, String>("booked"));
		colBookingdate.setCellValueFactory(new PropertyValueFactory<>("bookingdates"));
		colDoc.setCellValueFactory(new PropertyValueFactory<>("dateofcancel"));
		colServicename.setCellValueFactory(new PropertyValueFactory<>("service"));
		colVenuename.setCellValueFactory(new PropertyValueFactory<>("venue"));
		colSlotname.setCellValueFactory(new PropertyValueFactory<>("slot"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colDelete.setCellFactory(delete);
				
		contractbookingList.clear();
		contractbookingList.addAll(contract.getBookings());
		bookingtable.setItems(contractbookingList);
		
		
		colPerformanceid.setCellValueFactory(new PropertyValueFactory<>("performanceid"));
		colShowdate.setCellValueFactory(new PropertyValueFactory<>("showdate"));
		colShowname.setCellValueFactory(new PropertyValueFactory<>("showname"));
		colShowtime.setCellValueFactory(new PropertyValueFactory<>("showtime"));
		colShowdetails.setCellValueFactory(new PropertyValueFactory<>("showdetails"));
		
		contractshowList.clear();
		contractshowList.addAll(contract.getPerformances());
		showtable.setItems(contractshowList);

		bookingdate.setText(contract.getContractdate());
		contractid.setText(String.valueOf(contract.getContractid()));
		baseprice.setText(contract.getBaseprice());
		
		client.setText(contract.getCustomer().getCustomername());
		
		
		
	}
	
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
							Booking booking = getTableView().getItems().get(getIndex());
//							updateBooking(booking);
							System.out.println(booking);
							deletebooking(booking);					
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

				private void deletebooking(Booking booking) {
					bookingService.deleteById(booking);
					System.out.println("Deleted");
					bookingtable.refresh();
					
				}
			};
			return cell;
		}
	};


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		cIDList.clear();
		cIDList.addAll(contractService.getContractID());
		ContractID.setItems(cIDList);
		
		
		
		serviceList.clear();
		serviceList.addAll(serviceService.findName());
		ServiceName.setItems(serviceList);

	}
	
	/*
	 * Validations
	 */
	private boolean validate(String field, String value, String pattern) {
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