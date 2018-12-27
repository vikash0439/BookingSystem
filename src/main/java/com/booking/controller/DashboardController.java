package com.booking.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Booking;
import com.booking.bean.Receipt;
import com.booking.bean.Rep;
import com.booking.config.StageManager;
import com.booking.service.BookingService;
import com.booking.service.ContractService;
import com.booking.service.ReceiptService;
import com.booking.service.RepService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
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
public class DashboardController implements Initializable {

	@FXML
	private Label ContractID;
	@FXML
	private Label ShowDate;
	@FXML
	private Label Purpose;
	@FXML
	private Label CustomerName;
	@FXML
	private Label RepName;
	@FXML
	private Label RepEmail;
	@FXML
	private Label RepMobile;
	@FXML
	private Label ShowName;
	@FXML
	private TextField searchField;
	@FXML
	private Label repname;
	@FXML
	private Label repemail;
	@FXML
	private Label repmobile;
	@FXML
	private Label noc;

	@FXML
	TableView<Booking> bookingtable;
	@FXML
	TableColumn<Booking, String> colServiceDate;
	@FXML
	TableColumn<String, Boolean> colDay;
	@FXML
	TableColumn<Booking, String> colSlot;
	@FXML
	TableColumn<Booking, String> colService;
	@FXML
	TableColumn<Booking, String> colClient;
	@FXML
	TableColumn<Booking, String> ColContractid;
	@FXML
	TableColumn<Booking, String> colBookingDate;
	@FXML
	TableColumn<Booking, String> colPact;
	@FXML
	TableColumn<Booking, String> colNOC;
	@FXML
	TableColumn<Booking, String> colReceipt;
	@FXML
	TableColumn<Booking, String> colInvoice;
	@FXML
	TableColumn<String, Boolean> colRemaining;
	@FXML
	TableColumn<Booking, Boolean> colEdit;

	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired
	private RepService repService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private ContractService contractService;

	private ObservableList<Booking> bookingList = FXCollections.observableArrayList();

	@FXML
	private void logout(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	private void report(ActionEvent event) throws IOException {

		try {

			System.out.println("From Contract report try block");
			JasperReport jasperReport;
			try {
				jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/Contract.jrxml"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/Contract.jasper"));
			}

			Map<String, Object> parameters = new HashMap<String, Object>();

			HashMap<String, Object> cont = new HashMap<String, Object>();
			cont.put("contractid", Long.valueOf(ContractID.getText()));

			List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
			list.add(cont);

			JRDataSource jRDataSource = new JRBeanCollectionDataSource(list);
			parameters.put("jRDataSource", list);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jRDataSource);
			File outDir = new File("Reports/Contract");
			outDir.mkdirs();

			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
			Date date = new java.util.Date();
			System.out.println(date);
			String path = outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");

