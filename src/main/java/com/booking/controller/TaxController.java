package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

@Controller
public class TaxController implements Initializable{
	
	@FXML 
	private Label TaxID;
	@FXML
	private TextField TaxName;
	@FXML 
	private ToggleGroup TaxForm;
	@FXML 
	private RadioButton Percentage;
	@FXML 
	private RadioButton Amount;
	@FXML
	private TextField TaxCharges;
	@FXML 
	private ToggleGroup Applied;
	@FXML 
	private RadioButton Directly;
	@FXML 
	private RadioButton OnTax;
	@FXML 
	private ToggleGroup TaxInUse;
	@FXML 
	private RadioButton Yes;
	@FXML 
	private RadioButton No;
	@FXML
	private TableView<Tax> taxtable;

	@FXML
	private TableColumn<Tax, Long> colTaxID;
	
	@FXML
	private TableColumn<Tax, String> colTaxName;
	
	@FXML
	private TableColumn<Tax, String> colTaxForm;
	
	@FXML
	private TableColumn<Tax, String> colTaxCharges;
	
	@FXML
	private TableColumn<Tax, String> colApplied;
	
	@FXML
	private TableColumn<Tax, String> colTaxInUse;
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
		if (TaxID.getText() == null || TaxID.getText() == "") {
			Tax tax = new Tax();
			tax.setTaxname(TaxName.getText());
			tax.setTaxform(Percentage.isSelected() ? "Percentage" : "Amount");
			tax.setTaxcharges(TaxCharges.getText());
			tax.setApplied(Directly.isSelected() ? "Directly" : "Tax");
			tax.setTaxinuse(Yes.isSelected() ? "Yes"  : "No");
			
			taxService.save(tax);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tax updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Tax:  " + TaxName.getText() + "  has been created.");
			alert.showAndWait();
		} else {
			Tax tax = taxService.find(Long.parseLong(TaxID.getText()));
			tax.setTaxname(TaxName.getText());
			tax.setTaxform(Percentage.isSelected() ? "Percentage" : "Amount");
			tax.setTaxcharges(TaxCharges.getText());
			tax.setApplied(Directly.isSelected() ? "Directly" : "On Tax");
			tax.setTaxinuse(Yes.isSelected() ? "Yes"  : "No");
			
			taxService.save(tax);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tax updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Tax:  " + TaxName.getText() + "  has been updated.");
			alert.showAndWait();
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
		colTaxName.setCellValueFactory(new PropertyValueFactory<>("taxname"));
		colTaxForm.setCellValueFactory(new PropertyValueFactory<>("taxform"));
		colTaxCharges.setCellValueFactory(new PropertyValueFactory<>("taxcharges"));
		colApplied.setCellValueFactory(new PropertyValueFactory<>("applied"));
		colTaxInUse.setCellValueFactory(new PropertyValueFactory<>("taxinuse"));
		colEdit.setCellFactory(cellFactory);

		taxList.clear();
		taxList.addAll(taxService.getService());
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
					TaxID.setText(Long.toString(tax.getTaxid()));
					TaxName.setText(tax.getTaxname());
					if(tax.getTaxform().equals("Percentage")) Percentage.setSelected(true);else Amount.setSelected(true);
					TaxCharges.setText(tax.getTaxcharges());
					if(tax.getApplied().equals("Directly")) Directly.setSelected(true); else OnTax.setSelected(true);
					if(tax.getTaxinuse().equals("Yes")) Yes.setSelected(true); else No.setSelected(true);
					
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		TaxID.setText(null);
		TaxName.clear();
		Percentage.setSelected(true);
		TaxCharges.clear();
		Directly.setSelected(true);
		Yes.setSelected(true);

	}

}
