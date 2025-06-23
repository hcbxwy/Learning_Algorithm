package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 手写一个加强堆
 * 一个标准的堆应该有什么？加强点在哪里？
 * 标准堆：堆分小根堆和大根堆两种，默认小根堆。标准堆具备的方法有
 * - add()
 * - T poll()
 * - T peek()
 * - isEmpty()
 * - size()
 * - contains() 加强点：时间复杂度从O(n) -> O(1)
 * - remove() 加强点：时间复杂度从O(n) -> O(logn)
 * 加强点扩展的方法：
 * - resign()
 * - getAllElements()
 */
public class HeapGreater<T> {
    private final ArrayList<T> heap;
    // 反向索引表
    private final HashMap<T, Integer> indexMap;
    // 比较器
    private final Comparator<? super T> comp;
    private int size;

    public HeapGreater(Comparator<? super T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        comp = c;
    }

    public int size() {
        return size;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public List<T> getAllElements() {
        return heap;
    }

    public void add(T obj) {
        heap.add(obj);
        indexMap.put(obj, size);
        heapInsert(size++);
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return heap.get(0);
    }

    public T poll() {
        if (size == 0) {
            return null;
        }
        T ans = heap.get(0);
        // 首尾交换
        swap(0, size - 1);
        indexMap.remove(ans);
        // 断尾
        heap.remove(--size);
        // 重新调整堆
        heapify(0);
        return ans;
    }

    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    public void remove(T obj) {
        if (contains(obj)) {
            T replace = heap.get(size - 1);
            int index = indexMap.get(obj);
            indexMap.remove(obj);
            heap.remove(--size);
            if (obj != replace) {
                heap.set(index, replace);
                indexMap.put(replace, index);
                resign(replace);
            }
        }
    }

    private void heapInsert(int index) {
        int parent = (index - 1) / 2;
        // 当前值比父亲小，则交换
        while (comp.compare(heap.get(index), heap.get(parent)) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        // 较大孩子：有右孩子并且右孩子比左孩子大则较大孩子为右孩子，否则为左孩子
        int left = index * 2 + 1;
        while (left < size) {
            int smaller = left + 1 < size && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            if (comp.compare(heap.get(index), heap.get(smaller)) > 0) {
                swap(index, smaller);
                index = smaller;
                left = index * 2 + 1;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T d1 = heap.get(i);
        T d2 = heap.get(j);
        heap.set(i, d2);
        heap.set(j, d1);
        indexMap.put(d2, i);
        indexMap.put(d1, j);
    }
}
