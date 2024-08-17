

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        String str = scanner.nextLine();
        int A[] = new int[str.length()];


        for(int i = 0; i < str.length(); i++){
            A[i] = Integer.parseInt(str.substring(i, i+1));

        }
        //선택정렬
        for(int i = 0; i < str.length(); i++) {
            int Max = i; // 내림차순 정렬이기 때문에, 맨앞에 최댓값을 넣는다
            for (int j = i + 1; j < str.length(); j++){
                if(A[j] > A[Max]){
                    Max = j;
                }
            }
            if(A[i] < A[Max]) {
                int temp = A[i];
                A[i] = A[Max];
                A[Max] = temp;

            }
        }
        for(int i = 0; i< str.length(); i++){
            System.out.print(A[i]);
        }


    }
}
