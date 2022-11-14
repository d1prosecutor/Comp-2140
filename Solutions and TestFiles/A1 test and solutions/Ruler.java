// This program computes and displays the heights of the ticks on a ruler, as described in Asignment 1 webpage.
import java.util.*;

public class Ruler
{
  
  // (Recursively) sets the heights of the ticks on a ruler given left and right endpoints of the (sub)ruler.
  public static void rule(int left, int right, int height, int theRuler[])
  {		
    int mid = 0;				// holds midpoint of sub-ruler
    
    if (height == 1)
    {						
      mid = (right+left)/2;
      theRuler[mid] = height;
    }
    else
    {
      // set middle point to have given height
      mid = (right+left)/2;	
      theRuler[mid] = height;

      // recursively call rule() to handle the two halves.
      rule( left, mid, height-1, theRuler);
      rule( mid, right, height-1, theRuler);
    }
    return;
  }
  
  
  // compute log base 2 of val (notice no error checking, exception handling might be useful here).
  public static double log2(double val)
  {
    return (Math.log(val)/Math.log(2));
  }
  
  
  public static void main(String args[])
  {
    int theRuler[];  // used to hold heights
    int length = 0;  // length of ruler, using 0 as an indicator to keep trying
    Scanner console = new Scanner(System.in);
    
    // read in length of ruler
    do
    {
      System.out.print("Enter a power of 2, which is at least 2: ");
      // yes, there should be error handling here!
      length = console.nextInt();
      
      // need to check that it is a power of 2 and length >= 2
      if (( Math.ceil(log2(length)) != (int)(log2(length)) ) || (length < 2) )
      {
        System.out.println("Invalid length, try again.");
        length = 0;
      }
    }
    while (length == 0);

    console.close();
    
    theRuler = new int[length+1];
    
    System.out.println("You have entered a length of " + length);
    
    //initialize ruler heights to all 0. The program will proceed to change
    //all height ticks, except the end ticks.
    for (int i = 0; i <=length; i++)
    {
      theRuler[i] = 0;
    }
    
    // call recursive routine to compute height of ticks
    rule(0,length,(int)(log2(length)),theRuler);
    
    // print ruler
    for (int i = 0; i <=length; i++)
    {
      System.out.print(theRuler[i]);
      
    }
    System.out.println("");
    
    for (int i = 0; i <=length; i++)
    {
      System.out.print(i % 10);
      
    }
    System.out.println("");
    
    System.out.println("End of Processing");
  } 
} 