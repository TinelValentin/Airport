package com.example.airportmip;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Vector;

public class FlightsScene {
    private Group root ;
    private Group spareRoot = new Group();
    private Scene lastScene;
    private int width=1300;
    private int height=750;
    private Integer pageNr=0;
    private Integer maxPerPage = 5;
    private StackPane unite = new StackPane();
    private Vector<Flight> List ;
    Vector <StackPane> flights = new Vector<StackPane>();
    VBox flightsShow = new VBox();



    private void Menu ()
    {
        Menu  home = new Menu();
        Menu flightsButton = new Menu ("Zboruri");


        flightsButton.setId("item");
        Label backLabel = new Label("   Acasa");
        home.setGraphic(backLabel);
        backLabel.setId("item");

        MenuItem arrivals = new MenuItem("Sosiri");
        MenuItem departures = new MenuItem("Plecari");
        MenuItem searchFlight = new MenuItem("Cautare");


        flightsButton.getItems().addAll(arrivals, departures,searchFlight);

        MenuBar menu = new MenuBar(home,flightsButton);


        root.getChildren().add(menu);



        backLabel.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                event.consume();
                ScreenControler.setScene(lastScene);
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

    private void Logo()
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

    private void ShowFlight(Flight flightCurrent,Integer xValue,Integer yValue,StackPane unite)
    {


        Label background = new Label();
        background.setId("Afisare");
        background.setPadding(new Insets(0,0,0,50));
        background.setTranslateX(xValue);
       // background.setTranslateY(yValue);
        background.setPrefSize(width-150,60);
        background.setTextFill(Color.WHITE);
        String time =flightCurrent.getM_time().toString();
        background.setText(time.substring(0,time.length()-3)+"  |");
        background.setText(background.getText()+"    "+flightCurrent.getM_id_destinationTo().getCity());
        background.setVisible(true);



        Button details = new Button("Mai multe detalii");
        details.setTranslateX(width-300);
        details.setTranslateY(10);
        details.setId("detailsButton");
        details.setPrefSize(200,40);

        details.setOnAction(t -> {
            spareRoot=root;
            root.getChildren().clear();

            root.getChildren().add(flightDetails(flightCurrent));
            Menu();
            Logo();


        });


        Label active;
        if(flightCurrent.isM_active())
        {
             active = new Label("Activ");
            active.setId("activeButton");
        }

        else
        {
             active = new Label("Intarziere");
            active.setId("inactiveButton");
        }

        active.setTranslateX(width-600);
        active.setTranslateY(10);

        active.setPrefSize(80,40);

         StackPane newFlight = new StackPane();
        newFlight.getChildren().addAll(background,details,active);

        newFlight.setAlignment(background, Pos.TOP_CENTER);
        newFlight.setAlignment(details, Pos.TOP_LEFT);
        newFlight.setAlignment(active, Pos.TOP_LEFT);
        flightsShow.getChildren().add(newFlight);

    }

    String findClass(String className,Ticket a)
    {
        switch(a.getM_class())
        {
            case 1:
            {
                className="intai ";
                break;
            }
            case 2: {
                className = "business ";
                break;
            }
            case 3:
            {
                className="econom ";
                break;
            }
        }
        return className;
    }

    private StackPane flightDetails(Flight currentFlight)
    {
        StackPane detailsFlight = new StackPane();
        Rectangle background = backgroundRecatngle();
        Rectangle backgroundSecond = new Rectangle(width-250,height-200);
        backgroundSecond.setId("rectangle");
        backgroundSecond.setTranslateX(100);
        backgroundSecond.setTranslateY(60);
        backgroundSecond.setFill(Color.web("WHITE"));
        backgroundSecond.setArcWidth(30.0);
        backgroundSecond.setArcHeight(20.0);


        Button back = new Button("Back");
        back.setId("back");
        back.setTranslateX(150);
        back.setTranslateY(110);
        back.setPrefSize(130,40);
       ImageView img = new ImageView("C:\\Users\\40763\\IdeaProjects\\AirportMip\\src\\main\\resources\\com\\example\\airportmip\\backArrow.png");
        img.setPreserveRatio(true);
        img.setFitHeight(30);
        back.setGraphic(img);

        back.setOnAction(t -> {
            root.getChildren().clear();

            root.getChildren().add(unite);
            Menu();
            Logo();

        });


            String destination =currentFlight.getM_id_destinationFrom().getCity() +"-"+currentFlight.getM_id_destinationTo().getCity();
        Label flightName = new Label(destination);
        flightName.setStyle("-fx-font-size: 40px;-fx-font-weight:bold;");
        flightName.setTranslateX(180);
        flightName.setTranslateY(-110);

        String date =currentFlight.getM_date().toString();
        Label flightDate = new Label(date);
        flightDate.setId("flight");
        flightDate.setTranslateX(-310);
        flightDate.setTranslateY(-10);

        String time =currentFlight.getM_time().toString();
        Label flightTime = new Label(time.substring(0,time.length()-3));
        flightTime.setId("flight");
        flightTime.setTranslateX(-350);
        flightTime.setTranslateY(35);

        String terminal ="Terminalul " +String.valueOf(currentFlight.getM_terminal());
        Label flightTerminal = new Label(terminal);
        flightTerminal.setId("flight");
        flightTerminal.setTranslateX(-300);
        flightTerminal.setTranslateY(80);


        String ticketLabel ="Bilete: ";
        Label ticketLabelGeneral = new Label(ticketLabel);
        ticketLabelGeneral.setId("flight");
        ticketLabelGeneral.setTranslateX(-340);
        ticketLabelGeneral.setTranslateY(130);

        Vector<Label> ticketPrices = new Vector<Label>();
        int y = 170;
        for( Ticket a:currentFlight.getM_id_ticket())
        {
            String flightClassName="";
           flightClassName= findClass(flightClassName,a);

            String ticket ="clasa "+flightClassName +String.valueOf(" : " +a.getM_price())+"$";
            Label flightTicket = new Label(ticket);
            flightTicket.setId("flight");
            flightTicket.setTranslateX(-260+y/4);
            flightTicket.setTranslateY(y);
            y+=40;
            ticketPrices.add(flightTicket);
        }
        Line line = new Line(180, 60, 180, 330);
        line.setTranslateY(100);
        line.setTranslateX(50);

        Label nameLabel = new Label("Nume  : ");
        nameLabel.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
        nameLabel.setTranslateX(150);
        nameLabel.setTranslateY(10);


        TextField nameField = new TextField();
        nameField.setMaxHeight(30);
        nameField.setMaxWidth(250);
        nameField.setTranslateX(220);
        nameField.setTranslateY(50);

        Label surnameLabel = new Label("Prenume  : ");
        surnameLabel.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
        surnameLabel.setTranslateX(150);
        surnameLabel.setTranslateY(90);


        TextField surnameField = new TextField();
        surnameField.setMaxHeight(30);
        surnameField.setMaxWidth(250);
        surnameField.setTranslateX(220);
        surnameField.setTranslateY(130);

        Label ageLabel = new Label("Clasa  : ");
        ageLabel.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
        ageLabel.setTranslateX(150);
        ageLabel.setTranslateY(170);


        ComboBox ageField = new ComboBox();

        ageField.getItems().addAll("clasa intai","clasa business","clasa econom");

        ageField.setMaxHeight(30);
        ageField.setMaxWidth(250);
        ageField.setTranslateX(220);
        ageField.setTranslateY(200);

        Button submit = new Button("Rezervati un bilet");
        submit.setId("submitButton");
        submit.setTranslateX(width-300);
        submit.setTranslateY(200);
        submit.setTranslateX(450);
        submit.setPrefSize(150,40);

        submit.setOnAction(t -> {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setContentText("Introduceti date in campurile de mai sus!");
            if(nameField.getText().isEmpty()) {

                error.show();
            return;
            }
            if(surnameField.getText().isEmpty()) {

                error.show();
                return;
            }
            error.setAlertType(Alert.AlertType.CONFIRMATION);
            error.setContentText("Doriti sa rezervati un bilet?");
            error.show();
            ReadDB aux=  new ReadDB();
            ReadDB.insertReservation(nameField.getText().toString(),surnameField.getText().toString(),1,currentFlight.getM_id_flight());
            currentFlight.getM_id_plane().setM_nr_seats(currentFlight.getM_id_plane().getM_nr_seats()-1);

        });

        detailsFlight.getChildren().addAll(background,backgroundSecond,back,
                flightName,flightDate,flightTime,flightTerminal,
                ticketLabelGeneral,ticketPrices.get(0),ticketPrices.get(1),ticketPrices.get(2),
                line,
                 nameLabel,nameField,
                 surnameField,surnameLabel,
                 ageField,ageLabel,
                 submit);

        StackPane.setAlignment(background, Pos.CENTER);
        StackPane.setAlignment(backgroundSecond, Pos.CENTER);
        StackPane.setAlignment(back, Pos.TOP_LEFT);
        StackPane.setAlignment(flightName, Pos.CENTER_LEFT);

        return detailsFlight;
    }

    private Rectangle backgroundRecatngle() {
        Rectangle background = new Rectangle(width-150,height-100);
        background.setId("rectangle");
        background.setTranslateX(100);
        background.setTranslateY(60);
        background.setFill(Color.web("#376481"));
        background.setVisible(true);
        return background;
    }

    private void background()
    {
        Rectangle back =backgroundRecatngle();


        Label title = new Label("Zborurile care vor pleca de la aeroport");
        title.setId("Title");
        title.setPadding(new Insets(0,0,0,50));
        title.setTranslateX(width/2-300);
        title.setTranslateY(100);
        title.setPrefSize(width-150,60);

        unite.getChildren().addAll(back,title);
        unite.setAlignment(back, Pos.CENTER);
        unite.setAlignment(title, Pos.TOP_CENTER);
    }
    private void List()
    {
        flightsShow.setPadding(new Insets(170, 0, 0, 0));
        flightsShow.setSpacing(10);
        int x =100;
        int y =100;
        int maxReached =0;
        for(int a=pageNr;a< pageNr+maxPerPage;a++)
        {
            if(a>List.size()-1||a==-1)
                break;
            y += 0;
            ShowFlight(List.get(a), x, y, unite);
        }




            unite.getChildren().add(flightsShow);

        Button previous = new Button("<-Pagina anterioara");

        previous.setId("next");
        previous.setPrefSize(200,40);
        previous.setTranslateX(130);
        previous.setTranslateY(-10);
        unite.getChildren().add(previous);
        unite.setAlignment(previous, Pos.BOTTOM_LEFT);



        Button next = new Button("Pagina urmatoare->");

        next.setId("next");
        next.setPrefSize(200,40);
        next.setTranslateX(60);
        next.setTranslateY(-10);
        next.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if(pageNr!=List.size()-1) {
                    if (pageNr + maxPerPage < List.size())
                        pageNr += maxPerPage;
                    else
                        pageNr += List.size()-(List.size()-maxPerPage);
                if(pageNr>=List.size())
                {
                    pageNr -= List.size()-(List.size()-maxPerPage);
                    return;
                }

                        unite.getChildren().remove(flightsShow);
                    flightsShow.getChildren().clear();
                    List();
                    Menu();
                    Logo();
                }
            }
        });
        previous.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            if(pageNr!=0) {
                if (pageNr - maxPerPage >= 0)
                    pageNr -= maxPerPage;
                else
                    pageNr -= pageNr - maxPerPage;
                unite.getChildren().remove(flightsShow);
                flightsShow.getChildren().clear();
                List();
                Menu();
                Logo();
            }
            }
        });
        unite.getChildren().add(next);
        unite.setAlignment(next, Pos.BOTTOM_RIGHT);

        if(root.getChildren().contains(unite))
        {
            root.getChildren().remove(unite);

        }
        root.getChildren().add(unite);
    }


    public Scene start(Scene lastScene)
    {
        String css = this.getClass().getResource("styleIndex.css").toExternalForm();
        root = new Group();
        Scene scene = new Scene(root, width, height);
        this.lastScene=lastScene;
        scene.setFill(Color.web("#C3E5F6FF"));
        scene.getStylesheets().add(css);
        ReadDB read = new ReadDB();
         List = read.readFlightsArriveHome();

        background();
        List();
        Menu();
        Logo();

        return scene;

    }
}
