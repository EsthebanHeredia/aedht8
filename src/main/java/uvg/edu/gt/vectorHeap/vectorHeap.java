package uvg.edu.gt.vectorHeap;

import java.util.ArrayList;

public class VectorHeap<E extends Comparable<E>> {
    protected ArrayList<E> data;

    public VectorHeap() {
        data = new ArrayList<>();
    }

    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    public E remove() {
        if (data.isEmpty()) return null;
        E minVal = data.get(0);
        E lastVal = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, lastVal);
            percolateDown(0);
        }
        return minVal;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public E peek() {
        return data.get(0);
    }

    private void percolateUp(int index) {
        E value = data.get(index);
        while (index > 0) {
            int parent = (index - 1) / 2;
            E parentVal = data.get(parent);
            if (value.compareTo(parentVal) >= 0) break;
            data.set(index, parentVal);
            index = parent;
        }
        data.set(index, value);
    }

    private void percolateDown(int index) {
        E value = data.get(index);
        int size = data.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = left + 1;
            if (left >= size) break;

            int smallest = left;
            if (right < size && data.get(right).compareTo(data.get(left)) < 0) {
                smallest = right;
            }

            if (data.get(smallest).compareTo(value) >= 0) break;

            data.set(index, data.get(smallest));
            index = smallest;
        }
        data.set(index, value);
    }
}