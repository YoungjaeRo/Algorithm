import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        //배열의 크기와 질의의 개수를 가지고 옴
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());


        //1. 일반 배열 만들기
        int[][] A = new int[N + 1][N + 1];
        // 0번 인덱스를 쓰지 않기 위해서 (인덱스 0 ~ 4까지의 2차원 배열을 형성)


        for(int i = 1; i<=N; i++){
            StringTokenizer stringTokenizer1 = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++){
                A[i][j] = Integer.parseInt(stringTokenizer1.nextToken());
            }
        }


        //2. 합배열 만들기
        int[][] D = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j<= N; j++){
                D[i][j] = D[i][j - 1] + D[i - 1][j] - D[i - 1][j - 1] + A[i][j];
            }
        }
        // 입력 받은 질의 수 만큼 순회: 3회면 0~2 (총 3회 순회)
        for(int i = 0; i < M; i++){
            StringTokenizer stringTokenizer2 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(stringTokenizer2.nextToken());
            int y1 = Integer.parseInt(stringTokenizer2.nextToken());
            int x2 = Integer.parseInt(stringTokenizer2.nextToken());
            int y2 = Integer.parseInt(stringTokenizer2.nextToken());
            
            int result = D[x2][y2] - D[x1 - 1][y2] - D[x2][y1 - 1] + D[x1 - 1][y1 - 1];
            System.out.println(result);
        }

    }
}