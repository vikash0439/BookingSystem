package com.booking.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Booking;
import com.booking.bean.Contract;
import com.booking.bean.Invoice;
import com.booking.config.StageManager;
import com.booking.service.ContractService;
import com.booking.service.InvoiceService;
import com.booking.service.ServiceService;
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
public class InvoiceController implements Initializable {

	@FXML
	private Label InvoiceID;
	@FXML
	private DatePicker InvoiceDate;
	@FXML
	private TextField cancelled;
	@FXML
	private ComboBox<String> contractid;
	@FXML
	private Label ClientName;
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
	@Autowired
	private ServiceService serviceService;

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

	public void getContract() {

	}

	@FXML
	public void getContractDetail(ActionEvent event) {
		Contract contract = contractService.find(Long.parseLong(contractid.getSelectionModel().getSelectedItem().toString()));
		ClientName.setText(contract.getCustomer().getCustomername());
		InvoiceAmount.setText(contract.getBaseprice());

		Double ia = Double.parseDouble(contract.getBaseprice());
		Double cg = ia * 9 / 100;
		Double sg = ia * 9 / 100;
		Double t = cg + sg + ia;

		cgst.setText(String.valueOf(cg));
		sgst.setText(String.valueOf(sg));
		total.setText(String.valueOf(t));
	}

