import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = sc.nextInt();
            int[] buildings = new int[n];

           
            for (int i = 0; i < n; i++) {
                buildings[i] = sc.nextInt();
            }

            int result = 0;

            for (int i = 2; i < n - 2; i++) {
                int leftMax = Math.max(buildings[i - 2], buildings[i - 1]);
                int rightMax = Math.max(buildings[i + 1], buildings[i + 2]);
                int neighborMax = Math.max(leftMax, rightMax);

                if (buildings[i] > neighborMax) {
                    result += buildings[i] - neighborMax;
                }
            }
            System.out.println("#" + test_case + " " + result);
        }

        sc.close();
    }
}
