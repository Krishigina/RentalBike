package com.example.rentalbike;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public boolean updateClientPassword(String login, String oldPassword, String newPassword) {
        String passwordCheckQuery = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";

        try {
            PreparedStatement passwordCheckSt = getDbConnection().prepareStatement(passwordCheckQuery);
            passwordCheckSt.setString(1, login);

            ResultSet passwordCheckRes = passwordCheckSt.executeQuery();
            if (passwordCheckRes.next()) {
                String hashedOldPassword = passwordCheckRes.getString(Const.USER_PASSWORD);

                if (BCrypt.checkpw(oldPassword, hashedOldPassword)) {
                    String updatePasswordQuery = "UPDATE " + Const.USER_TABLE + " SET " + Const.USER_PASSWORD + "=? WHERE " + Const.USER_LOGIN + "=?";
                    PreparedStatement updatePasswordSt = getDbConnection().prepareStatement(updatePasswordQuery);
                    updatePasswordSt.setString(1, BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                    updatePasswordSt.setString(2, login);

                    int rowsAffected = updatePasswordSt.executeUpdate();
                    updatePasswordSt.close();

                    return rowsAffected > 0;
                } else {
                    //System.out.println("Старый пароль введен неверно");
                    return false;
                }
            } else {
                //System.out.println("Пользователь с таким логином не найден");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean updateClientData(String newLastName, String newFirstName, String newSecondName, String newPassport, String newAddress, String userId) {
        String updateQuery = "UPDATE " + Const.CLIENT_TABLE + " SET " +
                Const.CLIENT_FIRSTNAME + " = ?, " +
                Const.CLIENT_LASTNAME + " = ?, " +
                Const.CLIENT_SECONDNAME + " = ?, " +
                Const.CLIENT_PASSPORT + " = ?, " +
                Const.CLIENT_ADDRESS + " = ? " +
                "WHERE " + Const.CLIENT_USERID + " = ?";

        try (PreparedStatement statement = getDbConnection().prepareStatement(updateQuery)) {
            statement.setString(1, newLastName);
            statement.setString(2, newFirstName);
            statement.setString(3, newSecondName);
            statement.setString(4, newPassport);
            statement.setString(5, newAddress);
            statement.setString(6, userId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                //System.out.println("Данные клиента успешно обновлены");
                return true;
            } else {
                //System.out.println("Не удалось обновить данные клиента");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateClientLogin(String newLogin, String userId) {
        String updateUserQuery = "UPDATE " + Const.USER_TABLE + " SET " +
                Const.USER_LOGIN + " = ? " +
                "WHERE " + Const.USER_ID + " = ?";

        try (PreparedStatement statement = getDbConnection().prepareStatement(updateUserQuery)) {
            statement.setString(1, newLogin);
            statement.setString(2, userId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                //System.out.println("Логин клиента успешно обновлен");
                return true;
            } else {
                //System.out.println("Не удалось обновить логин клиента");
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            //System.out.println("Ошибка: логин уже существует");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
    public ResultSet getUserByLoginAndPassword(String login, String password) {
        String query = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD + "=?";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, hashPassword(password));
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public ResultSet getStoreName(){
        ResultSet resSet = null;
        String select = "SELECT name FROM " + Const.STORE_TABLE + ";";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getBookings() {
        ResultSet resultSet = null;
        String select = "SELECT bookings.id, CONCAT(clients.last_name, ' ', clients.first_name, ' ', clients.second_name) AS name, " +
                "clients.passport, " +
                "bookings.bike_id, " +
                "stores.name, " +
                "DATE_FORMAT(bookings.pickup_date, '%d.%m.%Y') AS pickup_date " +
                "FROM bookings " +
                "INNER JOIN clients ON bookings.client_id = clients.id " +
                "INNER JOIN bikes ON bookings.bike_id = bikes.id " +
                "INNER JOIN stores ON bookings.store_id = stores.id;";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getHistory(Integer user_id) {
        ResultSet resSet = null;
        String select = "SELECT " + Const.MODELS_TABLE + "." + Const.MODELS_NAME + ", " + Const.STORE_TABLE + "." + Const.STORE_NAME +
                ", DATE_FORMAT(" + Const.BOOKINGS_TABLE + "." + Const.BOOKINGS_PICKUPDATE + ", '%d.%m.%Y'), " +
                "DATE_FORMAT(" + Const.RENTALC_RETURNDATE + ", '%d.%m.%Y') FROM " + Const.BOOKINGS_TABLE +
                " JOIN " + Const.RENTALS_TABLE + " JOIN bikes ON " + Const.BOOKINGS_TABLE + ".bike_id = bikes.id " +
                "JOIN bike_models ON bikes.model_id = bike_models.id JOIN stores ON " + Const.BOOKINGS_TABLE + ".store_id = stores.id " +
                "JOIN clients ON " + Const.BOOKINGS_TABLE + ".client_id = clients.id WHERE clients.user_id = ? ";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(user_id));
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resSet;
    }
    public int getUserId(String login) {
        int userId = -1; // Значение по умолчанию, если пользователь не найден
        String select = "SELECT " + Const.USER_ID + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt(Const.USER_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
    public String geLastName(String login) {
        String lastname = "";
        String select = "SELECT " + Const.CLIENT_LASTNAME + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                lastname = resultSet.getString(Const.CLIENT_LASTNAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastname;
    }
    public String getFirstName(String login) {
        String firstname = "";
        String select = "SELECT " + Const.CLIENT_FIRSTNAME + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                firstname = resultSet.getString(Const.CLIENT_FIRSTNAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstname;
    }
    public String getSecondName(String login) {
        String secondname = "";
        String select = "SELECT " + Const.CLIENT_SECONDNAME + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                secondname = resultSet.getString(Const.CLIENT_SECONDNAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return secondname;
    }
    public String getPassport(String login) {
        String passport = "";
        String select = "SELECT " + Const.CLIENT_PASSPORT + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                passport = resultSet.getString(Const.CLIENT_PASSPORT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passport;
    }
    public String getAddress(String login) {
        String address = "";
        String select = "SELECT " + Const.CLIENT_ADDRESS + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                address = resultSet.getString(Const.CLIENT_ADDRESS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }
    public String getLoginField(String login) {
        String loginfield = "";
        String select = "SELECT " + Const.USER_LOGIN + " FROM " + Const.USER_TABLE +  " WHERE " +  Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                loginfield = resultSet.getString(Const.USER_LOGIN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginfield;
    }

    public boolean newBooking(Booking booking) {
        String clientIdQuery = "SELECT " + Const.CLIENT_ID + " FROM " + Const.CLIENT_TABLE + " WHERE " + Const.CLIENT_PASSPORT + "=? AND " +
                Const.CLIENT_FIRSTNAME + "=? AND " + Const.CLIENT_LASTNAME + "=? AND " + Const.CLIENT_SECONDNAME + "=?";
        try {
            PreparedStatement clientIdStatement = getDbConnection().prepareStatement(clientIdQuery);
            clientIdStatement.setString(1, booking.getPassport());
            clientIdStatement.setString(2, booking.getClientFirstName());
            clientIdStatement.setString(3, booking.getClientLastName());
            clientIdStatement.setString(4, booking.getClientSecondName());
            ResultSet clientIdResult = clientIdStatement.executeQuery();
            if (!clientIdResult.next()) {
                //System.out.println("Клиент с указанными данными не существует");
                return false; // Клиент с указанными данными не существует
            }
            int clientId = clientIdResult.getInt(Const.CLIENT_ID);

            String storeIdQuery = "SELECT " + Const.STORE_ID + " FROM " + Const.STORE_TABLE + " WHERE " + Const.STORE_NAME + "=?";
            PreparedStatement storeIdStatement = getDbConnection().prepareStatement(storeIdQuery);
            storeIdStatement.setString(1, booking.getStoreName());
            ResultSet storeIdResult = storeIdStatement.executeQuery();
            if (!storeIdResult.next()) {
                //System.out.println("Магазин с указанным названием не существует");
                return false; // Магазин с указанным названием не существует
            }
            int storeId = storeIdResult.getInt(Const.STORE_ID);

            String pickupDate = "";
            if (booking.getPickupDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate datepickup = LocalDate.parse(booking.getPickupDate(), formatter);
                pickupDate = datepickup.format(DateTimeFormatter.ISO_DATE);
            }

            int bikeId = booking.getBike_id();

            // Проверяем, свободен ли велосипед
            String checkBookingsQuery = "SELECT * FROM " + Const.BOOKINGS_TABLE + " WHERE " + Const.BOOKINGS_BIKEID + "=? " +
                    "AND " + Const.BOOKINGS_PICKUPDATE + " >= CURDATE()";
            PreparedStatement checkBookingsStatement = getDbConnection().prepareStatement(checkBookingsQuery);
            checkBookingsStatement.setInt(1, bikeId);
            ResultSet checkBookingsResult = checkBookingsStatement.executeQuery();

            if (checkBookingsResult.next()) {
                //System.out.println("Велосипед с id " + bikeId + " недоступен");
                return false; // Велосипед занят
            }

            String addBookingQuery = "INSERT INTO " + Const.BOOKINGS_TABLE + " (" + Const.BOOKINGS_CLIENTID + ", " +
                    Const.BOOKINGS_STOREID + ", " + Const.BOOKINGS_PICKUPDATE + ", " + Const.BOOKINGS_BIKEID + ") VALUES (?, ?, ?, ?)";
            PreparedStatement addBookingStatement = getDbConnection().prepareStatement(addBookingQuery);
            addBookingStatement.setInt(1, clientId);
            addBookingStatement.setInt(2, storeId);
            addBookingStatement.setString(3, pickupDate);
            addBookingStatement.setInt(4, bikeId);

            int rowsAffected = addBookingStatement.executeUpdate();
            if (rowsAffected == 1) {
                //System.out.println("Бронирование добавлено успешно");
                return true;
            } else {
                //System.out.println("Ошибка при добавлении бронирования");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteBooking(int bookingId) {
        String deleteBookingQuery = "DELETE FROM " + Const.BOOKINGS_TABLE + " WHERE " + Const.BOOKINGS_ID + " = ?";
        String deleteRentalQuery = "DELETE FROM " + Const.RENTALS_TABLE + " WHERE " + Const.RENTALS_BOOKINGID + " = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement deleteBookingStatement = connection.prepareStatement(deleteBookingQuery);
             PreparedStatement deleteRentalStatement = connection.prepareStatement(deleteRentalQuery)) {

            deleteBookingStatement.setInt(1, bookingId);
            int rowsAffected1 = deleteBookingStatement.executeUpdate();

            deleteRentalStatement.setInt(1, bookingId);
            int rowsAffected2 = deleteRentalStatement.executeUpdate();

            if (rowsAffected1 > 0 || rowsAffected2 > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}