import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {

    static int[] numOfPeople;   // 각 구역의 인구 수
    static int totalOfPeople = 0;   // 전체 인구 수

    static ArrayList<ArrayList<Integer>> vertex;    // 인접 리스트

    public static void main(String[] args) throws IOException {

        // 입력용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 몇 개의 구역으로 나뉘었는지 입력받기
        int N = Integer.parseInt(br.readLine());

        // 각 구역의 인구 수를 저장하는 배열 생성
        numOfPeople = new int[N];

        /* 1번 구역부터 N번 구역까지의 인구 수 입력받기 */
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 인구 수 입력 시, 임시값
        int tmp;

        // 구역 수만큼 반복문 반복
        for (int n = 0; n < N; n++) {
            tmp = Integer.parseInt(st.nextToken()); // 인구 수 입력
            numOfPeople[n] = tmp;   // n번 구역에 인구 수 입력
            totalOfPeople += tmp;   // 전체 인구 수에 더하기
        }

        /* 특정 구역에서 인접한 구역을 저장하는 ArrayList 생성 및 초기화 */
        vertex = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            vertex.add(new ArrayList<>());
        }

        /* 특정 구역에서 인접한 구역 입력받기 */

        int M; // 인접한 구역의 개수를 저장하는 변수

        // 구역 수만큼 반복문 반복
        for (int n = 0; n < N; n++) {

            st = new StringTokenizer(br.readLine());    // 인접한 구역 입력
            M = Integer.parseInt(st.nextToken());      // 인접한 구역의 개수

            // 인접한 구역 수만큼 반복문 반복
            for (int m = 0; m < M; m++) {
                // 인접한 각각의 구역 저장
                vertex.get(n).add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        // 구역이 오직 2개밖에 존재하지 않을 경우
        if (N == 2) {
            System.out.println(Math.abs(numOfPeople[0] - numOfPeople[1]));
            return;
        }

        /*
         * nC1 + nC2 + ... + nCn-1만큼 반복
         */
        for (int n = 1; n < N; n++) {
            doCombination(0, N, n, 0, new boolean[N]);
        }

        // 정답 출력
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    // 공정한 선거구를 나눈 후, 인구 차이의 최솟값을 저장하는 변수
    static int min = Integer.MAX_VALUE;

    /**
     * 1. nCr을 구해서, true인 선거구와 false인 선거구로 나누기
     * 2. false인 선거구가 연결되어 있는지 확인
     * 3. true인 선거구가 연결되어 있는지 확인
     * 4. 인구 차이를 구해 최솟값 갱신
     */
    private static void doCombination(int depth, int N, int R, int start, boolean[] subset) {

        if (depth == R) {

            /* true인 선거구의 총 인구 수 구하기 */
            int truePeople = 0;
            for (int n = 0; n < N; n++) {

                if (subset[n]) {
                    truePeople += numOfPeople[n];
                }
            }

            // false 선거구의 visited 배열
            boolean[] falseSubset = subset.clone();

            /* false 선거구가 연결되어 있는지 BFS로 확인*/

            // Queue 생성
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            // 첫 번째 false 선거구를 찾고, 방문 체크한 후, queue에 넣기
            for (int n = 0; n < N; n++) {

                if (falseSubset[n]) {
                    continue;
                }

                queue.offer(n);
                falseSubset[n] = true;
                break;
            }

            // BFS 시작
            int tmp;
            while (!queue.isEmpty()) {

                tmp = queue.poll();

                for (int n : vertex.get(tmp)) {

                    if (falseSubset[n])
                        continue;

                    falseSubset[n] = true;
                    queue.offer(n);
                }
            }
            // BFS 끝

            /* false 선거구가 서로 연결되어 있는지 확인 */
            boolean flag = true;
            for (int n = 0; n < N; n++) {

                if (!falseSubset[n]) {
                    flag = false;
                    break;
                }
            }

            // false 선거구가 서로 연결되어 있지 않을 경우, 함수 종료
            if (!flag) {
                return;
            }

            // true 선거구의 visited 배열
            boolean[] trueSubset = subset.clone();

            /* true 선거구가 연결되어 있는지 BFS로 확인*/

            // Queue 생성
            queue = new ArrayDeque<>();

            // 첫 번째 true 선거구를 찾고, 방문 체크한 후, queue에 넣기
            for (int n = 0; n < N; n++) {

                if (!trueSubset[n]) {
                    continue;
                }

                queue.offer(n);
                trueSubset[n] = false;
                break;
            }

            // BFS 시작
            while (!queue.isEmpty()) {

                tmp = queue.poll();

                for (int n : vertex.get(tmp)) {

                    if (!trueSubset[n])
                        continue;

                    trueSubset[n] = false;
                    queue.offer(n);
                }
            }
            // BFS 끝

            /* true 선거구가 서로 연결되어 있는지 확인 */
            flag = true;
            for (int n = 0; n < N; n++) {

                if (trueSubset[n]) {
                    flag = false;
                    break;
                }
            }

            // true 선거구가 서로 연결되어 있지 않을 경우, 함수 종료
            if (!flag) {
                return;
            }

            // 두 개의 선거구가 서로 연결되어 있으므로,
            // 두 선거구의 인구 차이를 구해 최솟값 업데이트

            // true 선거구의 인구 수 - false 선거구의 인구 수(전체 인구 수 - true 선거 구)
            min = Math.min(min, Math.abs(truePeople - (totalOfPeople - truePeople)));

            // 함수 종료
            return;
        }

        // Combination 진행
        for (int i = start; i < N; i++) {

            if (subset[i]) {
                continue;
            }

            subset[i] = true;
            doCombination(depth + 1, N, R, i + 1, subset);
            subset[i] = false;
        }
    }
}
