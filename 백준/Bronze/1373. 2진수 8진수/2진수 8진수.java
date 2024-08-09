import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String binaryNum = br.readLine();

        //3으로 나누어떨어지면 8진수 길이는 length/3 그렇지 않으면 length/3+1
        int octalLength = binaryNum.length() % 3 == 0 ? binaryNum.length()/3 : binaryNum.length()/3+1;
        int[] arr = new int[octalLength];

        //인덱스를 뒤에서 부터 세는 수작질
        int index = octalLength-1;
        for (int i = binaryNum.length()-1; i >= 0; i--) {
            // '0'의 아스키코드 값 빼서 1또는 0 나오도록 만듦
            int chVal = binaryNum.charAt(i) - '0';

            if((binaryNum.length()-i-1) % 3 ==0){
                arr[index] += chVal * 1;
            }
            if((binaryNum.length()-i-1) % 3 == 1){
                arr[index] += chVal * 2;
            }
            if((binaryNum.length()-i-1) % 3 == 2){
                arr[index--] += chVal * 4;
            }

        }
        StringBuilder sb = new StringBuilder();
        for(int num : arr){
            sb.append(num);
        }
        System.out.println(sb);
    }
}