// John Paul Larkin
// c00001754
// 17/02/2025 - Lab Nine

//Package java.awt Contains all the classes for creating user interfaces and for painting graphics and images.
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
// interfaces
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

// Swing is used to create a graphical user interface
// JFrame class, which is a top-level window with a title and border used to create the GUI.
import javax.swing.JFrame;

// JPanel class, a lightweight container that can be used to organise components in a window.
import javax.swing.JPanel;

// SwingUtilities class, which is a utility class for Swing.
// Such as ensuring that GUI updates are performed on the Event Dispatch Thread.
import javax.swing.SwingUtilities;

// Timer class, which fires one or more action events after a specified delay.
import javax.swing.Timer;

import java.util.ArrayList;   
import java.util.Random;      
import java.util.Iterator;    
import java.util.List;         

public class BasketGame {
    // Main method to create and show the GUI.
    public static void main(String[] args) {
        // Use SwingUtilities to create and show the GUI on the Event Dispatch Thread.
        SwingUtilities.invokeLater(() -> {
            // Create a new JFrame.
            JFrame frame = new JFrame("Falling balls Game");
            // Set the default close operation to exit the application.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Add a GamePanel object to the frame.
            frame.add(new GamePanel());
            // Pack the frame. - Resize the frame to fit the components.
            frame.pack();
            // Passing null centres the frame on the screen.
            frame.setLocationRelativeTo(null);
            // Make the frame visible.
            frame.setVisible(true);
        });
    }
    
}

// GamePanel handles rendering, game updates, and input.
class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    private final Timer timer;
    private Basket basket;
    private BallManager ballManager;
    private Score score;   
    // Define private boolean variables for the left and right arrow keys.
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    // Define a public constant for the panel size.
    public static final int PANEL_SIZE = 600;

    public GamePanel() {
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        setBackground(Color.WHITE);
        setFocusable(true);
        // Add listeners for the key and mouse events to the panel. 
        addKeyListener(this);
        addMouseListener(this);

        // Use a Swing Timer at 15ms
        timer = new Timer(15, this);
        // actionPerformed method is invoked every 15ms after the timer starts.
        startGame();
    }

    // Initialises game balls and state.
    public void initGame() {
        // Basket class represents the player-controlled object at the bottom.
        basket = new Basket();
        // Score class tracks and renders the player's score.
        score = new Score();
        // BallManager class handles creation, updating, and drawing of falling balls.
        ballManager = new BallManager();
    }

    // Starts / Resets the game when the user clicks the mouse.
    public void startGame() {
        initGame();
        timer.start();
    }


    // Update positions, handle collisions, and manage game state.
    public void updateGame() {
        // Stop the game if score is negative ( game is over).
        if (score.getScore() < 0) {
            timer.stop();
            return;
        }

        // Move the basket left or right based on the left and right arrow keys.
        if (leftPressed) {
            basket.moveLeft();
        }
        if (rightPressed) {
            basket.moveRight();
        }

        // If no balls are falling, spawn new ones.
        if (ballManager.isEmpty()) {
            ballManager.spawnBalls();
        }

        ballManager.updateBalls(basket, score);
        ballManager.removeOffScreenBalls();
    }


    // This method overrides the paintComponent method inherited from the JPanel class
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the basket, balls, and score
        basket.draw(g);
        ballManager.drawBalls(g);
        score.draw(g);

        // Display "Game Over" if score is negative
        if (score.getScore() < 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            // Centre the text on the screen.
            // 160 is roughly half the length of the 'Game Over' string.
            g.drawString("Game Over", PANEL_SIZE / 2 - 160, PANEL_SIZE / 2);
        }
    }

    // All below methods are implementations of the abstract methods
    // declared in the interfaces that GamePanel implements 
    // i.e. ActionListener, KeyListener, MouseListener

    
    // ------  ActionListener interface methods ------
    @Override
    public void actionPerformed(ActionEvent e) {
        // Invoked every 15ms while the timer is running.
        updateGame();
        repaint();
    }

    // ------  KeyListener interface methods ------
    @Override
    public void keyPressed(KeyEvent e) {
        // When the user presses a key, get the key code.
        int key = e.getKeyCode();
        // We are only interested in the left and right arrow keys.
        if (key == KeyEvent.VK_LEFT) {
            // Constant for the left arrow key. - 37
            leftPressed = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            // Constant for the right arrow key. - 39
            rightPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // When the user releases a key, set the left and right arrow keys to false.
        leftPressed = false;
        rightPressed = false;
    }
    @Override public void keyTyped(KeyEvent e) {}

    // ------  MouseListener interface methods ------
    @Override
    public void mouseClicked(MouseEvent e) {
        //  restart the game on mouse click.
        startGame();
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

// Basket represents the player-controlled object at the bottom.
class Basket {
    // Define private variables for the basket's x coordinate.
    private int x;
    // Default y-coordinate of the basket. 50 pixels from the bottom of the panel.
    final private int y = GamePanel.PANEL_SIZE - 50; 

    private static final int BASKET_SPEED = 5;
    // Define dimensions of the basket.
    private static final int BASKET_WIDTH = 60;
    private static final int BASKET_HEIGHT = 20;

    // Constructor uses PANEL_SIZE to initially center the basket horizontally
    public Basket() {
        this.x = GamePanel.PANEL_SIZE / 2 - BASKET_WIDTH / 2;
    }

    // Moves the basket left within the panel bounds.
    public void moveLeft() {
        // max of 0 ensure the basket doesn't move off the left edge of the panel.
        x = Math.max(0, x - BASKET_SPEED);
    }

    // Moves the basket right within the panel bounds.
    public void moveRight() {
        // min of PANEL_SIZE - BASKET_WIDTH ensure the basket doesn't move off the right edge of the panel.
        x = Math.min(GamePanel.PANEL_SIZE - BASKET_WIDTH, x + BASKET_SPEED);
    }

    // Returns the basket's bounding rectangle for collision detection with the balls.
    public Rectangle getBounds() {
        return new Rectangle(x, y, BASKET_WIDTH, BASKET_HEIGHT);
    }

    // Draws the basket.
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, BASKET_WIDTH, BASKET_HEIGHT);
    }
}

