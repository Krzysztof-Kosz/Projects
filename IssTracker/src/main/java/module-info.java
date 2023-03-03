module dev.iad2022.issgroup.isstracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;

    requires org.junit.jupiter.api;

    opens dev.iad2022.issgroup.isstracker to javafx.fxml;
    exports dev.iad2022.issgroup.isstracker;
    exports dev.iad2022.issgroup.isstracker.mapping;
    opens dev.iad2022.issgroup.isstracker.mapping to javafx.fxml;
    exports dev.iad2022.issgroup.isstracker.controllers;
    opens dev.iad2022.issgroup.isstracker.controllers to javafx.fxml;
    exports dev.iad2022.issgroup.isstracker.units;
    opens dev.iad2022.issgroup.isstracker.units to javafx.fxml;
}