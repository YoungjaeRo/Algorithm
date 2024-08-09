import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //붙어있는 숫자는 하나의 숫자로 입력받기 때문에, 문자열로 입력받아서 한글자씩 처리해주어야 한다.
        //첫번째 줄의 n은 숫자로 받고, 두번째 줄의 숫자들은 하나의 문자열로 받는다

        int n = scanner.nextInt(); // 총 입력할 숫자의 수를 입력받음
        scanner.nextLine(); // \n 문자 제거하기
        String inputs = scanner.nextLine(); //  그후부턴 다 문자열로 받기
        scanner.close();

        int result = 0;
        for(int i = 0; i < n; i++){
            //반복문을 순회하며 1자리마다 char형으로 변환후 다시 int형으로 변환하여 total변수에 더하기
            result = result + (int)inputs.charAt(i) -48; // 문자 '0'의 아스키코드는 48이다 문자'1'이 아스키 코드는 49

        }
        System.out.println(result);
    }
}