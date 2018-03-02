package urbanparks.view;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import urbanparks.model.Job;
import urbanparks.model.JobCollection;
import urbanparks.model.ParkManager;
import urbanparks.model.UrbanParksStaff;
import urbanparks.model.User;
import urbanparks.model.Volunteer;
import urbanparks.view.MainMenuPane.BackButtonEventHandler;
import urbanparks.model.UserCollection;

public class VolunteerPane extends GridPane {

	private VolunteerPane volunteerPane = this;
    private BorderPane root;
    private UserCollection userCollection;
    private JobCollection jobCollection;
    private MainMenuPane back;
    private Button backButton;
    private Volunteer volunteer;
    
    public VolunteerPane(BorderPane root, UserCollection userCollection, JobCollection jobCollection, MainMenuPane back, Button backButton, Volunteer volunteer) {
        super();

        this.root = root;
        this.userCollection = userCollection;
        this.jobCollection = jobCollection;
        this.back = back;
        this.backButton = backButton;
        this.volunteer = volunteer;
 
        show();
    }

    public void show() {
        backButton.setText("Sign out");
        backButton.setOnAction(new BackButtonEventHandler());

    	// Login pane styles
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 5, 5, 5));
        setHgap(5);
        setVgap(5);
        
        Button viewPendingButton = new Button("View your pending jobs");
        viewPendingButton.setOnAction(new viewPendingEventHandler());
        viewPendingButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button viewAvailableButton = new Button("View jobs available for signup");
        viewAvailableButton.setOnAction(new viewAvailableEventHandler());
        viewAvailableButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        add(viewPendingButton, 0, 0);
        add(viewAvailableButton, 0, 1);
    }
    
    
    private class viewPendingEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	JobDisplay jobDisplay = new JobDisplay(root,  backButton, volunteerPane);
        	jobDisplay.showVolunteerPendingJobs(volunteer, jobCollection, root);
        }
    }
    
    
    private class viewAvailableEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	JobDisplay jobDisplay = new JobDisplay(root,  backButton, volunteerPane);
        	jobDisplay.showVolunteerAvailJobs(volunteer, jobCollection, root);
        }
    }

    
    private class BackButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	root.setCenter(back);
        	back.show();
        }
    }
}