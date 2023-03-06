import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        // 입력용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());    // 배열 크기 입력
        int[][] map = new int[N][N];    // 배열 선언

        // 영역의 최대 높이
        // 이 이상으로 물이 차면, 모든 지역이 잠기게 된다.
        int maxLevel = 0;   
        
        // 각 영역의 높이를 입력받고,
        // 가장 높은 영역 찾기
        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                
                // 각 영역의 높이 입력
                map[i][j] = Integer.parseInt(st.nextToken());
                
                // 가장 높은 영역인지 확인
                maxLevel = Math.max(maxLevel, map[i][j]);
            }
        }

        /* BFS를 위한 변수들 */
        Queue<int[]> queue; // queue
        boolean[][] isVisited;  // 방문 배열
        int max = 0;    // 물에 잠기지 않은 안전한 영역의 최대 개수
        int cnt = 0;    // 물에 잠기지 않은 안전한 영역의 개수

        // 상하좌우
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        /*
         * 장마철 비의 범위가 문제에 기재되어 있지 않다.
         * 0부터 영역의 최대 높이까지 BFS를 반복
         */
        for (int level = 0; level < maxLevel; level++) {

            /* BFS를 위한 변수 선언 */
            queue = new ArrayDeque<>();
            isVisited = new boolean[N][N];
            cnt = 0;

            // 비의 양보다 높이가 크고, 방문하지 않은 구역 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    // 방문 체크
                    if (isVisited[i][j]) {
                        continue;
                    }

                    // 비의 양보다 높이가 클 때
                    if (map[i][j] > level) {

                        // 안전한 영역 + 1
                        cnt += 1;

                        // 큐에 넣고, 방문 확인
                        queue.offer(new int[]{i, j});
                        isVisited[i][j] = true;

                        int[] tmp;  // 임시값
                        int cr, cc; // 현재 row, column
                        int nr, nc; // 다음 row, column

                        while (!queue.isEmpty()) {

                            tmp = queue.poll();
                            cr = tmp[0];
                            cc = tmp[1];

                            // 사방 탐색
                            for (int d = 0; d < 4; d++) {

                                nr = cr + dr[d];
                                if (nr < 0 || nr >= N) {
                                    continue;
                                }

                                nc = cc + dc[d];
                                if (nc < 0 || nc >= N) {
                                    continue;
                                }

                                // 방문 확인
                                if (isVisited[nr][nc]) {
                                    continue;
                                }

                                // 다음 영역이 물에 잠긴 경우
                                if (map[nr][nc] <= level) {
                                    continue;
                                }

                                isVisited[nr][nc] = true;
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }

            // 현재 level의 BFS를 끝내고, 최대 영역 개수 비교
            max = Math.max(max, cnt);
        }

        // 출력
        System.out.println(max);
    }
}
