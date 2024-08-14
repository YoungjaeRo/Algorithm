import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 수의 개수를 입력받기, 그리고 그걸 기반으로 배열을 만들기
         int N = Integer.parseInt(br.readLine());
         int[] arr = new int[N];

         int result = 0;

         // 공백을 기준으로 입력된 숫자를 읽어들이기
        StringTokenizer st  = new StringTokenizer(br.readLine());

        // 입력한 수를 토근화 시켜 정수로 변환시킨 후, 각 배열에 저장
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터를 사용하기 이전 무조건 정렬하기 : Arrays.sort()
        Arrays.sort(arr);

        // 투 포인터 알고리즘 시작
       for (int f = 0; f < N; f++){ // 각 원소들에 대해 좋은 수 판별을 시작
           int find = arr[f];
           int start = 0;
           int end = N - 1;


           // 매우 중요 start 가 end보다 작을 때 까지만 실행하기
           while(start < end){
               if(arr[start] + arr[end] == find){
                   // 여기에 더불어 서로 다른 수의 합인것을 검증
                   if(start != f && end != f){
                       result++;
                       break; //  while문을 탈출, 다시 for 문으로 들어감
                   } else if(start == f){ // 찾으려는 수의 자기 자신의 인덱스에 도착하면 건너뛰기
                       start++;

                   } else if(end == f) {
                       end--;

                   }
               }
               // 좋은 수를 찾지 못했을때, 또 다시 조정
               else if(arr[start] + arr[end] < find){
                   start++;
               } else {
                   end--;

               }
           }
       }
        System.out.println(result);
    }
}
