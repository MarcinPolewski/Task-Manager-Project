module com.marcinpolewski.taskmanagerproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.marcinpolewski.taskmanagerproject to javafx.fxml;
    exports com.marcinpolewski.taskmanagerproject;
}