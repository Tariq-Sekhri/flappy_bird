module ca.sekhrit.flappy_bird {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ca.sekhrit.flappy_bird to javafx.fxml;
    exports ca.sekhrit.flappy_bird;
}