package com.booking;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.booking.config.StageManager;
import com.booking.view.FxmlView;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
@EnableScheduling
public class BookingSystemApplication extends Application{
	
	private static final Logger LOG = getLogger(BookingSystemApplication.class);

	protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;

    public static void main(final String[] args) {
    	LOG.info("Main application started");
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageManager = springContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    /**
     * Useful to override this method by sub-classes wishing to change the first
     * Scene to be displayed on startup. Example: Functional tests on main
     * window.
     */
    
    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.LOGIN);
    }
 
    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BookingSystemApplication.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }
    
 // Automatic database backup code
    
    @Scheduled(cron = "0 0 10 * * *")
	public void DataBackup(){	
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		File f = new File("DB_backup//");
		if (!f.exists()) {
			if (f.mkdir()) {
				LOG.info("Directory created");
			}
		}
		String path = f.getAbsolutePath();
//		path = path.replace('\\', '/');
		path = path + "/" + date + ".sql";

		Process p = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			p = runtime.exec("C:/Program Files (x86)/MySQL/MySQL Server 5.5/bin/mysqldump.exe -uroot -pdcm --add-drop-database -B srcpa -r"+ path);
			int processComplete = p.waitFor();
			if (processComplete == 0) {
				LOG.info("Backup Created Success at : " +path);
			} else {
				LOG.info("Can't Create backup");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
