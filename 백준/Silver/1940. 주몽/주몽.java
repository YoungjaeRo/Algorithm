

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 재료의 개수
        int M = Integer.parseInt(br.readLine()); // 갑옷을 만드는데 필요한 조건 (합)

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());

        }
        // 배열을 정렬해주는 메서드: Arrays.sort()
        Arrays.sort(arr);
        int count = 0;
        int start = 0;
        int end = N - 1; // start 와 end를 양쪽 끝에다가 위치

        while(start < end){ // 두포인터가 겹쳐지기 전까지 실행
            if(arr[start] + arr[end] < M){
                start++;
            } else if(arr[start] + arr[end] > M){
                end--;

            } else { // 조합을 찾은 경우,
                count++;
                start++;
                end--;
            }
        }
        System.out.println(count);

    }
}
