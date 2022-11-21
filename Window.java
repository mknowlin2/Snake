import java.awt.event.KeyListener;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Window extends JFrame {

    private static final long serialVersionUID = -2578079802913045987L;
    
    public static final ArrayList<ArrayList<DataOfSquare>> GRID = new ArrayList<ArrayList<DataOfSquare>>();
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    public Window() {

        //GRID = 
        ArrayList<DataOfSquare> data;

        for(int i = 0; i < WIDTH; i++) {
            data = new ArrayList<DataOfSquare>();

            for(int j = 0; j < HEIGHT; j++) {
                DataOfSquare color = new DataOfSquare(2);
                data.add(color);
            }

            GRID.add(data); 
        }

        getContentPane().setLayout(new GridLayout(20, 20, 0, 0));

        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGHT; j++) {
                getContentPane().add(GRID.get(i).get(j).getSquare());
            }
        }

        Tuple position = new Tuple(10,10);

        ThreadsController threadCntrl = new ThreadsController(position);
        threadCntrl.start();
        
        this.addKeyListener((KeyListener) new KeyboardListener());
    }
}
