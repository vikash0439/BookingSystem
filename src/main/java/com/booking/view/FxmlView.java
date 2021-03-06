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
            return "/fxml/Welcome.fxml";
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
            return "/fxml/Customer.fxml";
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
    },
	VENUE {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("venue.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Venue.fxml";
        }
    },
	TAX{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("tax.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Tax.fxml";
        }
      },
	CONTRACT {
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("contract.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/Contract.fxml";
          }
      },
	RESERVE {
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("reserve.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/Reserve.fxml";
          }
      },
	RECEIPT {
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("receipt.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/Receipt.fxml";
          }
      },
	INVOICE {
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("invoice.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/Invoice.fxml";
          }
      },
	SLOT {
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("slot.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/Slot.fxml";
          }
      },
	PURPOSE {
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("purpose.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/Purpose.fxml";
          }
      },
	ALLCONTRACT {
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("allcontract.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/AllContract.fxml";
          }
      },REPORTS {
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("reports.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/Reports.fxml";
          }
      }, STATECODE{
          @Override
  		public String getTitle() {
              return getStringFromResourceBundle("statecode.title");
          }

          @Override
  		public String getFxmlFile() {
              return "/fxml/StateCode.fxml";
          }
      };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