// Abstract class for falling balls.
abstract class FallingBall {
    // Define a public constant for ball size.
    public static final int BALL_DIAMETER = 20;
    
    // Define protected variables for the ball's x and y coordinates. 
    protected int x;
    // Y will always start at 0. - Top of the panel.
    protected int y = 0;
    // Define protected variable for the ball's speed.
    protected final int speed;

    // Constructor for the FallingBall class.
    public FallingBall(int x, int speed) {
        this.x = x;
        this.speed = speed;
    }

    // Updates the ball's vertical position.
    public void updatePosition() {
        y += speed;
    }

    // Getter for the y-coordinate - used for reaching bottom
    public int getY() {
        return y;
    }

    // Returns a rectangle for collision detection.
    public Rectangle getBounds() {
        return new Rectangle(x, y, BALL_DIAMETER, BALL_DIAMETER);
    }

    // Draws the ball.
    public abstract void draw(Graphics g);

    // Applies the ball's effect to the score.
    public abstract void applyEffect(Score score);
}

// GoodBalls are green increases the score when caught in the basket.
class GoodBall extends FallingBall {
    public GoodBall(int x, int speed) {
        super(x, speed);
    }

    // Overrides the draw method to draw a green ball.
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
    }

    // Overrides the applyEffect method to increment the score.
    @Override
    public void applyEffect(Score score) {
        score.increment();
    }
}

// BadBall are red and decreases the score when caught.
class BadBall extends FallingBall {
    public BadBall(int x, int speed) {
        super(x, speed);
    }

    // Overrides the draw method to draw a red ball.
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
    }

    // Overrides the applyEffect method to decrement the score.
    @Override
    public void applyEffect(Score score) {
        score.decrement();
    }
}

// BallManager handles creation, updating, and drawing of the falling balls.
class BallManager {
    private final Random random;
    // Define a List of falling balls. 
    private final List<FallingBall> balls;

    public BallManager() {
        this.random = new Random();
        this.balls = new ArrayList<>();
    }

    // Spawns a random number (between 3 and 6 inclusive) of falling objects at random positions.
    public void spawnBalls() {
        // Random number between 3 and 6.
        int count = random.nextInt(3,7);
        // Loop through the number of balls to spawn.
        for (int i = 0; i < count; i++) {
            // Random position between 0 and PANEL_SIZE - BALL_DIAMETER. 
            // This ensures the ball is within the panel.
            int x = random.nextInt(GamePanel.PANEL_SIZE - FallingBall.BALL_DIAMETER);
            // Randomly determine if the ball is good or bad.
            boolean isGood = random.nextBoolean();
            // Create a new ball.
            FallingBall ball;
            // Random ball fall speed between 2 and 5 inclusive.
            int speed = random.nextInt(2,6);
            if (isGood) {
                ball = new GoodBall(x, speed);
            } else {
                ball = new BadBall(x, speed);
            }
            // Add the ball to the list of balls.
            balls.add(ball);
        }
    }

    // Updates positions of balls and checks for collisions with the basket.
    public void updateBalls(Basket basket, Score score) {
        // Create an iterator for the list of balls.
        Iterator<FallingBall> iterator = balls.iterator();
        // Loop through the list of balls.
        while (iterator.hasNext()) {
            FallingBall ball = iterator.next();
            // Update the ball's position on the y-axis.
            ball.updatePosition();

            // If ball touches with the basket, apply effect and remove it
            if (ball.getBounds().intersects(basket.getBounds())) {
                // Effect is either to increment or decrement the score.
                ball.applyEffect(score);
                // Remove the ball from the list of balls.
                iterator.remove();
            }
        }
    }

    // Removes balls that have fallen off the screen. beyond the PANEL_SIZE.
    public void removeOffScreenBalls() {
        // Iterate through the list of balls.
        // If the balls y-coordinate is greater than the panel size, remove it.
        balls.removeIf(ball -> ball.getY() > GamePanel.PANEL_SIZE);
    }

    // Draws all falling objects.
    public void drawBalls(Graphics g) {
        // Iterate through the list of balls and draw based on the new y position.
        balls.forEach(ball -> ball.draw(g));
    }

    // Checks if there are no falling balls currently.
    public boolean isEmpty() {
        return balls.isEmpty();
    }
}

// Score class tracks and renders the player's score.
class Score {
    private int score;

    // Constructor for the Score class.
    public Score() {
        // Initialise score to 0.
        score = 0;
    }

    public void increment() {
        score += 10;
    }

    public void decrement() {
        score -= 10;
    }

    // Getter method to return the score.
    public int getScore() {
        return score;
    }

    // Draws the score on the screen.
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        // Draw on the top left of the screen.
        g.drawString("Score: " + score, 10, 20);
    }
}



