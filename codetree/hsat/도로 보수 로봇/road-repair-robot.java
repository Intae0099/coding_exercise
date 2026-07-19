import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[] hole;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        hole = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hole[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = hole[N-1];

        while (left< right){
            int mid = (left + right) / 2;

            if(check(mid)) left = mid + 1;
            else right = mid;
        }
        System.out.println(left);
    }

    public static boolean check(int len){
        int prev = hole[0];
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            int next = hole[i];
            if(next - prev + 1 > len){
                cnt++;
                prev = next;
            }
            if(cnt > K) return true;
        }

        if(cnt > K) return true;
        return false;
    }
}