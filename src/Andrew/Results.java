package Andrew;

public class Results
{
    private int studentId;
    private int assessmentId;
    private int studentMark;

    public Results(int studentId, int assessmentId, int mark)
    {
        this.studentId = studentId;
        this.assessmentId = assessmentId;
        studentMark = mark;
    }

    public int getAssessmentId()
    {
        return assessmentId;
    }

    public int getStudentMark()
    {
        return studentMark;
    }

    public int getStudentId()
    {
        return studentId;
    }
}
