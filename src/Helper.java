import java.util.List;
import java.util.Scanner;

public class Helper {
    private Dao dao;
    private Scanner scanner;

    public Helper(Dao dao) {
        this.dao = dao;
        this.scanner = new Scanner(System.in);
    }

    public void showStartPage(){

        System.out.println("Выберите желаемое действие ");
        System.out.println("1 - вход");
        System.out.println("2 - регистрация");

        int chooser = scanner.nextInt();
        scanner.skip("[\r\n]");

        if (chooser == 1) {
            showSignInPage();
        } else if (chooser == 2) {
            showRegisterPage();
        } else {
            System.out.println("Вы ввели неверное число, повторите запрос");
        }
    }

    private void showSignInPage(){
        System.out.println("Введите ваш логин:");
        String login = scanner.nextLine();
        System.out.println("Введите ваш пароль:");
        String password = scanner.nextLine();
        User user= dao.singIn(login,password);
        if (user !=null){
            System.out.println("Hello, "+ user.getName());

            if (user.getRole().equals("admin")){
                List<User> userList = dao.select();
                for (User user1:userList) {
                    System.out.println(user1.toString());
                }
            }
        } else {
            System.out.println("Неправильный Логин или Пароль. Повторите попытку");
            showSignInPage();
        }
    }

    private void showRegisterPage(){
        System.out.println("Введите ваше имя:");
        String name = scanner.nextLine();
        System.out.println("Введите ваш логин:");
        String login = scanner.nextLine();
        System.out.println("Введите ваш пароль:");
        String password = scanner.nextLine();
        dao.register(name,login,password);
    }
}
