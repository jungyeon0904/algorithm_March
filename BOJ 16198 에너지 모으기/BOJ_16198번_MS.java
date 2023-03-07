import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> energy = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int n = 0; n < N; n++) {
            energy.add(Integer.parseInt(st.nextToken()));
        }

        dfs(energy, 0, N - 1, N - 2);
        System.out.println(max);
    }

    private static int max = 0;

    private static void dfs(ArrayList<Integer> energy, int totalEnergy, int currLength, int depth) {

        if (depth == 0) {
            max = Math.max(max, totalEnergy);
            return;
        }

        for (int index = 1; index < currLength; index++) {

            int currEnergy = energy.get(index);
            int gainEnergy = energy.get(index - 1) * energy.get(index + 1);

            energy.remove(index);
            dfs(energy, totalEnergy + gainEnergy, currLength - 1, depth - 1);
            energy.add(index, currEnergy);
        }
    }
}
