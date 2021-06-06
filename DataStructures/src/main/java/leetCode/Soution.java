package leetCode;

//xinrongliao 20210606
//https://leetcode-cn.com/problems/ones-and-zeroes/
public class Soution {
   public static void main(String args[]){
          Soution soution=new Soution();
          String[] args1={"10", "0001", "111001", "1", "0"};
           System.out.println(soution.findMaxForm(args1,5,3));
   }

   public  int findMaxForm(String[] strs,int m ,int n){
       if(strs.length==0){
           return 0;
       }
       int[][] dp=new int[m+1][n+1]; //多维费用问题0-1背包问题。dp[i][j]表示使用i个0和j个1能表示的字符串的最大数量
       for(String s:strs){
           //状态转移方程：dp[i][j] = Math.max(dp[i][j],1+dp[i-numZero][j-numOne])
           int zeros=0,ones=0;
           for(char c:s.toCharArray()){
               if(c=='0'){
                   zeros++;
               }else{
                   ones++;
               }
           }

           for(int i=m;i>=zeros;i--){
               for(int j=n;j>=ones;j--){
                   dp[i][j]=Math.max(dp[i][j],1+dp[i-zeros][j-ones]);
               }
           }
       }

       return dp[m][n];

   }
}
