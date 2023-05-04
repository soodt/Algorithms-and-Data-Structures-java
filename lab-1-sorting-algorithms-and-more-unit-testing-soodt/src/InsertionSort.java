
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertionSort {

    List<Integer> sort(List<Integer> nums) {
        for (int i = 1; i < nums.size(); i++) {
            int value = nums.get(i);
            int j = i - 1;
            while (j >= 0 && nums.get(j) > value) {
                nums.set(j + 1, nums.get(j));
                j = j - 1;
            }
            nums.set(j + 1, value);
        }
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
        InsertionSort ob = new InsertionSort();
        List<Integer> nums = generateRandomArray(20);
        System.out.println("Original Array:");
        System.out.println(nums.toString());
        List<Integer> out = ob.sort(nums);
        System.out.println("Sorted Array");
        System.out.println(out.toString());
    }
}