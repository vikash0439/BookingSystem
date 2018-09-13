package com.booking.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Service;
import com.booking.config.StageManager;
import com.booking.service.ServiceService;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class ServiceController implements Initializable{
	
	@FXML
	private Label ServiceID;

	@FXML
	private TextField ServiceName;
	
	@FXML
	private TextField ServiceInUse;
	
	@FXML
	private TextField ServiceCharges;
	
	@FXML
	private TextField CancelCharges;
	
	@FXML
	private TableView<Service> servicetable;

	@FXML
	private TableColumn<Service, Long> colServiceID;
	
	@FXML
	private TableColumn<Service, String> colServiceName;
	
	@FXML
	private TableColumn<Service, String> colServiceInUse;
	
	@FXML
	private TableColumn<Service, String> colServiceCharges;
	
	@FXML
	private TableColumn<Service, String> colCancelCharges;
	
	@FXML
	private TableColumn<Service, Boolean> colEdit;

	@FXML
	private Button reset;
	
	@Lazy
	@Autowired
	private StageManager stageManager;
	
	@Autowired
	private ServiceService serviceService;
	
	private ObservableList<Service> serviceList = FXCollections.observableArrayList();
	
	/* Event methods */
	
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
	private void saveService(ActionEvent event) {
		if (ServiceID.getText() == null || ServiceID.getText() == "") {
			Service service = new Service();
			service.setServicename(ServiceName.getText());
			service.setServiceinuse(ServiceInUse.getText());
			service.setServicecharges(ServiceCharges.getText());
			service.setCancelcharges(CancelCharges.getText());

			serviceService.save(service);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Customer updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Service:  " + ServiceName.getText() + "  has been created.");
			alert.showAndWait();
		} else {
			Service service = serviceService.find(Long.parseLong(ServiceID.getText()));
			service.setServicename(ServiceName.getText());
			service.setServiceinuse(ServiceInUse.getText());
			service.setServicecharges(ServiceCharges.getText());
			service.setCancelcharges(CancelCharges.getText());

			serviceService.save(service);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Customer updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Service:  " + ServiceName.getText() + "  has been updated.");
			alert.showAndWait();
		}
		servicetable();
		clearFields();
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		servicetable();
		clearFields();
		
	}
	
	public void servicetable() {
		/*
		 * Set All userTable column properties
		 */
		colServiceID.setCellValueFactory(new PropertyValueFactory<>("serviceid"));
		colServiceName.setCellValueFactory(new PropertyValueFactory<>("servicename"));
		colServiceInUse.setCellValueFactory(new PropertyValueFactory<>("serviceinuse"));
		colServiceCharges.setCellValueFactory(new PropertyValueFactory<>("servicecharges"));
		colCancelCharges.setCellValueFactory(new PropertyValueFactory<>("cancelcharges"));
		colEdit.setCellFactory(cellFactory);

		serviceList.clear();
		serviceList.addAll(serviceService.getService());
		servicetable.setItems(serviceList);
	}

	Callback<TableColumn<Service, Boolean>, TableCell<Service, Boolean>> cellFactory = new Callback<TableColumn<Service, Boolean>, TableCell<Service, Boolean>>() {
		@Override
		public TableCell<Service, Boolean> call(final TableColumn<Service, Boolean> param) {
			final TableCell<Service, Boolean> cell = new TableCell<Service, Boolean>() {
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
							Service service = getTableView().getItems().get(getIndex());
							updateService(service);
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

				private void updateService(Service service) {
					ServiceID.setText(Long.toString(service.getServiceid()));
					ServiceName.setText(service.getServicename());
					ServiceInUse.setText(service.getServiceinuse());
					ServiceCharges.setText(service.getServicecharges());
					CancelCharges.setText(service.getCancelcharges());
					
				}
			};
			return cell;
		}
	};
	
	public void print(ActionEvent event) throws JRException {
		
		 // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/reports/services.jrxml");
       
        // Parameters for report .
        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("id", "1");
//        parameters.put("servicename", "Show");
//        parameters.put("serviceinuse", "Yes");
//        parameters.put("servicecharges", "1000");
//        parameters.put("cancelcharges", "2000");
        
        // DataSource
        // This is simple example, no database.
        // then using empty datasource.
        JRDataSource dataSource = new JREmptyDataSource();
  
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
  
     
        // Make sure the output directory exists.
        File outDir = new File("D:/Reports");
        outDir.mkdirs();
  
        // Export to PDF.
        JasperExportManager.exportReportToPdfFile(jasperPrint, "D:/Reports/Services.pdf");
         
        System.out.println("Done!");
	}
	
	private void clearFields() {
		ServiceID.setText(null);
		ServiceName.clear();
		ServiceInUse.clear();
		ServiceCharges.clear();
		CancelCharges.clear();

	}

}