			JasperExportManager.exportReportToPdfFile(jasperPrint, path);

			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
			alert.setTitle("Report");
			alert.setHeaderText("Contract");
			alert.setContentText("Contract report downloaded successfully");
			alert.showAndWait();
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			System.out.println("JR Exception");
			e.printStackTrace();
		}

	}

	@FXML
	private void cancel(ActionEvent event) throws IOException {
		System.out.println("Cancel");

		Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation!");
		alert.setHeaderText("All booking in this contract will be cancelled.");
		alert.setContentText("Are you sure you want to cancel the contract?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			String cid = ContractID.getText();

			contractService.updateStatus(Long.parseLong((String) cid));

		} else {
			// ... user chose CANCEL or closed the dialog
		}
		dashboard(event);

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
	public void allcontract(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.ALLCONTRACT);
	}

	@FXML
	public void statecode(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.STATECODE);
	}
	
	@FXML
	public void reports(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.REPORTS);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		contracttable();
	}

	public void contracttable() {
		/*
		 * Set All userTable column properties
		 */

		colServiceDate.setCellValueFactory(new PropertyValueFactory<>("servicedate"));
		colDay.setCellFactory(dateday);
		colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
		colService.setCellValueFactory(new PropertyValueFactory<>("servicename"));
		colClient.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
				return new SimpleStringProperty(param.getValue().getContract().getCustomer().getCustomername());
			}
		});
		ColContractid.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
				return new SimpleStringProperty(Long.toString(param.getValue().getContract().getContractid()));
			}
		});
		colBookingDate.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
				return new SimpleStringProperty(param.getValue().getContract().getBookingdate());
			}
		});
		colPact.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
				return new SimpleStringProperty(param.getValue().getContract().getPact());
			}
		});
		colNOC
				.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
						return new SimpleStringProperty(param.getValue().getContract().getNoc());
					}
				});

		colReceipt.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
				return new SimpleStringProperty(param.getValue().getContract().getReceipt().toString());
			}
		});
		colRemaining.setCellFactory(remainingAmount);
		colInvoice.setCellValueFactory(new Callback<CellDataFeatures<Booking, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
				try {
					return new SimpleStringProperty(param.getValue().getContract().getInvoice().getInvoicedate());
				} catch (Exception e) {
					System.out.println("No invoice created");
				}
				return null;
			}
		});
		colEdit.setCellFactory(cellFactory);

		bookingList.clear();
		bookingList.addAll(bookingService.getBooking());
		bookingtable.setItems(bookingList);

		FilteredList<Booking> filteredData = new FilteredList<>(bookingList, e -> true);
		searchField.setOnKeyPressed(e -> {
			searchField.textProperty().addListener((obeservableValue, oldValue, newValue) -> {
				filteredData.setPredicate((Predicate<? super Booking>) booking -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					if (Long.toString(booking.getContract().getContractid()).contains(newValue)) {
						return true;
					} else if (booking.getServicedate().contains(newValue)) {
						return true;
					} else if (booking.getSlot().toLowerCase().contains(newValue)) {
						return true;
					} else if (booking.getServicename().toLowerCase().contains(newValue)) {
						return true;
					} else if (booking.getContract().getCustomer().getCustomername().toLowerCase().contains(newValue)) {
						return true;
					}
					return false;
				});
			});
			SortedList<Booking> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(bookingtable.comparatorProperty());
			bookingtable.refresh();
			bookingtable.setItems(sortedData);
		});
	}

	public Callback<TableColumn<String, Boolean>, TableCell<String, Boolean>> remainingAmount = new Callback<TableColumn<String, Boolean>, TableCell<String, Boolean>>() {

		@Override
		public TableCell<String, Boolean> call(final TableColumn<String, Boolean> param) {
			final TableCell<String, Boolean> cell = new TableCell<String, Boolean>() {

				@Override
				public void updateItem(Boolean check, boolean empty) {
					super.updateItem(check, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {

						TableRow<Booking> currentRow = getTableRow();
						String pact = currentRow.getItem().getContract().getPact();

						List<Receipt> receiptlist = currentRow.getItem().getContract().getReceipt();
						double totalamount = 0;
						for (int i = 0; i < receiptlist.size(); i++) {
							System.out.println(receiptlist.get(i).getReceiptid());
							double receiptamount = Double.parseDouble(receiptService.PaidAmount(receiptlist.get(i).getReceiptid()));
							System.out.println("Paid Amount : " + receiptamount);
							totalamount = receiptamount + totalamount;
						}

						double remaining = Double.parseDouble(pact) - totalamount;

						colDay.setStyle("-fx-background-color: transparent;");
						setAlignment(Pos.CENTER);
						setText(String.valueOf(remaining));
					}
				}
			};
			return cell;
		}
	};

	public Callback<TableColumn<String, Boolean>, TableCell<String, Boolean>> dateday = new Callback<TableColumn<String, Boolean>, TableCell<String, Boolean>>() {

		@Override
		public TableCell<String, Boolean> call(final TableColumn<String, Boolean> param) {
			final TableCell<String, Boolean> cell = new TableCell<String, Boolean>() {

				@Override
				public void updateItem(Boolean check, boolean empty) {
					super.updateItem(check, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {

						TableRow<Booking> currentRow = getTableRow();
						String servicedate = currentRow.getItem().getServicedate();
						Date date1;
						try {
							date1 = new SimpleDateFormat("dd/MM/yyyy").parse(servicedate);
							SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
							colDay.setStyle("-fx-background-color: transparent;");
							setAlignment(Pos.CENTER);
							setText(simpleDateformat.format(date1));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

			};
			return cell;
		}

	};

	Callback<TableColumn<Booking, Boolean>, TableCell<Booking, Boolean>> cellFactory = new Callback<TableColumn<Booking, Boolean>, TableCell<Booking, Boolean>>() {
		@Override
		public TableCell<Booking, Boolean> call(final TableColumn<Booking, Boolean> param) {
			final TableCell<Booking, Boolean> cell = new TableCell<Booking, Boolean>() {
				Image imgEdit = new Image(getClass().getResourceAsStream("/images/view.png"));
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
							updateBooking(booking);
						});
						TableRow<Booking> currentRow = getTableRow();

						if (currentRow.getItem().getContract().getCustomer().getCustomername()
								.equalsIgnoreCase("SRCPA")) { // indicates srcpa
							currentRow.setStyle("-fx-background-color:#ffffcc");
						} else if (currentRow.getItem().getContract().getInvoice() != null && currentRow.getItem().getContract().getOverride() == null) { // indicates invoice																								// created
							currentRow.setStyle("-fx-background-color:#b3c6ff");
						} else if (currentRow.getItem().getContract().getOverride() != null && currentRow.getItem().getContract().getInvoice() != null) { // indicates cancelled
							currentRow.setStyle("-fx-background-color: #ff9999");
						}
						else if (currentRow.getItem().getContract().getOverride() != null) { // indicates cancelled
							currentRow.setStyle("-fx-background-color: #ff9999");
						}else {

						}

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

				private void updateBooking(Booking booking) {
					ContractID.setText(Long.toString(booking.getContract().getContractid()));
					Purpose.setText(booking.getContract().getPurpose());
					CustomerName.setText(booking.getContract().getCustomer().getCustomername());
                    noc.setText(booking.getContract().getNoc());
					repname.setText(booking.getContract().getRepname());

					Rep r = repService.findbyName(booking.getContract().getRepname());

					repemail.setText(r.getRepemail());
					repmobile.setText(r.getRepmobile());

				}
			};
			return cell;
		}
	};

}
