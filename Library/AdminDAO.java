import java.sql.PreparedStatement;
import java.sql.Connection;
public class AdminDAO {
    public static boolean addAdmin(String name,String email,String password) {
        String sql="insert into Admin(name,email,password) values (?, ?, ?)";


        try(Connection con=DBConnection.getConnection()){
     PreparedStatement pst=con.prepareStatement(sql);
     pst.setString(1,name);
      pst.setString(2,email);
       pst.setString(3,password);
     int row=pst.executeUpdate();
        return row>0;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}