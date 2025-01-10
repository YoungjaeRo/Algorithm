import java.util.*;
import java.io.*;

public class Main {

    static class Water {
        int a;
        int b;
        int c;
        public Water(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int a, b, c;
    static ArrayList<Integer> answer = new ArrayList<>();
    static boolean[][][] visited = new boolean[201][201][201];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        Queue<Water> que = new LinkedList<Water>();
        que.add(new Water(0, 0, c));

        while (!que.isEmpty()) {
            Water water = que.poll(); // 반환 후 삭제

            if (visited[water.a][water.b][water.c]) {
                continue;
            } else {
                visited[water.a][water.b][water.c] = true;
            }

            if (water.a == 0) { //첫 번째 물통(용량이 A인)이 비어 있을 때
                answer.add(water.c); //세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양
            }

            /** 총 6개의 경우의 수 (a,b)(b,a)(b,c)(c,b)(a,c)(c,a) **/

            /* (a,b)와 (b,a) */
            // b물통의 물을 a물통의 물에 합치려 할때, a물통이 넘친다면
            if (water.a + water.b > a) {
                // a물통에 전체 용량만큼 채우고, 나머지 물의 양은 b에 할당
                que.add(new Water(a, water.a + water.b - a, water.c));
            } else {
                // 넘치지 않으면 a물통에 모두 할당
                que.add(new Water(water.a + water.b, 0, water.c));
            }
            // a물통의 물을 b물통의 물에 합치려 할때, b물통이 넘친다면
            if (water.a + water.b > b) {
                // b물통에 전체 용량만큼 채우고, 나머지 물의 양은 a에 할당
                que.add(new Water(water.a + water.b - b, b, water.c));
            } else {
                // 넘치지 않으면 b물통에 모두 할당
                que.add(new Water(0, water.a + water.b, water.c));
            }

            /* (b,c)와 (c,b) */
            // c물통의 물을 b물통의 물에 합치려 할때, b물통이 넘친다면
            if (water.b + water.c > b) {
                // b물통에 전체 용량만큼 채우고, 나머지 물의 양은 c에 할당
                que.add(new Water(water.a, b, water.b + water.c - b));
            } else {
                // 넘치지 않으면 b물통에 모두 할당
                que.add(new Water(water.a, water.b + water.c, 0));
            }
            // b물통의 물을 c물통의 물에 합치려 할때, c물통이 넘친다면
            if (water.b + water.c > c) {
                // c물통에 전체 용량만큼 채우고, 나머지 물의 양은 b에 할당
                que.add(new Water(water.a, water.b + water.c - c, c));
            } else {
                // 넘치지 않으면 c물통에 모두 할당
                que.add(new Water(water.a, 0, water.b + water.c));
            }

            /* (a,c)와 (c,a) */
            // c물통의 물을 a물통의 물에 합치려 할때, a물통이 넘친다면
            if (water.a + water.c > a) {
                // a물통에 전체 용량만큼 채우고, 나머지 물의 양은 c에 할당
                que.add(new Water(a, water.b, water.a + water.c - a));
            } else {
                // 넘치지 않으면 a물통에 모두 할당
                que.add(new Water(water.a + water.c, water.b, 0));
            }
            // a물통의 물을 c물통의 물에 합치려 할때, c물통이 넘친다면
            if (water.a + water.c > c) {
                // c물통에 전체 용량만큼 채우고, 나머지 물의 양은 a에 할당
                que.add(new Water(water.a + water.c - c, water.b, c));
            } else {
                // 넘치지 않으면 c물통에 모두 할당
                que.add(new Water(0, water.b, water.a + water.c));
            }
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int a : answer) {
            set.add(a);
        }

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
        }

        System.out.println(sb);
    }
}