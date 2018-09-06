package com.booking.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.booking.config.StageManager;
import com.booking.view.FxmlView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Controller
public class DashboardController implements Initializable{

	@Lazy
    @Autowired
    private StageManager stageManager;
	
	
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
	public void booking(ActionEvent event) throws IOException {	
	
		
		
	}
	
	@FXML
	public void accounting(ActionEvent event) throws IOException {	
	
		
		
	}
	
	
	@FXML
	public void miscellanous(ActionEvent event) throws IOException {	
	
		
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
