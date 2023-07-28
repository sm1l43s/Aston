import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {

    private Object[] elements;
    public int size;

    public CustomArrayList() {
        this.elements = new Object[10];
        this.size = 0;
    }

    public boolean add(E element) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = element;
        size++;
        return true;
    }

    public void add(int index, E element) {
        checkIndex(index, size + 1);
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        int newSize = collection.size();

        if (newSize > elements.length - size) {
            grow(newSize);
        }

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
        quickSort(0, size - 1, comparator);
    }

    private void quickSort(int left, int right, Comparator<? super E> comparator) {
        if (left < right) {
            E pivot = (E) elements[(left + right) / 2];
            int i = left;
            int j = right;

            while (i <= j) {
                while (comparator.compare((E) elements[i], pivot) < 0) {
                    i++;
                }
                while (comparator.compare((E) elements[j], pivot) > 0) {
                    j--;
                }

                if (i <= j) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            quickSort(left, j, comparator);
            quickSort(i, right, comparator);
        }
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private void checkIndex(int index, int range) {
        if (index < 0 || index >= range) {
            throw new IndexOutOfBoundsException("Index: " + index + " , Size: " + size);
        }
    }

    private void grow() {
        grow(size + 1);
    }

    private void grow(int minCapacity) {
        int newCapacity = Math.max(elements.length * 2, minCapacity);
        elements = Arrays.copyOf(elements, newCapacity);
    }
}
