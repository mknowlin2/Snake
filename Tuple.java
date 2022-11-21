

public class Tuple {

    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void changeData(int x, int y) {
        this.x = x;
        this.y = y;
	}

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
