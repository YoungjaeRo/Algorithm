import java.io.*;
import java.util.*;

/**
 * BOJ 15686 - 치킨 배달
 * 핵심:
 *  - 치킨집 K개 중 M개를 고르는 모든 조합을 만들고
 *  - 각 조합에 대해 모든 집의 "최소 치킨거리"를 합산 -> 최솟값 갱신
 *  - 집-치킨 거리는 dist[h][k]로 전처리해 즉시 참조
 */
public class Main {
    static int N, M;

    // 집/치킨집 좌표 목록
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();

    // dist[h][k] = h번째 집과 k번째 치킨집 사이의 맨해튼 거리
    static int[][] dist;

    // 현재 선택한 치킨집 인덱스들(크기 M)
    static int[] pick;

    static int H, K;             // 집 수, 치킨집 수
    static int best = Integer.MAX_VALUE; // 최소 도시 치킨 거리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 입력: 0=빈칸, 1=집, 2=치킨집
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {      // ★ 여기 N으로
                int v = Integer.parseInt(st.nextToken());
                if (v == 1) houses.add(new int[]{r, c});
                else if (v == 2) chickens.add(new int[]{r, c});
            }
        }

        H = houses.size();
        K = chickens.size();

        // 집-치킨 거리 전처리
        dist = new int[H][K];
        for (int h = 0; h < H; h++) {
            int hr = houses.get(h)[0];
            int hc = houses.get(h)[1];
            for (int k = 0; k < K; k++) {
                int cr = chickens.get(k)[0];
                int cc = chickens.get(k)[1];
                dist[h][k] = Math.abs(hr - cr) + Math.abs(hc - cc);
            }
        }

        pick = new int[M];

        // 조합 DFS 시작 (치킨집 K개 중 M개 선택)
        dfs(0, 0);

        // 정답 출력
        System.out.println(best);
    }

    /**
     * dfs(depth, start)
     *  - depth: 현재까지 선택한 치킨집 수 (0..M)
     *  - start: 다음으로 고려할 치킨집 인덱스 시작점 (오름차순으로 조합 생성)
     */
    static void dfs(int depth, int start) {
        if (depth == M) {
            // 현재 pick 조합으로 도시 치킨 거리 계산
            int sum = 0;
            for (int h = 0; h < H; h++) {
                int minD = Integer.MAX_VALUE;
                for (int i = 0; i < M; i++) {
                    int k = pick[i];
                    minD = Math.min(minD, dist[h][k]);
                    if (minD == 0) break; // 미세 최적화
                }
                sum += minD;
                if (sum >= best) return; // 가지치기: 이미 최솟값 이상이면 중단
            }
            best = Math.min(best, sum);
            return;
        }

        // 조합 만들기
        for (int i = start; i < K; i++) {
            pick[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }
}
