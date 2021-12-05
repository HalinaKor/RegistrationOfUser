import java.sql.SQLException;

public class LoginMain {
    public static void main(String[] args) throws SQLException {
        Dao dao = new DaoImplement();
        if (!dao.connect("jdbc:mysql://localhost:3306/mysql","root","04051995")){
            return;
        }
        Helper helper = new Helper(dao);
        helper.showStartPage();
        dao.disconnect();
    }
}
