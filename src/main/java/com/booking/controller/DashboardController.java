package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Contract;
import com.booking.config.StageManager;
import com.booking.service.ContractService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;

@Controller
public class DashboardController implements Initializable{
	
	@FXML
	private Label ContractID;
	@FXML
	private Label ShowDate;
	@FXML
	private Label Purpose;
	@FXML
	private Label CustomerName;
	@FXML
	private Label ShowName;
	@FXML
	private Label Slot;
	@FXML
	private Label Services;
	@FXML
	private Label Total;
	
	@FXML
	private TableView<Contract> contracttable;
	@FXML
	private TableColumn<Contract, String> colContractID;
	@FXML
	private TableColumn<Contract, String> colPurpose;
	@FXML
	private TableColumn<Contract, String> colShowName;
	@FXML
	private TableColumn<Contract, String> colShowDetail;
	@FXML
	private TableColumn<Contract, String> colShowTime;
	@FXML
	private TableColumn<Contract, String> colCustomerName;
	@FXML
	private TableColumn<Contract, String> colRepName;
	@FXML
	private TableColumn<Contract, String> colRepEmail;
	@FXML
	private TableColumn<Contract, String> colRepMobile;
	@FXML
	private TableColumn<Contract, LocalDate> colShowDate;
	@FXML
	private TableColumn<Contract, String> colSlot;
	@FXML
	private TableColumn<Contract, String> colServices;
	@FXML
	private TableColumn<Contract, String> colCharges;
	@FXML
	private TableColumn<Contract, String> colTaxAmount;
	@FXML
	private TableColumn<Contract, String> colTotal;
	@FXML
	private TableColumn<Contract, Boolean> colEdit;

	@Lazy
    @Autowired
    private StageManager stageManager;
	@Autowired
	private ContractService contractService;
	
	private ObservableList<Contract> contractList = FXCollections.observableArrayList();
	
	@FXML
    private void logout(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		contracttable();
		clearFields();
	}
	
	public void contracttable() {
		/*
		 * Set All userTable column properties
		 */
		colShowDate.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			 String pattern = "dd-MM-yyyy";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 }));
		
		
		colContractID.setCellValueFactory(new PropertyValueFactory<>("contractid"));
		colPurpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
		colShowDate.setCellValueFactory(new PropertyValueFactory<>("showdate"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customername"));
		colShowName.setCellValueFactory(new PropertyValueFactory<>("showname"));
		colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
		colServices.setCellValueFactory(new PropertyValueFactory<>("services"));
		colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		colEdit.setCellFactory(cellFactory);

		contractList.clear();
		contractList.addAll(contractService.getContract());
		contracttable.setItems(contractList);
	}

	Callback<TableColumn<Contract, Boolean>, TableCell<Contract, Boolean>> cellFactory = new Callback<TableColumn<Contract, Boolean>, TableCell<Contract, Boolean>>() {
		@Override
		public TableCell<Contract, Boolean> call(final TableColumn<Contract, Boolean> param) {
			final TableCell<Contract, Boolean> cell = new TableCell<Contract, Boolean>() {
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
							Contract contract = getTableView().getItems().get(getIndex());
							updateContract(contract);
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

				private void updateContract(Contract contract) {
					ContractID.setText(Long.toString(contract.getContractid()));
					ShowDate.setVisible(true);
				    Purpose.setText(contract.getPurpose());
					CustomerName.setText(contract.getCustomername());
					ShowName.setText(contract.getShowname());
					Slot.setText(contract.getSlot());
					Services.setText(contract.getServices());
					Total.setText(contract.getTotal());
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		ContractID.setText(null);
		
	}

}
