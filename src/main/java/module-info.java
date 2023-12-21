module com.lina.lab4var13 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.lina.lab4var13 to javafx.fxml;
    exports com.lina.lab4var13;
}