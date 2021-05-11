package fitness.application.user;

public class Customer extends User{
    private String height;
    private String weight;
    private String age;
    private String gender;
    private String description;

    public Customer(){

    }

    public Customer(String username, String email,String password,String fullName, String role){
        super(username,email,password,fullName,role);
    }

    public void setCustomer(String height, String weight, String age, String gender, String description){
        this.height=height;
        this.weight=weight;
        this.age=age;
        this.gender=gender;
        this.description=description;
    }

    public String getHeight(){return height;}

    public void setHeight(String height){this.height=height;}

    public String getWeight(){return weight;}

    public void setWeight(String weight){this.weight=weight;}

    public String getAge(){return age;}

    public void setAge(String age){this.age=age;}

    public String getGender(){return gender;}

    public void setGender(String gender){this.gender=gender;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description=description;}


}
