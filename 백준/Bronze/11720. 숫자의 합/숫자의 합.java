import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String strInt = scanner.nextLine();
        char[] array = strInt.toCharArray();

        int sum = 0;

        for(char a : array){ // Integer.parseInt()는
            // 문자형 숫자를 int형으로 바꿔줄때는 - '0' 또는 -48을 하면 된다.
            sum = sum + (a - '0');
        }

        System.out.println(sum);



    }
}