package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.config.StageManager;
import com.booking.service.StateCodeService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

@Controller
public class StateCodeController implements Initializable{

   

    @FXML
    private Label StateCodeID;

    @FXML
    private TextField StateName;

    @FXML
    private TextField StateCode;

    @FXML
    private Button reset;

    @FXML
    private TableView<com.booking.bean.StateCode> statecodetable;

    @FXML
    private TableColumn<com.booking.bean.StateCode, Long> colSateCodeID;

    @FXML
    private TableColumn<com.booking.bean.StateCode, String> colStateName;
    @FXML
    private TableColumn<com.booking.bean.StateCode, String> colStateCode;

    @FXML
    private TableColumn<com.booking.bean.StateCode, Boolean> colEdit;
    @Lazy
	@Autowired
	private StageManager stageManager;
    @Autowired
	private StateCodeService stateCodeService;
    
    private ObservableList<com.booking.bean.StateCode> statecodeList = FXCollections.observableArrayList();
    
    

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
	private void savePurpose(ActionEvent event) {
		if (StateCodeID.getText() == null || StateCodeID.getText() == "") {
			com.booking.bean.StateCode sc = new com.booking.bean.StateCode();
			sc.setStatename(StateName.getText());
			sc.setStatecode(StateCode.getText());
			
			stateCodeService.save(sc);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("State Code added.");
			alert.setHeaderText(null);
			alert.setContentText(StateName.getText() + "  code has been added.");
			alert.showAndWait();
		} else {
			
			com.booking.bean.StateCode sc = stateCodeService.find(Long.parseLong(StateCodeID.getText()));
			sc.setStatename(StateName.getText());
			sc.setStatecode(StateCode.getText());
			
			stateCodeService.save(sc);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Purpose updated.");
			alert.setHeaderText(null);
			alert.setContentText(StateName.getText() + "  code has been added.");
			alert.showAndWait();
		}
		statecodetable();
		clearFields();
	}

	

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		statecodetable();
		clearFields();
		
	}
	
	public void statecodetable() {
		/*
		 * Set All userTable column properties
		 */
		colSateCodeID.setCellValueFactory(new PropertyValueFactory<>("statecodeid"));
		colStateName.setCellValueFactory(new PropertyValueFactory<>("statename"));
		colStateCode.setCellValueFactory(new PropertyValueFactory<>("statecode"));
		colEdit.setCellFactory(cellFactory);

		statecodeList.clear();
		statecodeList.addAll(stateCodeService.getStateCode());
		statecodetable.setItems(statecodeList);
	}

	Callback<TableColumn<com.booking.bean.StateCode, Boolean>, TableCell<com.booking.bean.StateCode, Boolean>> cellFactory = new Callback<TableColumn<com.booking.bean.StateCode, Boolean>, TableCell<com.booking.bean.StateCode, Boolean>>() {
		@Override
		public TableCell<com.booking.bean.StateCode, Boolean> call(final TableColumn<com.booking.bean.StateCode, Boolean> param) {
			final TableCell<com.booking.bean.StateCode, Boolean> cell = new TableCell<com.booking.bean.StateCode, Boolean>() {
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
							com.booking.bean.StateCode sc = getTableView().getItems().get(getIndex());
							updateSlot(sc);
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

				private void updateSlot(com.booking.bean.StateCode sc) {
					StateCodeID.setText(Long.toString(sc.getStatecodeid()));
					StateName.setText(sc.getStatename());
					StateCode.setText(sc.getStatecode());
				
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		StateCodeID.setText(null);
		StateName.clear();
		StateCode.clear();

	}
}

