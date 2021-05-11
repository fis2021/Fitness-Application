package fitness.application.user;

public class Customer extends User{
    private int height;
    private int weight;
    private int age;
    private String gender;
    private String description;

    public Customer(){

    }

    public Customer(String username, String email,String password,String fullName, String role){
        super(username,email,password,fullName,role);
    }

    public int getHeight(){return height;}

    public void setHeight(int height){this.height=height;}

    public int getWeight(){return weight;}

    public void setWeight(int weight){this.weight=weight;}

    public int getAge(){return age;}

    public void setAge(int age){this.age=age;}

    public String getGender(){return gender;}

    public void setGender(String gender){this.gender=gender;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description=description;}


}
