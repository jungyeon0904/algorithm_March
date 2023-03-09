import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6588번_YJ {
    static boolean[] notPrimes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        notPrimes = new boolean[1000000];
        checkPrime();
        while (N != 0) {
            boolean find = false;
            int num = 3;
            while (num <= N / 2) {
                if (!notPrimes[num] && !notPrimes[N - num]) { //두 홀수가 소수이다.
                    find = true;
                    break;
                }
                num += 2;
            }
            if (find) {
                sb.append(N + " = " + num + " + " + (N - num) + "\n");
            } else {
                sb.append("Goldbach's conjecture is wrong.\n");
            }
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb.toString());
    }

    private static void checkPrime() {
        notPrimes[0] = true;
        notPrimes[1] = true;
        for (int i = 2; i < 100000; i++) {
            if (notPrimes[i]) continue;
            for (int j = 2; i * j < 100000; j++) {
                notPrimes[i * j] = true;
            }
        }
    }
}
