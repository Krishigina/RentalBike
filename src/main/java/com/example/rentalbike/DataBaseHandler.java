package com.example.rentalbike;
import java.sql.*;

public class DataBaseHandler extends Configs{
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

    public void signUpClient(Client client) {
        String insert = "INSERT INTO " + Const.CLIENT_TABLE + "(" + Const.CLIENT_FIRSTNAME + ", " + Const.CLIENT_LASTNAME + ", "
                + Const.CLIENT_SECONDNAME + ", " + Const.CLIENT_PASSPORT + ", " + Const.CLIENT_ADDRESS + ")" + "VALUES(?,?,?,?,?);";

        String insertUser = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_LOGIN + ", " + Const.USER_PASSWORD + ", " + Const.USER_ROLE + ")" + "VALUES(?, ?, " + "1" + ");";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, client.getFirstname());
            prSt.setString(2, client.getLastname());
            prSt.setString(3, client.getSecondname());
            prSt.setString(4, client.getNpassport());
            prSt.setString(5, client.getAddress());

            prSt.executeUpdate();

            PreparedStatement prStUser = getDbConnection().prepareStatement(insertUser);
            prStUser.setString(1, client.getLogin());
            prStUser.setString(2, client.getPassword());

            prStUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public ResultSet getUser(Client client){
            ResultSet resSet = null;

            String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD + "=?";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(select);
                prSt.setString(1, client.getLogin());
                prSt.setString(2, client.getPassword());

                resSet = prSt.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return resSet;
        }
    }
