package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Tax;
import com.booking.config.StageManager;
import com.booking.service.TaxService;
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
public class TaxController implements Initializable{
	
	@FXML private Label taxid;
	@FXML private TextField saccode;
	@FXML private TextField description;
	@FXML private TextField gst;
	
	
	@FXML
	private TableView<Tax> taxtable;

	@FXML
	private TableColumn<Tax, Long> colTaxID;
	
	@FXML
	private TableColumn<Tax, String> colSaccode;
	
	@FXML
	private TableColumn<Tax, String> colDescription;
	
	@FXML
	private TableColumn<Tax, String> colgst;
	@FXML
	private TableColumn<Tax, Boolean> colEdit;

	@FXML
	private Button reset;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired 
	private TaxService taxService;
	
    private ObservableList<Tax> taxList = FXCollections.observableArrayList();
	
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
	private void saveTax(ActionEvent event) {
		if(emptyValidation("SAC Code", saccode.getText().isEmpty())) {
		if (taxid.getText() == null || taxid.getText() == "") {
			Tax tax = new Tax();
			tax.setSaccode(saccode.getText());
			tax.setDescription(description.getText());
			tax.setGst(gst.getText());
		
			
			taxService.save(tax);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tax updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Tax:  " + saccode.getText() + "  has been created.");
			alert.showAndWait();
		} else {
			Tax tax = taxService.find(Long.parseLong(taxid.getText()));
			tax.setSaccode(saccode.getText());
			tax.setDescription(description.getText());
			tax.setGst(gst.getText());
			
			
			taxService.save(tax);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tax updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Tax:  " + saccode.getText() + "  has been updated.");
			alert.showAndWait();
		}
		}
		taxtable();
		clearFields();
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		taxtable();
		clearFields();
		
	}
	
	public void taxtable() {
		/*
		 * Set All userTable column properties
		 */
		colTaxID.setCellValueFactory(new PropertyValueFactory<>("taxid"));
		colSaccode.setCellValueFactory(new PropertyValueFactory<>("saccode"));
		colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		colgst.setCellValueFactory(new PropertyValueFactory<>("gst"));
		colEdit.setCellFactory(cellFactory);

		taxList.clear();
		taxList.addAll(taxService.getTax());
		taxtable.setItems(taxList);
	}

	Callback<TableColumn<Tax, Boolean>, TableCell<Tax, Boolean>> cellFactory = new Callback<TableColumn<Tax, Boolean>, TableCell<Tax, Boolean>>() {
		@Override
		public TableCell<Tax, Boolean> call(final TableColumn<Tax, Boolean> param) {
			final TableCell<Tax, Boolean> cell = new TableCell<Tax, Boolean>() {
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
							Tax tax = getTableView().getItems().get(getIndex());
							updateTax(tax);
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

				private void updateTax(Tax tax) {
					taxid.setText(Long.toString(tax.getTaxid()));
					saccode.setText(tax.getSaccode());
					description.setText(description.getText());
					gst.setText(tax.getGst());
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		taxid.setText(null);
		saccode.clear();
		description.clear();
		gst.clear();

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

}
