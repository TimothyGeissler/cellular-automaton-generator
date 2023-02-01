import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application {

    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final String TITLE = "CA by T.Geissler";
    public static final int SIZE = 600;
    public static final Color GRID_COLOUR = Color.GRAY;
    public static ArrayList<Cell> cells = new ArrayList<>();
    public static int frameCount;

    public void start (Stage stage) {
        frameCount = 0;
        Group g = new Group();
        loadGrid(g);
        cells = loadCells(g);

        Scene scene = new Scene(g, SIZE, SIZE);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        //scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        stage.show();

        Controls c = new Controls();
        c.initControlsUI();

        Timeline animate = new Timeline();
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
        animate.play();
    }

    public ArrayList<Cell> loadCells(Group g) {
        ArrayList<Cell> c = new ArrayList<>();
        for (int y = 0; y < SIZE; y+=10) {
            for (int x = 0; x < SIZE; x+=10) {
                Cell cell = new Cell(x, y);
                c.add(cell);
                g.getChildren().add(cell.getShape());
                //System.out.println("Adding cell: (" + x + "," + y + ")");
            }
        }
        return c;
    }

    public void loadGrid(Group g) {
        for (int i = 0; i <= SIZE; i+=10) {
            //Horizontal line
            Line horiz = new Line();
            horiz.setStartX(0);
            horiz.setEndX(SIZE);
            horiz.setStartY(i);
            horiz.setEndY(i);
            horiz.setStroke(GRID_COLOUR);
            g.getChildren().add(horiz);
            //Vertical line
            Line vert = new Line();
            vert.setStartX(i);
            vert.setEndX(i);
            vert.setStartY(0);
            vert.setEndY(SIZE);
            vert.setStroke(GRID_COLOUR);
            g.getChildren().add(vert);
        }
    }

    public void step() {
        /*System.out.println("Frame: " + frameCount);
        if (frameCount == 3600) {
            frameCount = 0;
        } else {
            cells.get(frameCount).swapLive();
            frameCount++;
        }*/
    }
}