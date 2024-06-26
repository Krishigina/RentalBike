package com.example.rentalbike;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends Configs {
    private boolean keepConnectionOpen = false; // Переменная класса DataBaseHandler
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
        else if (instance.getDbConnection().isClosed()){
            instance = new DataBaseHandler();
        }
        return instance;
    }

    public Connection getDbConnection() throws SQLException {
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
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, client.getLogin());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                roleId = resultSet.getInt(Const.USER_ROLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return roleId;
    }

    public List<Store> getStore() {
        List<Store> stores = new ArrayList<>();
        String query = "SELECT * FROM " + Const.STORE_TABLE + ";";

        try (Connection connection = getInstance().getDbConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int storeId = resultSet.getInt("id");
                String storeName = resultSet.getString("name");
                String address = resultSet.getString("address");
                // Add more columns as needed

                Store store = new Store(storeId, storeName, address);
                stores.add(store);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stores;
    }
    public ResultSet getStoreName(){
        ResultSet resSet = null;
        String select = "SELECT name FROM " + Const.STORE_TABLE + ";";
        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<>();
        String select1 = "SELECT bookings.id, CONCAT(clients.last_name, ' ', clients.first_name, ' ', clients.second_name) AS name, " +
                "clients.passport, " +
                "bookings.bike_id, " +
                "stores.name, " +
                "DATE_FORMAT(bookings.pickup_date, '%d.%m.%Y') AS pickup_date " +
                "FROM bookings " +
                "INNER JOIN clients ON bookings.client_id = clients.id " +
                "INNER JOIN bikes ON bookings.bike_id = bikes.id " +
                "INNER JOIN stores ON bookings.store_id = stores.id;";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select1);
            ResultSet resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("id"));
                booking.setClientName(resultSet.getString("name"));
                booking.setPassport(resultSet.getString("passport"));
                booking.setBike_id(resultSet.getInt("bike_id"));
                booking.setStoreName(resultSet.getString("name"));
                booking.setPickupDate(resultSet.getString("pickup_date"));
                bookings.add(booking);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return bookings;
    }
    public List<Rentals> getRentals() {
        List<Rentals> rentals = new ArrayList<>();
        String select = "SELECT rentals.id, CONCAT(clients.last_name, ' ', clients.first_name, ' ', clients.second_name) AS name, " +
                "bookings.bike_id, " +
                "DATE_FORMAT(bookings.pickup_date, '%d.%m.%Y') AS pickup_date, " +
                "DATE_FORMAT(rentals.return_date, '%d.%m.%Y') AS return_date " +
                "FROM bookings " +
                "INNER JOIN clients ON bookings.client_id = clients.id " +
                "INNER JOIN bikes ON bookings.bike_id = bikes.id " +
                "INNER JOIN rentals ON bookings.id = rentals.booking_id;";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                Rentals rental = new Rentals();
                rental.setId(resultSet.getInt("id"));
                rental.setClientName(resultSet.getString("name"));
                rental.setBike_id(resultSet.getInt("bike_id"));
                rental.setPickup_date(resultSet.getString("pickup_date"));
                rental.setReturn_date(resultSet.getString("return_date"));
                rentals.add(rental);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return rentals;
    }

    public ResultSet getHistory(Integer user_id) {
        ResultSet resSet = null;
        String select = "SELECT bike_models.name, stores.name, DATE_FORMAT(bookings.pickup_date, '%d.%m.%Y'), " +
                "CASE WHEN rentals.return_date IS NOT NULL THEN DATE_FORMAT(rentals.return_date, '%d.%m.%Y') " +
                "ELSE 'в аренде' END " +
                "FROM bookings " +
                "JOIN bikes ON bookings.bike_id = bikes.id " +
                "JOIN bike_models ON bikes.model_id = bike_models.id " +
                "JOIN stores ON bookings.store_id = stores.id " +
                "JOIN clients ON bookings.client_id = clients.id " +
                "LEFT JOIN rentals ON bookings.id = rentals.booking_id " +
                "WHERE clients.user_id = ?";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(user_id));
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }
    public int getUserId(String login) {
        int userId = -1; // Значение по умолчанию, если пользователь не найден
        String select = "SELECT " + Const.USER_ID + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt(Const.USER_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return userId;
    }
    public String geLastName(String login) {
        String lastname = "";
        String select = "SELECT " + Const.CLIENT_LASTNAME + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                lastname = resultSet.getString(Const.CLIENT_LASTNAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lastname;
    }
    public String getFirstName(String login) {
        String firstname = "";
        String select = "SELECT " + Const.CLIENT_FIRSTNAME + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                firstname = resultSet.getString(Const.CLIENT_FIRSTNAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return firstname;
    }
    public String getSecondName(String login) {
        String secondname = "";
        String select = "SELECT " + Const.CLIENT_SECONDNAME + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                secondname = resultSet.getString(Const.CLIENT_SECONDNAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return secondname;
    }
    public String getPassport(String login) {
        String passport = "";
        String select = "SELECT " + Const.CLIENT_PASSPORT + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                passport = resultSet.getString(Const.CLIENT_PASSPORT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return passport;
    }
    public String getAddress(String login) {
        String address = "";
        String select = "SELECT " + Const.CLIENT_ADDRESS + " FROM " + Const.CLIENT_TABLE + " JOIN " + Const.USER_TABLE + " ON "
                + Const.CLIENT_USERID + " = " + Const.USER_TABLE + "." + Const.USER_ID + " WHERE " + Const.USER_TABLE + "." + Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                address = resultSet.getString(Const.CLIENT_ADDRESS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return address;
    }
    public String getLoginField(String login) {
        String loginfield = "";
        String select = "SELECT " + Const.USER_LOGIN + " FROM " + Const.USER_TABLE +  " WHERE " +  Const.USER_LOGIN + " = ?";

        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            ResultSet resultSet = prSt.executeQuery();
            if (resultSet.next()) {
                loginfield = resultSet.getString(Const.USER_LOGIN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loginfield;
    }

    public boolean newBooking(Booking booking) {
        String clientIdQuery = "SELECT " + Const.CLIENT_ID + " FROM " + Const.CLIENT_TABLE + " WHERE " + Const.CLIENT_PASSPORT + "=? AND " +
                Const.CLIENT_FIRSTNAME + "=? AND " + Const.CLIENT_LASTNAME + "=? AND " + Const.CLIENT_SECONDNAME + "=?";
        try {
            PreparedStatement clientIdStatement = getInstance().getDbConnection().prepareStatement(clientIdQuery);
            clientIdStatement.setString(1, booking.getPassport());
            clientIdStatement.setString(2, booking.getClientFirstName());
            clientIdStatement.setString(3, booking.getClientLastName());
            clientIdStatement.setString(4, booking.getClientSecondName());
            ResultSet clientIdResult = clientIdStatement.executeQuery();
            if (!clientIdResult.next()) {
                System.out.println("Клиент с указанными данными не существует");
                return false; // Клиент с указанными данными не существует
            }
            int clientId = clientIdResult.getInt(Const.CLIENT_ID);

            String storeIdQuery = "SELECT " + Const.STORE_ID + " FROM " + Const.STORE_TABLE + " WHERE " + Const.STORE_NAME + "=?";
            PreparedStatement storeIdStatement = getInstance().getDbConnection().prepareStatement(storeIdQuery);
            storeIdStatement.setString(1, booking.getStoreName());
            ResultSet storeIdResult = storeIdStatement.executeQuery();
            if (!storeIdResult.next()) {
                System.out.println("Магазин с указанным названием не существует");
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

            String checkBookingsQuery = "SELECT * FROM " + Const.BOOKINGS_TABLE + " LEFT JOIN " + Const.RENTALS_TABLE +
                    " ON " + Const.BOOKINGS_TABLE + ".id = " + Const.RENTALS_TABLE + ".booking_id " +
                    " WHERE " + Const.BOOKINGS_TABLE + "." + Const.BOOKINGS_BIKEID + "=? " +
                    " AND (" +
                    "     " + Const.BOOKINGS_TABLE + "." + Const.BOOKINGS_PICKUPDATE + " >= ? " + // Дата начала аренды больше или равна заданной
                    "     OR " + Const.RENTALS_TABLE + ".return_date IS NULL" + // Дата возврата равна NULL
                    " )";
            PreparedStatement checkBookingsStatement = getInstance().getDbConnection().prepareStatement(checkBookingsQuery);
            checkBookingsStatement.setInt(1, bikeId);
            checkBookingsStatement.setString(2, pickupDate);
            ResultSet checkBookingsResult = checkBookingsStatement.executeQuery();

            if (checkBookingsResult.next()) {
                System.out.println("Велосипед с id " + bikeId + " недоступен");
                return false; // Велосипед занят
            }

            String addBookingQuery = "INSERT INTO " + Const.BOOKINGS_TABLE + " (" + Const.BOOKINGS_CLIENTID + ", " +
                    Const.BOOKINGS_STOREID + ", " + Const.BOOKINGS_PICKUPDATE + ", " + Const.BOOKINGS_BIKEID + ") VALUES (?, ?, ?, ?)";
            PreparedStatement addBookingStatement = getInstance().getDbConnection().prepareStatement(addBookingQuery);
            addBookingStatement.setInt(1, clientId);
            addBookingStatement.setInt(2, storeId);
            addBookingStatement.setString(3, pickupDate);
            addBookingStatement.setInt(4, bikeId);

            int rowsAffected = addBookingStatement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleteBooking(String bookingId) {
        if (!isNumeric(bookingId)) {
            return false;
        }
        String deleteRentalQuery = "DELETE FROM " + Const.RENTALS_TABLE + " WHERE " + Const.RENTALS_BOOKINGID + " = ?";
        String deleteBookingQuery = "DELETE FROM " + Const.BOOKINGS_TABLE + " WHERE " + Const.BOOKINGS_ID + " = ?";

        try (
                PreparedStatement deleteRentalStatement = getInstance().getDbConnection().prepareStatement(deleteRentalQuery);
                PreparedStatement deleteBookingStatement = getInstance().getDbConnection().prepareStatement(deleteBookingQuery)) {

            deleteRentalStatement.setInt(1, Integer.parseInt(bookingId));
            int rowsAffected1 = deleteRentalStatement.executeUpdate();

            deleteBookingStatement.setInt(1, Integer.parseInt(bookingId));
            int rowsAffected2 = deleteBookingStatement.executeUpdate();

            return rowsAffected1 > 0 || rowsAffected2 > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean newRental(Rentals rental) {
        String returnDate = "";
        String pickupDate = "";
        if (rental.getReturn_date() != null && rental.getPickup_date() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate datereturn = LocalDate.parse(rental.getReturn_date(), formatter);
            LocalDate datepickup = LocalDate.parse(rental.getPickup_date(), formatter);
            returnDate = datereturn.format(DateTimeFormatter.ISO_DATE);
            pickupDate = datepickup.format(DateTimeFormatter.ISO_DATE);
        }
        String bookingIdQuery = "SELECT bookings.id FROM bookings JOIN clients ON bookings.client_id = clients.id " +
                " WHERE clients.last_name =? AND clients.first_name = ? AND clients.second_name =? AND bookings.bike_id = ? " +
                " AND bookings.pickup_date = ? ";
        try {
            PreparedStatement bookingId = getInstance().getDbConnection().prepareStatement(bookingIdQuery);
            bookingId.setString(1, rental.getClientLastName());
            bookingId.setString(2, rental.getClientFirstName());
            bookingId.setString(3, rental.getClientSecondName());
            bookingId.setString(4, String.valueOf(rental.getBike_id()));
            bookingId.setString(5, pickupDate);

            ResultSet bookingIdResult = bookingId.executeQuery();
            if (!bookingIdResult.next()) {
                // Запись не существует, возвращаем false
                return false;
            }

            int bookingIdResultInt = bookingIdResult.getInt(Const.BOOKINGS_ID);
            String rentalExistsQuery = "SELECT * FROM " + Const.RENTALS_TABLE + " WHERE " + Const.RENTALS_BOOKINGID + " = ?";
            PreparedStatement rentalExists = getInstance().getDbConnection().prepareStatement(rentalExistsQuery);
            rentalExists.setInt(1, bookingIdResultInt);
            ResultSet rentalExistsResult = rentalExists.executeQuery();

            if (rentalExistsResult.next()) {
                return false;
            }

            String addRentalQuery = "INSERT INTO " + Const.RENTALS_TABLE + " (" + Const.RENTALS_BOOKINGID + ", " +
                    Const.RENTALC_RETURNDATE  + ") VALUES (?, ?)";
            PreparedStatement addRentalStatement = getInstance().getDbConnection().prepareStatement(addRentalQuery);
            addRentalStatement.setInt(1, bookingIdResultInt);
            addRentalStatement.setString(2, returnDate);

            int rowsAffected = addRentalStatement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            if (e.getMessage().equals("Illegal operation on empty result set")) {
                return false;
            } else {
                e.printStackTrace();
                return false;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteRental(String rentalId) {
        if (!isNumeric(rentalId)) {
            return false;
        }
        String deleteBookingQuery = "DELETE FROM " + Const.RENTALS_TABLE + " WHERE " + Const.RENTALS_ID + " = ?";

        try (
             PreparedStatement deleteBookingStatement = getInstance().getDbConnection().prepareStatement(deleteBookingQuery)) {

            deleteBookingStatement.setInt(1, Integer.parseInt(rentalId));
            int rowsAffected = deleteBookingStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Accounting> getAccountings() {
        List<Accounting> accountings = new ArrayList<>();
        String query = "SELECT bikes.id, bike_models.name, DATE_FORMAT(bookings.pickup_date, '%d.%m.%Y') as pickup_date " +
                "FROM " + Const.BIKES_TABLE + " bikes " +
                "JOIN " + Const.MODELS_TABLE + " bike_models ON bikes.model_id = bike_models.id " +
                "JOIN " + Const.BOOKINGS_TABLE + " bookings ON bikes.id = bookings.bike_id " +
                "LEFT JOIN " + Const.RENTALS_TABLE + " rentals ON bookings.id = rentals.booking_id " +
                "WHERE rentals.return_date IS NULL ";

        try (Connection connection = getInstance().getDbConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int bikeId = resultSet.getInt("id");
                String modelName = resultSet.getString("name");
                String pickupDate = resultSet.getString("pickup_date");

                Accounting accounting = new Accounting(bikeId, modelName, pickupDate);
                accountings.add(accounting);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accountings;
    }

    public List<Manager> getManagers() {
        List<Manager> managers = new ArrayList<>();
        String query = "SELECT managers.id, CONCAT(managers.lastname, ' ', managers.firstname, ' ', managers.secondname) AS name, users.login FROM managers JOIN users ON managers.user_id = users.id";

        try (Connection connection = getInstance().getDbConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int managerId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");

                Manager manager = new Manager(managerId, name, login);
                managers.add(manager);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return managers;
    }

    public List<Admin> getAdmins() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT admins.id, CONCAT(admins.lastname, ' ', admins.firstname, ' ', admins.secondname) AS name, " +
                "users.login FROM admins JOIN users ON admins.user_id = users.id";

        try (Connection connection = getInstance().getDbConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int adminId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");

                Admin admin = new Admin(adminId, name, login);
                admins.add(admin);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public List<Models> getModel() throws SQLException {
        List<Models> modelList = new ArrayList<>();
        String query = "SELECT * FROM bike_models";

        try {
            PreparedStatement statement = getInstance().getDbConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String modelName = resultSet.getString("name");
                String type = resultSet.getString("type");
                int modelGear = resultSet.getInt("gear_count");

                Models model = new Models(id, modelName, type, modelGear);
                modelList.add(model);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return modelList;
    }

    public List<Bike> getBike() throws SQLException {
        List<Bike> bikeList = new ArrayList<>();
        String query = "SELECT bikes.id, bike_models.name FROM bikes JOIN bike_models ON bikes.model_id = bike_models.id";

        try {
            PreparedStatement statement = getInstance().getDbConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String modelName = resultSet.getString("name");

                Bike bike = new Bike(id, modelName);
                bikeList.add(bike);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return bikeList;
    }

    public List<Client> getClient() throws SQLException {
        List<Client> clientList = new ArrayList<>();
        String query = "SELECT clients.id,  CONCAT(clients.last_name, ' ', clients.first_name, ' ', clients.second_name) AS name, clients.passport, " +
                " clients.address FROM clients ";

        try {
            PreparedStatement statement = getInstance().getDbConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String passport = resultSet.getString("passport");
                String address = resultSet.getString("address");

                Client client = new Client(id, name, passport, address);
                clientList.add(client);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return clientList;
    }

    public boolean newAdmin(Admin admin) {
        String adminCheck = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";
        String insertClient = "INSERT INTO admins " + "( lastname, firstname, secondname, user_id "+ ")" + "VALUES(?,?,?,?);";

        String insertUser = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_LOGIN + ", " + Const.USER_PASSWORD + ", " + Const.USER_ROLE + ")" + "VALUES(?, ?, " + "3" + ");";

        try {
            PreparedStatement adminCheckSt = getDbConnection().prepareStatement(adminCheck);
            adminCheckSt.setString(1, admin.getLogin());

            ResultSet adminCheckRes = adminCheckSt.executeQuery();
            if (adminCheckRes.next()) {
                // Логин уже существует, вернуть false или выполнить другие действия
                return false;
            }

            PreparedStatement prStUser = getInstance().getDbConnection().prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            prStUser.setString(1, admin.getLogin());
            prStUser.setString(2, hashPassword(admin.getPassword())); // Сохранение хэша пароля

            int resultUser = prStUser.executeUpdate();

            if (resultUser == 0) {
                // Вставка пользователя не удалась, вернуть false или выполнить другие действия
                return false;
            }

            ResultSet generatedKeys = prStUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(insertClient);
                prSt.setString(1, admin.getFirstname());
                prSt.setString(2, admin.getLastname());
                prSt.setString(3, admin.getSecondname());
                prSt.setInt(4, userId);

                int resultAdmin = prSt.executeUpdate();

                return resultAdmin == 1; // Вернуть true, если запрос на вставку клиента выполнен успешно
            } else {
                // Идентификатор пользователя не был сгенерирован, вернуть false или выполнить другие действия
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteAdmin(String id) {
        if (!isNumeric(id)) {
            return false;
        }
        String selectAdmin = "SELECT user_id FROM admins WHERE id=?";
        String deleteAdmin = "DELETE FROM admins WHERE id=?";
        String deleteUser = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + "=?";

        try {
            PreparedStatement selectAdminSt = getInstance().getDbConnection().prepareStatement(selectAdmin);
            selectAdminSt.setInt(1, Integer.parseInt(id));

            ResultSet adminRes = selectAdminSt.executeQuery();
            if (!adminRes.next()) {
                // Запись с указанным id не найдена, вернуть false или выполнить другие действия
                return false;
            }

            int userId = adminRes.getInt("user_id");

            PreparedStatement deleteAdminSt = getInstance().getDbConnection().prepareStatement(deleteAdmin);
            deleteAdminSt.setInt(1, Integer.parseInt(id));
            int resultAdmin = deleteAdminSt.executeUpdate();

            PreparedStatement deleteUserSt = getInstance().getDbConnection().prepareStatement(deleteUser);
            deleteUserSt.setInt(1, userId);
            int resultUser = deleteUserSt.executeUpdate();

            return resultAdmin == 1 && resultUser == 1; // Вернуть true, если записи успешно удалены
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean newManager(Manager manager) {
        String managerCheck = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=?";
        String insertManager = "INSERT INTO managers " + "( lastname, firstname, secondname, user_id "+ ")" + "VALUES(?,?,?,?);";

        String insertUser = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_LOGIN + ", " + Const.USER_PASSWORD + ", " + Const.USER_ROLE + ")" + "VALUES(?, ?, " + "2" + ");";

        try {
            PreparedStatement managerCheckSt = getDbConnection().prepareStatement(managerCheck);
            managerCheckSt.setString(1, manager.getLogin());

            ResultSet managerCheckRes = managerCheckSt.executeQuery();
            if (managerCheckRes.next()) {
                // Логин уже существует, вернуть false или выполнить другие действия
                return false;
            }

            PreparedStatement prStUser = getInstance().getDbConnection().prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            prStUser.setString(1, manager.getLogin());
            prStUser.setString(2, hashPassword(manager.getPassword())); // Сохранение хэша пароля

            int resultUser = prStUser.executeUpdate();

            if (resultUser == 0) {
                // Вставка пользователя не удалась, вернуть false или выполнить другие действия
                return false;
            }

            ResultSet generatedKeys = prStUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(insertManager);
                prSt.setString(1, manager.getFirstname());
                prSt.setString(2, manager.getLastname());
                prSt.setString(3, manager.getSecondname());
                prSt.setInt(4, userId);

                int resultManager = prSt.executeUpdate();

                return resultManager == 1; // Вернуть true, если запрос на вставку клиента выполнен успешно
            } else {
                // Идентификатор пользователя не был сгенерирован, вернуть false или выполнить другие действия
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteManager(String id) {
        if (!isNumeric(id)) {
            return false;
        }

        String selectManager = "SELECT user_id FROM managers WHERE id=?";
        String deleteManager = "DELETE FROM managers WHERE id=?";
        String deleteUser = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + "=?";

        try {
            PreparedStatement selectManagerSt = getInstance().getDbConnection().prepareStatement(selectManager);
            selectManagerSt.setInt(1, Integer.parseInt(id));

            ResultSet managerRes = selectManagerSt.executeQuery();
            if (!managerRes.next()) {
                // Запись с указанным id не найдена, вернуть false или выполнить другие действия
                return false;
            }

            int userId = managerRes.getInt("user_id");

            PreparedStatement deleteManagerSt = getInstance().getDbConnection().prepareStatement(deleteManager);
            deleteManagerSt.setInt(1, Integer.parseInt(id));
            int resultAdmin = deleteManagerSt.executeUpdate();

            PreparedStatement deleteUserSt = getInstance().getDbConnection().prepareStatement(deleteUser);
            deleteUserSt.setInt(1, userId);
            int resultUser = deleteUserSt.executeUpdate();

            return resultAdmin == 1 && resultUser == 1; // Вернуть true, если записи успешно удалены
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean newStore(Store store) {
        String insertStore = "INSERT INTO " + Const.STORE_TABLE + "( " + Const.STORE_NAME + ", " + Const.STORE_ADDRESS + " )" + " VALUES(?, ?)";

        try (
             PreparedStatement pstmt = getInstance().getDbConnection().prepareStatement(insertStore)) {

            pstmt.setString(1, store.getName());
            pstmt.setString(2, store.getAddress());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean deleteStore(String name) {
        String deleteStoresQuery = "DELETE FROM " + Const.STORE_TABLE + " WHERE " + Const.STORE_NAME + " = ?";

        try (
                PreparedStatement deleteStoresStatement = getInstance().getDbConnection().prepareStatement(deleteStoresQuery)) {

            deleteStoresStatement.setString(1, name);
            int rowsAffected = deleteStoresStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getModelName() {
        ResultSet resSet = null;
        String select = "SELECT " + Const.MODELS_NAME + " FROM " + Const.MODELS_TABLE + ";";
        try {
            PreparedStatement prSt = getInstance().getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

    public boolean newBike(Bike bike1) {
        String selectModel = "SELECT " + Const.MODELS_ID + " FROM " + Const.MODELS_TABLE + " WHERE name = ?";
        String insertBike = "INSERT INTO " + Const.BIKES_TABLE + " (" + Const.BIKES_MODEL + " ) VALUES(?)";

        try (
             PreparedStatement selectStmt = getInstance().getDbConnection().prepareStatement(selectModel);
             PreparedStatement insertStmt =getInstance().getDbConnection().prepareStatement(insertBike)) {

            // Выполняем запрос для получения id модели велосипеда
            selectStmt.setString(1, bike1.getModelName());
            ResultSet modelResultSet = selectStmt.executeQuery();

            if (modelResultSet.next()) {
                int modelId = modelResultSet.getInt("id");

                // Вставляем новый велосипед в таблицу bikes
                insertStmt.setInt(1, modelId);
                int rowsInserted = insertStmt.executeUpdate();

                if (rowsInserted > 0) {
                    return true;
                }
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteBike(String id) {
        if (!isNumeric(id)) {
            return false;
        }

        String deleteBikeQuery = "DELETE FROM " + Const.BIKES_TABLE + " WHERE " + Const.BIKES_ID + " = ?";
        try (PreparedStatement deleteStoresStatement = getInstance().getDbConnection().prepareStatement(deleteBikeQuery)) {
            deleteStoresStatement.setInt(1, Integer.parseInt(id));
            int rowsAffected = deleteStoresStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}