package com.booking.controller;

import static org.slf4j.LoggerFactory.getLogger;

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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Booking;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class ReceiptController implements Initializable {

	private static final Logger LOG = getLogger(ReceiptController.class);

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
	private ComboBox<String> MultiplecID;
	@FXML
	private Label labelContractid;
	@FXML
	private Label ClientName;

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
	private ToggleGroup credit;
	@FXML
	private RadioButton CreditYes;
	@FXML
	private RadioButton CreditNo;

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
	private TableColumn<Receipt, String> colCredit;
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
	private ObservableList<String> modeList = FXCollections.observableArrayList("Cash", "Cheque", "NEFT/RTGS", "DD","Others");

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
	
	
	@FXML
	public void getContractDetail(ActionEvent event) {
		
		Contract contract = contractService.find(Long.parseLong(cID.getSelectionModel().getSelectedItem().toString()));
		
		System.out.println(contract.getCustomer().getCustomername());
		ClientName.setText(contract.getCustomer().getCustomername());
		
	}

	public void FreezeField() {

		String var = PaymentMode.getSelectionModel().getSelectedItem();

		if (var == null || var == "Cash") {
			TxnID.setVisible(false);
			TxnDate.setVisible(false);
			Bank.setVisible(false);
			PaidBy.setVisible(false);
		} else {
			TxnID.setVisible(true);
			TxnDate.setVisible(true);
			Bank.setVisible(true);
			PaidBy.setVisible(true);
		}
	}

	Double bp, ta, cgst;

	public void receiptDetail() {
		PaidAmount.getText();
		Double pa = Double.parseDouble(PaidAmount.getText());
		Double bp1 = pa / 118;
		bp = bp1 * 100;
		ta = pa - bp;
		cgst = ta / 2;
		BaseAmount.setText(String.valueOf(Math.round(bp)));
		TaxAmount.setText(String.valueOf(Math.round(ta)));
	}

	@FXML
	public void setLabelText() {

		String a = String.valueOf(MultiplecID.getValue());
		System.out.println("From setLabelText method: " + a);
		List<String> lcid = new ArrayList<String>();
		lcid.add(a);
		labelContractid.setText(a);

	}

	@FXML
	private void saveTax(ActionEvent event) {
		if (ReceiptID.getText() == null || ReceiptID.getText() == "") {
			Receipt receipt = new Receipt();
			receipt.setReceiptdate(convertDate(ReceiptDate.getValue()));
			receipt.setPaidamount(PaidAmount.getText());
			receipt.setTaxamount(TaxAmount.getText());
			receipt.setPaymentmode(PaymentMode.getSelectionModel().getSelectedItem());
			Contract contract = contractService.find(Long.parseLong(cID.getSelectionModel().getSelectedItem()));
			receipt.setContract(contract);

			/* Payment table */
			PaymentDetails payment = new PaymentDetails();
			payment.setModeid(TxnID.getText());
			payment.setModedate((String) TxnDate.getEditor().getText());
			payment.setModebank(Bank.getText());
			payment.setPaidby(PaidBy.getText());
			payment.setCredit(CreditYes.isSelected() ? "Yes" : "No");
			paymentDetailsService.save(payment);

			receipt.setPdetails(payment);

			receiptService.save(receipt);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Receipt.");
			alert.setHeaderText("Amount:  " + PaidAmount.getText() + "  receipt created.");
			alert.setContentText("Generating receipt....");
			alert.showAndWait();

			
			try {
				JasperReport jasperReport = null;
				
				try {
					LOG.info("Receipt before Compiling ");
					jasperReport = JasperCompileManager
							.compileReport(getClass().getResourceAsStream("/reports/Receipt.jrxml"));
				} catch (Exception e) {
					LOG.error("Unable to compile receipt ", e);
					jasperReport = JasperCompileManager
							.compileReport(getClass().getResourceAsStream("/reports/Receipt.jasper"));
				}
				Map<String, Object> parameters = new HashMap<String, Object>();

				List<Booking> bookinglist = receipt.getContract().getBookings();
				StringBuffer detailsbooking = new StringBuffer();
				String detail = null;
				Set<String> d = new HashSet<String>();

				for (int i = 0; i < bookinglist.size(); i++) {

					String showdate = bookinglist.get(i).getServicedate();
					String slot = bookinglist.get(i).getServicetime();
					detail = showdate.concat(" from ").concat(slot);

					d.add(detail);
				}
				System.out.println("Dates d : " + d);

				int x = 1;

				for (String i : d) {
					detailsbooking = detailsbooking.append(i);
					if (x < d.size()) {
						detailsbooking.append(", ");
					} else {
						detailsbooking.append(".");
					}
					x++;
				}
				
				String scode = receipt.getContract().getCustomer().getStatecode();
				String addWithCode = receipt.getContract().getCustomer().getAddress();
				String addresswithcode = addWithCode.concat(", "+scode);
				
				
				String pmode = receipt.getPaymentmode();
				String finalpmode;
				if(pmode.equals("Cheque")) {
					finalpmode = pmode.concat(" no: "+TxnID.getText()+" dated: "+(String) TxnDate.getEditor().getText()+" drawn on "+Bank.getText());
				}else if(pmode.equals("NEFT/RTGS")) {
					finalpmode = pmode.concat(" dated: "+(String) TxnDate.getEditor().getText());
				}else {
					finalpmode = pmode;
				}
				
				String detailsbooking2 = detailsbooking.toString();

				HashMap<String, Object> p = new HashMap<String, Object>();
				p.put("receiptdate", (String) ReceiptDate.getEditor().getText());
				p.put("receiptdate", (String) ReceiptDate.getEditor().getText());
				p.put("contractid", receipt.getContract().getContractid());
				p.put("receiptid", receipt.getReceiptid());
				p.put("paidamount", convert(Integer.parseInt(receipt.getPaidamount())) + " Only");
				p.put("paymentmode", finalpmode);
				p.put("client", receipt.getContract().getCustomer().getCustomername());
				p.put("details", detailsbooking2);
				p.put("address", addresswithcode);
				p.put("statecode", receipt.getContract().getCustomer().getStatecode());
				p.put("gst", receipt.getContract().getCustomer().getGstno());
				p.put("gross", (Math.round(bp)));
				p.put("cgst", (Math.round(cgst)));
				p.put("sgst", (Math.round(cgst)));
				p.put("total", PaidAmount.getText());

				List<HashMap<String, Object>> rec = new ArrayList<HashMap<String, Object>>();
				rec.add(p);

				JRDataSource jRDataSource = new JRBeanCollectionDataSource(rec);
				parameters.put("jRDataSource", rec);
				JasperPrint jasperPrint = null;
				try {
					LOG.info("In Jasperprint try method");
					jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jRDataSource);
				} catch (Exception e) {
					LOG.error("Unable to print receipt ", e);
				}
				File outDir = new File("Reports/Receipt");
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
			
			/* report for duplicate */
			
			try {
				
				JasperReport jasperReport2 = null;
				try {
					LOG.info("Receipt before Compiling ");
					
					jasperReport2 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/Receipt2.jrxml"));
				} catch (Exception e) {
					LOG.error("Unable to compile receipt ", e);
					jasperReport2 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/Receipt2.jasper"));
				}
				Map<String, Object> parameters = new HashMap<String, Object>();

				List<Booking> bookinglist = receipt.getContract().getBookings();
				StringBuffer detailsbooking = new StringBuffer();
				String detail = null;
				Set<String> d = new HashSet<String>();

				for (int i = 0; i < bookinglist.size(); i++) {

					String showdate = bookinglist.get(i).getServicedate();
					String slot = bookinglist.get(i).getServicetime();
					detail = showdate.concat(" from ").concat(slot);

					d.add(detail);
				}
				System.out.println("Dates d : " + d);

				int x = 1;

				for (String i : d) {
					detailsbooking = detailsbooking.append(i);
					if (x < d.size()) {
						detailsbooking.append(", ");
					} else {
						detailsbooking.append(".");
					}
					x++;
				}
				String detailsbooking2 = detailsbooking.toString();
				
				String scode = receipt.getContract().getCustomer().getStatecode();
				String addWithCode = receipt.getContract().getCustomer().getAddress();
				String addresswithcode = addWithCode.concat(", "+scode);

				HashMap<String, Object> p = new HashMap<String, Object>();
				p.put("receiptdate", (String) ReceiptDate.getEditor().getText());
				p.put("contractid", receipt.getContract().getContractid());
				p.put("receiptid", receipt.getReceiptid());
				p.put("paidamount", convert(Integer.parseInt(receipt.getPaidamount())) + " Only");
				p.put("paymentmode", receipt.getPaymentmode());
				p.put("client", receipt.getContract().getCustomer().getCustomername());
				p.put("details", detailsbooking2);
				p.put("address", addresswithcode);
				p.put("gst", receipt.getContract().getCustomer().getGstno());
				p.put("gross", (Math.round(bp)));
				p.put("cgst", (Math.round(cgst)));
				p.put("sgst", (Math.round(cgst)));
				p.put("total", PaidAmount.getText());

				List<HashMap<String, Object>> rec = new ArrayList<HashMap<String, Object>>();
				rec.add(p);

				JRDataSource jRDataSource = new JRBeanCollectionDataSource(rec);
				parameters.put("jRDataSource", rec);
				JasperPrint jasperPrint2 = null;
				try {
					LOG.info("In Jasperprint try method");
					jasperPrint2 = JasperFillManager.fillReport(jasperReport2, parameters, jRDataSource);
				} catch (Exception e) {
					LOG.error("Unable to print receipt ", e);
				}
				File outDir = new File("Reports/Receipt");
				outDir.mkdirs();
				
				

				DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss+");
				Date date = new java.util.Date();
				System.out.println(date);
				String path2 = outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");
				
				JasperExportManager.exportReportToPdfFile(jasperPrint2, path2);

				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Receipt");
				alert2.setHeaderText("Original & Duplicate receipt generated successfully");
				alert2.setContentText("Thank you");
				alert2.showAndWait();

			} catch (JRException e) {
				// TODO Auto-generated catch block
				System.out.println("JR Exception");
				e.printStackTrace();
			}
			
			/* End receipt duplicate code */

		} else {

			Receipt receipt = receiptService.find(Long.parseLong(ReceiptID.getText()));
			;
			receipt.setReceiptdate((String) ReceiptDate.getEditor().getText());
			receipt.setPaidamount(PaidAmount.getText());
			receipt.setTaxamount(TaxAmount.getText());
			receipt.setPaymentmode(PaymentMode.getSelectionModel().getSelectedItem());

			Contract contract = contractService.find(Long.parseLong(cID.getSelectionModel().getSelectedItem()));
			receipt.setContract(contract);

			/* Payment table */
			PaymentDetails payment = new PaymentDetails();
			payment.setModeid(TxnID.getText());
			payment.setModedate((String) TxnDate.getEditor().getText());
			payment.setModebank(Bank.getText());
			payment.setPaidby(PaidBy.getText());
			payment.setCredit(CreditYes.isSelected() ? "Yes" : "No");
			paymentDetailsService.save(payment);

			receipt.setPdetails(payment);

			receiptService.save(receipt);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Receipt.");
			alert.setHeaderText(null);
			alert.setContentText("Amount:  " + PaidAmount.getText() + "  receipt updated.");
			alert.showAndWait();
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

		colcontractid.setCellValueFactory(new Callback<CellDataFeatures<Receipt, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Receipt, String> param) {
				return new SimpleStringProperty(Long.toString(param.getValue().getContract().getContractid()));
			}
		});
		colReceiptID.setCellValueFactory(new PropertyValueFactory<>("receiptid"));
		colReceiptDate.setCellValueFactory(new PropertyValueFactory<>("receiptdate"));
		colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidamount"));
		colTaxAmount.setCellValueFactory(new PropertyValueFactory<>("taxamount"));
		colPaymentMode.setCellValueFactory(new PropertyValueFactory<>("paymentmode"));
		colTxnID.setCellValueFactory(new Callback<CellDataFeatures<Receipt, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Receipt, String> param) {
				return new SimpleStringProperty(param.getValue().getPdetails().getModeid());
			}
		});
		colCredit.setCellValueFactory(new Callback<CellDataFeatures<Receipt, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Receipt, String> param) {
				return new SimpleStringProperty(param.getValue().getPdetails().getCredit());
			}
		});
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
					((TextField) ReceiptDate.getEditor()).setText(receipt.getReceiptdate());
					PaidAmount.setText(receipt.getPaidamount());
					PaymentMode.getEditor().setText(receipt.getPaidamount());

					cID.getEditor().setText(String.valueOf(receipt.getContract().getContractid()));
					PaidAmount.setText(receipt.getPaidamount());
					;

					BaseAmount.setText(String
							.valueOf(Long.valueOf(receipt.getPaidamount()) - Long.valueOf(receipt.getTaxamount())));
					TaxAmount.setText(receipt.getTaxamount());
					TxnID.setText(receipt.getPdetails().getModeid());
					;
					TxnDate.getEditor().setText(receipt.getPdetails().getModedate());
					Bank.setText(receipt.getPdetails().getModebank());
					PaidBy.setText(receipt.getPdetails().getPaidby());
					if (receipt.getPdetails().getCredit().equals("Yes"))
						CreditYes.setSelected(true);
					else
						CreditNo.setSelected(true);

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

		// DataSource
		// This is simple example, no database.
		// then using empty datasource.
		JRDataSource dataSource = new JREmptyDataSource();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		// Make sure the output directory exists.
		File outDir = new File("D:/Reports/Receipt");
		outDir.mkdirs();

		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new java.util.Date();
		System.out.println(date);
		String path = outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");

		// Export to PDF.
		JasperExportManager.exportReportToPdfFile(jasperPrint, path);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Report");
		alert.setHeaderText(null);
		alert.setContentText("Report downloaded successfully.");
		alert.showAndWait();
	}

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
		cID.getSelectionModel().clearSelection();
		ReceiptID.setText(null);
		ReceiptDate.getEditor().clear();
		PaidAmount.clear();
		PaymentMode.getSelectionModel().clearSelection();
		BaseAmount.setText(null);
		TaxAmount.setText(null);
		TxnID.clear();
		TxnDate.getEditor().clear();
		Bank.clear();
		PaidBy.clear();
		CreditYes.setSelected(false);
		CreditNo.setSelected(true);
		ClientName.setText(null);
	}

	public ToggleGroup getCredit() {
		return credit;
	}
	
	public String convertDate(LocalDate date) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");		
		return dtf.format(date);
		
	}
}
