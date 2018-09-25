package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Customer;
import com.booking.bean.Rep;
import com.booking.config.StageManager;
import com.booking.service.CustomerService;
import com.booking.service.RepService;
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
import javafx.scene.control.TablePosition;
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
	private TextField customername;

	@FXML
	private TextField landline;

	@FXML
	private TextField website;

	@FXML
	private TextField address;

	@FXML
	private TextField category;
	@FXML
	private TextField statecode;
	@FXML
	private TextField gstno;

	@FXML
	private TextField remark;
	@FXML
	private TextField repname;
	@FXML
	private TextField repmobile;
	@FXML
	private TextField repemail;
	@FXML
	private TableView<Customer> customerTable;
	@FXML
	private TableView<Rep> repTable;
	@FXML
	private TableColumn<Customer, Long> colcustomerid;
	@FXML
	private TableColumn<Customer, String> colcustomername;
	@FXML
	private TableColumn<Customer, String> collandline;
	@FXML
	private TableColumn<Customer, String> colwebsite;
	@FXML
	private TableColumn<Customer, String> coladdress;
	@FXML
	private TableColumn<Customer, String> colstatecode;

	@FXML
	private TableColumn<Customer, String> colcategory;
	@FXML
	private TableColumn<Customer, String> colgstno;

	@FXML
	private TableColumn<Customer, String> colremark;
	@FXML
	private TableColumn<Customer, Long> colrepid;
	@FXML
	private TableColumn<Customer, String> colrepname;
	@FXML
	private TableColumn<Customer, String> colrepmobile;
	@FXML
	private TableColumn<Customer, String> colrepemail;
	@FXML
	private TableColumn<Customer, Boolean> colEdit;
	@FXML
	private Button reset;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private RepService repService;

	@Lazy
	@Autowired
	private StageManager stageManager;

	private ObservableList<Customer> customerList = FXCollections.observableArrayList();
	private ObservableList<Rep> repList = FXCollections.observableArrayList();
	
	/* Event Methods */

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
	private void saveCustomer(ActionEvent event) {

		if (customerid.getText() == null || customerid.getText() == "") {
			Customer customer = new Customer();
			customer.setCustomername(customername.getText());
			customer.setWebsite(website.getText());
			customer.setLandline(landline.getText());
			customer.setAddress(address.getText());
			customer.setStatecode(statecode.getText());
			customer.setCategory(category.getText());
			customer.setGstno(gstno.getText());
			customer.setRemark(remark.getText());	
			
			Rep r = new Rep();
			r.setRepname(repname.getText());
			r.setRepmobile(repmobile.getText());
			r.setRepemail(repemail.getText());
			
			List<Rep> rep = new ArrayList<Rep>();
			rep.add(r);
			
			/* establishing link in onetomany and manytoone way */
			r.setCustomer(customer);			
			customer.setRep(rep);
			
			customerService.save(customer);
			repService.save(r);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Customer updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("The customer " + customername.getText() + "  has been saved.");
			alert.showAndWait();
		} else {
			Customer customer = customerService.find(Long.parseLong(customerid.getText()));
			customer.setCustomername(customername.getText());
			customer.setWebsite(website.getText());
			customer.setLandline(landline.getText());
			customer.setAddress(address.getText());
			customer.setCategory(category.getText());
			customer.setGstno(gstno.getText());
			customer.setRemark(remark.getText());
        		
			customerService.save(customer);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Customer updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("The customer " + customername.getText() + "  has been updated.");
			alert.showAndWait();
		}
		customertable();
		reptable();
		clearFields();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		customertable();
		reptable();
		clearFields();

	}

	public void customertable() {
		/*
		 * Set All userTable column properties
		 */
		colcustomerid.setCellValueFactory(new PropertyValueFactory<>("customerid"));
		colcustomername.setCellValueFactory(new PropertyValueFactory<>("customername"));
		collandline.setCellValueFactory(new PropertyValueFactory<>("landline"));
		colwebsite.setCellValueFactory(new PropertyValueFactory<>("website"));
		coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colstatecode.setCellValueFactory(new PropertyValueFactory<>("statecode"));
		colcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		colgstno.setCellValueFactory(new PropertyValueFactory<>("gstno"));
		colremark.setCellValueFactory(new PropertyValueFactory<>("remark"));
		colEdit.setCellFactory(cellFactory);

		customerList.clear();
		customerList.addAll(customerService.getCustomer());
		customerTable.setItems(customerList);
	}
	public void reptable() {
		colrepid.setCellValueFactory(new PropertyValueFactory<>("repid"));
		colrepname.setCellValueFactory(new PropertyValueFactory<>("repname"));
		colrepemail.setCellValueFactory(new PropertyValueFactory<>("repemail"));
		colrepmobile.setCellValueFactory(new PropertyValueFactory<>("repmobile"));
		
		repList.clear();
		repList.addAll(repService.getRep());
		repTable.setItems(repList);
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
					customername.setText(customer.getCustomername());
					website.setText(customer.getWebsite());
					landline.setText(customer.getLandline());
					address.setText(customer.getAddress());
					category.setText(customer.getCategory());
					gstno.setText(customer.getGstno());
					remark.setText(customer.getRemark());
				}
			};
			return cell;
		}
	};
	
	 @FXML
	    private void repClient(ActionEvent event){
		 
		 TablePosition<?, ?> pos = customerTable.getSelectionModel().getSelectedCells().get(0);
		 long id = pos.getRow();
		 System.out.println("From context menu table:" +id);
		 repList.clear();
			repList.addAll(repService.getByCustomerid(id));
			repTable.setItems(repList);
	    			
	    	
	    }
	
	private void clearFields() {
		customerid.setText(null);
		customername.clear();
		website.clear();
		landline.clear();
		address.clear();
		category.clear();
		gstno.clear();	
		remark.clear();
		repname.clear();
		repemail.clear();
		repmobile.clear();
	}

}
