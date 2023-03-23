import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        // 입출력용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 크레인 개수
        Integer[] weightLimit = new Integer[N];     // 각 크레인의 무게 제한 배열

        // 각 크레인의 무게 제한 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            weightLimit[n] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());    // 박스 개수
        Integer[] boxWeight = new Integer[M];       // 각 박스의 무게 배열

        // 각 박스의 무게 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            boxWeight[m] = Integer.parseInt(st.nextToken());
        }

        // 크레인의 무게 및 박스의 무게 내림차순으로 정렬
        Arrays.sort(weightLimit, Collections.reverseOrder());
        Arrays.sort(boxWeight, Collections.reverseOrder());

        /*
        무게 제한이 가장 큰 크레인이
        가장 무거운 박스를 들 수 없다면,
        "-1" 출력
        */
        if (weightLimit[0] < boxWeight[0]) {
            System.out.println(-1);
            return;
        }

        /*
        Logic
        1. 각 크레인이 한 번씩 탐색하며, 들 수 있는 가장 무거운 박스를 옮긴다.
        2. 박스를 옮기고, 그 박스의 무게를 0으로 만든다.
        3. 옮긴 박스의 Index를 index 배열에 기록한다.
           옮긴 박스의 앞의 Index는 옮길 수 없다는 것을 알기에 굳이 다시 탐색할 필요가 없다.
        4. 더 이상 옮길 박스가 없을 때까지, 이를 반복한다.
         */
        
        int time = 0;    // 모든 박스를 배로 옮기는데 드는 시간의 최솟값
        int remainOfBoxes = M; // 옮겨야할 남은 박스의 개수
        int[] index = new int[N]; // 이전에 옮긴 박스의 Index를 저장할 배열

        // 더 이상 옮길 박스가 없을 때까지 반복
        while (remainOfBoxes != 0) {

            // 각 크레인 순차적으로 접근
            for (int i = 0; i < N; i++) {

                // 이전에 옮긴 박스의 Index부터 다음에 옮길 박스를 탐색
                for (int j = index[i]; j < M; j++) {

                    // 현재 박스의 무게가 0인 경우
                    if (boxWeight[j] == 0) {
                        continue;
                    }

                    // 현재 크레인이 박스를 들 수 없는 경우
                    if (boxWeight[j] > weightLimit[i]) {
                        index[i] += 1;
                        continue;
                    }
                    
                    // 박스를 옮기고, 0으로 초기화
                    boxWeight[j] = 0;

                    // 남은 박스의 개수를 감소
                    remainOfBoxes -= 1;

                    // 다음 크레인 선택하기
                    break;
                }
            }

            // 1초 지남
            time += 1;
        }

        // 답 출력
        System.out.println(time);
    }
}
