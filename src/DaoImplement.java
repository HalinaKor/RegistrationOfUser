import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImplement implements Dao {
    private static final String SQL_SELECT = "SELECT * FROM gala.users WHERE login like ? AND password like ?";
    private static final String SQL_SELECTUSER = "SELECT * FROM gala.users";
    private static final String SQL_INSERT = "INSERT INTO gala.users (Name, Login, Password, Role) Value (?,?,?,?)";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet1;


    @Override
    public boolean connect(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB successfully");
            return true;
        } catch (SQLException e) {
            System.out.println("Connection to DB is failed");
            return false;
        }
    }

    @Override
    public boolean disconnect()  {
        try {
            connection.close();
            System.out.println("Disconnected from DB");
            return true;
        } catch (NullPointerException | SQLException e) {
            System.out.println("Disconnection failure");
            return false;
        }
    }

    @Override
    public User singIn(String login, String password) {
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()){
                int id = resultSet1.getInt("id");
                String name = resultSet1.getString("name");
                String role = resultSet1.getString("role");

                User obj = new User();
                obj.setId(id);
                obj.setLogin(login);
                obj.setPassword(password);
                obj.setRole(role);
                obj.setName(name);

                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void register(String name, String login, String password) {
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, "user");

            // если писать int i = preparedStatement.executeUpdate(), то i будет показывать количество  элементов над которыми были произведены операции
            preparedStatement.executeUpdate();
            System.out.println("User added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List <User> select(){
        List<User> result1 = new ArrayList<>();
        try {
            preparedStatement =connection.prepareStatement(SQL_SELECTUSER);
            resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()){
                int id = resultSet1.getInt("id");
                String name = resultSet1.getString("name");
                String login = resultSet1.getString("login");
                String password = resultSet1.getString("password");
                String role = resultSet1.getString("role");

                User obj = new User();
                obj.setId(id);
                obj.setLogin(login);
                obj.setPassword(password);
                obj.setRole(role);
                obj.setName(name);

                result1.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result1;
    }
}
