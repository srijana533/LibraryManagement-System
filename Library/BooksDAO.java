import java.sql.Connection;
import java.sql.PreparedStatement;
public class BooksDAO{
    public static boolean addBook(String title, String author, long isbn, String subject, int quantity,int total_number_of_book_with_isbn){
        String sql ="INSERT into Books(title,author,isbn,subject,quantity,total_number_of_book_with_isbn) VALUES ( ?, ?, ?, ?, ?, ?)";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(sql)){
            pst.setString(1,title);
            pst.setString(2, author);
            pst.setLong(3,isbn);
            pst.setString(4,subject);
            pst.setInt(5,quantity);
            pst.setInt(6,total_number_of_book_with_isbn);
            int row=pst.executeUpdate();
            return row>0;
            } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
     }
}