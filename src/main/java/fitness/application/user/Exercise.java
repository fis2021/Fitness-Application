package fitness.application.user;
import org.dizitart.no2.objects.Id;

public class Exercise {
    private String trainerName;
    private String customerUsername;
    private String muscleGroup;
    @Id
    private String exerciseName;
    private String sets;
    private String series;
    private int year;
    private int month;
    private int day;
    private String dueDate;
    public Exercise(){}

    public Exercise(String trainerName,String customerUsername,String muscleGroup,String exerciseName,String sets,String series, int year,int month,int day){
        this.trainerName=trainerName;
        this.customerUsername=customerUsername;
        this.muscleGroup=muscleGroup;
        this.exerciseName=exerciseName;
        this.sets=sets;
        this.series=series;
        this.year=year;
        this.month=month;
        this.day=day;
        this.dueDate=day+"/"+month+"/"+year;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDueDate() {return dueDate;}

    public void setDueDate(int day, int month, int year) {this.dueDate=day+"/"+month+"/"+year;}
}
