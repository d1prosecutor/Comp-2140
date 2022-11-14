package Labs.Lab3;

//-----------------------------------------
// NAME: Chukwunaza Chukwuocha
// STUDENT NUMBER: 007928676
// COURSE: COMP 2140, SECTION: A01
// INSTRUCTOR: Cuneyt Ackora
// Lab: 3
//
// REMARKS: To evaluate postfix operations by implementing a stack
//-----------------------------------------

import java.util.*;

public class EvalPostfix
{


    /***********************************************************
     * main:
     *  - repeatedly read in a postfix expression and
     *    call method evaluate to evaluate the expression.
     *  - program ends when user enters 'q'
     ***********************************************************/
    public static void main(String args[])
    {
        Scanner console = new Scanner(System.in);
        String expression;
        int result;

        System.out.println("Enter a mathematical expression in postfix, q to quit");
        expression = console.nextLine();

        while (expression.charAt(0) != 'q')
        {
            System.out.println("Processing " + expression);

            result = evaluate(expression);

            System.out.println("Value of expression: " + result);
            System.out.println();

            System.out.println("Enter a mathematical expression in postfix, q to quit");
            expression = console.nextLine();
        } // end while

        System.out.println();
        System.out.println("Program ended normally.");

        System.exit(0);
    }


    /*****************************************************************
     * evaluate
     *
     * - evaluates a postfix expression using the algorithm discussed
     *   in class.
     ******************************************************************/
    public static int evaluate(String expression)
    {
        //put your code here
        Stack newStack = new Stack();
        int result;

        int firstOperand;
        int secondOperand;

        int index = 0;
        while (index < expression.length())
        {
            int currChar = expression.charAt(index);

            if (currChar == '+')
            {
                secondOperand = newStack.pop();
                firstOperand = newStack.pop();
                newStack.push(firstOperand + secondOperand);
            } else if (currChar == '-')
            {
                secondOperand = newStack.pop();
                firstOperand = newStack.pop();
                newStack.push(firstOperand - secondOperand);
            } else if (currChar == '*')
            {
                secondOperand = newStack.pop();
                firstOperand = newStack.pop();
                newStack.push(firstOperand * secondOperand);
            } else if (currChar == '/')
            {
                secondOperand = newStack.pop();
                firstOperand = newStack.pop();
                newStack.push(firstOperand / secondOperand);
            } else if (currChar != ' ')
            {
                newStack.push(Integer.parseInt(expression.substring(index, index + 1)));
            }
            index++;
        }

        result = newStack.pop();
        return result;
    }

}
