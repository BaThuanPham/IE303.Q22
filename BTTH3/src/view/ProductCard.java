package view;

import model.Product;

import javax.swing.*;
import java.awt.*;

public class ProductCard extends JPanel {

    private Product product;

    public ProductCard(Product product) {
        this.product = product;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(180, 260));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        // ===== TOP: TITLE + DESCRIPTION =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

        JLabel name = new JLabel(product.getName());
        name.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel desc = new JLabel("<html>This product is excluded from all promotional discounts.</html>");
        desc.setFont(new Font("Arial", Font.PLAIN, 10));
        desc.setForeground(Color.GRAY);

        topPanel.add(name);
        topPanel.add(Box.createVerticalStrut(3));
        topPanel.add(desc);

        add(topPanel, BorderLayout.NORTH);

        // ===== CENTER: IMAGE (NO SQUISH) =====
        ImageIcon icon = new ImageIcon("src/" + product.getImagePath());

        Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        add(imagePanel, BorderLayout.CENTER);

        // ===== BOTTOM: BRAND + PRICE =====
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

        JLabel brand = new JLabel("Adidas");
        brand.setFont(new Font("Arial", Font.PLAIN, 10));
        brand.setForeground(Color.GRAY);

        JLabel price = new JLabel("$" + product.getPrice() + ".00");
        price.setFont(new Font("Arial", Font.BOLD, 12));

        bottomPanel.add(brand, BorderLayout.WEST);
        bottomPanel.add(price, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public Product getProduct() {
        return product;
    }
}