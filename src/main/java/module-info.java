module com.captchapro.texteditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens com.captchapro.texteditor to javafx.fxml;
    exports com.captchapro.texteditor;
}