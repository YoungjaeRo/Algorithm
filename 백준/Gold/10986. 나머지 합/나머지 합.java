import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        // 들어오는 숫자의 수와 나누어질 수 선언
        int N = Integer.parseInt(stringTokenizer.nextToken());

        int M = Integer.parseInt(stringTokenizer.nextToken());

        // 합 베열 생성하기. 웬만해선 long형으로 선언하기
        long[] S = new long[N];

        // M으로 나누고 남은 나머지 값을 저장할 배열을 선언

        long[] C = new long[M];

        long answer = 0;

        // 다음 라인을 읽어 합배열의 첫 인덱스의 첫번째 값 넣어놓기
        StringTokenizer stringTokenizer1 = new StringTokenizer(br.readLine());

        S[0] = Integer.parseInt(stringTokenizer1.nextToken());
        //S[0]과 a[0]은 같음
        // 순회하면서 나머지 인덱스에도 합배열 값 공식을 통해 합배열 만들기
        for (int i = 1; i< N; i++) {
            S[i] = S[i -1] + Integer.parseInt(stringTokenizer1.nextToken());
        }

        // 합배열 S를 다 만들고 나면, 모든 원소에 M을 % 한 연산을 수행한다.
        for(int i = 0; i < N; i++){
            long remainder = S[i] % M; // long 형이기 때문에 int형으로 바꾸어줘야함

        // 구간이 0일때 answer에 카운팅
        if (remainder == 0){
            answer++;
        }
        // 일반 경우의 수를 위해 0인 인덱스 값을 더해줌

        C[(int) remainder]++; // 자바에서 배열의 인덱스로 들어오는 값은 int로 제한된다.
        // remainder로 지정될 인덱스는 나머지이기 때문에, M보다 클수가 없음, 그래서 < M까지만 순회하면 된다.
        // 예를 들어 나누는 수가 3이면, 나머지는 0,1,2가 될 수 있다 그래서 인덱스를 0,1,2까지만 탐색하면 된다.

        }
        for (int i = 0; i < M; i++){
            if (C[i] > 1){ // 나머지값 저장 배열에서 카운팅이 1이상인것들을 추출 , 그리고 쌍( ) 을 뽑는것이기 때문에, 분모는 2 * 1로 고정됨
                answer = answer + (C[i] * (C[i]-1)) / (2 * 1);
            }
        }
        System.out.println(answer);
    }
}
