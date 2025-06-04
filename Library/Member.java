//import java.lang.String;
public class Member {
    String name, email, role;
    int id;

    public Member(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
    public static void deletemember(int id,String name, String email, String role){
        if(true){
            System.out.println("give id__" +" " +id+" " + "__has been deleted");

        }

  
    }
      public String toString() {
        return String.format("| id: %d  |name: %s   | email id :%s    | role: %s ", id,name,email,role);
    }

}
