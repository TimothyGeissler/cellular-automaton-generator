import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controls {
    private Group g;
    private Stage stage;
    private Scene scene;
    private final String CONTROLS_TITLE = "XML Generator Control Panel";
    private final String [] SIM_MODES = new String[]{"Conway's Game of Life", "Fire Simulation"};
    private final XMLGenerator x = new XMLGenerator();
    private ComboBox<String> simType;
    //private TextField titleField, authField, descField, widthField, heightField, probFireField, probTreeGrow, similarityField, breedField, starveField;
    private enum Params {
        TITLE,
        AUTH,
        DESC,
        GRID_WIDTH,
        GRID_HEIGHT,
        PROB_FIRE,
        PROB_TREE_GROW,
        SEG_SIMILARITY,
        BREED_TIME,
        STARVE_TIME
    }

    public static final Params[] fieldTypes = new Params[]{Params.TITLE, Params.AUTH, Params.DESC, Params.GRID_WIDTH, Params.GRID_HEIGHT, Params.PROB_FIRE, Params.PROB_TREE_GROW, Params.SEG_SIMILARITY, Params.BREED_TIME, Params.STARVE_TIME};
    private final TextField [] fields = new TextField[fieldTypes.length];

    public Controls() {
        g = new Group();
        stage = new Stage();
        scene = new Scene(g, 420, 500);
        stage.setScene(scene);
        stage.setTitle(CONTROLS_TITLE);
        stage.show();
    }

    public void initControlsUI() {
        newButton("Generate XML", 220, 440, t -> chooseXML());
        simType = genComboBox(SIM_MODES, 20, 36);

        int i;
        for (i = 0; i < fieldTypes.length/2; i++) {
            fields[i] = newTxtField(fieldTypes[i].name(), 20, (i * 60) + 80);
        }
        for (; i < fieldTypes.length; i++) {
            fields[i] = newTxtField(fieldTypes[i].name(), 230, ((i - fieldTypes.length/2) * 60) + 20);
        }
        /*
        titleField = newTxtField("Simulation Title", 230, 20);
        authField = newTxtField("Author", 230, 80);
        descField = newTxtField("Description", 230, 140);
        widthField = newTxtField("Width", 20, 80);
        heightField = newTxtField("Height", 20, 140);

        newLine(10, 410, 205, 205);

        probFireField = newTxtField("Prob. of Fire", 20, 220);
        probTreeGrow = newTxtField("Prob. Tree Grow", 230, 220);
        similarityField = newTxtField("Segragation Similarity", 20, 280);
        breedField = newTxtField("Breed Time", 230, 280);
        starveField = newTxtField("Starve Time", 20, 340);*/
    }

    private void newLine(int x1, int x2, int y1, int y2) {
        Line ln = new Line();
        ln.setStartX(x1);
        ln.setStartY(y2);
        ln.setEndX(x2);
        ln.setEndY(y2);
        g.getChildren().add(ln);
    }

    private void chooseXML() {
        FileChooser picker = new FileChooser();
        File file = picker.showSaveDialog(stage);
        if (file != null) {
            KeyValueList parameters = new KeyValueList(fields.length + 1);
            parameters.insertKV(0, "sim-type", simType.getValue());
            for (int i = 0; i < fields.length; i++) {
                parameters.insertKV(i+1, fieldTypes[i].name().toLowerCase(), fields[i].getText());
            }
            x.formatXML(file.getPath(), parameters, Main.cells);
        }
    }

    private ComboBox<String> genComboBox(String [] list, int x, int y) {
        ComboBox<String> combo_box = new ComboBox<>(FXCollections.observableArrayList(list));
        combo_box.setLayoutX(x);
        combo_box.setLayoutY(y);
        combo_box.getSelectionModel().selectFirst();
        g.getChildren().add(combo_box);
        return combo_box;
    }

    private TextField newTxtField(String hint, int x, int y) {
        TextField txt = new TextField();
        txt.setLayoutX(x);
        txt.setLayoutY(y + 16);

        Label l = new Label();
        l.setText(hint);
        l.setLayoutX(x);
        l.setLayoutY(y);

        g.getChildren().add(txt);
        g.getChildren().add(l);
        return txt;
    }

    private void newButton(String txt, int x, int y, javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> e) {
        Button b = new Button();
        b.setText(txt);
        b.setLayoutX(x);
        b.setLayoutY(y);
        b.setOnMouseClicked(e);
        g.getChildren().add(b);
    }
}
