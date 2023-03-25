package algo_study_March.BOJ.P1388;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                cnt++;
                dfs(i, j);
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int i, int j) {
        if (visited[i][j]) return;

        visited[i][j] = true;
        char now = map[i][j];

        int nextR = i, nextC = j, dr = 0, dc = 0, dr2 = 0, dc2 = 0;
        if (now == '-') {
            dr = 0;
            dc = -1;
            dr2 = 0;
            dc2 = 1;
        } else {
            dr = -1;
            dc = 0;
            dr2 = +01;
            dc2 = 0;
        }
        nextR = i + dr;
        nextC = j + dc;
        while (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M) {
            if (map[nextR][nextC] != now) break;
            visited[nextR][nextC] = true;
            nextR += dr;
            nextC += dc;
        }
        nextR = i + dr2;
        nextC = j + dc2;
        while (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M) {
            if (map[nextR][nextC] != now) break;
            visited[nextR][nextC] = true;
            nextR += dr2;
            nextC += dc2;
        }
    }
}