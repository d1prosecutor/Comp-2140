package Andrew;

public class Assessments
{
    private String assessmentType;
    private String assessmentDescription;
    private int assessmentId;
    private int assessmentWeight;

    public Assessments(int id, String type, String description, int weight)
    {
        assessmentId = id;
        assessmentType = type;
        assessmentDescription = description;
        assessmentWeight = weight;
    }

    public int getAssessmentId()
    {
        return assessmentId;
    }

    public String getAssessmentType()
    {
        return assessmentType;
    }

    public String getAssessmentDescription()
    {
        return assessmentDescription;
    }

    public int getAssessmentWeight()
    {
        return assessmentWeight;
    }

}
