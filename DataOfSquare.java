import java.util.ArrayList;
import java.awt.Color;

public class DataOfSquare {

    private ArrayList<Color> colorList = new ArrayList<Color>();
    private int color;
    private SquarePanel square;

    public DataOfSquare(int color) {

        this.colorList.add(Color.GREEN);
        this.colorList.add(Color.BLUE);
        this.colorList.add(Color.WHITE);

        this.color = color;

        square = new SquarePanel(this.colorList.get(this.color));
    }

    public SquarePanel getSquare() {
        return this.square;
    }

	public void lightMeUp(int color) {
        this.square.changeColor(this.colorList.get(color));
	}
}
