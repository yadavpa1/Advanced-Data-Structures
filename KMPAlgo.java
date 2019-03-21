
import java.util.Scanner;


public class KMPAlgo {
    public static void main(String[] args) {
//        String pattern = "abab";
//        String text = "baababbabababb";
        Scanner sc=new Scanner(System.in);
        String pattern = sc.next();
        String text = sc.next();
//        int pi[]=(compute_prefix_function(pattern));
//        for(int i=0;i<pi.length;i++)
//            System.out.println(pi[i]);
        KMP_Matcher(text,pattern);
    }
    
    private static int[] compute_prefix_function(String pattern){
        int m=pattern.length();
        int pi[]=new int[m]; 
        pi[0]=0;//first char has pi value 0
        int k=0;
        for(int q=1;q < m;q++){
            while(k>0 && pattern.charAt(k)!=pattern.charAt(q))
                k=pi[k-1];
            if(pattern.charAt(k)==pattern.charAt(q))
                k=k+1;
            pi[q]=k;
        }
        return pi;
    }
    
    private static void KMP_Matcher(String text,String pattern){
        int n=text.length();
        int m=pattern.length();
        int pi[]=(compute_prefix_function(pattern));
        int q=0;
        for(int i=0;i<n;i++){
            while(q>0 && pattern.charAt(q)!=text.charAt(i))
                q = pi[q-1];
            if(pattern.charAt(q)==text.charAt(i))
                q = q+1;
            if(q==m){
                System.out.println("Pattern found at shift "+(i-m+1));
                q = pi[q-1];
            }
        }
    }
}
