import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean add(E element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
        return true;
    }

    public void add(int index, E element) {
        checkIndex(index, size + 1);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        int newSize = collection.size();

        ensureCapacity(size + newSize);

        System.arraycopy(collection.toArray(), 0, elements, size, newSize);
        size += newSize;
        return true;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public E get(int index) {
        checkIndex(index, size);
        return (E) elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E remove(int index) {
        checkIndex(index, size);
        E oldElement = (E) elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return oldElement;
    }

    public boolean remove(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(elements[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void sort(Comparator<? super E> comparator) {
        CustomArrayListSorter<E> sorter = new CustomArrayListSorter<>(comparator);
        sorter.sort(elements, 0, size - 1);
    }

    private void checkIndex(int index, int range) {
        if (index < 0 || index >= range) {
            throw new IndexOutOfBoundsException("Index: " + index + " , Size: " + size);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
}