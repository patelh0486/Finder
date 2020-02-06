
import java.sql.Timestamp;
import java.util.ArrayList;





/**
 *
 * @author hinal
 */

public class User_Account  {
     public  User_Account()  {}
    
     private String user_id;
    private String first_name; 
    private String last_name;
    private String gender;
    private int age;
    
    //*******For viewing a user's profile
    
    private String city;
    private String interests1;
    private String interests2;
    private String interests3;
    private String password;
    private Timestamp   datetime;
    private int ViewsCount;
    private String Bio;
    private String occupation;
    private String last_login;



    public String getBio() {
        return Bio;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    
    //*******For viewing a user's profile
   
   public String input;
   public String input1;
   public String input2;
    
  public String input4;
 


   public User_Account(String user_id, String first_name, String last_name, String gender, String city, String interests1, String interests2,String interests3,String Bio, String occupation, String last_login) {
         this.user_id=user_id;
          this.first_name=first_name;
           this.last_name=last_name;
            this.gender=gender;
             this.city=city;
              this.interests1=interests1;
               this.interests2=interests2;
               this.interests3=interests3;
                this.Bio = Bio;
        this.occupation = occupation;
        this.last_login = last_login;
    }

    public String getInput4() {
        return input4;
    }

    public void setInput4(String input4) {
        this.input4 = input4;
    }
  public String input3;

    public String getInput3() {
        return input3;
    }

    public void setInput3(String input3) {
        this.input3 = input3;
    }

   
   
   
    

  

   

    /////constructor for curent user
    
    
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInterests1() {
        return interests1;
    }

    public void setInterests1(String interests1) {
        this.interests1 = interests1;
    }

    public String getInterests2() {
        return interests2;
    }

    public void setInterests2(String interests2) {
        this.interests2 = interests2;
    }

    public String getInterests3() {
        return interests3;
    }

    public void setInterests3(String interests3) {
        this.interests3 = interests3;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
    
    
    public int getViewsCount() {
        return ViewsCount;
    }

    public void setViewsCount(int ViewsCount) {
        this.ViewsCount = ViewsCount;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
     public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }
    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }
     
    

}