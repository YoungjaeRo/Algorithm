

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int [] S = new int[N];

        // 개인별 입금시간 배열에 데이터 입력하기
        for (int i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }
        // 삽입정렬 알고리즘
        for(int i = 1; i< N; i++){ //A[5,3]
            int insert_point = i;
            int insert_value = A[i];
            for(int j = i - 1; j>= 0; j--){ // 뒤에서부터 검색
                if(A[j] < A[i]) { // 삽입정렬의 대상의 앞칸 값이 더 작으면,
                    insert_point = j + 1; // 삽입하려는 값이 더 작으니까, 오른쪽 인덱스에 해당 값을 저장
                    break;
                }
                if (j == 0) { // 0번 인덱스까지 탐색을 마침, 즉 들어온 값이 제일 작음
                    insert_point = 0;
                }
            }
            for(int j = i; j > insert_point; j--){
                A[j] = A[j - 1]; // 다른 인덱스 값들 시프트
            }
            A[insert_point] = insert_value;

        }
        //합배열 만들기
        S[0] = A[0];
        for(int i = 1; i < N; i++){
            S[i] = S[i - 1] + A[i];

        }
        int sum = 0;
        for(int i = 0; i <N; i++){
            sum = sum + S[i];
        }
        System.out.println(sum);
    }
}
