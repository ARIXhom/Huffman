
class MinHeap {
    private HuffmanNode[] heap;
    private int size;
    private int capacity;

    public MinHeap() {
        this.capacity = 10;
        this.size = 0;
        this.heap = new HuffmanNode[capacity];
    }

    public int getSize() {
        return size;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private void swap(int index1, int index2) {
        HuffmanNode temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public void insert(HuffmanNode node) {
        if (size == capacity) {
            increaseCapacity();
        }

        heap[size] = node;
        size++;
        heapifyUp(size - 1);
    }

    private void increaseCapacity() {
        capacity *= 2;
        HuffmanNode[] newHeap = new HuffmanNode[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    public HuffmanNode extractMin() {
        if (size == 0)
            throw new IllegalStateException("Heap is empty");

        HuffmanNode minNode = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return minNode;
    }

    private void heapifyUp(int index) {
        while (hasParent(index) && heap[index].frequency < heap[getParentIndex(index)].frequency) {
            int parentIndex = getParentIndex(index);
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && heap[getRightChildIndex(index)].frequency < heap[smallerChildIndex].frequency) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (heap[index].frequency < heap[smallerChildIndex].frequency) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }
}