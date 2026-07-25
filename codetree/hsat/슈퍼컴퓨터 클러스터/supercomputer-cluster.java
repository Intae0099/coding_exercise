import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long B, max, min;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        arr = new long[N];

        st = new StringTokenizer(br.readLine());

        max = 0;
        min = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());

            arr[i] = num;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        long left = min;
        long right = min + (long) Math.sqrt(B);
        long result = min;

        while (left <= right) {
            long mid = (left + right) / 2;
            long temp = 0;

            for (int i = 0; i < N; i++) {

                if (arr[i] >= mid) {
                    continue;
                }

                long diff = mid - arr[i];
                temp += diff * diff;
                if (temp > B) {
                    break;
                }
            }

            if (temp <= B) {
                result = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}