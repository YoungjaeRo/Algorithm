import java.io.*;
import java.util.*;

/**
 * BOJ 23289 온풍기 안녕!
 *
 * 핵심 루프(초콜릿 1개당 1번):
 * 1) 모든 온풍기 바람 퍼뜨리기
 * 2) 온도 조절(확산)
 * 3) 테두리 온도 -1
 * 4) 조사 칸이 K 이상인지 체크
 *    - 만족하면 종료
 *    - 아니면 초콜릿 +1
 *    - 초콜릿이 100 넘어가면 종료(정답 101)
 */
public class Main {
    static int R, C, K;
    static int[][] board;     // 입력 정보(온풍기, 조사칸)
    static int[][] temp;      // 현재 온도

    // 벽 정보
    // wallUp[r][c] = (r,c)에서 위로 이동 불가
    // wallRight[r][c] = (r,c)에서 오른쪽 이동 불가
    static boolean[][] wallUp;
    static boolean[][] wallRight;

    // 온풍기 정보 저장
    static class Heater {
        int r, c, dir;
        Heater(int r, int c, int dir) {
            this.r = r; this.c = c; this.dir = dir;
        }
    }

    static List<Heater> heaters = new ArrayList<>();
    static List<int[]> checks = new ArrayList<>(); // 조사 칸들

