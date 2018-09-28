package com.booking.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Contract;
import com.booking.bean.PaymentDetails;
import com.booking.bean.Receipt;
import com.booking.config.StageManager;
import com.booking.service.ContractService;
import com.booking.service.PaymentDetailsService;
import com.booking.service.ReceiptService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReceiptController implements Initializable{
	
	@FXML 
	private Label ReceiptID;
	@FXML
	private ComboBox<String> cID;
	@FXML
	private DatePicker ReceiptDate;
	@FXML
	private TextField PaidAmount;
	@FXML 
	private Label BaseAmount;
	@FXML
	private Label TaxAmount;
	@FXML
	private ComboBox<String> PaymentMode;
	@FXML
	private TextField FinalPayment;	
	@FXML
	private ComboBox<String> MultiplecID;
	@FXML
	private Label labelContractid;
	
	/* Payment Table */
	@FXML
	private TextField TxnID;
	@FXML
	private DatePicker TxnDate;
	@FXML
	private TextField Bank;
	@FXML
	private TextField PaidBy;
	@FXML
	private TextField Credit;
	@FXML
	private CheckBox CreditYes;
	@FXML
	private CheckBox CreditNo;
	
	
	@FXML
	private TableView<Receipt> receipttable;
	@FXML
	private TableColumn<Receipt, String> colcontractid;
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
	private TableColumn<Receipt, String> colTxnID;
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
	@Autowired
	private PaymentDetailsService paymentDetailsService;
	
    private ObservableList<Receipt> receiptList = FXCollections.observableArrayList();
    private ObservableList cIDList = FXCollections.observableArrayList();
    private ObservableList<String> modeList = FXCollections.observableArrayList("Cash", "DD", "Cheque", "NEFT", "Others");
	
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
	
	public void receiptDetail() {
		PaidAmount.getText();
		double pa = Double.parseDouble(PaidAmount.getText());
		double bp1 =  pa/118;
		double bp =  bp1 * 100;
		double ta =  pa-bp;
		BaseAmount.setText(String.valueOf(bp));
		TaxAmount.setText(String.valueOf(ta));
	}
	
	@FXML
	public void setLabelText(){
		MultiplecID.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> 
		               labelContractid.setText(newValue)
				);
	}
	
	@FXML
	private void saveTax(ActionEvent event) {
		if (ReceiptID.getText() == null || ReceiptID.getText() == "") {
			Receipt receipt = new Receipt();
			receipt.setReceiptdate((String)ReceiptDate.getEditor().getText());
			receipt.setPaidamount(PaidAmount.getText());
			receipt.setTaxamount(TaxAmount.getText());
			receipt.setPaymentmode(PaymentMode.getSelectionModel().getSelectedItem());
			receipt.setFinalpayment(FinalPayment.getText());
			Contract contract = contractService.find(Long.parseLong(cID.getSelectionModel().getSelectedItem()));
			receipt.setContract(contract);
			
			/* Payment table */
			PaymentDetails payment = new PaymentDetails();
			payment.setModeid(TxnID.getText());
			payment.setModedate((String)TxnDate.getEditor().getText());
			payment.setModebank(Bank.getText());
			payment.setPaidby(PaidBy.getText());
			payment.setCredit(CreditYes.isSelected() ? "Yes" : "No");
			paymentDetailsService.save(payment);
			
			receipt.setPdetails(payment);
			
			receiptService.save(receipt);
			
			 try {
				JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/reports/receipt.jrxml");
				 Map<String, Object> parameters = new HashMap<String, Object>();
				 List<Receipt> rec = new ArrayList<Receipt>();
				 rec.add(receipt);
			     JRDataSource jRDataSource = new JRBeanCollectionDataSource(rec);
			     parameters.put("jRDataSource", rec);
			     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jRDataSource);
			     File outDir = new File("D:/Reports/Receipt");
			       outDir.mkdirs();
			     
			       DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
			       Date date=new java.util.Date();
			       System.out.println(date);
			       String path =outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");

			       JasperExportManager.exportReportToPdfFile(jasperPrint, path);
			       
			       Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Report");
					alert.setHeaderText(null);
					alert.setContentText("Report downloaded successfully.");
					alert.showAndWait();
				
			} catch (JRException e) {
				// TODO Auto-generated catch block
				System.out.println("JR Exception");
				e.printStackTrace();
			}
			

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
		MultiplecID.setItems(cIDList);
		
		PaymentMode.setItems(modeList);
		
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
	
//		colcontractid.setCellValueFactory(new Callback<CellDataFeatures<Contract,Long>,ObservableValue<Long>>(){
//
//            @Override
//            public SimpleLongProperty call(CellDataFeatures<Contract, Long> param) {
//                return new SimpleLongProperty(param.getValue().getContractid());
//            }
//        });
		colcontractid.setCellValueFactory(new Callback<CellDataFeatures<Receipt,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Receipt, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getBookingdate());
            }
        });
		colReceiptID.setCellValueFactory(new PropertyValueFactory<>("receiptid"));
		colReceiptDate.setCellValueFactory(new PropertyValueFactory<>("receiptdate"));
		colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidamount"));
		colTaxAmount.setCellValueFactory(new PropertyValueFactory<>("taxamount"));
		colPaymentMode.setCellValueFactory(new PropertyValueFactory<>("paymentmode"));
		colTxnID.setCellValueFactory(new Callback<CellDataFeatures<Receipt,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Receipt, String> param) {
                return new SimpleStringProperty(param.getValue().getPdetails().getModeid());
            }
          });		
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
					((TextField)ReceiptDate.getEditor()).setText(receipt.getReceiptdate());
					PaidAmount.setText(receipt.getPaidamount());
					TaxAmount.setText(receipt.getTaxamount());
					PaymentMode.getEditor().setText(receipt.getPaidamount());
					FinalPayment.setText(receipt.getFinalpayment());
					
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
		cID.getSelectionModel().clearSelection();
		ReceiptID.setText(null);
		ReceiptDate.getEditor().clear();
		PaidAmount.clear();
		PaymentMode.getSelectionModel().clearSelection();
		FinalPayment.clear();;
	}
}
