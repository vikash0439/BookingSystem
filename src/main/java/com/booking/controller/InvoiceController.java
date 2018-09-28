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
import com.booking.bean.Invoice;
import com.booking.config.StageManager;
import com.booking.service.ContractService;
import com.booking.service.InvoiceService;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class InvoiceController implements Initializable{
	
	@FXML 
	private Label InvoiceID;
	@FXML
	private DatePicker InvoiceDate;
	@FXML
	private TextField cancelled;
	@FXML
	private ComboBox<String> contractid;	
	@FXML
	private Label InvoiceAmount;	
	@FXML
	private Label cgst;
	@FXML
	private Label sgst;
	@FXML
	private Label total;
	
	@FXML
	private TableView<Invoice> invoicetable;
	@FXML
	private TableColumn<Invoice, Long> colInvoiceID;
	@FXML
	private TableColumn<Invoice, String> colcontractid;
	@FXML
	private TableColumn<Invoice, LocalDate> colInvoiceDate;	
	@FXML
	private TableColumn<Invoice, String> colCancelled;
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
    private ObservableList contractList = FXCollections.observableArrayList();
	
 
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
	
	public void getContract() {
		
	}
	
	public void getContractDetail() {
		Contract contract = contractService.find(Long.parseLong(contractid.getSelectionModel().getSelectedItem()));
		InvoiceAmount.setText(contract.getBaseprice());
		
		Double ia = Double.parseDouble(contract.getBaseprice());
		Double cg = ia/118*9;
		Double sg = ia/118*9;
		Double t = cg+sg+ia;
		cgst.setText(String.valueOf(cg));
		sgst.setText(String.valueOf(sg));
		total.setText(String.valueOf(t));
	}
	
	@FXML
	private void saveInvoice(ActionEvent event) {
		if (InvoiceID.getText() == null || InvoiceID.getText() == "") {
			Invoice invoice = new Invoice();
			invoice.setInvoicedate((String)InvoiceDate.getEditor().getText());
			invoice.setCancelled(cancelled.getText());
 
			Contract contract = contractService.find(Long.parseLong(contractid.getSelectionModel().getSelectedItem()));
	        invoice.setContract(contract);
			
			invoiceService.save(invoice);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invoice created.");
			alert.setHeaderText(null);
			alert.setContentText("Contract No :  " + contractid.getSelectionModel().getSelectedItem() + "  invoice created.");
			alert.showAndWait();
			try {
				JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/reports/Invoice.jrxml");
				 Map<String, Object> parameters = new HashMap<String, Object>();
				 List<Invoice> inv = new ArrayList<Invoice>();
				 inv.add(invoice);
			     JRDataSource jRDataSource = new JRBeanCollectionDataSource(inv);
			     parameters.put("jRDataSource", inv);
			     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jRDataSource);
			     File outDir = new File("D:/Reports/Invoice");
			       outDir.mkdirs();
			     
			       DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
			       Date date=new java.util.Date();
			       System.out.println(date);
			       String path =outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");

			       JasperExportManager.exportReportToPdfFile(jasperPrint, path);
			       
			       Alert a = new Alert(AlertType.INFORMATION);
					a.setTitle("Report");
					a.setHeaderText(null);
					a.setContentText("Report downloaded successfully.");
					a.showAndWait();
				
			} catch (JRException e) {
				// TODO Auto-generated catch block
				System.out.println("JR Exception");
				e.printStackTrace();
			}
			
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
		contractid.setItems(contractList);
		
		invoicetable();
		clearFields();
		
	}
	
	public void invoicetable() {
		/*
		 * Set All userTable column properties
		 */
		colcontractid.setCellValueFactory(new Callback<CellDataFeatures<Invoice,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Invoice, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getBookingdate());
            }
        });
		colInvoiceID.setCellValueFactory(new PropertyValueFactory<>("invoiceid"));
		colInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("invoicedate"));
		colCancelled.setCellValueFactory(new PropertyValueFactory<>("cancelled"));
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
					InvoiceDate.getEditor().setText(invoice.getInvoicedate());
//					cancelled.setText(invoice);
					
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		contractid.getSelectionModel().clearSelection();
		InvoiceID.setText(null);
		InvoiceDate.getEditor().clear();
		cancelled.clear();
		
	}
}
