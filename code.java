import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void MergeSort(int[] prices,int start,int end){

        if(start == end)
            return;
        
        MergeSort(prices,start,(start+end)/2);
        MergeSort(prices,(start+end)/2+1,end);

        Merge(prices,start,(start+end)/2,end);

    }

    static void Merge(int []prices,int start,int middle,int end){
        int array[] = new int[end-start+1];

        int j = start;
        int k = middle+1;
        for(int i=0;i<end-start+1;i++){
            if(j > middle)
                array[i] = prices[k++];
            else if(k > end)
                array[i] = prices[j++];
            else if(prices[j] > prices[k])
                array[i] = prices[k++];
            else if(prices[j] < prices[k])
                array[i] = prices[j++];
        }

        for(int x = start,y=0;x<=end;x++,y++){
            prices[x] = array[y];
        }
    }

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {

        int length = prices.length;
        MergeSort(prices,0,length-1);

        for(int i=0;i<length;i++)
        {
            System.out.println(prices[i]);
        }
        int count = 0;

        for(int i=0;i<length;i++){
            if(k - prices[i] >= 0){
                count++;
                k-=prices[i];
            }
            else
                break;
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] prices = new int[n];

        String[] pricesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pricesItem = Integer.parseInt(pricesItems[i]);
            prices[i] = pricesItem;
        }

        int result = maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
