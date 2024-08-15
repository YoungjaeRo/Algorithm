import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] myArr;
    static int[] checkArr;
    static int checkSecret;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // 문자열 길이
        int P = Integer.parseInt(st.nextToken()); // 부분 문자열 길이

        int result = 0;
        char[] A = new char[S];
        checkArr = new int[4]; // 'A', 'C', 'G', 'T' 각각의 최소 요구 개수
        myArr = new int[4];    // 현재 윈도우 내에서 'A', 'C', 'G', 'T'의 개수
        checkSecret = 0;

        A = br.readLine().toCharArray(); // 입력 문자열을 char 배열로 변환

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) {
                checkSecret++;
            }
        }

        for (int i = 0; i < P; i++) { // 첫 번째 윈도우 설정
            Add(A[i]);
        }
        if (checkSecret == 4) { // 초기 윈도우가 조건을 만족하는지 확인
            result++;
        }

        // 슬라이딩 윈도우
        for (int end = P; end < S; end++) {
            int start = end - P;
            Add(A[end]);
            Remove(A[start]);
            if (checkSecret == 4) {
                result++;
            }
        }

        System.out.println(result); // 결과 출력
        br.close();
    }

    private static void Remove(char c) {
        switch (c) {
            case 'A':
                if (myArr[0] == checkArr[0]) {
                    checkSecret--;
                }
                myArr[0]--;
                break;
            case 'C':
                if (myArr[1] == checkArr[1]) {
                    checkSecret--;
                }
                myArr[1]--;
                break;
            case 'G':
                if (myArr[2] == checkArr[2]) {
                    checkSecret--;
                }
                myArr[2]--;
                break;
            case 'T':
                if (myArr[3] == checkArr[3]) {
                    checkSecret--;
                }
                myArr[3]--;
                break;
        }
    }

    public static void Add(char c) {
        switch (c) {
            case 'A':
                myArr[0]++;
                if (myArr[0] == checkArr[0]) {
                    checkSecret++;
                }
                break;
            case 'C':
                myArr[1]++;
                if (myArr[1] == checkArr[1]) {
                    checkSecret++;
                }
                break;
            case 'G':
                myArr[2]++;
                if (myArr[2] == checkArr[2]) {
                    checkSecret++;
                }
                break;
            case 'T':
                myArr[3]++;
                if (myArr[3] == checkArr[3]) {
                    checkSecret++;
                }
                break;
        }
    }
}
