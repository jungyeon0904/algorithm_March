import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        // 입출력용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(st.nextToken());   // 카드의 개수
        int K = Integer.parseInt(st.nextToken());   // 카드를 섞은 횟수

        int[] prev = new int[N];    // K번 카드를 섞은 후, 카드의 배치 배열
        int[] next = new int[N];    // 원래 카드의 배치 배열

        // K번 카드를 섞은 후, 카드의 배치 배열 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int s = 0; s < N; s++) {
            prev[s] = Integer.parseInt(st.nextToken());
        }

        // D 배열
        // 현재 인덱스의 카드를 "Di"번째로 보냄
        int[] pickIndex = new int[N];   

        // D 배열 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int d = 0; d < N; d++) {
            pickIndex[d] = Integer.parseInt(st.nextToken());
        }

        /*
        Logic
        카드를 섞는 방식은 현재 "Di"번째에 있는 카드를 "i"번째로 가져오는 것이다.
        따라서, K번째 섞은 카드에서 역순으로, "i"번째에 있는 카드를 "Di"번째로 보내면 된다.
         */
        
        // K번만큼 반복
        for (int k = 0; k < K; k++) {

            // "i"번째에 있는 카드를 "Di"번째로 보내기
            for (int pick = 0; pick < N; pick++) {
                next[pickIndex[pick] - 1] = prev[pick];
            }
            
            // k번째에서 카드를 반대로 섞고, 이를 저장
            prev = Arrays.copyOf(next, N);
        }

        // 출력용 저장
        for (int n : next) {
            sb.append(n).append(" ");
        }

        // 출력하기
        System.out.println(sb);
    }
}
