package algo_study_February.PG.P49189;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        Solution s = new Solution();
        s.solution(n, vertex);
    }

    static ArrayList<Integer>[] list;
    static boolean[] v;
    static int max, maxCnt;

    static class Solution {
        public int solution(int n, int[][] edge) {
            int answer = 0;

            list = new ArrayList[n + 1];
            v = new boolean[n + 1];
            for (int i = 0; i < n + 1; i++) {
                list[i] = new ArrayList<>();
            }

            int from, to;
            for (int i = 0; i < edge.length; i++) {
                from = edge[i][0];
                to = edge[i][1];
                list[from].add(to);
                list[to].add(from);
            }

            max = -1;
            maxCnt = 0;
            bfs();

            answer = maxCnt;
            return answer;
        }

        private void bfs() {
            Queue<int[]> que = new ArrayDeque<>();
            que.offer(new int[]{1, 0}); //노드 번호와 깊이
            v[1] = true;

            while (!que.isEmpty()) {
                int[] now = que.poll();

                if (max == now[1]) maxCnt++;
                else if (max < now[1]) {
                    max = now[1];
                    maxCnt = 1;
                }

                for (int i = 0; i < list[now[0]].size(); i++) {
                    int next = list[now[0]].get(i);
                    if (v[next]) continue;

                    que.offer(new int[]{next, now[1] + 1});
                    v[next] = true;
                }
            }

        }
    }
}
