package com.example.pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;
import java.util.Random;




public class HelloApplication extends Application {
    private int pacrow;
    private int paccol;
    private Circle pacman;
    private String[] maze;
    private Circle[][] dots;
    private int score;
    private Text text;
    private Circle ghost;
    private int ghostrow;
    private int ghostcol;
    private final Random random = new Random();
    private final int[] rowChanges = {-1,1,0,0};
    private final int[] colChanges = {0,0,-1,1};
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(pane, 200,130);
        pacman = new Circle(10,10,8,Color.YELLOW);

        score = 0;
        text = new Text("Points : " + score);
        text.setTranslateX(10);
        text.setTranslateY(120);
        text.setFill(Color.WHITE);



        maze = new String[] {
                "##########",
                "#P.......#",
                "#.##.###.#",
                "#.......G#",
                "##########"
        };
        dots = new Circle[maze.length][maze[0].length()];
        ghost = new Circle(10,10, 6,Color.RED);

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
                if(c == '.'){
                    Circle dot = new Circle(10,10,3);
                    dot.setTranslateX(col * 20);
                    dot.setTranslateY(row * 20);
                    dot.setFill(Color.WHITE);
                    dots[row][col] = dot;
                    pane.getChildren().add(dot);

                }
                if(c == 'G'){
                    ghostrow = row;
                    ghostcol = col;

                }

            }
        }

            pacman.setTranslateX(paccol * 20);
            pacman.setTranslateY(pacrow  * 20);
            ghost.setTranslateX(ghostcol * 20);
            ghost.setTranslateY(ghostrow * 20);
            pane.getChildren().add(pacman);
            pane.getChildren().add(text);
            pane.getChildren().add(ghost);

            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT) {
                    move(0,-1,pane);
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    move(0,+1,pane);
                }
                if (event.getCode() == KeyCode.UP) {
                    move(-1,0,pane);
                }
                if (event.getCode() == KeyCode.DOWN) {
                    move(+1,0,pane);
                }
            });
            new AnimationTimer(){
                private long lastMove = 0;
               @Override
               public void handle(long now){
                   if(now - lastMove > 500_000_000) {
                       moveGhost();
                       lastMove = now;
                   }
               }
            }.start();


        stage.setScene(scene);
        stage.show();
    }
    private void move(int rowChange, int colChange,Pane pane){
        int newRow = pacrow + rowChange;
        int newCol = paccol + colChange;
        if(!(maze[newRow].charAt(newCol) == '#') ){
            pacrow = newRow;
            paccol = newCol;
            pacman.setTranslateX(paccol * 20);
            pacman.setTranslateY(pacrow * 20);
            if(!(dots[pacrow][paccol] == null)){
                pane.getChildren().remove(dots[pacrow][paccol]);
                dots[pacrow][paccol] = null;
                score++;
                text.setText("Points : " + score);
            }
        }


    }
    private void moveGhost(){
        int dice = random.nextInt(4);
        int rowChange = rowChanges[dice];
        int colChange = colChanges[dice];
        int newRow = ghostrow + rowChange;
        int newCol = ghostcol  + colChange ;
        if(maze[newRow].charAt(newCol) != '#'){
            ghostrow = newRow;
            ghostcol = newCol;
            ghost.setTranslateX(newCol * 20);
            ghost.setTranslateY(newRow * 20);
        }

    }

}
