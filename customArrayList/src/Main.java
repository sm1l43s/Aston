import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("List after adding 10, 20 and 30: ");
        printCustomList(list);

        list.add(1, 15);
        list.add(3, 25);
        System.out.println("List after adding 15 at index 1 and 25 at index 3: ");
        printCustomList(list);

        List<Integer> anotherList = Arrays.asList(40, 50, 60);
        list.addAll(anotherList);
        System.out.println("List after adding another list: ");
        printCustomList(list);

        list.clear();
        System.out.println("List after clearing: ");
        printCustomList(list);

        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("List after adding 10, 20 and 30 again: ");
        printCustomList(list);
        System.out.println("Element at index 1: " + list.get(1));

        System.out.println("Is the list empty? " + list.isEmpty());

        int removedElement = list.remove(1);
        System.out.println("Removed element at index 1: " + removedElement);
        System.out.println("List after removing element at index 1: ");
        printCustomList(list);

        Object o = 30;
        boolean removed = list.remove(o);
        System.out.println("Did we remove 30? " + removed);
        System.out.println("List after removing 30: ");
        printCustomList(list);

        list.add(40);
        list.add(50);
        list.add(15);
        System.out.println("List before sorting: ");
        printCustomList(list);

        CustomArrayListSorter<Integer> sorter = new CustomArrayListSorter<>((a, b) -> b - a);

        list.sort(sorter);

        System.out.println("List after sorting in descending order: ");
        printCustomList(list);
    }

    private static void printCustomList(CustomArrayList list) {
        for (int i = 0; i < list.getSize(); i++) {
            System.out.printf("%3d", list.get(i));
        }
        System.out.println();
    }
}

