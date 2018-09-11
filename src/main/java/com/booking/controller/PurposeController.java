package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.config.StageManager;
import com.booking.service.PurposeService;
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
public class PurposeController implements Initializable{
	
	@FXML 
	private Label PurposeID;
	@FXML
	private TextField Purpose; 
	
	@FXML
	private TableView<com.booking.bean.Purpose> purposetable;

	@FXML
	private TableColumn<com.booking.bean.Purpose, Long> colPurposeID;
	
	@FXML
	private TableColumn<com.booking.bean.Purpose, String> colPurpose;
	
	@FXML
	private TableColumn<com.booking.bean.Purpose, Boolean> colEdit;

	@FXML
	private Button reset;
	@Lazy
	@Autowired
	private StageManager stageManager;
	@Autowired 
	private PurposeService purposeService;
	
    private ObservableList<com.booking.bean.Purpose> purposeList = FXCollections.observableArrayList();
	
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
	private void savePurpose(ActionEvent event) {
		if (PurposeID.getText() == null || PurposeID.getText() == "") {
			com.booking.bean.Purpose purpose = new com.booking.bean.Purpose();
			purpose.setPurpose(Purpose.getText());
			
			purposeService.save(purpose);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Purpose added.");
			alert.setHeaderText(null);
			alert.setContentText("Purpose:  " + Purpose.getText() + "  has been added.");
			alert.showAndWait();
		} else {
			com.booking.bean.Purpose purpose = purposeService.find(Long.parseLong(PurposeID.getText()));
			purpose.setPurpose(Purpose.getText());
			
			purposeService.save(purpose);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Purpose updated.");
			alert.setHeaderText(null);
			alert.setContentText("Slot:  " + Purpose.getText() + "  has been updated.");
			alert.showAndWait();
		}
		purposetable();
		clearFields();
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		purposetable();
		clearFields();
		
	}
	
	public void purposetable() {
		/*
		 * Set All userTable column properties
		 */
		colPurposeID.setCellValueFactory(new PropertyValueFactory<>("purposeid"));
		colPurpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
		colEdit.setCellFactory(cellFactory);

		purposeList.clear();
		purposeList.addAll(purposeService.getPurpose());
		purposetable.setItems(purposeList);
	}

	Callback<TableColumn<com.booking.bean.Purpose, Boolean>, TableCell<com.booking.bean.Purpose, Boolean>> cellFactory = new Callback<TableColumn<com.booking.bean.Purpose, Boolean>, TableCell<com.booking.bean.Purpose, Boolean>>() {
		@Override
		public TableCell<com.booking.bean.Purpose, Boolean> call(final TableColumn<com.booking.bean.Purpose, Boolean> param) {
			final TableCell<com.booking.bean.Purpose, Boolean> cell = new TableCell<com.booking.bean.Purpose, Boolean>() {
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
							com.booking.bean.Purpose purpose = getTableView().getItems().get(getIndex());
							updateSlot(purpose);
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

				private void updateSlot(com.booking.bean.Purpose purpose) {
					PurposeID.setText(Long.toString(purpose.getPurposeid()));
					Purpose.setText(purpose.getPurpose());
				
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		PurposeID.setText(null);
		Purpose.clear();
		

	}

}
