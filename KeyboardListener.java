import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    public KeyboardListener() {

    }

    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if(ThreadsController.DIRECTION != 2) {
                    ThreadsController.DIRECTION = 1;
                }
                break;
            case KeyEvent.VK_UP:
                if(ThreadsController.DIRECTION != 4) {
                    ThreadsController.DIRECTION = 3;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(ThreadsController.DIRECTION != 1) {
                    ThreadsController.DIRECTION = 2;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(ThreadsController.DIRECTION != 3) {
                    ThreadsController.DIRECTION = 4;
                }
                break;
            default:
                break;
        }
    }
}
