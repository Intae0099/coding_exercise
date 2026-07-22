import java.util.*;
import java.io.*;

public class Main {

    static int N, Q, start, end;
    static int[] fuel_dist;
    static StringBuilder sb = new StringBuilder();
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        fuel_dist = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fuel_dist[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(fuel_dist);
        for (int i = 1; i < N - 1; i++) {
            hashMap.put(fuel_dist[i], N - i - 1);
        }



        for (int i = 0; i < Q; i++) {
            int num = Integer.parseInt(br.readLine());
            if(!hashMap.containsKey(num)) {
                sb.append(0).append("\n");
                continue;
            }
            int high = hashMap.get(num);
            int low = N - high - 1;
            sb.append(high * low).append("\n");
        }

        System.out.println(sb);

    }


}