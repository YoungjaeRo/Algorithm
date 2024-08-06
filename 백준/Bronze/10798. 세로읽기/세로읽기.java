import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 각 줄의 문자열을 저장할 배열
        String[] words = new String[5];

        // 각 줄의 문자열을 배열에 저장
        for (int i = 0; i < 5; i++) {
            words[i] = reader.readLine();
        }

        // 최대 열 수를 결정 (각 문자열의 최대 길이)
        int maxLength = 0;
        for (String word : words) {
            maxLength = Math.max(maxLength, word.length());  // 각 문자열의 길이와 maxLength를 비교
        }

        // 결과를 저장할 StringBuilder 객체 생성
        StringBuilder result = new StringBuilder();

        // 열(column)마다 문자를 읽어들임
        for (int i = 0; i < maxLength; i++) {  // maxLength만큼 열을 반복
            for (int j = 0; j < 5; j++) {      // 5줄의 문자열을 반복
                if (i < words[j].length()) {   // 현재 열 인덱스가 문자열의 길이 이내인지 확인
                    result.append(words[j].charAt(i));  // 문자를 result에 추가
                }
            }
        }

        // 결과 출력
        System.out.println(result.toString());
    }
}