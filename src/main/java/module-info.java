module edu.wright.airviewer2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.wright.airviewer2 to javafx.fxml;
    exports edu.wright.airviewer2;
    requires javafx.graphicsEmpty;
    requires javafx.swing;
    requires pdfbox;
}
