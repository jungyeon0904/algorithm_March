package algo_study_March.BOJ.P22858;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] result, Di, res;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = new int[N];
        Di = new int[N];
        res = new int[N];
        v = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Di[i] = Integer.parseInt(st.nextToken());
        }
        res = shake(result);
        for (int i = 0; i < N; i++) {
            System.out.print(res[i]+" ");
        }
    }

    private static int[] shake(int[] res) {
        int[] shake = res.clone();
        int[] tmp = new int[N];
        if(K>N) K = K%(N-1);
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                tmp[Di[j] - 1] = shake[j];
            }
            shake = tmp.clone();
        }
        return shake;
    }
}