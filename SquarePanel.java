import java.awt.Color;
import javax.swing.JPanel;

public class SquarePanel extends JPanel {

    private static final long serialVersionUID = -1913377706227110414L;

    public SquarePanel(Color color) {
        this.setBackground(color);
    }

	public void changeColor(Color color) {
        this.setBackground(color);
        this.repaint();
	}
}
