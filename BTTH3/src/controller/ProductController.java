package controller;

import model.Product;
import view.MainView;
import view.ProductCard;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductController {

    private MainView view;
    private List<Product> products;

    private ProductCard selectedCard = null;

    public ProductController(MainView view, List<Product> products) {
        this.view = view;
        this.products = products;
    }

    public void init() {

        for (Product p : products) {
            ProductCard card = new ProductCard(p);

            card.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (card != selectedCard) {
                        setCardColor(card, new Color(245, 245, 245));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (card != selectedCard) {
                        setCardColor(card, Color.WHITE);
                        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {

                    updateMainProduct(p);

                    if (selectedCard != null) {
                        setCardColor(selectedCard, Color.WHITE);
                        selectedCard.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
                    }

                    setCardColor(card, new Color(230, 240, 255));
                    card.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2));

                    selectedCard = card;
                }
            });

            view.rightPanel.add(card);
        }

        view.rightPanel.revalidate();
        view.rightPanel.repaint();

        // DEFAULT DISPLAY (THIS WAS YOUR MISSING PIECE)
        if (!products.isEmpty()) {
            updateMainProduct(products.get(0));
        }
    }

    private void updateMainProduct(Product p) {

        view.productName.setText(p.getName());
        view.productPrice.setText("$" + p.getPrice() + ".00");

        // HARD-CODED because Product model doesn't have these fields
        view.productBrand.setText("Adidas");
        view.productDesc.setText(
            "<html>This product is excluded from all promotional discounts.</html>"
        );

        view.productImage.setIcon(loadScaledIcon("src/" + p.getImagePath(), 280, 280));

        view.leftPanel.revalidate();
        view.leftPanel.repaint();
    }

    private ImageIcon loadScaledIcon(String path, int width, int height) {
        try {
            BufferedImage source = ImageIO.read(new File(path));
            if (source == null) {
                return null;
            }

            Image scaled = source.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (IOException ex) {
            return null;
        }
    }

    // 🔥 THIS FIXES THE "WHITE TEXT AREA" ISSUE
    private void setCardColor(Container card, Color color) {
        card.setBackground(color);
        for (Component c : card.getComponents()) {
            c.setBackground(color);
            if (c instanceof Container) {
                setCardColor((Container) c, color);
            }
        }
    }
}