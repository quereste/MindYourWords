module com.example.myw {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.myw to javafx.fxml;
    exports com.example.myw;
}