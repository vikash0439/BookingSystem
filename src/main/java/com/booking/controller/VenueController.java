package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Venue;
import com.booking.config.StageManager;
import com.booking.service.SlotService;
import com.booking.service.VenueService;
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
public class VenueController implements Initializable{
	
	@FXML 
	private Label VenueID;
	@FXML
	private TextField Date; 
	@FXML
	private TextField VenueName;
	@FXML
	private TextField Booked;
	
	@FXML
	private TableView<Venue> venuetable;

	@FXML
	private TableColumn<Venue, Long> colVenueID;
	
	@FXML
	private TableColumn<Venue, String> colVenueName;
	@FXML
	private TableColumn<Venue, String> colDate;
	@FXML
	private TableColumn<Venue, String> colBooked;
	@FXML
	private TableColumn<Venue, Boolean> colEdit;

	@FXML
	private Button reset;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired 
	private VenueService venueService;
	
    private ObservableList<Venue> venueList = FXCollections.observableArrayList();
	
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
	public void venue(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.VENUE); 		
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
	private void saveVenue(ActionEvent event) {
		if(emptyValidation("Venue Name", VenueName.getText().isEmpty())) {
		if (VenueID.getText() == null || VenueID.getText() == "") {
			Venue venue = new Venue();
			venue.setVenuename(VenueName.getText());
			venue.setDate(Date.getText());
			venue.setBooked(Booked.getText());
			
			venueService.save(venue);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Slot added.");
			alert.setHeaderText(null);
			alert.setContentText("Venue:  " + VenueName.getText() + "  has been added.");
			alert.showAndWait();
		} else {
			Venue venue = new Venue();
			venue.setVenuename(VenueName.getText());
			venue.setDate(Date.getText());
			venue.setBooked(Booked.getText());
			
			venueService.save(venue);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Slot added.");
			alert.setHeaderText(null);
			alert.setContentText("Venue:  " + VenueName.getText() + "  has been updated.");
			alert.showAndWait();
		 }
		}
		venuetable();
		clearFields();
		
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		venuetable();
		clearFields();
		
	}
	
	public void venuetable() {
		/*
		 * Set All userTable column properties
		 */
		colVenueID.setCellValueFactory(new PropertyValueFactory<>("venueid"));
		colVenueName.setCellValueFactory(new PropertyValueFactory<>("venuename"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colBooked.setCellValueFactory(new PropertyValueFactory<>("booked"));
		colEdit.setCellFactory(cellFactory);

		venueList.clear();
		venueList.addAll(venueService.getAllVenue());
		venuetable.setItems(venueList);
	}

	Callback<TableColumn<Venue, Boolean>, TableCell<Venue, Boolean>> cellFactory = new Callback<TableColumn<Venue, Boolean>, TableCell<Venue, Boolean>>() {
		@Override
		public TableCell<Venue, Boolean> call(final TableColumn<Venue, Boolean> param) {
			final TableCell<Venue, Boolean> cell = new TableCell<Venue, Boolean>() {
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
							Venue venue = getTableView().getItems().get(getIndex());
							updateVenue(venue);
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

				private void updateVenue(Venue venue) {
					
					VenueID.setText(Long.toString(venue.getVenueid()));
				    Date.setText(venue.getDate());
					VenueName.setText(venue.getVenuename());
					Booked.setText(venue.getBooked());
				
				}
			};
			return cell;
		}
	};
	

	
	private void clearFields() {
		VenueID.setText(null);
		Date.clear();
		VenueName.clear();
		Booked.clear();

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
