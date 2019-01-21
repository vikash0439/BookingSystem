package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.config.StageManager;
import com.booking.service.SlotService;
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
public class SlotController implements Initializable{
	
	@FXML 
	private Label SlotID;
	@FXML
	private TextField Slot; 
	@FXML
	private TextField SlotTiming;
	
	@FXML
	private TableView<com.booking.bean.Slot> slottable;

	@FXML
	private TableColumn<com.booking.bean.Slot, Long> colSlotID;
	
	@FXML
	private TableColumn<com.booking.bean.Slot, String> colSlot;
	@FXML
	private TableColumn<com.booking.bean.Slot, String> colSlotTiming;
	@FXML
	private TableColumn<com.booking.bean.Slot, Boolean> colEdit;

	@FXML
	private Button reset;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired 
	private SlotService slotService;
	
    private ObservableList<com.booking.bean.Slot> slotList = FXCollections.observableArrayList();
	
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
		if(emptyValidation("Slot Name", Slot.getText().isEmpty())) {
		if (SlotID.getText() == null || SlotID.getText() == "") {
			com.booking.bean.Slot slot = new com.booking.bean.Slot();
			slot.setSlot(Slot.getText());
			slot.setTimings(SlotTiming.getText());
			
			slotService.save(slot);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Slot added.");
			alert.setHeaderText(null);
			alert.setContentText("Slot:  " + Slot.getText() + "  has been added.");
			alert.showAndWait();
		} else {
			com.booking.bean.Slot slot = slotService.find(Long.parseLong(SlotID.getText()));
			slot.setSlot(Slot.getText());
			slot.setTimings(SlotTiming.getText());
			slotService.save(slot);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Slot added.");
			alert.setHeaderText(null);
			alert.setContentText("Slot:  " + Slot.getText() + "  has been added.");
			alert.showAndWait();
		 }
		}
		slottable();
		clearFields();
		
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		slottable();
		clearFields();
		
	}
	
	public void slottable() {
		/*
		 * Set All userTable column properties
		 */
		colSlotID.setCellValueFactory(new PropertyValueFactory<>("slotid"));
		colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
		colSlotTiming.setCellValueFactory(new PropertyValueFactory<>("timings"));
		colEdit.setCellFactory(cellFactory);

		slotList.clear();
		slotList.addAll(slotService.getSlot());
		slottable.setItems(slotList);
	}

	Callback<TableColumn<com.booking.bean.Slot, Boolean>, TableCell<com.booking.bean.Slot, Boolean>> cellFactory = new Callback<TableColumn<com.booking.bean.Slot, Boolean>, TableCell<com.booking.bean.Slot, Boolean>>() {
		@Override
		public TableCell<com.booking.bean.Slot, Boolean> call(final TableColumn<com.booking.bean.Slot, Boolean> param) {
			final TableCell<com.booking.bean.Slot, Boolean> cell = new TableCell<com.booking.bean.Slot, Boolean>() {
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
							com.booking.bean.Slot slot = getTableView().getItems().get(getIndex());
							updateSlot(slot);
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

				private void updateSlot(com.booking.bean.Slot slot) {
					SlotID.setText(Long.toString(slot.getSlotid()));
					Slot.setText(slot.getSlot());
					SlotTiming.setText(slot.getTimings());
				
				}
			};
			return cell;
		}
	};
	

	
	private void clearFields() {
		SlotID.setText(null);
		Slot.clear();
		SlotTiming.clear();

	}
	
	/*
	 * Validations
	 */
	private boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
			Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
	
	private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }	
	private void validationAlert(String field, boolean empty){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Client")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}

}
