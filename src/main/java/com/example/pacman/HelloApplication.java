package com.example.pacman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 200,100);



        String[] maze = {
                "##########",
                "#P.......#",
                "#.##.###.#",
                "#........#",
                "##########"
        };
        for(int row = 0; row <maze.length; row++){
            for(int col = 0; col < maze[row].length(); col++ ){
                char c = maze[row].charAt(col);
                if(c == '#'){
                    Rectangle rectangle = new Rectangle(col * 20,row * 20,20,20);
                    rectangle.setFill(Color.BLUE);
                    pane.getChildren().add(rectangle);
                }
            }
        }


        stage.setScene(scene);
        stage.show();
    }
}
