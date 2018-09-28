package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.bean.Booking;
import com.booking.config.StageManager;
import com.booking.service.BookingService;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

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
	private Label RepName;
	@FXML
	private Label RepEmail;
	@FXML
	private Label RepMobile;
	@FXML
	private Label ShowName;
	@FXML
	private Label Slot;
	@FXML
	private Label Services;
	@FXML
	private Label Total;
	@FXML
	private TextField searchField;	
	
	@FXML TableView<Booking> bookingtable;
	@FXML TableColumn<Booking, String> colServiceDate;
	@FXML TableColumn<Booking, String> colDay;
	@FXML TableColumn<Booking, String> colSlot;
	@FXML TableColumn<Booking, String> colService;
	@FXML TableColumn<Booking, String> colClient;
	@FXML TableColumn<Booking, String> ColContractid;
	@FXML TableColumn<Booking, String> colBookingDate;
	@FXML TableColumn<Booking, String> colPact;
	@FXML TableColumn<Booking, String> colPaymentStatus;
	@FXML TableColumn<Booking, String> colEstimatedCost;
	@FXML TableColumn<Booking, String> colReceipt;
	@FXML TableColumn<Booking, String> colInvoice;
	@FXML TableColumn<Booking, String> colRemaining;
	@FXML TableColumn<Booking, Boolean> colEdit;
	
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	@Autowired
	private BookingService bookingService;
	
	private ObservableList<Booking> bookingList = FXCollections.observableArrayList();

	
	@FXML
    private void logout(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }
	
	@FXML
    private void report(ActionEvent event) throws IOException {
    	   	
    }
	@FXML
    private void cancel(ActionEvent event) throws IOException {
      	
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
		
		colServiceDate.setCellValueFactory(new PropertyValueFactory<>("servicedate"));
		colDay.setCellValueFactory(new PropertyValueFactory<>("contractid"));
		colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
		colService.setCellValueFactory(new PropertyValueFactory<>("servicename"));
		colClient.setCellValueFactory(new Callback<CellDataFeatures<Booking,String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getCustomer().getCustomername());
            }
          });		
		ColContractid.setCellValueFactory(new Callback<CellDataFeatures<Booking,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getOverride());
            }
          });
		colBookingDate.setCellValueFactory(new Callback<CellDataFeatures<Booking,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getBookingdate());
            }
          });
		colPact.setCellValueFactory(new Callback<CellDataFeatures<Booking,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getPact());
            }
          });	
		colPaymentStatus.setCellValueFactory(new Callback<CellDataFeatures<Booking,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getPaymentstatus());
            }
          });
		colEstimatedCost.setCellValueFactory(new Callback<CellDataFeatures<Booking,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getPact());
            }
          });
		colReceipt.setCellValueFactory(new Callback<CellDataFeatures<Booking,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
                return new SimpleStringProperty(param.getValue().getContract().getReceipt().getClass().toString());
            }
          });
		colRemaining.setCellValueFactory(new PropertyValueFactory<>("abc"));
		colInvoice.setCellValueFactory(new Callback<CellDataFeatures<Booking,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Booking, String> param) {
            	try {
                return new SimpleStringProperty(param.getValue().getContract().getInvoice().getInvoicedate());
            }catch(Exception e) {
            	System.out.println("No invoice created");
            }
				return null;
		   }
        });
		colEdit.setCellFactory(cellFactory);
		
		bookingList.clear();
		bookingList.addAll(bookingService.getBooking());
		bookingtable.setItems(bookingList);
		
		FilteredList<Booking> filteredData = new FilteredList<>(bookingList, e -> true);
		searchField.setOnKeyPressed(e ->{
			searchField.textProperty().addListener((obeservableValue, oldValue, newValue) ->{
				filteredData.setPredicate((Predicate< ?  super Booking>) booking ->{
					if(newValue == null || newValue.isEmpty()) {
					return true;
				    }
					if(Long.toString(booking.getContract().getContractid()).contains(newValue)) {
				    	return true;
				    }else if(booking.getServicedate().contains(newValue)){
				    	return true;
				    }	
					return false;
				});
			});
			SortedList<Booking> sortedData = new SortedList<>(filteredData);
			sortedData.comparatorProperty().bind(bookingtable.comparatorProperty());
			bookingtable.setItems(sortedData);
		});
	}

	Callback<TableColumn<Booking, Boolean>, TableCell<Booking, Boolean>> cellFactory = new Callback<TableColumn<Booking, Boolean>, TableCell<Booking, Boolean>>() {
		@Override
		public TableCell<Booking, Boolean> call(final TableColumn<Booking, Boolean> param) {
			final TableCell<Booking, Boolean> cell = new TableCell<Booking, Boolean>() {
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
							Booking booking = getTableView().getItems().get(getIndex());
							updateBooking(booking);
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

				private void updateBooking(Booking booking) {
					ContractID.setText(Long.toString(booking.getContract().getContractid()));
					Purpose.setText(booking.getContract().getPurpose());
					Total.setText(booking.getContract().getPact());
				}
			};
			return cell;
		}
	};
	
	private void clearFields() {
		ContractID.setText(null);
		
	}

}
