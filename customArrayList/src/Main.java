import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CustomArrayList list = new CustomArrayList();

        list.add("1");
        list.add(1, "asas");
        list.add("2");
        list.add("3");
        list.add("4");
        list.remove(3);
        list.add(14);
        list.remove("2");

        System.out.println("a = " + list.remove("asas"));

        for (int i = 0; i < list.size; i++) {
            System.out.println(list.get(i));
        }

    }

}
