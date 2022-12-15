package com.example.demo;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    void setModify(boolean modify) {
        this.modify = modify;
    }

    boolean getModify() {
        return modify;
    }

    Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        this.root = root;
        rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

//    void Multiplier(Cell cell) {
//        cell.getTextClass().setText((cell.getNumber() * this.getNumber()) + "");
//        textClass.setText("0");
//        root.getChildren().remove(textClass);
//        cell.setColorByNumber(cell.getNumber());
//        setColorByNumber(getNumber());
//    }

    void setColorByNumber(int number) {
        switch (number) {
            case 0:
                rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
                break;
            case 2:
                rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
                break;
            case 3:
                rectangle.setFill(Color.rgb(100, 144, 255, 0.5));
                break;
            case 4:
                rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
                break;
            case 6:
                rectangle.setFill(Color.rgb(100, 185, 255, 0.5));
                break;
            case 8:
                rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
                break;
            case 12:
                rectangle.setFill(Color.rgb(100, 237, 255, 0.5));
                break;
            case 16:
                rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
                break;
            case 24:
                rectangle.setFill(Color.rgb(100, 225, 219, 0.5));
                break;
            case 32:
                rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
                break;
            case 48:
                rectangle.setFill(Color.rgb(100, 225, 192, 0.5));
                break;
            case 64:
                rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
                break;
            case 96:
                rectangle.setFill(Color.rgb(17, 225, 109, 0.5));
                break;
            case 128:
                rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
                break;
            case 192:
                rectangle.setFill(Color.rgb(45, 232, 83, 0.5));
                break;
            case 256:
                rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
                break;
            case 384:
                rectangle.setFill(Color.rgb(57, 232, 45, 0.5));
                break;
            case 512:
                rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
                break;
            case 768:
                rectangle.setFill(Color.rgb(107, 232, 45, 0.5));
                break;
            case 1024:
                rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
                break;
            case 1536:
                rectangle.setFill(Color.rgb(142, 232, 45, 0.5));
                break;
            case 2048:
                rectangle.setFill(Color.rgb(250,0,0,1));
                break;
            case 3072:
                rectangle.setFill(Color.rgb(179, 232, 45, 0.5));
                break;
            case 4096:
                rectangle.setFill(Color.rgb(250,0,229,1));
                break;
            case 6144:
                rectangle.setFill(Color.rgb(232, 216, 45, 0.5));
                break;
            case 8192:
                rectangle.setFill(Color.rgb(250,50,229,1));
                break;
            case 12288:
                rectangle.setFill(Color.rgb(232, 151, 45, 0.5));
                break;
            case 16384:
                rectangle.setFill(Color.rgb(237,50,250,1));
                break;
            case 32768:
                rectangle.setFill(Color.rgb(220,50,250,1));
                break;
            case 65536:
                rectangle.setFill(Color.rgb(70,50,250,1));
                break;
            default:
                rectangle.setFill(Color.rgb(66,56,63,1));
                break;

        }

    }

    double getX() {
        return rectangle.getX();
    }

    double getY() {
        return rectangle.getY();
    }

    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    private Text getTextClass() {
        return textClass;
    }

}
