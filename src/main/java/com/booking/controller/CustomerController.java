package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Customer;
import com.booking.config.StageManager;
import com.booking.service.impl.CustomerServiceImpl;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

@Controller
public class CustomerController implements Initializable {

	@FXML
	private Label customerid;

	@FXML
	private TextField customerName;

	@FXML
	private TextField landline;

	@FXML
	private TextField website;

	@FXML
	private TextField address;

	@FXML
	private TextField category;

	@FXML
	private TextField remark;

	@FXML
	private TableView<Customer> customerTable;

	@FXML
	private TableColumn<Customer, Long> colcustomerid;

	@FXML
	private TableColumn<Customer, String> colcustomerName;

	@FXML
	private TableColumn<Customer, String> collandline;

	@FXML
	private TableColumn<Customer, String> colwebsite;

	@FXML
	private TableColumn<Customer, String> coladdress;

	@FXML
	private TableColumn<Customer, String> colcategory;

	@FXML
	private TableColumn<Customer, String> colremark;

	@FXML
	private TableColumn<Customer, Boolean> colEdit;

	@FXML
	private Button reset;

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Lazy
	@Autowired
	private StageManager stageManager;

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
	private void saveCustomer(ActionEvent event) {

		if (customerid.getText() == null || customerid.getText() == "") {
			Customer customer = new Customer();
			customer.setCustomerName(customerName.getText());
			customer.setWebsite(website.getText());
			customer.setLandline(landline.getText());
			customer.setAddress(address.getText());
			customer.setCategory(category.getText());
			customer.setRemark(remark.getText());

			customerServiceImpl.save(customer);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Customer updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("The customer " + customerName.getText() + "  has been saved.");
			alert.showAndWait();
		} else {
			Customer customer = customerServiceImpl.find(Long.parseLong(customerid.getText()));
			customer.setCustomerName(customerName.getText());
			customer.setWebsite(website.getText());
			customer.setLandline(landline.getText());
			customer.setAddress(address.getText());
			customer.setCategory(category.getText());
			customer.setRemark(remark.getText());

			customerServiceImpl.save(customer);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Customer updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("The customer " + customerName.getText() + "  has been updated.");
			alert.showAndWait();
		}
		customertable();
		clearFields();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		customertable();
		clearFields();

	}

	public void customertable() {
		/*
		 * Set All userTable column properties
		 */
		colcustomerid.setCellValueFactory(new PropertyValueFactory<>("customerid"));
		colcustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		collandline.setCellValueFactory(new PropertyValueFactory<>("landline"));
		colwebsite.setCellValueFactory(new PropertyValueFactory<>("website"));
		coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		colremark.setCellValueFactory(new PropertyValueFactory<>("remark"));
		colEdit.setCellFactory(cellFactory);

		customerList.clear();
		customerList.addAll(customerServiceImpl.getCustomer());
		customerTable.setItems(customerList);
	}

	Callback<TableColumn<Customer, Boolean>, TableCell<Customer, Boolean>> cellFactory = new Callback<TableColumn<Customer, Boolean>, TableCell<Customer, Boolean>>() {
		@Override
		public TableCell<Customer, Boolean> call(final TableColumn<Customer, Boolean> param) {
			final TableCell<Customer, Boolean> cell = new TableCell<Customer, Boolean>() {
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
							Customer customer = getTableView().getItems().get(getIndex());
							updateCustomer(customer);
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

				private void updateCustomer(Customer customer) {
					customerid.setText(Long.toString(customer.getCustomerid()));
					customerName.setText(customer.getCustomerName());
					website.setText(customer.getWebsite());
					landline.setText(customer.getLandline());
					address.setText(customer.getAddress());
					category.setText(customer.getCategory());
					remark.setText(customer.getRemark());
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		customerid.setText(null);
		customerName.clear();
		website.clear();
		landline.clear();
		address.clear();
		category.clear();
		remark.clear();
	}

}
