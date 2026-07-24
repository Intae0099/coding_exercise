import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int idx, num;
        Node(int idx, int num){
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(o.num, this.num);
        }
    }
    static int N;
    static int[] sum_arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        
        sum_arr = new int[N];
        for(int i = 0; i < 3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Node> arr = new ArrayList<>();
            for(int j = 0; j < N; j++){
                int num = Integer.parseInt(st.nextToken());
                sum_arr[j] += num;
                arr.add(new Node(j, num));
            }
            rank(arr);
            sb.append("\n");
        }

        ArrayList<Node> result = new ArrayList<>();
        for(int i = 0; i < N; i++) result.add(new Node(i, sum_arr[i]));
        
        rank(result);
        System.out.println(sb);
    }

    public static void rank(ArrayList<Node> arr){
        Collections.sort(arr);
        int[] arr_rank = new int[N];
        Node node = arr.get(0);
        int now = node.idx;
        arr_rank[now] = 1;
        int prev = node.num;
        int prev_idx = now;
        for(int i = 1; i < N; i++){
            node = arr.get(i);
            int now_num = node.num;
            int now_idx = node.idx;
            if(prev == now_num){
                arr_rank[now_idx] = arr_rank[prev_idx];
            }
            else{
                arr_rank[now_idx] = i + 1;
            }
            prev = now_num;
            prev_idx = now_idx;

        }

        for(int i : arr_rank){
            sb.append(i).append(" ");
        }
    }
}