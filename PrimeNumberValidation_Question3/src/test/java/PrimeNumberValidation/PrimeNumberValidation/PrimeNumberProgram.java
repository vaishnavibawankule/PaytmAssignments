package PrimeNumberValidation.PrimeNumberValidation;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeNumberProgram {

	@Test
	public void first100PrimeNumbers() {
		
		 boolean flag=false; 
		int[] arr=new int[100];
		
		  int number=2;  
		     int primeNumberCount=0;
		  while(primeNumberCount!=100) {
		 
		   for(int i=2;i<=number/2;i++){    
		    if(number%i==0){    
		     
		     flag=true;    
		       break;  
		    }    
		   }    
		   if(flag==false)  { 
			   arr[primeNumberCount]=number;
			   primeNumberCount++;
			  
		   }
		   number++;
		flag=false;
		  }
		  
		  for(int i=0;i<arr.length;i++) {
			  System.out.print(arr[i]+" ");
		  }
		  
		  //Array should not contain 1 and 0 as both are not prime number
		  for(int i=0;i<arr.length;i++) {
			  Assert.assertNotEquals(arr[i], 0);
			  Assert.assertNotEquals(arr[i], 1);
		  }
		  
		  //Divide number by 1 and itself then remainder should be zero and for other number it should not be zero

		  for(int i=0;i<arr.length;i++) {
			for(int j=2;j<arr[i]/2;j++) {
			Assert.assertNotEquals(arr[i]%j, 0);	
			
			}
			Assert.assertEquals(arr[i]%1, 0);
			Assert.assertEquals(arr[i]%arr[i], 0); 
		  }
		  //Array size should not be more or less than 100 so that we can verify only first 100 prime numbers are in output
		  int size=arr.length;
		  Assert.assertEquals(size, 100);
		  //Verify array should not contain negative numbers
		  for(int i=0;i<arr.length;i++) {
			  Assert.assertTrue(arr[i]>0);
		  }
		  //Verify Prime numbers are in Sequence
		  for(int i=0;i<arr.length-1;i++) {
			  
			  Assert.assertTrue(arr[i+1]>arr[i]);
		  }
		  //Verify Prime numbers are starting from 2 
		  Assert.assertTrue(arr[0]==2);
		  
		  
	}
	
	
}
