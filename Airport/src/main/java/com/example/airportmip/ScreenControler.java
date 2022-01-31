package com.example.airportmip;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ScreenControler {

    static int width=1300;
    static int height=750;
    static Scene main;

    public static Scene getMain() {
        return main;
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static Group getMainGroup() {
        return mainGroup;
    }

    static Stage mainStage;
    static Group mainGroup;

    static public void setGroup(Group root)
    {
        mainGroup=root;
    }


    static public void setScene(Scene main) {
        mainStage.setScene(main);
    }

    static public void setMainStage(Stage mainStage) {
        ScreenControler.mainStage = mainStage;
    }


    ScreenControler(Group root)
    {
        String css = this.getClass().getResource("styleIndex.css").toExternalForm();
        mainGroup=root;
         main = new Scene(mainGroup, width, height);
        main.setFill(Color.web("#5C93AFFF"));
        main.getStylesheets().add(css);
        mainStage.setTitle("Aeroport");
        mainStage.setScene(main);
        mainStage.show();
    }

    ScreenControler(Stage stage,Group root)
    {
        String css = this.getClass().getResource("styleIndex.css").toExternalForm();
        mainGroup=root;
        main = new Scene(mainGroup, width, height);
        main.setFill(Color.web("#5C93AFFF"));
        main.getStylesheets().add(css);
        mainStage=stage;
        mainStage.setTitle("Aeroport");
        mainStage.setScene(main);
        mainStage.show();
    }
}
