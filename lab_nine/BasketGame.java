import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer; // Explicitly import the Swing Timer

import java.util.ArrayList;    // For ArrayList
import java.util.Iterator;     // For Iterator
import java.util.List;         // For List
import java.util.Random;       // For Random

public class BasketGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Falling Objects Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GamePanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

// GamePanel handles rendering, game updates, and input.
class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    private Timer timer;         // Using javax.swing.Timer
    private Basket basket;
    private ObjectManager objectManager;
    private Score score;

    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 600;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GamePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.WHITE);
        setFocusable(true);

        addKeyListener(this);
        addMouseListener(this);

        initGame();

        // Use a Swing Timer at ~60 FPS (1000ms / 15 ≈ 66.7 FPS)
        timer = new Timer(15, this);
        timer.start();
    }

    // Initializes game objects and state.
    public void initGame() {
        basket = new Basket(PANEL_WIDTH / 2 - 30, PANEL_HEIGHT - 50, 60, 20);
        score = new Score();
        objectManager = new ObjectManager(PANEL_WIDTH, PANEL_HEIGHT);
    }

    // Resets the game when the user clicks the mouse.
    public void resetGame() {
        initGame();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    // Update positions, handle collisions, and manage game state.
    public void updateGame() {
        // Stop the game if score is negative (game over).
        if (score.getValue() < 0) {
            timer.stop();
            return;
        }

        if (leftPressed) {
            basket.moveLeft();
        }
        if (rightPressed) {
            basket.moveRight(PANEL_WIDTH);
        }

        // If no objects are falling, spawn new ones.
        if (objectManager.isEmpty()) {
            objectManager.spawnObjects();
        }

        objectManager.updateObjects(basket, score);
        objectManager.removeOffScreenObjects();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the basket, objects, and score
        basket.draw(g);
        objectManager.drawObjects(g);
        score.draw(g);

        // Display "Game Over" if score is negative
        if (score.getValue() < 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", PANEL_WIDTH / 2 - 80, PANEL_HEIGHT / 2);
        }
    }

    // KeyListener methods.
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used.
    }

    // MouseListener method to restart the game on click.
    @Override
    public void mouseClicked(MouseEvent e) {
        resetGame();
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

// Basket represents the player-controlled object at the bottom.
class Basket {
    private int x, y;
    private final int width, height;
    private final int speed = 5;

    public Basket(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Moves the basket left, ensuring it doesn't leave the screen.
    public void moveLeft() {
        x = Math.max(0, x - speed);
    }

    // Moves the basket right within the panel bounds.
    public void moveRight(int panelWidth) {
        x = Math.min(panelWidth - width, x + speed);
    }

    // Returns the basket's bounding rectangle for collision detection.
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Draws the basket.
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}

// Abstract class for falling objects.
abstract class FallingObject {
    protected int x, y;
    protected final int width, height, speed;

    public FallingObject(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    // Updates the object's vertical position.
    public void updatePosition() {
        y += speed;
    }

    // Getter for the y-coordinate (used for removal off-screen).
    public int getY() {
        return y;
    }

    // Returns a rectangle for collision detection.
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Draws the object.
    public abstract void draw(Graphics g);

    // Applies the object's effect to the score.
    public abstract void applyEffect(Score score);
}

// GoodObject increases the score when caught.
class GoodObject extends FallingObject {
    public GoodObject(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void applyEffect(Score score) {
        score.increment(10);
    }
}

// BadObject decreases the score when caught.
class BadObject extends FallingObject {
    public BadObject(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void applyEffect(Score score) {
        score.decrement(10);
    }
}

// ObjectManager handles creation, updating, and drawing of falling objects.
class ObjectManager {
    private final int panelWidth;
    private final int panelHeight;
    private final int objectWidth = 20;
    private final int objectHeight = 20;

    private final Random random;
    private final List<FallingObject> objects;

    public ObjectManager(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.random = new Random();
        this.objects = new ArrayList<FallingObject>();
    }

    // Spawns a random number (1 to 3) of falling objects at random positions.
    public void spawnObjects() {
        int count = random.nextInt(3) + 1; // 1 to 3 objects
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(panelWidth - objectWidth);
            boolean isGood = random.nextBoolean();
            FallingObject obj;
            if (isGood) {
                obj = new GoodObject(x, 0, objectWidth, objectHeight, 3);
            } else {
                obj = new BadObject(x, 0, objectWidth, objectHeight, 3);
            }
            objects.add(obj);
        }
    }

    // Updates positions of objects and checks for collisions with the basket.
    public void updateObjects(Basket basket, Score score) {
        Iterator<FallingObject> it = objects.iterator();
        while (it.hasNext()) {
            FallingObject obj = it.next();
            obj.updatePosition();

            // If object intersects with the basket, apply effect and remove it
            if (obj.getBounds().intersects(basket.getBounds())) {
                obj.applyEffect(score);
                it.remove();
            }
        }
    }

    // Removes objects that have fallen off the screen.
    public void removeOffScreenObjects() {
        objects.removeIf(obj -> obj.getY() > panelHeight);
    }

    // Draws all falling objects.
    public void drawObjects(Graphics g) {
        for (FallingObject obj : objects) {
            obj.draw(g);
        }
    }

    // Checks if there are no falling objects currently.
    public boolean isEmpty() {
        return objects.isEmpty();
    }

    // Clears all falling objects (optional usage).
    public void reset() {
        objects.clear();
    }
}

// Score class tracks and renders the player's score.
class Score {
    private int score;

    public Score() {
        score = 10;
    }

    public void increment(int amount) {
        score += amount;
    }

    public void decrement(int amount) {
        score -= amount;
    }

    public int getValue() {
        return score;
    }

    public void reset() {
        score = 10;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 10, 20);
    }
}



