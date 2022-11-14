package Andrew;

public class Students
{
    private int studentId;
    private String lastName;
    private String firstName;

    public Students(int id, String lastName, String firstName)
    {
        studentId = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }
}
