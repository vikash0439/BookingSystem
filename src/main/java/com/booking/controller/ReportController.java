package com.booking.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Customer;
import com.booking.config.StageManager;
import com.booking.service.BookingService;
import com.booking.service.ContractService;
import com.booking.service.CustomerService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

@Controller
public class ReportController {
	
	@FXML
	private ToggleGroup c;
	@FXML
	private Text type;
	
	@FXML
	private TableView<Customer> reportTable;
	@FXML
	private TableColumn<Customer, String> colCustomerName;
	@FXML
	private TableColumn<String, Boolean> colTotal;
	
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ContractService contractService;
	@Autowired
	private BookingService bookingService;
	
	
	
	private ObservableList<Customer> customerList = FXCollections.observableArrayList();
	
	@FXML
	private void logout(ActionEvent event) throws IOException {
		stageManager.switchScene(FxmlView.LOGIN);
	}

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
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
	
	
	 @FXML
	 public void getWeekday(ActionEvent event) {
		 
		 colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customername"));
		 colTotal.setCellFactory(totalcount);
		 
		 customerList.clear();
		 customerList.addAll(customerService.getCustomer());
		 reportTable.setItems(customerList);
		 
		 type.setText("Weekdays Customers");
		
	 }
	 
	 public Callback<TableColumn<String, Boolean>, TableCell<String, Boolean>> totalcount = new Callback<TableColumn<String, Boolean>, TableCell<String, Boolean>>() {

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

							TableRow<Customer> currentRow = getTableRow();
							String cname = currentRow.getItem().getCustomername();
							
							System.out.println("From getweekday : "+cname);
							Long id = customerService.getCustomerID(cname);
							Long count = contractService.getContractCount(id);
								colTotal.setStyle("-fx-background-color: transparent;");							
								setText(String.valueOf(count));
						}
					}

				};
				return cell;
			}

		};

	@FXML
	public void getOutStation(ActionEvent event) {
		
		 colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customername"));
		 customerList.clear();
		 customerList.addAll(customerService.getCustomerOutStation());
		 reportTable.refresh();
		 reportTable.setItems(customerList);
		 
		 type.setText("Out Station Customers");
		 
	}
	
	@FXML
	public void MorningShow(ActionEvent event) {
		
		colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customername"));
		
		 customerList.clear();
		 customerList.addAll(customerService.getMorningBooking());
		 reportTable.refresh();
		 reportTable.setItems(customerList);
		 
		 type.setText("Morning Show Customers");
		 
	}	 

}
