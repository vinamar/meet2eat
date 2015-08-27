package netgloo.commons.util;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println("sum:" +maxSum(a));
		System.out.println("sum:" +maxSubArraySum(a));

	}
	
	public static int maxSum(int [] a){
		 
		int sum=a[0]+a[1];
		int tempSum=0;

		for(int i=2; i<a.length;i++)
		{
		    for( int j=i;j>=0;j--)
		    {
		        tempSum=tempSum+a[j];
		        if(tempSum>sum)
		            sum=tempSum;  
		    }
		    tempSum=0;
		}

		return sum;
		}
	
	
	static int maxSubArraySum(int a[])
	{
	   int max_so_far = 0, max_ending_here = 0;
	   int i;
	   for(i = 0; i < a.length; i++)
	   {
	     max_ending_here = max_ending_here + a[i];
	     if(max_ending_here < 0)
	        max_ending_here = 0;
	     if(max_so_far < max_ending_here)
	        max_so_far = max_ending_here;
	    }
	    return max_so_far;
	} 


}
