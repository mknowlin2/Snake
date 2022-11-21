import java.util.ArrayList;

public class ThreadsController extends Thread 
{

    public static int DIRECTION = 1;

    private ArrayList<ArrayList<DataOfSquare>> squares;
    private ArrayList<Tuple> positions = new ArrayList<Tuple>();
    private Tuple headSnakePos;
    private Tuple foodPosition;

    private long speed = 50L;

    private int snakeSize = 3;
    

    public ThreadsController(Tuple positionDepart) {

        squares = Window.GRID;

        headSnakePos = new Tuple(positionDepart.getX(), positionDepart.getY());
        //DIRECTION = 1;

        Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
        positions.add(headPos);

        int foodX = Window.HEIGHT - 1;
        int foodY = Window.WIDTH - 1;

        foodPosition = new Tuple(foodX, foodY);
        spawnFood(foodPosition);
    }

    public void run() {
        while(true) {
            moveInterne(DIRECTION);
            checkCollision();
            moveExterne();
            deleteTail();
            pauser();
        }
    }

    private void checkCollision() {
        Tuple posCritical = positions.get(positions.size() - 1);

        for(int i = 0; i <= positions.size() - 2; i++) {
            boolean biteSelf = ((posCritical.getX() == positions.get(i).getX()) && (posCritical.getY() == positions.get(i).getY()));

            if(biteSelf) {
                stopGame();
            }
        }

        boolean eatingFood = ((posCritical.getX() == foodPosition.getY()) && (posCritical.getY() == foodPosition.getX()));

        if(eatingFood) {
            System.out.println("Yummy!");
            snakeSize++;
            foodPosition = getNewFoodPos();
            spawnFood(foodPosition);
        }
    }

    private void deleteTail() {
        int size = snakeSize;
        int startSize = this.positions.size() - 1;

        for(int i = startSize; i > 0; i--) {
            if(size == 0) {
                Tuple tuple = this.positions.get(i);
                this.squares.get(tuple.getY()).get(tuple.getX()).lightMeUp(2);
            } else {
                size--;
            }
        }

        size = snakeSize;

        for(int i = startSize; i > 0; i--) {
            if(size == 0) {
                this.positions.remove(i);
            } else {
                size --;
            }
        }

    }

    private Tuple getNewFoodPos() {
        Tuple tuple;
        int ranX = getRandomNum();
        int ranY = getRandomNum();

        tuple = new Tuple(ranX, ranY);

        for(int i = 0; i <= positions.size() - 1; i++) {
            if((tuple.getY() == positions.get(i).getX()) && (tuple.getX() == positions.get(i).getY())) {
                ranX = getRandomNum();
                ranY = getRandomNum();

                tuple = new Tuple(ranX, ranY);
                i = 0;
            }
        }

        return tuple;
    }

    private int getRandomNum() {
        return (int)(Math.random() * 19);
    }

    private void moveExterne() {
        for(Tuple tuple : this.positions) {
            int y = tuple.getX();
            int x = tuple.getY();

            this.squares.get(x).get(y).lightMeUp(0);
        }
    }

    private void moveInterne(int direction) {
        switch(direction) {
            case 1:
                int xRightPos = Math.abs(headSnakePos.getX() + 1) % 20;
                headSnakePos.changeData(xRightPos, headSnakePos.getY());
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
                break;
            case 2:
                if((headSnakePos.getX() - 1) < 0) {
                    headSnakePos.changeData(19, headSnakePos.getY());
                } else {
                    int xLeftPos = Math.abs(headSnakePos.getX() - 1) % 20;
                    headSnakePos.changeData(xLeftPos, headSnakePos.getY());
                }

                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
                break;
            case 3:
                if((headSnakePos.getY() - 1) < 0) {
                    headSnakePos.changeData(headSnakePos.getX(), 19);
                } else {
                    int yUpPos = Math.abs(headSnakePos.getY() - 1) % 20;
                    headSnakePos.changeData(headSnakePos.getX(), yUpPos);
                }
                            
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
                break;
            case 4:
                int yDownPos = Math.abs(headSnakePos.getY() + 1) % 20;
                headSnakePos.changeData(headSnakePos.getX(), yDownPos);
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
            default:
                break;
        }
    }

    private void pauser() {
        try {
            sleep(speed);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void spawnFood(Tuple foodPosition) {
        this.squares.get(foodPosition.getX()).get(foodPosition.getY()).lightMeUp(1);
    }

    private void stopGame() {
        System.out.println("COLLiSION! \n");
        while(true) {
            pauser();
        }
    }
}
