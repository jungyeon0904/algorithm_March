package algo_study_March.BOJ.P1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1012 {
    static int N, M;
    static boolean[][] map, v;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            map = new boolean[N][M];
            v = new boolean[N][M];
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == false) continue;
                    if (v[i][j] == true) continue;
                    dfs(i, j);
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    private static void dfs(int r, int c) {
        v[r][c] = true;
        int nr, nc;
        for (int d = 0; d < 4; d++) {
            nr = r + dy[d];
            nc = c + dx[d];
            if (nr < 0 || nr >= N | nc < 0 || nc >= M) continue;
            if (v[nr][nc] == true) continue;
            if (map[nr][nc] == false) continue;
            dfs(nr, nc);
        }
    }
}
