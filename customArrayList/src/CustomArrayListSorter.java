import java.util.Comparator;

public class CustomArrayListSorter<E> implements Comparator<E> {

    private Comparator<? super E> comparator;

    public CustomArrayListSorter(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(E o1, E o2) {
        return comparator.compare(o1, o2);
    }

    public void sort(Object[] elements, int left, int right) {
        quickSort(elements, left, right);
    }

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

    private void swap(Object[] elements, int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
