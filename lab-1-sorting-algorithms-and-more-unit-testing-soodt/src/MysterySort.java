
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MysterySort {

    List<Integer> sort(List<Integer> nums) {

        // Add code here

        return nums;
    }

    static List<Integer> generateRandomArray(int n) {
        ArrayList<Integer> list = new ArrayList<Integer>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(1000));
        }
        return list;
    }

    public static void main(String args[]) {
        MysterySort ob = new MysterySort();
        List<Integer> nums = generateRandomArray(20);
        System.out.println("Original Array:");
        System.out.println(nums.toString());
        List<Integer> out = ob.sort(nums);
        System.out.println("Sorted Array");
        System.out.println(out.toString());
    }

}
