import java.util.Comparator;

/**
 * A custom implementation of a sorter that can sort any type of elements using a comparator.
 * This class provides a method for sorting an array of elements using a quick sort algorithm.
 * It also implements the Comparator interface, so it can be used as a comparator for other classes.
 * @param <E> the type of elements to be sorted
 */
public class CustomArrayListSorter<E> implements Comparator<E> {

    private Comparator<? super E> comparator;

    /**
     * Constructs a new sorter with the specified comparator.
     * @param comparator the comparator to determine the order of the elements
     */
    public CustomArrayListSorter(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Compares two elements for order according to the underlying comparator.
     * @param o1 the first element to be compared
     * @param o2 the second element to be compared
     * @return a negative integer, zero, or a positive integer as the first element is less than, equal to, or greater than the second element
     */
    @Override
    public int compare(E o1, E o2) {
        return comparator.compare(o1, o2);
    }

    /**
     * Sorts an array of elements using a quick sort algorithm.
     * @param elements the array of elements to be sorted
     * @param left the leftmost index of the subarray to be sorted (inclusive)
     * @param right the rightmost index of the subarray to be sorted (inclusive)
     */
    public void sort(Object[] elements, int left, int right) {
        quickSort(elements, left, right);
    }

    /**
     * Performs a quick sort on a subarray of elements.
     * @param elements the array of elements to be sorted
     * @param left the leftmost index of the subarray to be sorted (inclusive)
     * @param right the rightmost index of the subarray to be sorted (inclusive)
     */
    private void quickSort(Object[] elements, int left, int right) {
        if (left < right) {
            E pivot = (E) elements[(left + right) / 2];
            int i = left;
            int j = right;

            while (i <= j) {
                while (compare((E) elements[i], pivot) < 0) {
                    i++;
                }
                while (compare((E) elements[j], pivot) > 0) {
                    j--;
                }

                if (i <= j) {
                    swap(elements, i, j);
                    i++;
                    j--;
                }
            }
            quickSort(elements, left, j);
            quickSort(elements, i, right);
        }
    }

    /**
     * Swaps two elements in an array.
     * @param elements the array of elements
     * @param i the index of the first element to be swapped
     * @param j the index of the second element to be swapped
     */
    private void swap(Object[] elements, int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
