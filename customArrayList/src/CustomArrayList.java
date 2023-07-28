import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * A custom implementation of an array list that can store any type of elements.
 * This class provides methods for adding, removing, getting, sorting and clearing elements.
 * It also implements the Iterable interface, so it can be used in a for-each loop.
 * @param <E> the type of elements in this list
 */
public class CustomArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }


    public int getSize() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element the element to be added
     * @return true if the element was added successfully
     */
    public boolean add(E element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param index the index at which the element is to be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    public void add(int index, E element) {
        checkIndex(index, size + 1);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the collection's iterator.
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * (Note that this will occur if the specified collection is this list, and it's nonempty.)
     * @param collection the collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
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

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E get(int index) {
        checkIndex(index, size);
        return (E) elements[index];
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E remove(int index) {
        checkIndex(index, size);
        E oldElement = (E) elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return oldElement;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present. If this list does not contain the element, it is unchanged.
     * More formally, removes the element with the lowest index i such that (object==null ? get(i)==null : object.equals(get(i))) (if such an element exists).
     * Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
     * @param object the element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
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

    /**
     * Sorts this list according to the order induced by the specified comparator.
     * All elements in this list must be mutually comparable using the specified comparator (that is, c.compare(e1, e2) must not throw a ClassCastException for any elements e1 and e2 in the list).
     * This sort is guaranteed to be stable: equal elements will not be reordered as a result of the sort.
     * @param comparator the comparator to determine the order of the list. A null value indicates that the elements' natural ordering should be used
     */
    public void sort(Comparator<? super E> comparator) {
        CustomArrayListSorter<E> sorter = new CustomArrayListSorter<>(comparator);
        sorter.sort(elements, 0, size - 1);
    }

    /**
     * Checks if the given index is within the valid range of [0, range).
     * Throws an IndexOutOfBoundsException if it is not.
     * @param index the index to be checked
     * @param range the upper bound of the valid range (exclusive)
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= range)
     */
    private void checkIndex(int index, int range) {
        if (index < 0 || index >= range) {
            throw new IndexOutOfBoundsException("Index: " + index + " , Size: " + size);
        }
    }

    /**
     * Ensures that this list has enough capacity to store at least minCapacity elements.
     * If the current capacity is smaller than minCapacity, it creates a new array with a larger capacity and copies all the elements to it.
     * @param minCapacity the minimum capacity that this list should have
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
}