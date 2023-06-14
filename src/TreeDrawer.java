import javax.swing.*;
import java.awt.*;


public class TreeDrawer extends JPanel {
    private HuffmanNode root;

    public TreeDrawer(HuffmanNode root) {
        this.root = root;
//        JTextField textField = new JTextField();
//        textField.setPreferredSize(new Dimension(200, 30));
//        add(textField, BorderLayout.NORTH);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.PINK);
        drawTree(g, getWidth() / 2, 50, getWidth() / 2, 50, 300, 300, 20, 25, root);
    }

    private void drawTree(Graphics g, int x1, int y1, int x, int y, int pixel, int recPixel, int radius, int fontSize, HuffmanNode node) {
        if (node.left != null) {
            drawTree(g, x, y, x - pixel, y + 100, pixel / 2, recPixel / 2, radius / 1 , fontSize - 1, node.left);
        }

        if (node.right != null) {
            drawTree(g, x, y, x + pixel, y + 100, pixel / 2, recPixel / 2, radius / 1, fontSize - 1, node.right);
        }

        g.setColor(Color.GRAY);
        g.drawLine(x1, y1, x, y);

        if (node.left == null && node.right == null) {
            g.setColor(Color.black);
            g.fillRect(x - recPixel, y, recPixel * 2, 80);
            g.setColor(Color.orange);
            g.setFont(new Font("Arial", Font.PLAIN, fontSize));
            g.drawString(Integer.toString(node.getFrequency()), x, y + 20);
            g.drawString(Character.toString(node.getData()), x, y + 60);
        } else {
            g.setColor(Color.black);
            g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
            g.setColor(Color.orange);
            g.setFont(new Font("Arial", Font.PLAIN, fontSize));
            g.drawString(Integer.toString(node.getFrequency()), x - fontSize / 2, y + fontSize / 2);
        }

    }
    
    
     private static void setWindowsLookAndFeelAndCreateGUI() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
     }


    public static void main(String[] args) {}
}
