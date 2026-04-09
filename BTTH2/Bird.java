import javax.swing.ImageIcon;
import java.awt.Image;

public class Bird {
    public Image image;
    public int x = 30;
    public int y = 0;
    public int width = 48;
    public int height = 36;

    public Bird() {
        this.image = new ImageIcon("flappybird.png").getImage();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}