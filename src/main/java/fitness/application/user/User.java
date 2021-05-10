package fitness.application.user;

import org.dizitart.no2.objects.Id;
public class User {
    @Id
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String role;

    public User(String username, String email,String password,String fullName, String role) {
        this.username = username;
        this.email=email;
        this.password = password;
        this.fullName=fullName;
        this.role = role;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail(){return email;}

    public void setEmail(String email){this.email=email;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfullName(){return fullName;}

    public void setfullName(String email){this.fullName=fullName;}

    public String getRole() {
        return role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (username != null ? !user.equals(user.username) : user.username != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (fullName != null ? !fullName.equals(user.fullName) : user.fullName != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}