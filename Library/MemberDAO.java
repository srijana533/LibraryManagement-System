import java.sql.Connection;
import java.sql.PreparedStatement;
public class MemberDAO {
    public static boolean addMember(int id, String name, String email, String role) {
        String sql = "INSERT INTO members (id, name, email, role) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, role);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
