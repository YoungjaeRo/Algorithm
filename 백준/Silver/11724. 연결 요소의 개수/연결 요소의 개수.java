import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;

    //인접리스트로 표현하기
    static ArrayList<Integer>[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //n은 노드의 개수, m은 에지의 개수 (연결 선)
        int n = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        A = new ArrayList[n + 1];

        for(int i = 1; i < n + 1; i++){

            A[i] = new ArrayList<>(); // 각각의 배열에 어레이리스트의 참조값을 대입

        }
        for(int i = 0; i< m; i++){
            st = new StringTokenizer(br.readLine());
            //s는 시작점, e는 종료점
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            // 정해진 방향이 없기 때문에, 각 해당하는 인덱스의 인접 리스트에 서로의 값을 저장해 주어야 한다.
            A[s].add(e);
            A[e].add(s);

        }

        int count = 0;

        for(int i = 1; i < n + 1; i++){
            if(!visited[i]) { // visited가 false이면, 방문하지 않은것이라면,
                count++;
                DFS(i); // 방문하지 않는 i를 시작으로 DFS를 실행한다.

            }
        }
        System.out.println(count);
    }
    private static void DFS(int v){
        if(visited[v]){ // true이면 한번 탐색을 한것이기 때문에 DFS를 진행하지 않는다.
            return; // 더이상 탐색하지 않는다
        }
        visited[v] = true;// 일단 이제 방문했으니까 visited의 상태를 false에서 true로 바꿔주고,
        for(int i :A[v]) {
            if(!visited[i]){
                DFS(i);
            }
        }

    }
}
