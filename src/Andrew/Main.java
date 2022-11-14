package Andrew;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main
{
    static int[] numberSubmitted;
    static int[] sumMarksPerAssessment;
    static int numAssessments;
    static int numStudents;
    final static int TOTAL_ASSESSMENT_WEIGHT = 100;

    public static void main(String[] args)
    {
        Scanner assessmentReader;
        Scanner resultReader;
        Scanner studentReader;

        /************************
         * Try to open the files
         ************************/
        try
        {
            assessmentReader = openFile("assessments.txt");
            resultReader = openFile("results.txt");
            studentReader = openFile("students.txt");

            /*********************************************************
             Read and process the files into their different ArrayLists
             ********************************************************/
            ArrayList<Students> studentList = processStudents(studentReader);
            System.out.println();

            ArrayList<Assessments> assessmentList = processAssessments(assessmentReader);
            System.out.println();

            ArrayList<Results> resultList = processResults(resultReader);
            System.out.println();

            /*********************************************************
             Print the Summary (Number of different assessments and students)
             ********************************************************/
            System.out.println("Summary:");
            numStudents = studentList.size();
            numAssessments = assessmentList.size();

            System.out.println("There are " + numAssessments + " assessments");
            System.out.println("There are " + numStudents + " students");

            /**************************************************
             * calculate sum of marks for each assessment
             * Check to see if the assessment Weights equal 100
             **************************************************/
            int weightSum = 0;
            for (int i = 0; i < numAssessments; i++)
            {
                weightSum += assessmentList.get(i).getAssessmentWeight();
            }

            if (weightSum != TOTAL_ASSESSMENT_WEIGHT)
            {
                System.out.println("Warning: The total weight of the assessment is " + weightSum +
                        " instead of " + TOTAL_ASSESSMENT_WEIGHT);
            } else
            {
                System.out.println("The total weight of the assessment is " + TOTAL_ASSESSMENT_WEIGHT);
            }
            System.out.println();


            /**************************************************
             print out a summary of each assessment
             **************************************************/
            System.out.println("Assessments: ");
            printAssessmentSummary(assessmentList);

            System.out.println();

            /**************************************************
             print out a summary of each student
             **************************************************/
            System.out.println("Students: ");
            printStudentSummary(studentList, resultList);

        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }// End Main

    public static Scanner openFile(String fileName) throws FileNotFoundException
    {
        return new Scanner(new File(fileName));
    }// End OpenFile

    /************************
     * processAssessmentFile()
     ***********************/
    public static ArrayList<Assessments> processAssessments(Scanner assessmentFile)
    {
        int numValues = 4;// required values to be read from the assessment file;
        int lineNumber = 1;//keeping track of the line being read

        ArrayList<Assessments> temp = new ArrayList<Assessments>();
        while (assessmentFile.hasNextLine())
        {
            String[] newLine = assessmentFile.nextLine().trim().split(",");

            if (newLine.length != numValues)
            {
                System.out.println("Invalid number of Values in Assessments File at line: " + lineNumber);
                continue;// or add a boolean flag to the .add statement below to skip it;
            } else
            {
                try
                {
                    Integer.parseInt(newLine[0]);
                    Integer.parseInt(newLine[3]);
                } catch (NumberFormatException e)
                {
                    System.out.println("Invalid Input in Assessments File at line " + lineNumber);
                    continue;// or add a boolean flag to the .add statement below to skip it;
                } finally
                {
                    lineNumber++;
                }
            }

            temp.add(new Assessments(Integer.parseInt(newLine[0]), newLine[1],
                    newLine[2], Integer.parseInt(newLine[3])));

        }

        return temp;
    }// End processAssessments

    /*********************
     * processStudentFile()
     *********************/
    public static ArrayList<Students> processStudents(Scanner studentFile)
    {
        int numValues = 3;// required values to be read from the student file;
        int lineNumber = 1;//keeping track of the line being read

        ArrayList<Students> temp = new ArrayList<Students>();

        while (studentFile.hasNextLine())
        {
            String[] newLine = studentFile.nextLine().trim().split(",");

            if (newLine.length != numValues)
            {
                System.out.println("Invalid number of Values in Students File at line: " + lineNumber);
                continue;// or add a boolean flag to the .add statement below to skip it;
            } else
            {
                try
                {
                    Integer.parseInt(newLine[0]);
                } catch (NumberFormatException e)
                {
                    System.out.println("Invalid Input in Students File at line " + lineNumber);
                    continue;// or add a boolean flag to the .add statement below to skip it;
                } finally
                {
                    lineNumber++;
                }
            }

            temp.add(new Students(Integer.parseInt(newLine[0]), newLine[1],
                    newLine[2]));

        }

        return temp;
    }// End processStudents


    /*********************
     * processResultFile()
     *********************/
    public static ArrayList<Results> processResults(Scanner studentFile)
    {
        int numValues = 3;// required values to be read from the student file;
        int lineNumber = 1;//keeping track of the line being read

        ArrayList<Results> temp = new ArrayList<Results>();

        while (studentFile.hasNextLine())
        {
            String[] newLine = studentFile.nextLine().trim().split(",");

            if (newLine.length != numValues)
            {
                System.out.println("Invalid number of Values in results File at line " + lineNumber);
                continue;// or add a boolean flag to the .add statement below to skip it;
            } else
            {
                try
                {
                    Integer.parseInt(newLine[0]);
                    Integer.parseInt(newLine[1]);
                    Integer.parseInt(newLine[2]);
                } catch (NumberFormatException e)
                {
                    System.out.println("Invalid Input in Results File at line " + lineNumber);
                    continue;// or add a boolean flag to the .add statement below to skip it;
                } finally
                {
                    lineNumber++;
                }
            }

            temp.add(new Results(Integer.parseInt(newLine[0]), Integer.parseInt(newLine[1]),
                    Integer.parseInt(newLine[2])));

        }

        /**************************************************************
         * Process the number of students who submitted each assignment
         * and the sum of the marks of all the students for each assessment
         **************************************************************/
        numberSubmitted = new int[temp.size()];
        sumMarksPerAssessment = new int[temp.size()];

        for (int i = 0; i < temp.size(); i++)
        {
            numberSubmitted[temp.get(i).getAssessmentId()]++;
            sumMarksPerAssessment[temp.get(i).getAssessmentId()] += temp.get(i).getStudentMark();
        }

        return temp;
    }// End processResults

    /**************************************************
     print out a summary of each student
     **************************************************/
    public static void printStudentSummary(ArrayList<Students> studentList, ArrayList<Results> resultList)
    {
        int resultCounter = 0;
        for (int i = 0; i < numStudents; i++)
        {
            int currStudentId = studentList.get(i).getStudentId();
            System.out.print(currStudentId + ": " + studentList.get(i).getLastName() + ", " +
                    studentList.get(i).getFirstName() + " earned ");

            int numCurrStudentValues = 0;
            int currStudentResult = 0;
            while (resultCounter < resultList.size() &&
                    currStudentId == resultList.get(resultCounter).getStudentId())
            {
                currStudentResult += resultList.get(resultCounter).getStudentMark();

                numCurrStudentValues++;
                resultCounter++;
            }

            System.out.print(currStudentResult + " marks and a grade of ");
            if (numCurrStudentValues == numAssessments)
            {
                System.out.println("Something");
            } else
            {
                System.out.println("Incomplete");
            }
        }
    }// End printStudentSummary

    public static void printAssessmentSummary(ArrayList<Assessments> assessmentList)
    {
        for (int i = 0; i < numAssessments; i++)
        {
            Assessments temp = assessmentList.get(i);

            System.out.print(temp.getAssessmentId() + ":\t" +
                    temp.getAssessmentType() + " " + temp.getAssessmentDescription() + " (out of " +
                    temp.getAssessmentWeight() + ")" + "\n " +
                    "  \tnumber submitted: " + numberSubmitted[i + 1] + "\n" +
                    "  \taverage mark: ");

            System.out.printf("%.2f\n", (sumMarksPerAssessment[i + 1] / (float) numberSubmitted[i + 1]));

        }
    }

}

