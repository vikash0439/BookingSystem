package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Reserve;
import com.booking.config.StageManager;
import com.booking.service.CustomerService;
import com.booking.service.ReserveService;
import com.booking.service.ServiceService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

@Controller
public class ReserveController implements Initializable{
	
	@FXML
	private Label ReserveID;
	@FXML
	private DatePicker ServiceDate;	
	@FXML
	private TextField ServiceTime;	
	@FXML
	private ComboBox<String> ServiceID;	
	@FXML
	private ComboBox<String> CustomerID;
	@FXML
	private TextField ReserveTitle;
	@FXML
	private TextField InternalUsage;
	@FXML
	private DatePicker BookingDate;	
	@FXML
	private TableView<Reserve> reservetable;
	@FXML
	private TableColumn<Reserve, Long> colReserveID;	
	@FXML
	private TableColumn<Reserve, LocalDate> colServiceDate;
	@FXML
	private TableColumn<Reserve, String> colServiceTime;
	
	@FXML
	private TableColumn<Reserve, String> colServiceID;
	
	@FXML
	private TableColumn<Reserve, String> colCustomerID;
	@FXML
	private TableColumn<Reserve, String> colReserveTitle;
	
	@FXML
	private TableColumn<Reserve, String> colInternalUsage;
	
	@FXML
	private TableColumn<Reserve, LocalDate> colBookingDate;
	
	@FXML
	private TableColumn<Reserve, Boolean> colEdit;

	@FXML
	private Button reset;
	
	@Lazy
	@Autowired
	private StageManager stageManager;
	
	@Autowired
	private ReserveService reserveService;
	
	@Lazy
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ServiceService serviceService;
	
	private ObservableList<Reserve> reserveList = FXCollections.observableArrayList();
	private ObservableList<String> customerNameList = FXCollections.observableArrayList();
	private ObservableList<String> serviceNameList = FXCollections.observableArrayList();
	
	
	
	/* Event methods */
	
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
		clearFields();
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
	private void saveService(ActionEvent event) {
		if (ReserveID.getText() == null || ReserveID.getText() == "") {
			Reserve reserve = new Reserve();
			reserve.setServicedate(ServiceDate.getValue());
			reserve.setServicetime(ServiceTime.getText());
			reserve.setCustomerid(CustomerID.getSelectionModel().getSelectedItem());
			reserve.setReservetitle(ReserveTitle.getText());
			reserve.setInternalusage(InternalUsage.getText());
			reserve.setBookingdate(BookingDate.getValue());
			reserve.setServiceid(ServiceID.getSelectionModel().getSelectedItem());

			reserveService.save(reserve);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Reserved");
			alert.setHeaderText(null);
			alert.setContentText("Reserved:  " + ServiceDate.getValue() + "  has been reserved by: ."+ CustomerID.getSelectionModel().getSelectedItem());
			alert.showAndWait();
		} else {
			System.out.println("No update code available");
		}
		reservetable();
		clearFields();
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		serviceNameList.clear();
		serviceNameList.addAll(serviceService.findName());
		ServiceID.setItems(serviceNameList);
		customerNameList.addAll(customerService.findName());
		CustomerID.setItems(customerNameList);

		reservetable();
		clearFields();
		
	}
	
	public void reservetable() {
		/*
		 * Set All userTable column properties
		 */
		colServiceDate.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			 String pattern = "dd-MM-yyyy";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 }));
		colBookingDate.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			 String pattern = "dd-MM-yyyy";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 }));
		
		colReserveID.setCellValueFactory(new PropertyValueFactory<>("reserveid"));
		colServiceDate.setCellValueFactory(new PropertyValueFactory<>("servicedate"));
		colServiceID.setCellValueFactory(new PropertyValueFactory<>("serviceid"));
		colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerid"));
		colReserveTitle.setCellValueFactory(new PropertyValueFactory<>("reservetitle"));
		colInternalUsage.setCellValueFactory(new PropertyValueFactory<>("internalusage"));
		colBookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingdate"));
		colServiceTime.setCellValueFactory(new PropertyValueFactory<>("servicetime"));
//		colEdit.setCellFactory(cellFactory);

		reserveList.clear();
		reserveList.addAll(reserveService.getReserve());
		reservetable.setItems(reserveList);
	}

	/*Callback<TableColumn<Reserve, Boolean>, TableCell<Service, Boolean>> cellFactory = new Callback<TableColumn<Service, Boolean>, TableCell<Service, Boolean>>() {
		@Override
		public TableCell<Service, Boolean> call(final TableColumn<Service, Boolean> param) {
			final TableCell<Service, Boolean> cell = new TableCell<Service, Boolean>() {
				Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
				final Button btnEdit = new Button();

				@Override
				public void updateItem(Boolean check, boolean empty) {
					super.updateItem(check, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						btnEdit.setOnAction(e -> {
							Service service = getTableView().getItems().get(getIndex());
							updateService(service);
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

				private void updateService(Service service) {
					ServiceID.setText(Long.toString(service.getServiceid()));
					ServiceName.setText(service.getServicename());
					ServiceInUse.setText(service.getServiceinuse());
					ServiceCharges.setText(service.getServicecharges());
					CancelCharges.setText(service.getCancelcharges());
					
				}
			};
			return cell;
		}
	};*/
	
	private void clearFields() {
		ReserveID.setText(null);
		ServiceDate.getEditor().clear();
		ServiceTime.clear();
		ServiceID.getSelectionModel().clearSelection();
		CustomerID.getSelectionModel().clearSelection();
		ReserveTitle.clear();
		InternalUsage.clear();
		BookingDate.getEditor().clear();
	}

}
