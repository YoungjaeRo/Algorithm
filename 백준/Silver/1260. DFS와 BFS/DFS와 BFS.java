import java.util.*;
import java.io.*;
public class Main {
    static boolean[] visited; // 방문 기록용 배열
    static ArrayList<Integer>[] A; // 인접리스트

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int N = scan.nextInt(); // 노드의 개수
       int M = scan.nextInt(); // 애지 개수
        int start = scan.nextInt(); // 시작점

        A = new ArrayList[N + 1]; // 1번 인덱스부터 사용하기 위해서
        for(int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            int S = scan.nextInt(); // 시작점
            int E = scan.nextInt(); // 도착점
            A[S].add(E);
            A[E].add(S);
        }
        //같은 레벨의 노드중 가장 작은수의 노드에 먼저 접근해라
        for(int i = 1; i < N + 1; i++){
            Collections.sort(A[i]); // 자동으로 각 노드의 ArrayList를 정렬해줌 오름차순으로 정렬됨

        }
        visited = new boolean[N + 1];
        DFS(start);
        System.out.println();//공백용

        //DFS가 끝난 뒤엔, 방문배열을 초기화
        visited = new boolean[N + 1];
        BFS(start);
        System.out.println();//공백용
    }
    //자바에서는 같은 클래스 내에서 static 메서드나 변수를 사용할 때, 클래스 이름을 생략할 수 있습니다. 즉, 클래스 내에서 메서드를 호출할 때는 클래스 이름을 굳이 붙이지 않아도 됩니다.
    //
    //당신이 작성한 코드에서 BFS 메서드는 P1260DFS와BFS구현하기 클래스에 정의되어 있고, main 메서드도 같은 클래스에 정의되어 있습니다. 그래서 main 메서드 안에서 BFS(start)처럼 직접 호출이 가능한 것이죠.

    public static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(Node); // 큐에 시작 노드를 삽입
        visited[Node] = true;
        while(!queue.isEmpty()) {
            int now_Node = queue.poll(); // 가장 앞에 있는 요소를 뽑기
            System.out.print(now_Node + " "); // 처음엔 시작노드가 뽑힐 것이다
            for(int i : A[now_Node]) {
                if(!visited[i]) { // 해당 노드의 에지들 중 방문하지 않은 것들을 탐색해서 큐에 삽입
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }

    public static void DFS(int Node) {
        System.out.print(Node + " ");
        visited[Node] = true;
        for(int i : A[Node]) {
            if(!visited[i]) {
                DFS(i);
            }
        }
    }


}
