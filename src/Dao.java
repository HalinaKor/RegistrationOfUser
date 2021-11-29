import java.sql.SQLException;
import java.util.List;

public interface Dao {
    boolean connect(String url, String user, String password) throws SQLException;
    boolean disconnect() throws SQLException;
    User singIn(String login, String password);
    void register(String name, String login, String password);
    List<User> select();
}
