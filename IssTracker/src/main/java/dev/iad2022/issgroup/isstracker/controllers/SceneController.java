package dev.iad2022.issgroup.isstracker.controllers;

import dev.iad2022.issgroup.isstracker.APIrequest.APIController;
import dev.iad2022.issgroup.isstracker.APIrequest.ISSData;
import dev.iad2022.issgroup.isstracker.APIrequest.ISSDataFormater;
import dev.iad2022.issgroup.isstracker.units.Dimensions;
import dev.iad2022.issgroup.isstracker.units.Units;
import dev.iad2022.issgroup.isstracker.views.ViewType;
import dev.iad2022.issgroup.isstracker.mapping.EarthPoint;
import dev.iad2022.issgroup.isstracker.mapping.EarthView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    @FXML
    private ChoiceBox<Units> unitsPicker;
    @FXML
    private ChoiceBox<ViewType> viewPicker;

    @FXML
    private StackPane imageCanvasStack;
    @FXML
    private ImageView imageView;
    @FXML
    private Canvas canvas;

    @FXML
    private Text longitude;
    @FXML
    private Text latitude;
    @FXML
    private Text speedPerSec;
    @FXML
    private Text speedPerHour;
    @FXML
    private Text altitude;
    private final EarthView earthView;
    private Scene currentScene;
    private Image image;

    public SceneController() {
        this.earthView = new EarthView();
        this.earthView.setView(ViewType.MAP);
    };

    private void setupViewPicker(){
        viewPicker.getItems().addAll(ViewType.values());
        viewPicker.setConverter(ViewType.createStringConverter());
        viewPicker.setValue(ViewType.MAP);
    }

    private void setupUnitsPicker(){
        unitsPicker.getItems().addAll(Units.values());
        unitsPicker.setConverter(Units.createStringConverter());
        unitsPicker.setValue(Units.METRIC);
    }

    private Dimensions calculateImageDimensions(Image image){
        double aspectRatio = image.getHeight() / image.getWidth();
        double maxImageHeight = currentScene.getHeight() - 150;
        double width = currentScene.getWidth();
        double height = width * aspectRatio;
        if(height > maxImageHeight){
            height = maxImageHeight > 0 ? maxImageHeight : 400;
            width = height / aspectRatio;
        }
        return new Dimensions(width, height);
    }

    private void scaleImageView(Dimensions dimensions){
        imageView.setFitWidth(dimensions.getWidth());
        syncCanvas(dimensions);
    }
    private void syncCanvas(Dimensions dimensions){
        canvas.setWidth(dimensions.getWidth());
        canvas.setHeight(dimensions.getHeight());
        canvas.getGraphicsContext2D().clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        canvas.toFront();
    }

    private void addControlPoints(){
        earthView.drawPointFromLatLon(canvas, new EarthPoint(0,0));
        earthView.drawPointFromLatLon(canvas, new EarthPoint(90,0));
        earthView.drawPointFromLatLon(canvas, new EarthPoint(0,-180));
        earthView.drawPointFromLatLon(canvas, new EarthPoint(52.237049,21.017532)); //Warszawa
        earthView.drawPointFromLatLon(canvas, new EarthPoint(48.0000, 25.0000));

        EarthPoint[] earthPoints = new EarthPoint[3];
        earthPoints[0] = new EarthPoint(52.237049,21.017532);
        earthPoints[1] = new EarthPoint(48.0000, 25.0000);
        earthPoints[2] = new EarthPoint(40.0000, 20.0000);

        earthView.drawLineFromLatLon(canvas, earthPoints);
    }

    private void clearCanvas() {
        canvas.getGraphicsContext2D().clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    private void updateISSLocation(ISSData issData){
        earthView.drawPointFromLatLon(canvas, issData.getEarthPoint());
    }

    private void updateISSFutureLocation(ISSData[] multipleISSData) {
        int size = multipleISSData.length;
        EarthPoint[] earthPoints = new EarthPoint[size];
        for(int i = 0; i < size; i++) {
            earthPoints[i] = multipleISSData[i].getEarthPoint();
        }
        earthView.drawLineFromLatLon(canvas, earthPoints);
    }

    private void updateInformationLabels(ISSData fetchedISSData){
        ISSDataFormater formattedISSData = new ISSDataFormater(fetchedISSData, unitsPicker.getValue());
        longitude.setText(formattedISSData.getLongitude());
        latitude.setText(formattedISSData.getLatitude());
        speedPerHour.setText(formattedISSData.getSpeedPerHour());
        speedPerSec.setText(formattedISSData.getSpeedPerSecond());
        altitude.setText(formattedISSData.getAltitude());
    }

    private void updateView(ViewType currentView) {
        earthView.setView(currentView);
        image = earthView.getImage();

        imageView.setImage(this.image);
        imageView.setFitWidth(800);

        Dimensions dimensions = calculateImageDimensions(this.image);
        scaleImageView(dimensions);
        syncCanvas(dimensions);
    }

    private final Runnable updateUI = () -> {
        ISSData[] fetchedISSData = APIController.getISSData();
        // Fetch and use
        if (fetchedISSData == null){
            // TODO: Add popup with internet connection error
            System.out.println("Error - internet connection");
            return ;
        }
        // Update displayed information
        ViewType currentView = viewPicker.getValue();
        updateInformationLabels(fetchedISSData[0]);
        updateView(currentView);
        // Update pointer location
        clearCanvas();
        updateISSLocation(fetchedISSData[0]);
        updateISSFutureLocation(fetchedISSData);
    };

    private void updateUIInThread(){
        new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateUI.run();
                return null;
            }
        }).start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Retrieve current scene
        currentScene = imageCanvasStack.getScene();
        // Configure ChoiceBox pickers
        setupViewPicker();
        setupUnitsPicker();
        // Base image
        this.image = earthView.getImage();
        imageView.setImage(this.image);
        imageView.setFitWidth(800);
        // Scale image and canvas on window resize
        currentScene.widthProperty().addListener(e -> {
            Dimensions dimensions = calculateImageDimensions(this.image);
            scaleImageView(dimensions);
            syncCanvas(dimensions);
        });
        // Create update timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            // Update UI in another thread every 2 seconds
            updateUIInThread();
        }));
        // Start timeline
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
