import java.util.Scanner;
// 확장은 먼저 이동하고 그 이동값을 더해주고, 축소는 현재 인덱스 값을 빼주고, 다음 인덱스로 이동

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        // 각 변수들을 초기화
        int count = 1;
        int start_index = 1;
        int end_index = 1;
        int sum = 1;

        while(end_index != N) { //이미 자기자신을 가르키는 값을 포함했기 때문에, end_index가 끝까지 가면 안된다.
            if(sum == N) {
                count++; // 조합을 찾았다!
                end_index++; // 다음 조합을 찾기 위해 엔드 인덱스를 또 이동
                sum = sum + end_index; // 이동한 엔드 인덱스를 더해서 다음 또 비교를 시행
            } else if(sum > N){
                sum = sum - start_index; // 일단 현재 스타트 인덱스 값을 sum에서 빼주고
                start_index++; // 스타트 인덱스를 이동시킨다.

            } else if (sum < N){ // 타켓보다 더 작으므로 확장해야한다
               end_index++;
               sum = sum + end_index;
            }


        }
        System.out.println(count);

    }
}
