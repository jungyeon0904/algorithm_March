package algo_study_March.BOJ.P16198;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int max;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> marbles = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            marbles.add(Integer.parseInt(st.nextToken()));
        }
        max = Integer.MIN_VALUE;
        getEnergy(marbles, 0);
        System.out.println(max);
    }

    private static void getEnergy(ArrayList<Integer> marbles, int sum) {
        if (marbles.size() == 2) {
            max = Math.max(max, sum);
            return; // 구슬이 2개 남았으면 종료한다.
        }
        // 구슬 하나를 고른다.
        for (int i = 1; i < marbles.size() - 1; i++) { // 첫번째, 마지막 구슬은 제외
            ArrayList<Integer> delma = new ArrayList<>();
            copyArray(marbles, delma);
            // 새로 얻어질 에너지를 계산한다.
            int newE = marbles.get(i - 1) * marbles.get(i + 1);
            delma.remove(i); // 구슬을 삭제한다.
            getEnergy(delma, sum + newE); // 구슬을 삭제하고 새로 얻어진 에너지로 재귀한다.
        }
    }

    private static void copyArray(ArrayList<Integer> marbles, ArrayList<Integer> delma) {
        for (int i : marbles) {
            delma.add(i);
        }
    }
}