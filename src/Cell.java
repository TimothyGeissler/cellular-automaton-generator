import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {
    private int isLive;
    private Rectangle cell;
    private static final Color CELL_VISIBLE = Color.BLACK;
    private static final Color CELL_INVISIBLE = Color.TRANSPARENT;
    private static final int CELL_SIZE = 10;

    public Cell(int x, int y) {
        cell = new Rectangle();

        cell.setOnMouseClicked(t -> {
            swapLive();
            System.out.println("Cell clicked");
        });

        /*cell.setOnMouseDragOver(t -> {
            swapLive();
            System.out.println("Cell dragged over");
        });*/

        cell.setFill(CELL_INVISIBLE);
        cell.setX(x);
        cell.setY(y);
        cell.setWidth(CELL_SIZE);
        cell.setHeight(CELL_SIZE);
        this.isLive = 0;
    }

    public int getRow() {
        return (int) (cell.getX()/CELL_SIZE);
    }

    public int getCol() {
        return (int) (cell.getY()/CELL_SIZE);
    }

    public int isLive() {
        return isLive;
    }

    public Rectangle getShape() {
        return cell;
    }

    public void swapLive() {
        switch (this.isLive) {
            case 0 -> setLive(1);
            case 1 -> setLive(0);
        }

    }
    public void setLive(int live) {
        isLive = live;
        if (live == 1) {
            cell.setFill(CELL_VISIBLE);
        } else {
            cell.setFill(CELL_INVISIBLE);
        }
    }
}
