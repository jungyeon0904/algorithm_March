package com.ssafy.BOJ.P17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, min;
    static boolean[][] adj;
    static boolean[] isSelect, v;
    static int[] popul;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        popul = new int[N + 1]; //각 도시별 인구수, 1부터
        isSelect = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            popul[i] = Integer.parseInt(st.nextToken());
        }
        adj = new boolean[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int cityCnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cityCnt; j++) {
                int near = Integer.parseInt(st.nextToken());
                adj[i][near] = true;
            }
        } //입력

        min = Integer.MAX_VALUE;
        //부분 집합으로 도시 선택
        makeSet(1);//1부터 시작 -> cur==N까지 이므로 도시 전부 선택은 제외됨
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void makeSet(int cur) {
        if (cur == N + 1) {
            //선택, 미선택 도시 배열
            List<Integer> cityA = new ArrayList<>();
            List<Integer> cityB = new ArrayList<>();
            for (int i = 1; i < isSelect.length; i++) {
                if (isSelect[i]) cityA.add(i);
                else cityB.add(i);
            }

            if (cityA.size() == 0 || cityA.size() == N) return; //도시가 두개로 나뉘지 않은 경우

            // 선택도시, 미선택 도시끼리 연결됐는지 확인
            if (isConnected(cityA) && isConnected(cityB)) { // 두 도시로 나눠져서 연결됐는가?
                // 맞다 -> 두 도시의 인구 차이 확인, 최소값으로 저장
                int Asum = 0, Bsum = 0;
                for (int a : cityA) {
                    Asum += popul[a];
                }
                for (int b : cityB) {
                    Bsum += popul[b];
                }
                min = Math.min(min, Math.abs(Asum - Bsum));
            }
            // 아니다 -> return
            return;
        }

        isSelect[cur] = true;
        makeSet(cur + 1);
        isSelect[cur] = false;
        makeSet(cur + 1);
    }

    private static boolean isConnected(List<Integer> city) { //같은 집합 city가 연결되었는지 확인
        //start에 해당하는 노드부터 연결노드를 따라가며 확인
        v = new boolean[N + 1];
        dfs(city, 0);
        for (int c : city) { //city 리스트의 모든 노드를 순회 완료했으면 연결된 것이다.
            if (v[c] == false) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(List<Integer> city, int cur) { //같은 city 집합이면서 연결된 도시 순회하며 방문체크
        if (v[city.get(cur)]) return;

        v[city.get(cur)] = true;
        for (int i = 0; i < city.size(); i++) {
            if (adj[city.get(cur)][city.get(i)]) {
                dfs(city, i);
            }
        }
    }
}