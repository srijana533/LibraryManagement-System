import java.util.*;
import java.lang.String;
import java.time.LocalDate;


public class IssueRecord {
    int memberId;
    long  isbn;
    LocalDate issueDate;
    LocalDate dueDate;

    public IssueRecord(int memberId, long isbn, LocalDate issueDate, LocalDate dueDate) {
        this.memberId = memberId;
        this.isbn = isbn;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }
    // public String toString(){
    //     return "|member id: %s "
        


    // }
    
}
