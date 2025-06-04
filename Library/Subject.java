public class Subject {
    String sname, department;

    public Subject(String name, String department) {
        this.sname = name;
        this.department = department;
    }
    public static void deletesubject(String sname){
        System.out.println("book of  "+ " "+ sname + " " +"  subject has been deleted");

    }
}