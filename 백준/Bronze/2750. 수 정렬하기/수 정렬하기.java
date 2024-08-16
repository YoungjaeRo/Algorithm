

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] A = new int[n];

        for(int i = 0; i < n; i++){
            A[i] = scanner.nextInt();
        }

        //버블정렬 구현
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < n-1-i; j++){
                if(A[j] > A[j + 1]) { //비교 배열과 바로 옆 배열값의 비교
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
        for(int i = 0; i < n; i++){
            System.out.println(A[i]);
        }
    }
}
