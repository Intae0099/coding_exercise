import java.util.*;
import java.io.*;

public class Main {

    static HashMap<Integer, Integer> map;
    static String S;
    static int K, M, S_len, last_bit;
    static boolean[] bit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S_len = S.length();
        last_bit = (int) Math.pow(2, K - 1);
        bit = new boolean[S_len];
        map = new HashMap<>();

        for (int i = 0; i < S_len; i++) {
            if(S.charAt(i) == '1') bit[i] = true;
        }

        if(check()) System.out.println(1);
        else System.out.println(0);


    }

    public static boolean check(){
        int now = 0;
        for (int i = 0; i < K; i++) {
            now = now << 1;
            if(bit[i]) now += 1;
        }

        map.put(now, 1);

        for (int i = K; i < S_len; i++) {
            if(now >= last_bit) now -= last_bit;
            now = now << 1;
            if(bit[i]) now += 1;

            int cnt = map.getOrDefault(now, 0) + 1;
            if(cnt >= M) return true;
            map.put(now, cnt);

        }

        return false;
    }
}