	@FXML
	private void saveInvoice(ActionEvent event) {
		if(emptyValidation("Invoice Date", ((String)(InvoiceDate.getEditor().getText())).isEmpty())) {
		if (InvoiceID.getText() == null || InvoiceID.getText() == "") {
			Invoice invoice = new Invoice();
			invoice.setInvoicedate(convertDate(InvoiceDate.getValue()));
			invoice.setCancelled(cancelled.getText());

			Contract contract = contractService.find(Long.parseLong(contractid.getSelectionModel().getSelectedItem()));
			invoice.setContract(contract);

			invoiceService.save(invoice);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invoice created.");
			alert.setHeaderText("Contract No :  " + contractid.getSelectionModel().getSelectedItem() + "  invoice created.");
			alert.setContentText("Generating report..");
			alert.showAndWait();

			try {

				System.out.println("From Invoice Controller report try block");
				
				JasperReport jasperReport;
				try {
					jasperReport = JasperCompileManager
							.compileReport(getClass().getResourceAsStream("/reports/Invoice.jrxml"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					jasperReport = JasperCompileManager
							.compileReport(getClass().getResourceAsStream("/reports/Invoice.jasper"));
				}

				Map<String, Object> parameters = new HashMap<String, Object>();

				List<HashMap<String, Object>> inv = new ArrayList<HashMap<String, Object>>();
				
				HashMap<String, Object> p = new HashMap<String, Object>();

				int i = 1;
	
				List<Booking> allbookings = invoice.getContract().getBookings();
				StringBuffer buffername = new StringBuffer();
				String stringname = null;
				StringBuffer bufferdate = new StringBuffer();
				String stringdate = null;
				StringBuffer buffercost = new StringBuffer();
				String stringcost = null;
				
				StringBuffer bufferqty = new StringBuffer();
				String stringqty = null;
				
				StringBuffer bufferunit = new StringBuffer();
				String stringunit = null;

				for (i = 0; i < allbookings.size(); i++) {

					String name = allbookings.get(i).getService();
					buffername = buffername.append(" ").append(name);
					stringname = buffername.toString();

					String date = allbookings.get(i).getBookingdates();
					bufferdate = bufferdate.append(" ").append(date);
					stringdate = bufferdate.toString();

					String cost = allbookings.get(i).getPrice();
					buffercost = buffercost.append(" ").append(cost);
					stringcost = buffercost.toString();
					
					String qty = "1";
					bufferqty = bufferqty.append("  ").append(qty);
					stringqty = bufferqty.toString();
					
					String unit = serviceService.getServiceUnit(name);
					System.out.println(serviceService.getServiceUnit(name));
					bufferunit = bufferunit.append("  ").append(unit);
					stringunit = bufferunit.toString();
				}
				
				p.put("invoicedate", (String) InvoiceDate.getEditor().getText());							
				p.put("contractid", invoice.getContract().getContractid());
				p.put("invoiceid", invoice.getInvoiceid());
				p.put("customername", invoice.getContract().getCustomer().getCustomername());
				p.put("address", invoice.getContract().getCustomer().getAddress());
				p.put("statecode", invoice.getContract().getCustomer().getStatecode());
				p.put("gstno", invoice.getContract().getCustomer().getGstno());
				p.put("baseprice", invoice.getContract().getBaseprice());

				p.put("servicename", stringname);
				p.put("servicedate", stringdate);
				p.put("servicecost", stringcost);
				p.put("qty", stringqty);
				p.put("unit", stringunit);
				p.put("amount", stringcost);

				Double ia = Double.parseDouble(invoice.getContract().getBaseprice());
				Double cg = ia * 9 / 100;
				Double sg = ia * 9 / 100;
				Double t = cg + sg + ia;

				p.put("cg", Math.round(cg));
				p.put("sg", Math.round(sg));
				p.put("t", Math.round(t));
				p.put("NumInWords", convert((int) Math.round(t)) + " Only");

				inv.add(p);

				JRDataSource jRDataSource = new JRBeanCollectionDataSource(inv);
				parameters.put("jRDataSource", inv);

				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jRDataSource);
				File outDir = new File("Reports/Invoice");
				outDir.mkdirs();

				DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
				Date date = new java.util.Date();
				System.out.println(date);
				String path = outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");

				JasperExportManager.exportReportToPdfFile(jasperPrint, path);

			} catch (JRException e) {
				// TODO Auto-generated catch block
				System.out.println("JR Exception");
				e.printStackTrace();
			}
			
			
			/* Duplicate report invoice code */
			try {

				System.out.println("From Invoice Controller report try block");
				
				JasperReport jasperReport2;
				try {
					jasperReport2 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/Invoice2.jrxml"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					jasperReport2 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/Invoice2.jasper"));
				}

				Map<String, Object> parameters = new HashMap<String, Object>();

				List<HashMap<String, Object>> inv = new ArrayList<HashMap<String, Object>>();
				
				HashMap<String, Object> p = new HashMap<String, Object>();

				int i = 1;
	
				List<Booking> allbookings = invoice.getContract().getBookings();
				StringBuffer buffername = new StringBuffer();
				String stringname = null;
				StringBuffer bufferdate = new StringBuffer();
				String stringdate = null;
				StringBuffer buffercost = new StringBuffer();
				String stringcost = null;
				
				StringBuffer bufferqty = new StringBuffer();
				String stringqty = null;
				
				StringBuffer bufferunit = new StringBuffer();
				String stringunit = null;

				for (i = 0; i < allbookings.size(); i++) {

					String name = allbookings.get(i).getService();
					buffername = buffername.append(" ").append(name);
					stringname = buffername.toString();

					String date = allbookings.get(i).getService();
					bufferdate = bufferdate.append(" ").append(date);
					stringdate = bufferdate.toString();

					String cost = allbookings.get(i).getService();
					buffercost = buffercost.append(" ").append(cost);
					stringcost = buffercost.toString();
					
					String qty = "1";
					bufferqty = bufferqty.append("  ").append(qty);
					stringqty = bufferqty.toString();
					
					String unit = serviceService.getServiceUnit(name);
					bufferunit = bufferunit.append("  ").append(unit);
					stringunit = bufferunit.toString();
				}
					
				p.put("invoicedate", (String) InvoiceDate.getEditor().getText());
				p.put("contractid", invoice.getContract().getContractid());
				p.put("invoiceid", invoice.getInvoiceid());
				p.put("customername", invoice.getContract().getCustomer().getCustomername());
				p.put("address", invoice.getContract().getCustomer().getAddress());
				p.put("statecode", invoice.getContract().getCustomer().getStatecode());
				p.put("gstno", invoice.getContract().getCustomer().getGstno());
				p.put("baseprice", invoice.getContract().getBaseprice());

				p.put("servicename", stringname);
				p.put("servicedate", stringdate);
				p.put("servicecost", stringcost);
				p.put("qty", stringqty);
				p.put("unit", stringunit);
				p.put("amount", stringcost);

				Double ia = Double.parseDouble(invoice.getContract().getBaseprice());
				Double cg = ia * 9 / 100;
				Double sg = ia * 9 / 100;
				Double t = cg + sg + ia;

				p.put("cg", Math.round(cg));
				p.put("sg", Math.round(sg));
				p.put("t", Math.round(t));
				p.put("NumInWords", convert((int) Math.round(t)) + " Only");

				inv.add(p);

				JRDataSource jRDataSource = new JRBeanCollectionDataSource(inv);
				parameters.put("jRDataSource", inv);

				JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, parameters, jRDataSource);
				File outDir = new File("Reports/Invoice");
				outDir.mkdirs();

				DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss+");
				Date date = new java.util.Date();
				System.out.println(date);
				String path2 = outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");

				JasperExportManager.exportReportToPdfFile(jasperPrint2, path2);

				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Report");
				a.setHeaderText("Original & Duplicate invoice generated.");
				a.setContentText("Report downloaded successfully.");
				a.showAndWait();

			} catch (JRException e) {
				// TODO Auto-generated catch block
				System.out.println("JR Exception");
				e.printStackTrace();
			}
			
			/* End duplicate receipt invoice code */
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
		colcontractid.setCellValueFactory(new Callback<CellDataFeatures<Invoice, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Invoice, String> param) {
				return new SimpleStringProperty(Long.toString(param.getValue().getContract().getContractid()));
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
					// cancelled.setText(invoice);

				}
			};
			return cell;
		}
	};

	/* convert number to words */
	public static final String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
			"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
			"Nineteen" };

	public static final String[] tens = { "", // 0
			"", // 1
			"Twenty", // 2
			"Thirty", // 3
			"Forty", // 4
			"Fifty", // 5
			"Sixty", // 6
			"Seventy", // 7
			"Eighty", // 8
			"Ninety" // 9
	};

	public static String convert(final int n) {
		if (n < 0) {
			return "Minus " + convert(-n);
		}

		if (n < 20) {
			return units[n];
		}

		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		}

		if (n < 1000) {
			return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 100000) {
			return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}

		return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}

	private void clearFields() {
		contractid.getSelectionModel().clearSelection();
		InvoiceID.setText(null);
		InvoiceDate.getEditor().clear();
		cancelled.clear();
		InvoiceAmount.setText(null);
		ClientName.setText(null);
		cgst.setText(null);
		sgst.setText(null);
		total.setText(null);
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
