
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int K = sc.nextInt();

        int[] A = new int[N];

        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt();

        }

        //그리디 알고리즘 --> 최대한 큰 동전 먼저 사용하기(뒤에서부터 탐색하기)
        int count = 0;
        for(int i = N - 1; i >= 0; i--){
            if(A[i] <= K) {
                count = count + (K / A[i]);
                K = K % A[i];
            }
        }
        System.out.println(count);

    }
}
