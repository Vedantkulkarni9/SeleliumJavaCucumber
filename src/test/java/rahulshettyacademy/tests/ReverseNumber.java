package rahulshettyacademy.tests;

public class ReverseNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    int num= 121;
    int originnum=121;
    
    int rev=0;
     while(num!=0) {
    int	 digit=num%10;
    	 rev=rev*10+digit;
    	 num=num/10;
         

     }
     System.out.println(rev);
     if (originnum==rev){
    	 System.out.println("the number is plaiindrome");
    	 
    		 
    	 }else {
    		 System.out.println("the number is not plaiindrome"); 
     }
	}

}
