import javax.swing.JFrame;

public class BTTH2 {
    static public void main(String[] args) {
        JFrame mainWindow = new JFrame();
        mainWindow.setTitle("Flappy Bird");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        Background background = new Background();
        mainWindow.add(background);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        background.requestFocusInWindow();
    }
}
