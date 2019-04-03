package com.booking.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Service;
import com.booking.config.StageManager;
import com.booking.service.ServiceService;
import com.booking.service.SlotService;
import com.booking.service.TaxService;
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
import javafx.scene.control.ComboBox;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ServiceController implements Initializable {

	private static final Logger LOG = getLogger(ServiceController.class);

	@FXML
	private Label ServiceID;

	@FXML
	private TextField ServiceName;

	@FXML
	private TextField Price;
    @FXML ComboBox<String> slot;
    @FXML ComboBox<String> venue;
    @FXML ComboBox<String> SacCode;
	@FXML
	private TextField UnitOfMeasurement;
	
	@FXML
	private TableView<Service> servicetable;

	@FXML
	private TableColumn<Service, Long> colServiceID;
	@FXML
	private TableColumn<Service, String> colServiceName;
	@FXML
	private TableColumn<Service, String> colPrice;
	@FXML
	private TableColumn<Service, String> colSlot;
	@FXML
	private TableColumn<Service, String> colVenue;
	@FXML
	private TableColumn<Service, String> colUnitOfMeasurement;
    @FXML private TableColumn<Service, String> colSaccode; 
	@FXML
	private TableColumn<Service, Boolean> colEdit;

	@FXML
	private Button reset;

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Autowired
	private ServiceService serviceService;
	@Autowired
	private SlotService slotService;
	@Autowired
	private VenueService venueService;
	@Autowired
	private TaxService taxService;

	private ObservableList<Service> serviceList = FXCollections.observableArrayList();
	private ObservableList<String> slotList = FXCollections.observableArrayList();
	private ObservableList<String> venueList = FXCollections.observableArrayList();
	private ObservableList<String> sacList = FXCollections.observableArrayList();

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
	public void venue(ActionEvent event) throws IOException {	
		stageManager.switchScene(FxmlView.VENUE); 		
	}

	@FXML
	private void saveService(ActionEvent event) {
		
		if (emptyValidation("Service Name", ServiceName.getText().isEmpty())) {
		if (ServiceID.getText() == null || ServiceID.getText() == "") {
			Service service = new Service();
			service.setServicename(ServiceName.getText());
			service.setPrice(Price.getText());
			service.setUnitofmeasurement(UnitOfMeasurement.getText());
			service.setSlot(slot.getSelectionModel().getSelectedItem());
			service.setVenue(venue.getSelectionModel().getSelectedItem());
			service.setSaccode(SacCode.getSelectionModel().getSelectedItem());

			serviceService.save(service);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Customer updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Service:  " + ServiceName.getText() + "  has been created.");
			alert.showAndWait();

		} else {

			Service service = serviceService.find(Long.parseLong(ServiceID.getText()));
			service.setServicename(ServiceName.getText());
			service.setPrice(Price.getText());
			service.setUnitofmeasurement(UnitOfMeasurement.getText());
			service.setSlot(slot.getSelectionModel().getSelectedItem());
			service.setVenue(venue.getSelectionModel().getSelectedItem());
			service.setSaccode(SacCode.getSelectionModel().getSelectedItem());

			serviceService.save(service);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Customer updated successfully.");
			alert.setHeaderText(null);
			alert.setContentText("Service:  " + ServiceName.getText() + "  has been updated.");
			alert.showAndWait();
		}
	}
		servicetable();
		clearFields();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		servicetable();
		clearFields();
		
		slotList.clear();
		slotList.addAll(slotService.findSlot());
		slot.setItems(slotList);
		
		venueList.clear();
		venueList.addAll(venueService.findVenue());
		venue.setItems(venueList);
		
		sacList.clear();
		sacList.addAll(taxService.findSaccode());
		SacCode.setItems(sacList);
		
	}

	public void servicetable() {
		/*
		 * Set All userTable column properties
		 */
		colServiceID.setCellValueFactory(new PropertyValueFactory<>("serviceid"));
		colServiceName.setCellValueFactory(new PropertyValueFactory<>("servicename"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));
		colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
		colUnitOfMeasurement.setCellValueFactory(new PropertyValueFactory<>("unitofmeasurement"));
		colSaccode.setCellValueFactory(new PropertyValueFactory<>("saccode"));
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
					Price.setText(service.getPrice());
					UnitOfMeasurement.setText(service.getUnitofmeasurement());

				}
			};
			return cell;
		}
	};

	public void print(ActionEvent event) throws JRException {
		JasperReport jasperReport = null;
		LOG.info("Service controller class  print method ");
		try {

			LOG.info("Service Report Compiling ");
			jasperReport = JasperCompileManager
					.compileReport(getClass().getResourceAsStream("/reports/services.jrxml")); // First jrxml compile,
																								// file.
		} catch (Exception e) {
			LOG.error("Unable to parse data ", e);
			jasperReport = JasperCompileManager
					.compileReport(getClass().getResourceAsStream("/reports/services.jasper"));
		}
		LOG.info("Service Report Compiled ");

		Map<String, Object> parameters = new HashMap<String, Object>(); // Parameters for report .

		List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();

		for (Service s : serviceService.getService()) {

			LOG.info("In Map Loop ");

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("serviceid", s.getServiceid());
			m.put("servicename", s.getServicename());
			
			list.add(m);

			LOG.info("After Map Loop ");
		}

		JRDataSource jRDataSource = new JRBeanCollectionDataSource(list);
		parameters.put("jRDataSource", list);

		LOG.info("Before Jasper Print ");
		JasperPrint jasperPrint = null;
		try {

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jRDataSource);

		} catch (Exception e) {
			LOG.error("Uanble to print jasper ", e);
		}
		LOG.info("Before Folder created ");

		File outDir = new File("Reports/Service"); // Make sure the output directory exists.
		outDir.mkdirs();

		LOG.info("Folder created ");

		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new java.util.Date();
		System.out.println(date);
		String path = outDir.toString().concat("/").concat(dateFormat.format(date)).concat(".pdf");

		LOG.info("Before export ");
		// Export to PDF.
		JasperExportManager.exportReportToPdfFile(jasperPrint, path);
		LOG.info("After export ");

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Report");
		alert.setHeaderText(null);
		alert.setContentText("Report downloaded successfully.");
		alert.showAndWait();
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


	private void clearFields() {
		ServiceID.setText(null);
		ServiceName.clear();
		Price.clear();
		UnitOfMeasurement.clear();
		SacCode.getSelectionModel().clearSelection();

	}

}
