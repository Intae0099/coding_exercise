import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] rank;
    static int[] total_rank;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        total_rank = new int[3001];
        int[] total_people = new int[N];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rank = new int[1001];
            int[] people = new int[N];
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                people[j] = num;
                total_people[j] += num;
            }
            cal_rank(people, rank);
            sb.append("\n");

        }

        cal_rank(total_people, total_rank);

        System.out.println(sb);

    }

    public static void cal_rank(int[] people, int[] rank){
        int[] temp = new int[N];
        for (int i = 0; i < N; i++) {
            temp[i] = people[i];
        }

        Arrays.sort(temp);
        int cnt = 1;
        for (int i = N-1; i >= 0 ; i--) {
            int now_rank = temp[i];
            if(rank[now_rank] == 0) rank[now_rank] = cnt;
            cnt++;
        }

        print(people, rank);
    }

    public static void print(int[] people, int[] rank){
        for (int i = 0; i < N; i++) {
            sb.append(rank[people[i]]).append(" ");
        }
    }
}