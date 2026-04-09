import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Background extends JPanel
implements ActionListener {

    Image backgroundImage;
    Bird flappyBird;
    int birdVelocity = 0;
    int gravity = 1;
    int score = 0;
    boolean gameOver = false;
    ArrayList<Pipe> pipes;
    Timer gameLoop;
    Timer pipeLoop;
    Random random = new Random();
    static final int PIPE_SPAWN_DELAY_MS = 1450;

    public Background() {
        this.backgroundImage = new ImageIcon("flappybirdbg.png").getImage();
        flappyBird = new Bird();
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                handleKeyPressed(event);
            }
        });

        setPreferredSize(new Dimension(360, 640));

        pipes = new ArrayList<Pipe>();
        gameLoop = new Timer(1000/60, this); // 60 FPS
        gameLoop.start();
        pipeLoop = new Timer(PIPE_SPAWN_DELAY_MS, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                pipes.add(new Pipe(-300 + random.nextInt(250)));
            }
        });
        pipeLoop.start();
    }

    public void actionPerformed(ActionEvent event) {
        moveBird();
        movePipes();
        checkCollision();
        repaint();
    }

    public void moveBird() {
        if (gameOver) {
            return;
        }

        birdVelocity += gravity;
        flappyBird.y += birdVelocity;

        if (flappyBird.y > 640 - flappyBird.getHeight()) {
            flappyBird.y = 640 - flappyBird.getHeight(); // Prevent bird from falling below the ground
            birdVelocity = 0; // Reset velocity when hitting the ground
            endGame();
        }
    }



    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.backgroundImage, 0, 0, this);
        graphics.drawImage(flappyBird.image, flappyBird.x, flappyBird.y, flappyBird.width, flappyBird.height, this);
        for (Pipe pipe: pipes) {
            pipe.draw(graphics);
        }

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.BOLD, 32));
        graphics.drawString(String.valueOf(score), 10, 35);

        if (gameOver) {
            String gameOverText = "Game Over";
            String restartText = "Press R to restart";

            graphics.setFont(new Font("Arial", Font.BOLD, 32));
            FontMetrics gameOverMetrics = graphics.getFontMetrics();
            int gameOverX = (getWidth() - gameOverMetrics.stringWidth(gameOverText)) / 2;
            int gameOverY = getHeight() / 2;
            graphics.drawString(gameOverText, gameOverX, gameOverY);

            graphics.setFont(new Font("Arial", Font.PLAIN, 18));
            FontMetrics restartMetrics = graphics.getFontMetrics();
            int restartX = (getWidth() - restartMetrics.stringWidth(restartText)) / 2;
            int restartY = gameOverY + 30;
            graphics.drawString(restartText, restartX, restartY);
        }
    }

    public void movePipes() {
        if (gameOver) {
            return;
        }

        for (int index = 0; index < pipes.size(); index++) {
            Pipe pipe = pipes.get(index);
            pipe.move();
            if (pipe.x + pipe.width < 0) {
                pipes.remove(index);
                index--;
            }
        }
    }
    
    private void handleKeyPressed(KeyEvent event) {
        if (gameOver && event.getKeyCode() == KeyEvent.VK_R) {
            resetGame();
            return;
        }

        if (!gameOver && (event.getKeyCode() == KeyEvent.VK_SPACE || event.getKeyCode() == KeyEvent.VK_ENTER)) {
            birdVelocity = -11; // Make the bird jump
        }
    }

    public boolean hasCollision(Pipe pipe) {
        Rectangle birdRect = new Rectangle(flappyBird.x, flappyBird.y, flappyBird.getWidth(), flappyBird.getHeight());
        return birdRect.intersects(pipe.getTopBounds()) || birdRect.intersects(pipe.getBottomBounds());
    }

    public void checkCollision() {
        if (gameOver) {
            return;
        }

        for (Pipe pipe: pipes) {
            if (hasCollision(pipe)) {
                endGame();
                break;
            }
            if ((flappyBird.x > pipe.x + pipe.width) && (!pipe.hasPass)) {
                score++;
                pipe.hasPass = true;
            }
        }
    }

    public void endGame() {
        gameOver = true;
        gameLoop.stop();
        pipeLoop.stop();
    }

    public void resetGame() {
        flappyBird.x = 30;
        flappyBird.y = 0;
        birdVelocity = 0;
        score = 0;
        gameOver = false;
        pipes.clear();
        gameLoop.start();
        pipeLoop.start();
        repaint();
        requestFocusInWindow();
    }
}
