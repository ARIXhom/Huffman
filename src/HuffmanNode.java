
class HuffmanNode {
    char data;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;
    
    
    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = right = null;
    }

    public char getData() {
        return data;
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setData(char data) {
        this.data = data;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

}