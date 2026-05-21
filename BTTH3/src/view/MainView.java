package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public JPanel leftPanel;
    public JPanel rightPanel;

    public JLabel productImage;
    public JLabel productName;
    public JLabel productPrice;
    public JLabel productBrand;
    public JLabel productDesc;

    public MainView() {

        setTitle("Product UI");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== LEFT PANEL (UNIFIED WHITE) =====
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(320, 0));
        leftPanel.setBackground(Color.WHITE); // ✅ unify
        leftPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        JPanel content = new JPanel(new BorderLayout(0, 15));
        content.setBackground(Color.WHITE); // ✅ same as parent
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ===== IMAGE =====
        productImage = new JLabel();
        productImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        productImage.setHorizontalAlignment(SwingConstants.CENTER);
        productImage.setPreferredSize(new Dimension(280, 280));

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.add(productImage, BorderLayout.CENTER);

        // ===== TITLE =====
        productName = new JLabel(" ");
        productName.setFont(new Font("Arial", Font.BOLD, 18));
        productName.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== PRICE =====
        productPrice = new JLabel(" ");
        productPrice.setFont(new Font("Arial", Font.BOLD, 16));
        productPrice.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== BRAND =====
        productBrand = new JLabel(" ");
        productBrand.setFont(new Font("Arial", Font.PLAIN, 12));
        productBrand.setForeground(Color.GRAY);
        productBrand.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== DESCRIPTION =====
        productDesc = new JLabel("<html><div style='width:260px;'> </div></html>");
        productDesc.setFont(new Font("Arial", Font.PLAIN, 12));
        productDesc.setForeground(Color.DARK_GRAY);
        productDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== ADD COMPONENTS =====
        detailsPanel.add(productName);
        detailsPanel.add(Box.createVerticalStrut(5));

        detailsPanel.add(productPrice);
        detailsPanel.add(Box.createVerticalStrut(10));

        detailsPanel.add(productBrand);
        detailsPanel.add(Box.createVerticalStrut(10));

        detailsPanel.add(productDesc);

        content.add(imagePanel, BorderLayout.NORTH);
        content.add(detailsPanel, BorderLayout.CENTER);

        leftPanel.add(content, BorderLayout.NORTH);

        // ===== RIGHT PANEL =====
        rightPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        rightPanel.setBackground(new Color(245, 245, 245)); // grid background (intentional contrast)
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setBorder(null);

        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }
}