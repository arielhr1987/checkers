package checkers.ui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

import static checkers.ui.CheckersApp.TILE_SIZE;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Piece extends StackPane {

    private PieceType type;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public PieceType getType() {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x, y);

        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.03);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        ellipse.setFill((type == PieceType.RED || type == PieceType.REDQUEEN)
                ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * 0.03);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);

        if((type == PieceType.WHITE || type == PieceType.WHITEQUEEN)){
            setOnMousePressed(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
            });

            setOnMouseDragged(e -> {
                if (CheckersApp.turn == "w" && (type == PieceType.WHITE || type == PieceType.WHITEQUEEN)){
                    relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
                }
            });
        }
    }
    public void promote(){
        Text text = new Text("W");
        text.setStroke(Color.BLACK);
        text.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        text.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);
        getChildren().addAll(text);
        if(type == PieceType.RED){
            type = PieceType.REDQUEEN;
        }
        if(type == PieceType.WHITE){
            type = PieceType.WHITEQUEEN;
        }
    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }
}
