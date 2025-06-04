import java.util.List;

public class Admin {
    String name, email, password;

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
      public static void deleteadmin(String name,String email,String password){
        System.out.println("admin with name _"+" "+name+" " +" _has been deleated");
    }
    // public String toString(){
    //     return String.format("|title:    %s  |author:     %s  |isbn:     %d    |quantity:     %d   |total_number_of_book_with_isbn :      %d", title,author, isbn, quantity+1, total_number_of_book_with_isbn+1);
    // }
  public static void addadmin(Admin name){
        System.out.println("admin with name _"+" "+name+" " +" _has been deleated");
    }
    public String toString() {
        return String.format("|name: %s | email id: %s | password: %s", name, email, password);
    }
}
