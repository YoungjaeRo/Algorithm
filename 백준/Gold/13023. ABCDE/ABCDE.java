import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean visited[]; //방문기록 저장 배열
    static ArrayList<Integer>[] A; //데이터를 저장 할 인접리스트를 선언 배열안의 각 원소들이 리스트배열임

    static boolean arrive; // 도착 확인 변수

    public static void main(String[] args) {
        int N; // 노드의 개수
        int M; // 엣지의 개수
        arrive = false;

        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        A = new ArrayList[N]; //노드이 개수만큼의 ArrayList 배열을 생성

        visited = new boolean[N]; // 노드의 개수만큼 방문 배열의 크기를 생성

        for(int i = 0; i < N; i++){
            A[i] = new ArrayList<>(); // 처음 배열이 만들어 질때에는 참조값이 없는 상태이기 때문에 각 배열에 ArrayList객체를 할당해 주어야 한다.

        }

        for(int i = 0; i < M; i++){ //인접 리스트에 데이터를 저장하기(각 원소들이 서로 어떻게 이어져 있는지 저정)
            int S = scan.nextInt(); // 시작 노드
            int E = scan.nextInt(); // 도착 노드
            A[S].add(E);
            A[E].add(S); //양방향으로 넣어줌

        }
        for(int i = 0; i < N; i++) {
            DFS(i, 1); //depth 1부터 시작
            if (arrive) {
                break;
            }
        }
            if(arrive) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }


    }
    public static void DFS(int Nnow,int Ndepth) {
        if(Ndepth == 5 | arrive) {
            arrive = true;
            return;
        }
        visited[Nnow] = true;
        for(int i : A[Nnow]) {
            if(!visited[i]) { // 방문한적 없는 노드이면
                DFS(i, Ndepth + 1);
            }
        }
        visited[Nnow] = false;


    }

}
