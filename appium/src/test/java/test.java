import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String args[]) {
        Integer[] arr = {1, 2, 3, 4};
        int position = 2;
        int b = 5;
        System.out.println(arr.length);
        List<Integer> list = new ArrayList(Arrays.asList(arr));
        list.add(position-1, b);
        arr = list.toArray(arr);
        System.out.println(arr.length);



    }
}

