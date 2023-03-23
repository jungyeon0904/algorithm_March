import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    
    public static void main(String[] args) throws IOException {

        // 입출력용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int[][] map;    // 배추밭 배열
        int cnt;        // 필요한 최소의 배추흰지렁이 개수

        // 사방탐색
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        //  테스트 케이스의 개수 입력
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            // 필요한 최소의 배추흰지렁이 개수 초기화
            cnt = 0;

            /*
            배추밭의 가로 및 세로 길이 입력 받기
            배추가 심어져 있는 위치의 개수 입력 받기
            */
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // 배추밭 배열 선언
            map = new int[N][M];

            /* 
            배추가 심어져 있는 위치 입력 받기
            배추가 심어진 곳은 1로 표시
            */
            for (int k = 0; k < K; k++) {

                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                map[Y][X] = 1;
            }

            /*
            Logic
            - 보호받을 수 있는 배추를 0으로 만든다.
            - 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면,
              사방 탐색으로 보호받을 수 있는 인접한 배추를 구해서
              추가로 0으로 만든다.
            - 사방 탐색은 BFS로 구한다.
             */
            
            // 배추밭을 전체 탐색하여,
            // 보호받지 않은 배추를 구해 BFS 작동
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {

                    // 이미 보호받거나 배추가 없을 경우
                    if (map[n][m] == 0) {
                        continue;
                    }

                    // 보호받지 않는 배추가 있을 경우
                    
                    // 배추흰지렁이 개수 + 1
                    cnt += 1;

                    // 보호받기에 0으로 방문 체크
                    map[n][m] = 0;
                    
                    // BFS를 위한 Queue 생성 및 현재 배추 위치 삽입
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{n, m});

                    // BFS를 위한 변수 선언
                    int[] tmp;
                    int cr, cc;
                    int nr, nc;

                    while (!queue.isEmpty()) {

                        tmp = queue.poll();
                        cr = tmp[0];
                        cc = tmp[1];

                        // 사방탐색
                        for (int d = 0; d < 4; d++) {

                            nr = cr + dr[d];
                            if (nr < 0 || nr >= N) {
                                continue;
                            }

                            nc = cc + dc[d];
                            if (nc < 0 || nc >= M) {
                                continue;
                            }

                            if (map[nr][nc] == 0) {
                                continue;
                            }

                            map[nr][nc] = 0;
                            queue.offer(new int[]{nr, nc});
                        }
                    }
                }
            }

            // 출력 저장
            sb.append(cnt).append("\n");
        }

        // 출력
        System.out.println(sb);
    }
}
