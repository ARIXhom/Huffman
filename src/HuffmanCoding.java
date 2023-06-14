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
    private static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        MinHeap minHeap = new MinHeap();

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            minHeap.insert(node);
        }

        while (minHeap.getSize() > 1) {
            HuffmanNode left = minHeap.extractMin();
            HuffmanNode right = minHeap.extractMin();

            HuffmanNode newNode = new HuffmanNode('\0', left.frequency + right.frequency);
            newNode.left = left;    
            newNode.right = right;

            minHeap.insert(newNode);
        }

        return minHeap.extractMin();
    }

    private static String decode(HuffmanNode root, String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;

        for (char c : encodedText.toCharArray()) {
            if (c == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            if (current.left == null && current.right == null) {
                decodedText.append(current.data);
                current = root;
            }
            CodesCount++;
        }

        return decodedText.toString();
    }
}
