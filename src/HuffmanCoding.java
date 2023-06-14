import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanCoding {

    static float CharCount = 0, CodesCount = 0;
    
    private static void buildHuffmanCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.data, code);
        }

        buildHuffmanCodes(root.left, code + "0", huffmanCodes);
        buildHuffmanCodes(root.right, code + "1", huffmanCodes);
    }

    private static String encode(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
            CharCount++;
        }

        return encodedText.toString();
    }

    public static void main(String[] args) {
        String choice = "";
        String text = "";
        
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println(""
                    + "\n1.Huffman Coding"
                    + "\n0.EXIT"
                    + "\nChoose an Option : ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter Your Text To Encode In Window Just Opened ");
                    text = "";

                    text = JOptionPane.showInputDialog("Enter your text to Huffman Encoding:");
                    text = text.strip();

                    Map<Character, Integer> frequencyMap = new HashMap<>();
                    for (char c : text.toCharArray()) {
                        frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
                    }

                    HuffmanNode root = buildHuffmanTree(frequencyMap);

                    Map<Character, String> huffmanCodes = new HashMap<>();
                    buildHuffmanCodes(root, "", huffmanCodes);

                    String encodedText = encode(text, huffmanCodes);
                    String decodedText = decode(root, encodedText);
                    System.out.println("Encoded text: " + encodedText);
                    TreeDrawer treeDrawer = new TreeDrawer(root);
                    treeDrawer.setPreferredSize(new Dimension(1000, 800));
                    JScrollPane scrollPane = new JScrollPane(treeDrawer);
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JFrame frame = new JFrame("Huffman TreeDrawer");
                    frame.setExtendedState(frame.MAXIMIZED_BOTH);
                    frame.add(scrollPane);
                    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    float CompressionRate = (CodesCount/(CharCount*8))*100;
                    JTextField textField = new JTextField(" Compression rate: " + CompressionRate + "%" + " | Encoded text:" + encodedText + "   | Decoded text: "+ decodedText, 100);
                    textField.setEditable(false);
                    Font currentFont = textField.getFont();
                    Font biggerFont = currentFont.deriveFont(currentFont.getSize() + 5f);
                    textField.setFont(biggerFont);
                    panel.add(textField);
                    frame.add(panel, BorderLayout.NORTH);
                    frame.setVisible(true);
                    frame.requestFocus();

                    System.out.println("\n\nFrequency Table :");
                    for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
                        if (entry.getKey().toString().equals(" ")){
                            System.out.println("' ':" + "\t" + entry.getValue());
                        }
                        else {
                            String a = entry.getKey().toString();
                            System.out.println(a = entry.getKey().toString() + ":\t\t" + entry.getValue());
                        }
                    }
                    break;

                case "0":
                    System.exit(0);
                default:
                System.out.println("Enter a Valid Option!!!");
            }
        }
    }
}
