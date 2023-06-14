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
}
