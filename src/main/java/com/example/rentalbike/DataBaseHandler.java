package com.example.rentalbike;
import java.sql.*;

public class DataBaseHandler extends Configs {
    private static DataBaseHandler instance; // Статическое поле для хранения единственного экземпляра класса
    private Connection dbConnection;

    private DataBaseHandler() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    public static DataBaseHandler getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DataBaseHandler(); // Создание экземпляра класса, если он еще не был создан
        }
        return instance;
    }

    public Connection getDbConnection() {
        return dbConnection; // Возвращение уже установленного соединения
    }

    public boolean signUpClient(Client client) {
        String usernameCheck = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";
        String insertClient = "INSERT INTO " + Const.CLIENT_TABLE + "(" + Const.CLIENT_FIRSTNAME + ", " + Const.CLIENT_LASTNAME + ", "
                + Const.CLIENT_SECONDNAME + ", " + Const.CLIENT_PASSPORT + ", " + Const.CLIENT_ADDRESS + ", " + Const.CLIENT_USERID + ")" + "VALUES(?,?,?,?,?,?);";

        String insertUser = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_LOGIN + ", " + Const.USER_PASSWORD + ", " + Const.USER_ROLE + ")" + "VALUES(?, ?, " + "1" + ");";

        try {
            PreparedStatement usernameCheckSt = getDbConnection().prepareStatement(usernameCheck);
            usernameCheckSt.setString(1, client.getLogin());

            ResultSet usernameCheckRes = usernameCheckSt.executeQuery();
            if (usernameCheckRes.next()) {
                // Логин уже существует, вернуть false или выполнить другие действия
                return false;
            }

            PreparedStatement prStUser = getDbConnection().prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            prStUser.setString(1, client.getLogin());
            prStUser.setString(2, hashPassword(client.getPassword())); // Сохранение хэша пароля

            int resultUser = prStUser.executeUpdate();

            if (resultUser == 0) {
                // Вставка пользователя не удалась, вернуть false или выполнить другие действия
                return false;
            }

            ResultSet generatedKeys = prStUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                PreparedStatement prSt = getDbConnection().prepareStatement(insertClient);
                prSt.setString(1, client.getFirstname());
                prSt.setString(2, client.getLastname());
                prSt.setString(3, client.getSecondname());
                prSt.setString(4, client.getNpassport());
                prSt.setString(5, client.getAddress());
                prSt.setInt(6, userId);

                int resultClient = prSt.executeUpdate();

                return resultClient == 1; // Вернуть true, если запрос на вставку клиента выполнен успешно
            } else {
                // Идентификатор пользователя не был сгенерирован, вернуть false или выполнить другие действия
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(Client client) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, client.getLogin());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    protected String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public int getUserRole(String login) {
        int roleId = -1; // Значение по умолчанию, если роль не найдена
        String select = "SELECT " + Const.USER_ROLE + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                roleId = resultSet.getInt(Const.USER_ROLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleId;
    }

    public ResultSet getStore(){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.STORE_TABLE + ";";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resSet;
    }
}