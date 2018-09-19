package com.booking.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Contract;
import com.booking.bean.Receipt;
import com.booking.config.StageManager;
import com.booking.service.ContractService;
import com.booking.service.ReceiptService;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class ReceiptController implements Initializable{
	
	@FXML 
	private Label ReceiptID;
	@FXML
	private ComboBox<Long> cID;
	@FXML
	private DatePicker ReceiptDate;
	@FXML
	private TextField PaidAmount;
	@FXML
	private TextField TaxAmount;
	@FXML
	private TextField PaymentMode;
	@FXML
	private TextField FinalPayment;	
	@FXML
	private TableView<Receipt> receipttable;
	@FXML
	private TableColumn<Receipt, Long> colReceiptID;	
	@FXML
	private TableColumn<Receipt, LocalDate> colReceiptDate;	
	@FXML
	private TableColumn<Receipt, String> colPaidAmount;
	@FXML
	private TableColumn<Receipt, String> colTaxAmount;	
	@FXML
	private TableColumn<Receipt, String> colPaymentMode;	
	@FXML
	private TableColumn<Receipt, String> colFinalPayment;
	@FXML
	private TableColumn<Receipt, Boolean> colEdit;
	@FXML
	private Button reset;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired 
	private ReceiptService receiptService;
	@Autowired 
	private ContractService contractService;
	
    private ObservableList<Receipt> receiptList = FXCollections.observableArrayList();
    private ObservableList<Long> cIDList = FXCollections.observableArrayList();
	
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
	
	@FXML
	private void saveTax(ActionEvent event) {
		if (ReceiptID.getText() == null || ReceiptID.getText() == "") {
			Receipt receipt = new Receipt();
			receipt.setReceiptdate(ReceiptDate.getValue());
			receipt.setPaidamount(PaidAmount.getText());
			receipt.setTaxamount(TaxAmount.getText());
			receipt.setPaymentmode(PaymentMode.getText());
			receipt.setFinalpayment(FinalPayment.getText());
			Long id = (long) 4;
			Contract contract = contractService.find(id);
			receipt.setContract(contract);
			receiptService.save(receipt);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tax updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Amount:  " + PaidAmount.getText() + "  receipt created.");
			alert.showAndWait();
		} else {
			System.out.println("Nothing to update");
		}
		receipttable();
		clearFields();
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cIDList.clear();
		cIDList.addAll(contractService.getContractID());
		cID.setItems(cIDList);
		receipttable();
		clearFields();
		
	}
	
	public void receipttable() {
		/*
		 * Set All userTable column properties
		 */
		/*
		 * Set All userTable column properties
		 */
		colReceiptDate.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
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
	
		
		colReceiptID.setCellValueFactory(new PropertyValueFactory<>("receiptid"));
		colReceiptDate.setCellValueFactory(new PropertyValueFactory<>("receiptdate"));
		colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidamount"));
		colTaxAmount.setCellValueFactory(new PropertyValueFactory<>("taxamount"));
		colPaymentMode.setCellValueFactory(new PropertyValueFactory<>("paymentmode"));
		colFinalPayment.setCellValueFactory(new PropertyValueFactory<>("finalpayment"));
		colEdit.setCellFactory(cellFactory);

		receiptList.clear();
		receiptList.addAll(receiptService.getReceipt());
		receipttable.setItems(receiptList);
	}

	Callback<TableColumn<Receipt, Boolean>, TableCell<Receipt, Boolean>> cellFactory = new Callback<TableColumn<Receipt, Boolean>, TableCell<Receipt, Boolean>>() {
		@Override
		public TableCell<Receipt, Boolean> call(final TableColumn<Receipt, Boolean> param) {
			final TableCell<Receipt, Boolean> cell = new TableCell<Receipt, Boolean>() {
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
							Receipt receipt = getTableView().getItems().get(getIndex());
							updateTax(receipt);
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

				private void updateTax(Receipt receipt) {
					ReceiptID.setText(Long.toString(receipt.getReceiptid()));
					PaidAmount.setText(receipt.getPaidamount());
					TaxAmount.setText(receipt.getTaxamount());
					PaymentMode.setText(receipt.getPaidamount());
					FinalPayment.setText(receipt.getFinalpayment());
					ReceiptDate.getEditor().commitValue();
				}
			};
			return cell;
		}
	};
	
	public void report(ActionEvent event) throws JRException {
		
		 // First, compile jrxml file.
       JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/reports/receipt.jrxml");
      
       // Parameters for report .
       Map<String, Object> parameters = new HashMap<String, Object>();
       
//       List<Receipt> list = new ArrayList<Receipt>();
//       JRDataSource jRDataSource = new JRBeanCollectionDataSource(list);
//       parameters.put("jRDataSource", list);
       
       // DataSource
       // This is simple example, no database.
       // then using empty datasource.
       JRDataSource dataSource = new JREmptyDataSource();
 
       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
 
    
       // Make sure the output directory exists.
       File outDir = new File("D:/Reports/Receipt");
       outDir.mkdirs();
     
       DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
       Date date=new java.util.Date();
       System.out.println(date);
       String path =outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");
       
       // Export to PDF.
       JasperExportManager.exportReportToPdfFile(jasperPrint, path);
       
       Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Report");
		alert.setHeaderText(null);
		alert.setContentText("Report downloaded successfully.");
		alert.showAndWait();
	}
	
	private void clearFields() {
		ReceiptID.setText(null);
		ReceiptDate.getEditor().clear();
		PaidAmount.clear();
		TaxAmount.clear();
		PaymentMode.clear();
		FinalPayment.clear();;
	}
}
