package com.booking.view;

import java.util.ResourceBundle;

public enum FxmlView {

    USER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/User.fxml";
        }
    }, 
    LOGIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },
    DASHBOARD {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("dashboard.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/dashboard.fxml";
        }
    },
	CUSTOMER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("customer.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/customer.fxml";
        }
    },
	SERVICE {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("service.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/service.fxml";
        }
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
