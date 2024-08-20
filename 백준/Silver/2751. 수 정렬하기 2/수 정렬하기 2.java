
import java.util.*;
import java.io.*;

public class Main {
    //정렬할 배열 A를 선언하기
    public static int[] A; // 나중에 꼭 배열의 크기를 할당해줘야 함

    // 정렬할 때, 잠시 사용할 임시 배열을 선언하기
    public static int[] tmp;
    public static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        A = new int[N + 1]; // 1번 인덱스부터 사용하기 위해
        tmp = new int[N + 1];

        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(br.readLine());

        }

        merge_sort(1, N);
        for(int i = 1; i<=N; i++){ //인덱스 1부터 정렬
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
    public static void merge_sort(int s, int e){ // s:시작점, e: 종료점, m:중간점

        if(e - s < 1){// 시작점과 종료점이 같다면
            return; // 함수를 종료함
        }

        int m = s + (e - s) / 2;
        // 재귀함수로 구현
        merge_sort(s, m);
        merge_sort(m + 1, e);
        for(int i = s; i <= e; i++){
            tmp[i] = A[i];

        }
        int k = s;
        int index1 = s;
        int index2 = m + 1;
        while(index1 <= m && index2 <= e){
            if(tmp[index1] > tmp[index2]){
                A[k] = tmp[index2];
                k++;
                index2++;

            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }
        while(index1 <= m){ // 한쪽 그룹이 다 선택된 후, 남아있는 값 정리하기
            A[k] = tmp[index1];
            k++;
            index1++;
        }

        while(index2 <= e){
            A[k] = tmp[index2];
            k++;
            index2++;

        }
    }

}
