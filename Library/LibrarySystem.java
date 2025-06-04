import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
public class LibrarySystem {
    static ArrayList<Member> members = new ArrayList<>();
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<IssueRecord> issues = new ArrayList<>();
    static ArrayList<FineRule> fineRules = new ArrayList<>();
    static ArrayList<Subject> subjects = new ArrayList<>();
    static ArrayList<Admin> admins = new ArrayList<>();
    private static int count;
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try{
            System.out.println("\n1. Add Member\n2. Add Book\n3. Add Book Copies\n4. Signup\n5. Issue Book\n6. Add Fine Rule\n7. Add Subject\n8. Add Admin\n9.remove member\n10. remove book \n11. modify \n12. delete member \n13 final list \n0. Exit");
           // System.out.println("1:remove member\n2:remove book\n3remove book copies with torn pages");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                  sc.nextLine();   
                  try{
                  System.out.print("ID:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name:"); 
                    String name=sc.nextLine();
                    if(name.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }
                
                    System.out.println("email id:");
                    String email=sc.nextLine();
                    if(email.trim().isEmpty()){
                        System.out.println("email cant be empty");
                        break;
                    }
                    String role=" ";
                    if(email.endsWith("vitap.ac.in")){
                    System.out.print("Role (student/teacher): ");
                    role = sc.nextLine();
                    if(role.trim().isEmpty()){
                        System.out.println("role cant be empty");
                        break;
                    }
                    System.out.println("members are --- "+ members);
                    Member Member = new Member(id, name, email, role);
                    members.add(Member);
                System.out.println("A new member has been added:\n "+"id:"+id+"\n name:"+name + "\n email address:"+email  );
                System.out.println("members after addition --- \n"+ members.toString());
                    }
                    else{
                        System.out.println("user is not allowed to enter");
                    }  
                     boolean success = MemberDAO.addMember(id, name, email, role);
                        if (success) {
                            members.add(new Member(id, name, email, role));
                            System.out.println("Member added successfully.");
                        } else {
                            System.out.println("Failed to add member.");
                        }
                }catch(Exception e){
                    System.out.println("invalid input");
                    e.printStackTrace();
                }
                }
            case 2 -> 
            {
                sc.nextLine(); // clear buffer before input starts
                try {
                    System.out.print("Title:\n "); 
                    String title = sc.nextLine();
                    if(title.trim().isEmpty()){
                        System.out.println("title cant be empty");
                    }
                    System.out.print("Author:\n "); 
                    String author = sc.nextLine();
                    if(author.trim().isEmpty()){
                        System.out.println("author name cant be empty");
                    }
        System.out.print("ISBN: ");
        long isbn = sc.nextLong();
        sc.nextLine(); // clear buffer
        System.out.print("Subject: "); 
        String subject = sc.nextLine();
        if(subject.trim().isEmpty()){
                        System.out.println("subject cant be empty");
                    }
        System.out.print("Quantity: "); 
        int qty = sc.nextInt();
        System.out.println("Total number of book with same ISBN:");
        int tWi = sc.nextInt();
        System.out.println("books before addition are"+ books);

        books.add(new Book(title, author, isbn, subject, qty, tWi));
        System.out.println(" A Book has been added with:\n" +
                           "Title: " + title + "\n" +
                           "Author: " + author + "\n" +
                           "ISBN: " + isbn + "\n" +
                           "Subject: " + subject+ "\n" +
                           "Quantity: " + (qty+1)+ "\n" +
                           "Total number of copies with same ISBN: " + (tWi + 1));
                           System.out.println("books after addition are ----\n"+ books.toString());
                           BooksDAO booksDAO = new BooksDAO();
                           Boolean success=BooksDAO.addBook(title, author, isbn, subject, qty, tWi);
                           if(success){
                            books.add(new Book(title,author,isbn,subject,qty,tWi));
                            System.out.println("book added successfully");
                           }
                           else{
                            System.out.println("books cant be added");
                           }

                           
                          
    } catch (Exception e) {
        System.out.println(" Invalid input. Please try again.");
        sc.nextLine(); // flush incorrect input to avoid infinite loop
    }
}
                case 3 -> {
                    try{
                    System.out.print("ISBN to add copies: ");
                     long isbn = sc.nextLong();
                     System.out.println("total number of boooks right now in the library is :");
                     int qty=sc.nextInt();
                     System.out.println("total_number_of_book_with_isbn:");
                     int tWi=sc.nextInt();
                     System.out.println("total number of copis added is:");
                     int count=sc.nextInt();

                     
                    for(Book b :books){
                        if(b.isbn==isbn){
                            b.addCopies(count);
                            System.out.println("copies added");
                        }
                    }
                    System.out.println("total number of books in the library is :"+ (qty+count));
                    System.out.println("total number of elements with given isbn in the library is "+ (tWi+count));
                       }catch(Exception e){
                    System.out.println("invalid input");
                }
                }
                  case 4 -> {
                    sc.nextLine();
                    try{
              
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                        if(name.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }

                    System.out.print("Email: "); 
                    String email = sc.nextLine();
                        if(email.trim().isEmpty()){
                        System.out.println("email cant be empty");
                        break;
                    }
                    System.out.println("role (student/teacher):");
                    String role=sc.nextLine();
                        if(role.trim().isEmpty()){
                        System.out.println("role cant be empty");
                        break;
                    }
                    if(email.endsWith("vitap.ac.in")){
                    System.out.print("Password: ");
                    String password = sc.nextLine();
                    members.add(new Member(members.size() + 1, name, email, "student"));
                    System.out.println("Signup successful.");
                    }
                    else{
                        System.out.println("you cant login as this mail id does not belongs to out university");
                    }
                     }catch(Exception e){
                    System.out.println("invalid input");
                }
                }
                case 5 -> {
                    sc.nextLine();
                    System.out.print("Member ID: ");
                     int id = sc.nextInt(); 
                     sc.nextLine();
                    System.out.print("ISBN: "); 
                    long isbn = sc.nextLong();
                    issues.add(new IssueRecord(id, isbn, java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(15)));
                    System.out.println("Book issued.");
                }
                case 6 -> {
                    System.out.print("Allowed days: ");
                     int days = sc.nextInt();
                    System.out.print("Fine per day: "); 
                    double fine = sc.nextDouble();
                     sc.nextLine();
                    fineRules.add(new FineRule(days, fine));
                    System.out.println("Fine rule added.");
                }
                case 7 -> {
                    sc.nextLine();
                    System.out.print("Subject Name: ");
                     String sname = sc.nextLine();
                         if(sname.trim().isEmpty()){
                        System.out.println("student name cant be empty");
                        break;
                    }
                    System.out.print("Department: "); 
                    String dept = sc.nextLine();
                        if(dept.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }
                    subjects.add(new Subject(sname, dept));
                    System.out.println("Subject added.");
                }
                case 8  -> {
                    sc.nextLine();
                    System.out.print("Name: "); 
                    String name = sc.nextLine();
                        if(name.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }
                    System.out.print("Email: "); 
                    String email = sc.nextLine();
                    if(email.trim().isEmpty()){
                        System.out.println("email cant be empty");
                        break;
                    }
                    System.out.print("Password: "); 
                    String password = sc.nextLine();
                     if(password.trim().isEmpty()){
                        System.out.println("password cant be empty");
                        break;
                    }
                    admins.add(new Admin(name, email, password));
                    System.out.println("Admin added.");
                    //  AdminDAO adminsDao=new AdminDAO();
                    Boolean success=AdminDAO.addAdmin(name, email, password);
                    if(success){
                        Admin.addadmin(new Admin(name ,email,password));
                        System.out.println("admin added successfully");
                    }
                    else{
                        System.out.println("no admin got added");

                    }
                }
                case 9 ->{
                    System.out.println("enter the id of the member:");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.println("name of the guy to remove:");
                    String name=sc.nextLine();
                     if(name.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }
                    System.out.println("enter the mail of him:");
                    String email=sc.nextLine();
                     if(email.trim().isEmpty()){
                        System.out.println("email can't be empty");
                        break;
                    }
                    System.out.println("role of the member (student/teacher):");
                    String role=sc.nextLine();
                     if(role.trim().isEmpty()){
                        System.out.println("role cant be empty");
                        break;
                    }
                    if(email.endsWith("vitap.ac.in")){
                    members.remove(new Member(id, name, email, role));
                    System.out.println(name+" has been removed from the library ");
                    }
                    else{
                        System.out.println("this email id was never a part of this oganisationh");
                    }
                }
                case 10 ->{
                    sc.nextLine();
                    System.out.println("title of the book");
                    String title=sc.nextLine();
                     if(title.trim().isEmpty()){
                        System.out.println("title cant be empty");
                        break;
                    }
                    System.out.println("name of the author:");
                    String author=sc.nextLine();
                     if(author.trim().isEmpty()){
                        System.out.println("name of the author cant be empty");
                        break;
                    }
                    System.out.println("subject name:");
                    String subject=sc.nextLine();
                     if(subject.trim().isEmpty()){
                        System.out.println("subject cant be empty");
                        break;
                    }
                    System.out.println("enter the isbn number");
                    long isbn=sc.nextLong();
                    System.out.println("total number of books available");
                    int total_quantity=sc.nextInt();
                    System.out.println("quantity_with_same_number");
                    int quantity_with_same_number=sc.nextInt();
                    books.remove(new Book(title, author, isbn, subject, total_quantity, quantity_with_same_number));
                    System.out.println(title+"book has been issued by someone so the total number of books in th library is "+(total_quantity-1)+" and quantity of that book  currently available is "+(quantity_with_same_number-1));
                }
                case 11 ->{
                    while(true){
                        System.out.println("\n11.1. modify admin details \n11.2.modify members details \n11.3.modify books details  \n11.4. modify FineRules  \n11.5. modify issue records  ");
                        int choice1=sc.nextInt();
                        switch(choice1){
                            case 1 ->{
                            while(true){
                                System.out.println("\n11.1.1. modify name of admin\n11.1.2. modify email of admin\n11.1.3. modify password \n11.1.4. exit ");
                                int choice2=sc.nextInt();
                                switch (choice2) {
                                    case 1 ->{
                                        try{
                                       
                                        System.out.println("current name of the admin:");
                                        String name=sc.nextLine();
                                        sc.nextLine();
                                         if(name.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }
                                        System.out.println("give the name it should be:");
                                        String name1=sc.nextLine();
                                         if(name1.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }
                                        System.out.println("the modified name of the admin is :"+ name1);
                                        }
                                        catch(Exception e){
                                            System.out.println("invalid input");
                                        }
                                    }
                                    case 2 ->{
                                        sc.nextLine();
                                        System.out.println("current email of the admin:");
                                        String email=sc.nextLine();
                                         if(email.trim().isEmpty()){
                        System.out.println("email cant be empty");
                        break;
                    }
                                        sc.nextLine();
                                        System.out.println("give the email it should be:");
                                        String email1=sc.nextLine();
                                         if(email1.trim().isEmpty()){
                        System.out.println("email1 cant be empty");
                        break;
                    }
                                        System.out.println("the modified email_id of the admin is :"+ email1);
                                    }
                                    case 3 ->{
                                        sc.nextLine();
                                        System.out.println("current password of the admin");
                                        String password=sc.nextLine();
                                         if(password.trim().isEmpty()){
                        System.out.println("password cant be empty");
                        break;
                    }
                                        System.out.println("give the password it should be");
                                        String password1=sc.nextLine();
                                         if(password1.trim().isEmpty()){
                        System.out.println("password1 cant be empty");
                        break;
                    }
                                        System.out.println("the modified password of the admin is :"+ password1);
                                    }
                                    case 4 -> {
                                    System.out.println("Exiting...");
                                    return;
                                    }
                                }
                            }
                         }
                             case 2->{
                                sc.nextLine();
                                while(true){
                                System.out.println("11.2.1. modify name of member \n11.2.2 modify email id of the member \n11.2.3. role of the member   \n11.2.4 modify id of the member  \n11.2.5. exit");
                                int choice3=sc.nextInt();
                                sc.nextInt();
                                 switch(choice3){
                                    case 1->{
                                        sc.nextLine();
                                        System.out.println("enter the member's name which is wrong:");
                                        String name=sc.nextLine();
                                         if(name.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }
                                        System.out.println("enter the correct name: ");
                                        String name1=sc.nextLine();
                                         if(name1.trim().isEmpty()){
                        System.out.println("name1 cant be empty");
                        break;
                    }
                                        System.out.println("the modified name of the member is:"+name1);

                                    }
                                     case 2->{
                                        sc.nextLine();
                                        System.out.println("enter the member's email_id which is wrong:");
                                        String email=sc.nextLine();
                                         if(email.trim().isEmpty()){
                        System.out.println("email cant be empty");
                        break;
                    }
                                        System.out.println("enter the correct email: ");
                                        String email1=sc.nextLine();
                                                   if(email1.trim().isEmpty()){
                        System.out.println("email1 cant be empty");
                        break;
                    }
                    System.out.println("the modified email id  of the member is:"+email1);
                 }
                                      case 3->{
                                        sc.nextLine();
                                        System.out.println("enter the member's role which is wrong:");
                                        String role=sc.nextLine();
                                                   if(role.trim().isEmpty()){
                        System.out.println("role cant be empty");
                        break;
                    }
                    System.out.println("enter the correct role :");
                                        String role1=sc.nextLine();
                                                                      if(role1.trim().isEmpty()){
                        System.out.println("role1 cant be empty");
                        break;
                    }
                    System.out.println("the modified role of the member is:"+role1);
                 }
                 case 4->{
                    sc.nextLine();
                                        System.out.println("enter the member's id which is wrong:");
                                        String email=sc.nextLine();
                                        if(email.trim().isEmpty()){
                                            System.out.println("emai1 cant be empty");
                                        }

                                        System.out.println("enter the correct id :");
                                        String email1=sc.nextLine();
                                          if(email1.trim().isEmpty()){
                                            System.out.println("email1 cant be empty");
                                        }
                                        System.out.println("the modified id of the member is:"+email1);     
                                    }
                                        case 5 -> {
                                        System.out.println("Exiting...");
                                        return;
                                        }
                                }
                                }
                                    }
                               case 3 ->{
                                while(true){
                                    System.out.println("11.3.1. modify title of the book: \n11.3.2. moidify  name of the author: \n11.3.3.  modify isbn:\n11.3.4.  modify name of the subject: \n11.3.5.modify the  quantity of the total books in library:  \n11.3.6. modify total_no_of_books_with_same_isbn \n11.3.7. exit ");
                                    int choice4=sc.nextInt();
                                    switch(choice4){
                                        case 1 ->{
                                            sc.nextLine();
                                            System.out.println("give the title of the book which is wrong:");
                                            String title=sc.nextLine();
                                              if(title.trim().isEmpty()){
                                            System.out.println("title cant be empty");
                                        }
                                            System.out.println("enter the correct title of the book :");
                                            String title1=sc.nextLine();
                                             if(title1.trim().isEmpty()){
                                            System.out.println("title1 cant be empty");
                                        }
                                            System.out.println("corrected title of the book is :" + title1);  
                                        }
                                         case 2 ->{
                                            sc.nextLine();
                                            System.out.println("give the author of the book which is wrong:");
                                            String author=sc.nextLine();
                                               if(author.trim().isEmpty()){
                        System.out.println("author name cant be empty");
                        break;
                    }
                                            System.out.println("enter the correct title of the book :");
                                            String author1=sc.nextLine();
                                              if(author1.trim().isEmpty()){
                        System.out.println("author's corrected name cant be empty");
                        break;
                    }
                                            System.out.println("corrected title of the book is :" + author1);  
                                        }
                                         case 3 ->{
                                           sc.nextLine();

                                            System.out.println("give the isbn of the book which is wrong:");
                                            long isbn=sc.nextLong();
                                            sc.nextLine();
                                            System.out.println("enter the correct isbn of the book :");
                                            long isbn1=sc.nextLong();
                                            System.out.println("corrected title of the book is :" + isbn1);

                                        }
                                        case 4->{
                                            sc.nextLine();
                                        System.out.println("enter the subject name which is wrong:");
                                        String sname=sc.nextLine();
                                          if(sname.trim().isEmpty()){
                        System.out.println("author name cant be empty");
                        break;
                    }
                                        System.out.println("enter the correct name: ");
                                        String sname1=sc.nextLine();
                                         if(sname1.trim().isEmpty()){
                        System.out.println("author name cant be empty");
                        break;
                    }
                                        System.out.println("the modified name of the member is:"+sname1);
                                    }
                                     case 5 ->{
                                     sc.nextLine();
                                        System.out.println("enter quantity of the total books in library");
                                        int quantity=sc.nextInt();
                                        sc.nextLine();
                                        System.out.println(" emter the correct no of books available of same isbn");
                                        int quantity1=sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("modified quantity of books availibility  of same isbn number is :"+ quantity1);
                                    }
                                     case 6 ->{
                                        sc.nextLine();
                                        System.out.println("enter given quantity of the books in library with same isbn");
                                        int sin=sc.nextInt();
                                        sc.nextLine();
                                        System.out.println(" enter the correct no of books available of same isbn");
                                        int sin1=sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("modified quantity of books availibility  of same isbn number is :"+ sin1);
                                    }
                                    case 7 -> {
                                      System.out.println("Exiting...");
                                       return;
                                      }
                                    }
                                }
                                
                               }  
                               case 4->{
                                while(true){
                                    System.out.println("11.4.1 . modify alloweddays  \n11.4.2.  fineperday \n11.4.3 exit");
                                    int choice5=sc.nextInt();
                                    switch(choice5){
                                        case 1->{
                                        sc.nextLine();
                                            System.out.println("enter the number of day books are allowed currently:");
                                            int alloweddays=sc.nextInt();
                                            System.out.println("enter the modified alloweddays:");
                                            int allowedDays1=sc.nextInt();
                                            System.out.println("modified number of days books are allowed is :"+allowedDays1);
                                        }
                                        case 2->{
                                            sc.nextLine();
                                            System.out.println("enter the fineperday currently:");
                                            int finePerDay=sc.nextInt();
                                            System.out.println("enter the modified alloweddays:");
                                            int finePerDay1=sc.nextInt();
                                            System.out.println("modified fine per day is :"+ finePerDay1);
                                        }
                                        case 3 -> {
                                        System.out.println("Exiting...");
                                        return;
                                        }
                                    }
                                }
                               }  
                               case 5->{
                                while(true){
                                System.out.println("11.5.1. modify memberid\n11.5.2 .modify isbn \n11.5.3.modify issuedate \n11.5.4 modify duedate \n11.5.5. exit");
                                int choice6=sc.nextInt();
                                switch(choice6){
                                    case 1 ->{
                                        try{
                                        
                                        System.out.println("enter the current memberid:");
                                        int memberid=sc.nextInt();
                                        System.out.println("enter the correct memberid:");
                                        int memberid1=sc.nextInt();
                                        System.out.println("the modified memberid is:"+memberid1);
                                        }
                                        
                                        catch(Exception e){
                                            System.out.println("invalid input");
                                          }
                                         }
                                

                                    case 2 ->{
                                       sc.nextLine();
                                       try{
                                        System.out.println("enter the current isbn:");
                                        int isbn=sc.nextInt();
                                        System.out.println("enter the correct isbn:");
                                        int isbn1=sc.nextInt();
                                        System.out.println("the modified memberid is:"+isbn1);
                                       }
                                       catch(Exception e){
                                        System.out.println("invalid input");
                                       }
                                        
                                    }
                                    case 3->{
                                        sc.nextLine();
                                        System.out.println("enter the current issuedate:");
                                        int issuedate=sc.nextInt();
                                        System.out.println("enter the correct issuedate:");
                                        int issuedate1=sc.nextInt();
                                        System.out.println("the modified issuedate is:"+issuedate1);
                                        
                                    }
                                    case 4->{
                                        // sc.nextLine();
                                       try {
                                          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                              System.out.println("Enter the current due date (dd-MM-yyyy):");
                                               String dueDateInput = sc.nextLine();
                                              LocalDate dueDate = LocalDate.parse(dueDateInput, formatter);
                                             System.out.println("Enter the correct due date (dd-MM-yyyy):");
                                             String correctedDateInput = sc.nextLine();
                                             LocalDate correctedDueDate = LocalDate.parse(correctedDateInput, formatter);
                                              System.out.println("The modified due date is: " + correctedDueDate);
                                            } catch (DateTimeParseException e) {
                                            System.out.println("Invalid input. Please enter the date in dd-MM-yyyy format.");
        }
                                    }
                                    case 5 -> {
                                        System.out.println("Exiting...");
                                        return;
                                     }
                                }
                               } 
                        }

                    }
                }
                }
                case 12 ->{
                    sc.nextLine();

                    while(true){
                        System.out.println("1. delete member\n2. delete book\n3. delete admin details  \n4 delete subjects \n");
                    int choice4=sc.nextInt();
                    switch(choice4){
                        case 1 ->{
                            sc.nextLine();
                            System.out.println("enter the id ");
                            int id=sc.nextInt();
                            sc.nextLine();
                            System.out.println("enter the name:");
                            String name=sc.nextLine();
                             if(name.trim().isEmpty()){
                        System.out.println("name cant be empty");
                        break;
                    }
                            System.out.println("enter the email:");
                            String email=sc.nextLine();
                             if(email.trim().isEmpty()){
                        System.out.println("mail id cant be empty");
                        break;
                    }
                            System.out.println("enter the role:");
                            String role=sc.nextLine();
                             if(role.trim().isEmpty()){
                        System.out.println("role cant be empty");
                        break;
                    }
                           members.add(new Member(id, name, email, role));
                    System.out.println("A new member has been added:\n "+"id:"+id+"\n name:"+name + "\nemail address:"+email  );
                    Member.deletemember(id, name, email, role);
                        }
                        case 2 ->{
                            sc.nextLine();
                            System.out.println("enter the title of the book:");
                            String title =  sc.nextLine();
                             if(title.trim().isEmpty()){
                        System.out.println("title cant be empty");
                        break;
                    }
                            System.out.print("Author's name:\n "); 
                            String author = sc.nextLine();
                             if(author.trim().isEmpty()){
                        System.out.println("author name cant be empty");
                        break;
                    }
                            System.out.print("ISBN: ");
                            long isbn = sc.nextLong();
                            sc.nextLine();
                            System.out.print("Subject: "); 
                            String subject = sc.nextLine();
                             if(subject.trim().isEmpty()){
                        System.out.println("subject cant be empty");
                        break;
                    }
                            System.out.print("Quantity: "); 
                            int qty = sc.nextInt();
                            System.out.println("Total number of book with same ISBN:");
                            int tWi = sc.nextInt();
                            books.add(new Book(title, author, isbn, subject, qty, tWi));
                            Book.deletebook(title);}
                        case 3 ->{
                    sc.nextLine();
                    System.out.print("Name: "); 
                    String name = sc.nextLine();
                     if(name.trim().isEmpty()){
                        System.out.println(" name cant be empty");
                        break;
                    }
                    System.out.print("Email: "); 
                    String email = sc.nextLine();
                     if(email.trim().isEmpty()){
                        System.out.println("author name cant be empty");
                        break;
                    }
                    System.out.print("Password: "); 
                    String pass = sc.nextLine();
                    if(pass.trim().isEmpty()){
                        System.out.println("password cant be empty");
                    }
                    admins.add(new Admin(name, email, pass));
                    Admin.deleteadmin(name, email, pass);
                     }
                     case 4 ->{
                        sc.nextLine();
                        System.out.print("Subject Name: ");
                        String sname = sc.nextLine();
                        if(sname.trim().isEmpty()){
                            System.out.println("subject name cant be empty");
                        }
                        System.out.print("Department: ");
                        String dept = sc.nextLine();
                        if(dept.trim().isEmpty()){
                            System.out.println("corrected department name cant be left empty");
                        }
                        subjects.add(new Subject(sname, dept));
                        Subject.deletesubject(sname);
                        sc.nextLine();
                }
            }
        }
    }
    case 13 -> {
        sc.nextLine();
        while(true){
        
        System.out.println("1: added member are: \n2. added books are : \n3 added admins are :  ");
        int choice6=sc.nextInt();
        switch(choice6){
            case 1 ->{
                if(members.isEmpty()){
                    System.out.println("no members added yet");
                }
                else{
                    System.out.println("added members are----");
                    for(Member member:members){
                        System.out.println(member);
                    }
                }
            }
            case 2 ->{
                if(books.isEmpty()){
                    System.out.println("no books added");
                }
                else{
                    for(Book book: books){
                        System.out.println(book);
                    }
                }
            }
            case 3 -> {
                if(admins.isEmpty()){
                    System.out.println("no admin added");
                }
                else{
                    for(Admin admin : admins){
                        System.out.println(admin);
                    }
                }
            }
        }
    }
}
    case 0 -> {
        System.out.println("Exiting...");
        return;
                }
                default -> System.out.println("Invalid input.");
            }
               }catch(NumberFormatException e){
                    System.out.println("invalid input, please enter  a number");
                }
               
        }
    }
}
