
import java.util.*;
import java.io.*;

public class Main { //버블 소트의 시간복잡도는 On^2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        myData[] arr = new myData[N];

        for(int i = 0; i < N; i++){ // 항상 까먹는거 같은데, 기본형이 아닌 자료형의 배열은 값이 아니라, 참조값이 들어간다, 즉 생성자의 주소를 주면 됨
            arr[i] =  new myData(Integer.parseInt(br.readLine()), i); // 배열에 값 주입
        }

        //이제 myData[]을 정렬하기
        Arrays.sort(arr); // 이 메서드가 유효하기 위해서, Comparable을 구현했고, public int compareTo 메서드도 오버라딩 함

        int Max = 0;
        for(int i = 0; i < N; i++){
            //정렬전 index - 정렬 후 index  최대값 저장하기
            if( Max < arr[i].index - i){
                Max = arr[i].index - i;
            }
        }
        System.out.println(Max + 1);
        

    }

   static class myData implements Comparable<myData>{ // 정적 중첩 클래스는, 이것과 별개임
        int value;
        int index;

        public myData(int value, int index){
            this.value = value;
            this.index = index;

        }

        @Override
        public int compareTo(myData o){
            return this.value - o.value;

        }

    }
}
