module com.example.texttospeechapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;


    opens com.example.texttospeechapp to javafx.fxml;
    exports com.example.texttospeechapp;
}