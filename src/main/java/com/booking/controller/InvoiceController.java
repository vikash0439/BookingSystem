package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Contract;
import com.booking.bean.Invoice;
import com.booking.config.StageManager;
import com.booking.service.ContractService;
import com.booking.service.InvoiceService;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;

@Controller
public class InvoiceController implements Initializable{
	
	@FXML 
	private Label InvoiceID;
	@FXML
	private DatePicker InvoiceDate;
	@FXML
	private TextField InvoiceAmount;
	@FXML
	private TextField Validity;
	@FXML
	private ComboBox<Long> contractid;
	
	@FXML
	private TableView<Invoice> invoicetable;

	@FXML
	private TableColumn<Invoice, Long> colInvoiceID;
	
	@FXML
	private TableColumn<Invoice, LocalDate> colInvoiceDate;
	
	@FXML
	private TableColumn<Invoice, String> colInvoiceAmount;
	
	@FXML
	private TableColumn<Invoice, String> colValidity;
	@FXML
	private TableColumn<Invoice, Boolean> colEdit;

	@FXML
	private Button reset;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired 
	private InvoiceService invoiceService;
	@Autowired 
	private ContractService contractService;
	
    private ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();
    private ObservableList<Long> contractList = FXCollections.observableArrayList();
	
 
	/* Tax Event methods */

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
	
	public void getContract() {
		
	}
	
	@FXML
	private void saveInvoice(ActionEvent event) {
		if (InvoiceID.getText() == null || InvoiceID.getText() == "") {
			Invoice invoice = new Invoice();
			invoice.setInvoicedate((String)InvoiceDate.getEditor().getText());
 
			Contract contract = contractService.find(contractid.getValue());
	       invoice.setContract(contract);
			
			invoiceService.save(invoice);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invoice created.");
			alert.setHeaderText(null);
			alert.setContentText("Amount:  " + InvoiceAmount.getText() + "  invoice created.");
			alert.showAndWait();
		} else {
			System.out.println("Nothing to update");
		}
		invoicetable();
		clearFields();
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		contractList.clear();
		contractList.addAll(contractService.getContractID());
		System.out.println(contractList);
		contractid.setItems(contractList);
		
		invoicetable();
		clearFields();
		
	}
	
	public void invoicetable() {
		/*
		 * Set All userTable column properties
		 */
		colInvoiceDate.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
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
	
		
		colInvoiceID.setCellValueFactory(new PropertyValueFactory<>("invoiceid"));
		colInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("invoicedate"));
		colInvoiceAmount.setCellValueFactory(new PropertyValueFactory<>("invoiceamount"));
		colValidity.setCellValueFactory(new PropertyValueFactory<>("validity"));
		colEdit.setCellFactory(cellFactory);

		invoiceList.clear();
		invoiceList.addAll(invoiceService.getInvoice());
		invoicetable.setItems(invoiceList);
	}

	Callback<TableColumn<Invoice, Boolean>, TableCell<Invoice, Boolean>> cellFactory = new Callback<TableColumn<Invoice, Boolean>, TableCell<Invoice, Boolean>>() {
		@Override
		public TableCell<Invoice, Boolean> call(final TableColumn<Invoice, Boolean> param) {
			final TableCell<Invoice, Boolean> cell = new TableCell<Invoice, Boolean>() {
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
							Invoice invoice = getTableView().getItems().get(getIndex());
							updateInvoice(invoice);
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

				private void updateInvoice(Invoice invoice) {
					InvoiceID.setText(Long.toString(invoice.getInvoiceid()));
//					InvoiceAmount.setText(invoice.getInvoiceamount());
//					Validity.setText(invoice.getValidity());
					
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		InvoiceID.setText(null);
		InvoiceDate.getEditor().clear();
		InvoiceAmount.clear();
		Validity.clear();
		
	}
}
