package com.example.airportmip;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPage extends Application {

    private Group root ;
    private int width=1300;
    private int height=750;


    public Group getRoot() {
        return root;
    }

    public void Menu ( )
    {

        Menu flightsButton = new Menu ("Zboruri");
        Menu back = new Menu ("   Acasa");

        Label backLabel = new Label();
        flightsButton.setId("item");
        back.setId("item");



        MenuItem arrivals = new MenuItem("Sosiri");
        MenuItem departures = new MenuItem("Plecari");
        MenuItem searchFlight = new MenuItem("Cautare");


        flightsButton.getItems().addAll(arrivals, departures,searchFlight);

        MenuBar menu = new MenuBar(back,flightsButton);


        root.getChildren().add(menu);

        departures.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                FlightsScene flightScene = new FlightsScene();
                Scene lastScene = ScreenControler.getMain();
                ScreenControler.setScene(flightScene.start(lastScene));
            }
        });

        arrivals.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                FlightSceneBrasov flightScene = new FlightSceneBrasov();
                Scene lastScene = ScreenControler.getMain();
                ScreenControler.setScene(flightScene.start(lastScene));
            }
        });

        searchFlight.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                searchFlight search = new searchFlight();
                Scene lastScene = ScreenControler.getMain();
                ScreenControler.setScene(search.start(lastScene));
            }
        });




    }

    public void Logo()
    {
        Ellipse circle = new Ellipse();
        circle.setCenterX((float)width/2);
        circle.setCenterY((float)height/22);
        circle.setRadiusX(75.0f);
        circle.setRadiusY(67.0f);
        Image img = new Image("C:\\Users\\40763\\IdeaProjects\\AirportMip\\src\\main\\resources\\com\\example\\airportmip\\logo.png");
        circle.setFill(new ImagePattern(img));
        root.getChildren().add(circle);
    }

    public void image()
    {

        ColorAdjust colorAdjust = new ColorAdjust();

        colorAdjust.setBrightness(-0.6);

        Image img = new Image("C:\\Users\\40763\\IdeaProjects\\AirportMip\\src\\main\\resources\\com\\example\\airportmip\\back.jpg");
        ImageView image = new ImageView(img);
        image.setX(5);
        image.setY(50);
        image.setOpacity(0.8);
        image.setFitHeight(height-100);
        image.setFitWidth(width);
        image.setEffect(colorAdjust);

        root.getChildren().add(image);
    }

    public void text()
    {
        Label welcome = new Label("Aeroportul");
        Label welcome_3 = new Label("international");
        Label welcome_2 = new Label("Brasov");
        welcome.setAlignment(Pos.CENTER_RIGHT);
        welcome.setId("Welcome");
        welcome_2.setId("Welcome_2");
        welcome_3.setId("Welcome_3");

        root.getChildren().add(welcome);
        root.getChildren().add(welcome_3);
        root.getChildren().add(welcome_2);

    }



    @Override
    public void start(Stage stage) throws IOException {
        root = new Group();
        image();
        text();
        Menu();
        Logo();
        ScreenControler firstPages = new ScreenControler(stage,root);
    }

    public static void main(String[] args)
    {
        launch();
    }
}