import java.lang.String;
public class Book {
    String title, author,  subject;
    long isbn;
    int quantity,total_number_of_book_with_isbn;
    boolean isAvailable;

    public Book(String title, String author, long isbn, String subject, int quantity,int total_number_of_book_with_isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.subject = subject;
        this.quantity = quantity;
        this.total_number_of_book_with_isbn=total_number_of_book_with_isbn;
    }
    public void addCopies(int count) {
        this.quantity += count;
    }
    public void remove(int count){
        this.quantity-=count;
    }
    public static void deletebook(String title){
        System.out.println("books with title _"+" "+title+" " +" _has been deleated");
    }
    public String toString(){
        return String.format("|title:    %s  |author:     %s  |isbn:     %d    |quantity:     %d   |total_number_of_book_with_isbn :      %d", title,author, isbn, quantity+1, total_number_of_book_with_isbn+1);
    }
}
