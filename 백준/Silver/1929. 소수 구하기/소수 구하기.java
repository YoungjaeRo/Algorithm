import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        primeNumber(m, n);
    }
    
    //에라토스테네스의 체 이용
    static void primeNumber(int m, int n) {
        int[] arr = new int[n+1];
        StringBuilder sb = new StringBuilder();
        
        //배열 초기화
        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }
        
        //2부터 시작해서 i의 배수들을 배열에서 지워준다
        for (int i = 2; i <= n; i++) {
            //이미 지워진 수는 건너뛴다
            if (arr[i] == 0) continue;  
            for (int j = i+i; j <= n; j += i) {
                arr[j] = 0;
            }
        }
        for (int i = m; i <= n; i++) {
            if (arr[i] != 0) sb.append(i + "\n");
        }
        System.out.print(sb);
    }
}