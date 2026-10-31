package com.example.pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;




public class HelloApplication extends Application {
    private int pacrow;
    private int paccol;
    private Circle pacman;
    private String[] maze;
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 200,100);
            pacman = new Circle(10,10,8,Color.YELLOW);

        maze = new String[] {
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
                if(c == 'P'){
                    pacrow = row;
                    paccol = col;
                }

            }
        }

            pacman.setTranslateX(paccol * 20);
            pacman.setTranslateY(pacrow  * 20);
            pane.getChildren().add(pacman);

            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT) {
                    move(0,-1);
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    move(0,+1);
                }
                if (event.getCode() == KeyCode.UP) {
                    move(-1,0);
                }
                if (event.getCode() == KeyCode.DOWN) {
                    move(+1,0);
                }
            });


        stage.setScene(scene);
        stage.show();
    }
    private void move(int rowChange, int colChange){
        int newRow = pacrow + rowChange;
        int newCol = paccol + colChange;
        if(!(maze[newRow].charAt(newCol) == '#') ){
            pacrow = newRow;
            paccol = newCol;
            pacman.setTranslateX(paccol * 20);
            pacman.setTranslateY(pacrow * 20);
        }

    }

}
