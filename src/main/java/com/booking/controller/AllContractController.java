package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Booking;
import com.booking.bean.Contract;
import com.booking.bean.Rep;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	private Label booking;
	@FXML
	private Label performance;
	@FXML
	private Label receipt;
	@FXML
	private Label customer;
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
	private TableView<Booking> bookingtable;
	@FXML
	private TableColumn<Booking, String> servicedate;
	@FXML
	private TableColumn<Booking, String> servicename;
	@FXML
	private TableColumn<Booking, String> time;
	@FXML
	private TableColumn<Booking, Long> serviceid;
	@FXML
	private TableColumn<Booking, String> cost;
	@FXML
	private TableColumn<Booking, String> slot; 
	@FXML
	private TableColumn<Booking, Boolean> colDelete;

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

	public void slotChange() {
		Slot.getSelectionModel().getSelectedItem();
		String time = slotService.slotTiming(Slot.getSelectionModel().getSelectedItem());
		ServiceTime.setText(time);
	}

	public void serviceDetail() {

		String ser = ServiceName.getValue();
		Service service = serviceService.getDetail(ser);
		ServiceCost.setText(service.getServicecharges());

		Long bp = Long.parseLong(baseprice.getText()) + Long.parseLong(ServiceCost.getText());
		baseprice.setText(bp.toString());


		Double ta = 0.18 * bp;
		Double t = bp + ta;
		taxamount.setText(String.valueOf(ta));
		pact.setText(String.valueOf(t));
	}
	
	public void addMore() {
		System.out.println("Add more button ");
		
		Booking b = new Booking();
		b.setServicedate((String) ServiceDate.getEditor().getText());
		b.setServicename(ServiceName.getValue());
		b.setServicetime(ServiceTime.getText());
		b.setSlot(Slot.getSelectionModel().getSelectedItem());
		b.setServicecost(ServiceCost.getText());
		// b.setServiceused(ServiceUsed.getText());
		Contract contract = contractService.find(Long.parseLong((String) ContractID.getEditor().getText()));
		b.setContract(contract);
		
		bookingService.save(b);
		
		ServiceDate.getEditor().clear();
		ServiceName.getSelectionModel().clearSelection();
		ServiceTime.clear();
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

		serviceid.setCellValueFactory(new PropertyValueFactory<Booking, Long>("serviceid"));
		servicedate.setCellValueFactory(new PropertyValueFactory<Booking, String>("servicedate"));
		servicename.setCellValueFactory(new PropertyValueFactory<>("servicename"));
		cost.setCellValueFactory(new PropertyValueFactory<>("servicecost"));
		slot.setCellValueFactory(new PropertyValueFactory<>("slot"));
		time.setCellValueFactory(new PropertyValueFactory<>("servicetime"));
		colDelete.setCellFactory(delete);
		
		contractbookingList.clear();
		contractbookingList.addAll(contract.getBookings());
		bookingtable.setItems(contractbookingList);

		bookingdate.setText(contract.getBookingdate());
		contractid.setText(String.valueOf((contract.getContractid())));
		purpose.setText(contract.getPurpose());
		baseprice.setText(contract.getBaseprice());
		taxamount.setText(contract.getTaxamount());
		pact.setText(contract.getPact());
		client.setText(contract.getCustomer().getCustomername());
		repname.setText(contract.getRepname());
		
		Rep r = repService.findbyName(contract.getRepname());
		
		repemail.setText(r.getRepemail());
		repmobile.setText(r.getRepmobile());

		booking.setText(contract.getBookings().toString());
		performance.setText(contract.getPerformances().toString());
		receipt.setText(contract.getReceipt().toString());
		customer.setText(contract.getCustomer().toString());
		invoice.setText(contract.getInvoice().toString());
	
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
					bookingService.delete(booking);
					System.out.println("Deleted");
					
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
		
		slotList.clear();
		slotList.addAll(slotService.findSlot());
		Slot.setItems(slotList);
		
		serviceList.clear();
		serviceList.addAll(serviceService.findName());
		ServiceName.setItems(serviceList);

	}

}