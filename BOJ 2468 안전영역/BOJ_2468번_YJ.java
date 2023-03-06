package algo_study_March.BOJ.P2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max, min;
    static boolean[][] v;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        min = 100;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
            }
        } //입력

        max = 1;
        while (true) {
            v = new boolean[N][N];
            int cnt = 0;
            flood(min);//min으로 잠군다, 잠긴 곳은 0이 된다.
            if (noSafeZone()) break; //안전 구역이 없으면 종료한다.
            for (int i = 0; i < N; i++) { //안전 구역의 개수를 센다.
                for (int j = 0; j < N; j++) {
                    if (v[i][j]) continue;
                    if (map[i][j] == 0) continue;
                    cnt++;
                    dfs(i, j);//해당 위치에서 연결되는 영역을 모두 방문한다.
                }
            }
            max = Math.max(max, cnt);
            min++;
        }
        System.out.println(max);
    }

    private static void flood(int min) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= min) map[i][j] = 0;
            }
        }
    }

    private static boolean noSafeZone() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }

    private static void dfs(int r, int c) {
        if (v[r][c]) return;
        if (map[r][c] == 0) return;
        v[r][c] = true;

        //상하좌우 중 잠기지 않은(0이 아닌) 영역으로 재귀
        int nr, nc;
        for (int d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (map[nr][nc] == 0) continue;
            if (v[nr][nc]) continue;
            dfs(nr, nc);
        }
    }
}