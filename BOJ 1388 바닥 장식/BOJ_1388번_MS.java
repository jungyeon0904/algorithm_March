import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        // 입출력용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());   // 바닥의 세로 크기
        int C = Integer.parseInt(st.nextToken());   // 바닥의 가로 크기

        char[][] map = new char[R][C];  // 바닥 장식 배열

        // 바닥 장식 입력 받기
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        int cnt = 0;    // 필요한 나무 판자의 개수

        /*
        Logic
        전체 탐색하며, 
        같은 행에 있는 연속된 '-' 모양의 판자와
        같은 열에 있는 연속된 '|' 모양의 판자를 구해서
        '0'으로 만든다.
         */

        // 전체 탐색
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {

                // 이전에 연속된 특정 모양의 판자인 경우
                if (map[r][c] == '0') {
                    continue;
                } else if (map[r][c] == '-') {

                    // 연속된 '-' 모양의 판자가 같은 행에 있는지 파악
                    for (int nc = c + 1; nc < C; nc++) {

                        if (map[r][nc] == '-') {
                            // 있으면 '0'으로 만들기
                            map[r][nc] = '0';
                        } else {
                            // 없으면 다음으로 이동
                            break;
                        }
                    }
                } else {

                    // 연속된 '|' 모양의 판자가 같은 열에 있는지 파악
                    for (int nr = r + 1; nr < R; nr++) {

                        if (map[nr][c] == '|') {
                            // 있으면 '0'으로 만들기
                            map[nr][c] = '0';
                        } else {
                            // 없으면 다음으로 이동
                            break;
                        }
                    }
                }

                // 현재 나무 판자를 '0'으로 만들기
                map[r][c] = '0';

                // 필요한 나무 판자 개수 + 1
                cnt += 1;
            }
        }

        // 출력
        System.out.println(cnt);
    }
}
