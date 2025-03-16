package com.example.texttospeechapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static final int APP_WIDTH = 400;
    private static final int APP_HEIGHT = 500;

    private TextArea textArea;
    private ComboBox<String> voices, rates, volumes;

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = createScene();
        scene.getStylesheets().add(getClass().getResource(
                "style.css"
        ).toExternalForm());

        stage.setTitle("WordsEx");
        stage.setScene(scene);
        stage.show();
    }

    public Scene createScene() {
        VBox box = new VBox();
        box.getStyleClass().add("body");

        Label textToSpeechLabel = new Label("Text->>>-Speech");
        textToSpeechLabel.getStyleClass().add("text-to-speech-label");
        textToSpeechLabel.setMaxWidth(Double.MAX_VALUE);
        textToSpeechLabel.setAlignment(Pos.CENTER);
        box.getChildren().add(textToSpeechLabel);

        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.getStyleClass().add("text-area");
        box.getChildren().add(textArea);

        StackPane textAreaPane = new StackPane();
        textAreaPane.setPadding(new Insets(0, 20, 0, 20));
        textAreaPane.getChildren().add(textArea);
        box.getChildren().add(textAreaPane);

        GridPane settingsPane = createSettingComponents();
        box.getChildren().add(settingsPane);

        Button speakButton = createImageButton();
        speakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String msg = textArea.getText();
                String voice = voices.getValue();
                String rate = rates.getValue();
                String volume = volumes.getValue();

                AppController.speak(msg, voice, rate, volume);
            }
        });

        StackPane speakButtonPane = new StackPane();
        speakButtonPane.setPadding(new Insets(50, 30, 0, 30));
        speakButtonPane.getChildren().add(speakButton);

        box.getChildren().add(speakButtonPane);

        return new Scene(box, APP_WIDTH, APP_HEIGHT);

    }

    private GridPane createSettingComponents(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20, 0, 0, 0));

        Label voiceLabel = new Label("Voice");
        voiceLabel.getStyleClass().add("setting-label");

        Label rateLabel = new Label("Rate");
        rateLabel.getStyleClass().add("setting-label");

        Label volumeLabel = new Label("Volume");
        volumeLabel.getStyleClass().add("setting-label");

        gridPane.add(voiceLabel, 0, 0);
        gridPane.add(rateLabel, 1, 0);
        gridPane.add(volumeLabel, 2 , 0);

        // center labels
        GridPane.setHalignment(voiceLabel, HPos.CENTER);
        GridPane.setHalignment(rateLabel, HPos.CENTER);
        GridPane.setHalignment(volumeLabel, HPos.CENTER);

        voices = new ComboBox<>();
        voices.getItems().addAll(
                AppController.getVoices()
        );
        voices.setValue(voices.getItems().get(0));
        voices.getStyleClass().add("setting-combo-box");

        rates = new ComboBox<>();
        rates.getItems().addAll(
                AppController.getSpeedRates()
        );
        rates.setValue(rates.getItems().get(0));
        rates.getStyleClass().add("setting-combo-box");

        volumes = new ComboBox<>();
        volumes.getItems().addAll(
                AppController.getVolumeLevels()
        );
        volumes.setValue(volumes.getItems().get(5));
        volumes.getStyleClass().add("setting-combo-box");

        gridPane.add(voices, 0, 1);
        gridPane.add(rates, 1, 1);
        gridPane.add(volumes, 2 , 1);

        gridPane.setAlignment(Pos.CENTER);



        return gridPane;
    }

    private Button createImageButton() {
        Button button = new Button("Convert");
        button.getStyleClass().add("speak-btn");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);


        ImageView imageView = new ImageView(
                new Image(
                        getClass().getResourceAsStream("conv.png")
                )
        );
        imageView.setFitHeight(40);
        imageView.setFitWidth(50);
        button.setGraphic(imageView);
        return button;
    }

    public static void main(String[] args) {
        launch();
    }
}