    // 방향 매핑: 문제는 1~4 (1:오,2:왼,3:위,4:아래)
    // 편의상 0~3으로 변환해서 사용
    // 0:오, 1:왼, 2:위, 3:아
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static boolean in(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    /**
     * (r,c) -> (nr,nc)로 "직접" 한 칸 이동 가능한지(벽 체크 포함)
     */
    static boolean canMove(int r, int c, int nr, int nc) {
        // 위
        if (nr == r - 1 && nc == c) {
            return !wallUp[r][c];
        }
        // 아래
        if (nr == r + 1 && nc == c) {
            return !wallUp[r + 1][c];
        }
        // 오른쪽
        if (nr == r && nc == c + 1) {
            return !wallRight[r][c];
        }
        // 왼쪽
        if (nr == r && nc == c - 1) {
            return !wallRight[r][c - 1];
        }
        return false;
    }

    /**
     * 온풍기 1개 바람 퍼뜨리기
     * - 시작: 온풍기 바로 앞칸에 5
     * - BFS 방식으로 5->4->3->2->1
     * - 같은 온풍기에서 같은 칸은 중복 방문하지 않음(중복 가열 방지)
     */
    static void blowOneHeater(Heater h, int[][] add) {
        boolean[][] visited = new boolean[R][C];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        int sr = h.r + dr[h.dir];
        int sc = h.c + dc[h.dir];

        // 온풍기 바로 앞 칸이 격자 밖이면 아무것도 못 뿜음
        if (!in(sr, sc)) return;

        // 시작점은 벽 체크가 필요 없음(문제 규칙상 "바로 앞 칸"으로 바람이 나감)
        visited[sr][sc] = true;
        q.add(new int[]{sr, sc, 5});
        add[sr][sc] += 5;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], power = cur[2];

            // 다음으로 퍼질 바람 세기가 1 미만이면 종료
            if (power == 1) continue;

            int np = power - 1;

            // 방향별로 다음 후보 3칸(대각/직진/대각)을 만든다.
            // 그리고 "대각"은 벽을 2번 검사해야 한다.
            if (h.dir == 0) { // 오른쪽
                // 1) 오른쪽-위 대각: (r,c)->(r-1,c)->(r-1,c+1)
                int r1 = r - 1, c1 = c;
                int r2 = r - 1, c2 = c + 1;
                if (in(r2, c2) && !visited[r2][c2]) {
                    if (in(r1, c1) && canMove(r, c, r1, c1) && canMove(r1, c1, r2, c2)) {
                        visited[r2][c2] = true;
                        add[r2][c2] += np;
                        q.add(new int[]{r2, c2, np});
                    }
                }

                // 2) 직진: (r,c)->(r,c+1)
                int rr = r, cc = c + 1;
                if (in(rr, cc) && !visited[rr][cc]) {
                    if (canMove(r, c, rr, cc)) {
                        visited[rr][cc] = true;
                        add[rr][cc] += np;
                        q.add(new int[]{rr, cc, np});
                    }
                }

                // 3) 오른쪽-아래 대각: (r,c)->(r+1,c)->(r+1,c+1)
                r1 = r + 1; c1 = c;
                r2 = r + 1; c2 = c + 1;
                if (in(r2, c2) && !visited[r2][c2]) {
                    if (in(r1, c1) && canMove(r, c, r1, c1) && canMove(r1, c1, r2, c2)) {
                        visited[r2][c2] = true;
                        add[r2][c2] += np;
                        q.add(new int[]{r2, c2, np});
                    }
                }

            } else if (h.dir == 1) { // 왼쪽
                // 1) 왼쪽-위 대각: (r,c)->(r-1,c)->(r-1,c-1)
                int r1 = r - 1, c1 = c;
                int r2 = r - 1, c2 = c - 1;
                if (in(r2, c2) && !visited[r2][c2]) {
                    if (in(r1, c1) && canMove(r, c, r1, c1) && canMove(r1, c1, r2, c2)) {
                        visited[r2][c2] = true;
                        add[r2][c2] += np;
                        q.add(new int[]{r2, c2, np});
                    }
                }

                // 2) 직진: (r,c)->(r,c-1)
                int rr = r, cc = c - 1;
                if (in(rr, cc) && !visited[rr][cc]) {
                    if (canMove(r, c, rr, cc)) {
                        visited[rr][cc] = true;
                        add[rr][cc] += np;
                        q.add(new int[]{rr, cc, np});
                    }
                }

                // 3) 왼쪽-아래 대각: (r,c)->(r+1,c)->(r+1,c-1)
                r1 = r + 1; c1 = c;
                r2 = r + 1; c2 = c - 1;
                if (in(r2, c2) && !visited[r2][c2]) {
                    if (in(r1, c1) && canMove(r, c, r1, c1) && canMove(r1, c1, r2, c2)) {
                        visited[r2][c2] = true;
                        add[r2][c2] += np;
                        q.add(new int[]{r2, c2, np});
                    }
                }

            } else if (h.dir == 2) { // 위
                // 1) 위-왼 대각: (r,c)->(r,c-1)->(r-1,c-1)
                int r1 = r, c1 = c - 1;
                int r2 = r - 1, c2 = c - 1;
                if (in(r2, c2) && !visited[r2][c2]) {
                    if (in(r1, c1) && canMove(r, c, r1, c1) && canMove(r1, c1, r2, c2)) {
                        visited[r2][c2] = true;
                        add[r2][c2] += np;
                        q.add(new int[]{r2, c2, np});
                    }
                }

                // 2) 직진: (r,c)->(r-1,c)
                int rr = r - 1, cc = c;
                if (in(rr, cc) && !visited[rr][cc]) {
                    if (canMove(r, c, rr, cc)) {
                        visited[rr][cc] = true;
                        add[rr][cc] += np;
                        q.add(new int[]{rr, cc, np});
                    }
                }

                // 3) 위-오 대각: (r,c)->(r,c+1)->(r-1,c+1)
                r1 = r; c1 = c + 1;
                r2 = r - 1; c2 = c + 1;
                if (in(r2, c2) && !visited[r2][c2]) {
                    if (in(r1, c1) && canMove(r, c, r1, c1) && canMove(r1, c1, r2, c2)) {
                        visited[r2][c2] = true;
                        add[r2][c2] += np;
                        q.add(new int[]{r2, c2, np});
                    }
                }

            } else { // 아래 (3)
                // 1) 아래-왼 대각: (r,c)->(r,c-1)->(r+1,c-1)
                int r1 = r, c1 = c - 1;
                int r2 = r + 1, c2 = c - 1;
                if (in(r2, c2) && !visited[r2][c2]) {
                    if (in(r1, c1) && canMove(r, c, r1, c1) && canMove(r1, c1, r2, c2)) {
                        visited[r2][c2] = true;
                        add[r2][c2] += np;
                        q.add(new int[]{r2, c2, np});
                    }
                }

                // 2) 직진: (r,c)->(r+1,c)
                int rr = r + 1, cc = c;
                if (in(rr, cc) && !visited[rr][cc]) {
                    if (canMove(r, c, rr, cc)) {
                        visited[rr][cc] = true;
                        add[rr][cc] += np;
                        q.add(new int[]{rr, cc, np});
                    }
                }

                // 3) 아래-오 대각: (r,c)->(r,c+1)->(r+1,c+1)
                r1 = r; c1 = c + 1;
                r2 = r + 1; c2 = c + 1;
                if (in(r2, c2) && !visited[r2][c2]) {
                    if (in(r1, c1) && canMove(r, c, r1, c1) && canMove(r1, c1, r2, c2)) {
                        visited[r2][c2] = true;
                        add[r2][c2] += np;
                        q.add(new int[]{r2, c2, np});
                    }
                }
            }
        }
    }

    /**
     * 1) 모든 온풍기 바람 퍼뜨리기
     */
    static void blowAllHeaters() {
        int[][] add = new int[R][C];
        for (Heater h : heaters) {
            blowOneHeater(h, add);
        }
        // 누적 가열 결과를 온도 배열에 더한다
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j] += add[i][j];
            }
        }
    }

    /**
     * 2) 온도 조절(확산)
     * - 동시에 반영해야 해서 delta 배열 사용
     * - 중복 방지: 오른쪽/아래만 비교
     */
    static void controlTemp() {
        int[][] delta = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 오른쪽 칸과 비교
                int nr = r, nc = c + 1;
                if (in(nr, nc) && canMove(r, c, nr, nc)) {
                    int diff = temp[r][c] - temp[nr][nc];
                    if (diff > 0) {
                        int d = diff / 4;
                        delta[r][c] -= d;
                        delta[nr][nc] += d;
                    } else if (diff < 0) {
                        int d = (-diff) / 4;
                        delta[r][c] += d;
                        delta[nr][nc] -= d;
                    }
                }

                // 아래 칸과 비교
                nr = r + 1; nc = c;
                if (in(nr, nc) && canMove(r, c, nr, nc)) {
                    int diff = temp[r][c] - temp[nr][nc];
                    if (diff > 0) {
                        int d = diff / 4;
                        delta[r][c] -= d;
                        delta[nr][nc] += d;
                    } else if (diff < 0) {
                        int d = (-diff) / 4;
                        delta[r][c] += d;
                        delta[nr][nc] -= d;
                    }
                }
            }
        }

        // 동시에 반영
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[i][j] += delta[i][j];
            }
        }
    }

    /**
     * 3) 테두리 온도 1 감소(온도>0일 때만)
     * - 모서리 중복 감소 방지 위해 한 번만 처리
     */
    static void decreaseBorder() {
        // 위/아래 행
        for (int c = 0; c < C; c++) {
            if (temp[0][c] > 0) temp[0][c]--;
            if (temp[R - 1][c] > 0) temp[R - 1][c]--;
        }
        // 좌/우 열 (모서리는 이미 처리했으니 1~R-2)
        for (int r = 1; r <= R - 2; r++) {
            if (temp[r][0] > 0) temp[r][0]--;
            if (temp[r][C - 1] > 0) temp[r][C - 1]--;
        }
    }

    /**
     * 4) 조사 칸 모두 K 이상인지 확인
     */
    static boolean checkAll() {
        for (int[] p : checks) {
            if (temp[p[0]][p[1]] < K) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        temp = new int[R][C];

        // 벽 배열은 경계 체크를 편하게 하려고 R+1, C+1 정도로 넉넉히 잡기도 하는데
        // 여기서는 정확히 R,C로 쓰고, 아래 이동 체크 시 wallUp[r+1][c] 접근하므로
        // r+1이 가능하도록 wallUp을 R 크기로 두되 "아래 체크는 r+1이 in일 때만" 호출되게 구성했다.
        wallUp = new boolean[R][C];
        wallRight = new boolean[R][C];

        // 격자 입력
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                int v = board[i][j];

                // 1~4: 온풍기
                if (v >= 1 && v <= 4) {
                    int dir = v - 1; // 0~3 변환
                    heaters.add(new Heater(i, j, dir));
                }

                // 5: 조사 칸
                if (v == 5) {
                    checks.add(new int[]{i, j});
                }
            }
        }

        // 벽 입력
        int W = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1; // 0-index
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) {
                // (x,y)와 (x-1,y) 사이 벽 => (x,y)에서 위로 못 감
                wallUp[x][y] = true;
            } else {
                // (x,y)와 (x,y+1) 사이 벽 => (x,y)에서 오른쪽 못 감
                wallRight[x][y] = true;
            }
        }

        int chocolate = 0;

        while (true) {
            // 0) 초콜릿이 100 넘어가면 중단 (정답 101)
            if (chocolate > 100) break;

            // 1) 온풍기 바람
            blowAllHeaters();

            // 2) 온도 조절
            controlTemp();

            // 3) 테두리 감소
            decreaseBorder();

            // 4) 초콜릿 먹기
            chocolate++;

            // 5) 조사 칸 검사
            if (checkAll()) break;
        }

        // 문제는 "먹은 초콜릿 개수" 출력
        // 위에서 chocolate를 루프 말미에 증가시키는 방식이므로
        // 100 초과로 끊기면 101이 될 수 있고, 그대로 출력하면 됨.
        System.out.println(chocolate);
    }
}
