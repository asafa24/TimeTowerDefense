module universite_paris8.iut.bak.timetowerdefense {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens universite_paris8.iut.bak.timetowerdefense to javafx.fxml;
    exports universite_paris8.iut.bak.timetowerdefense;
    exports universite_paris8.iut.bak.timetowerdefense.controleur;
    opens universite_paris8.iut.bak.timetowerdefense.controleur to javafx.fxml;
}