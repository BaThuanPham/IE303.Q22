import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Pipe {
    public int x = 360;
    public int y;
    public int width = 64;
    public int height = 512;
    public int gap = 180;
    public boolean hasPass = false;
    public Image topImage;
    public Image bottomImage;

    public Pipe(int y) {
        this.y = y;
        this.topImage = new ImageIcon("toppipe.png").getImage();
        this.bottomImage = new ImageIcon("bottompipe.png").getImage();
    }

    public void move() {
        x -= 4;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(topImage, x, y, width, height, null);
        graphics.drawImage(bottomImage, x, y + height + gap, width, height, null);
    }

    public Rectangle getTopBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle(x, y + height + gap, width, height);
    }
}