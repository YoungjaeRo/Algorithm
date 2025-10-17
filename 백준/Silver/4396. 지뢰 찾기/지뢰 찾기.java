import java.io.*;
import java.util.*;

public class Main {
    static final int[] dr = {-1,-1,-1, 0, 0, 1, 1, 1};
    static final int[] dc = {-1, 0, 1,-1, 1,-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        char[][] mine = new char[N][N];
        char[][] open = new char[N][N];

        for (int i = 0; i < N; i++) mine[i] = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) open[i] = br.readLine().toCharArray();

        // 1) 폭발 여부 확인 (소문자 'x' 사용)
        boolean exploded = false;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (open[r][c] == 'x' && mine[r][c] == '*') {
                    exploded = true;
                }
            }
        }

        // 2) 인접 지뢰 수 계산
        int[][] cnt = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (mine[r][c] == '*') continue;
                int sum = 0;
                for (int k = 0; k < 8; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (0 <= nr && nr < N && 0 <= nc && nc < N && mine[nr][nc] == '*') {
                        sum++;
                    }
                }
                cnt[r][c] = sum; // ← 여기!
            }
        }

        // 3) 결과 보드 구성
        char[][] ans = new char[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(ans[i], '.');

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (open[r][c] == 'x' && mine[r][c] != '*') {
                    ans[r][c] = (char) ('0' + cnt[r][c]);
                }
            }
        }

        if (exploded) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (mine[r][c] == '*') ans[r][c] = '*';
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(ans[i]).append('\n');
        System.out.print(sb); // println 말고 print 권장
    }
}
