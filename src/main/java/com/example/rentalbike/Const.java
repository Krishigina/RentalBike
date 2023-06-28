package com.example.rentalbike;

public class Const {
    public static final String USER_TABLE = "users";

    public static final String USER_ID = "id";
    public static final String USER_ROLE = "role_id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";

    public static final String CLIENT_TABLE = "clients";

    public static final String CLIENT_ID = "id";
    public static final String CLIENT_ADDRESS = "address";
    public static final String CLIENT_LASTNAME = "last_name";
    public static final String CLIENT_FIRSTNAME = "first_name";
    public static final String CLIENT_SECONDNAME = "second_name";
    public static final String CLIENT_PASSPORT = "passport";
    public static final String CLIENT_USERID = "user_id";

    public static final String STORE_TABLE = "stores";
    public static final String STORE_ID = "id";
    public static final String STORE_NAME = "name";
    public static final String STORE_ADDRESS = "address";

    public static final String RENTALS_TABLE = "rentals";
    public static final String RENTALS_ID = "id";
    public static final String RENTALS_BOOKINGID = "booking_id";
    public static final String RENTALC_RETURNDATE = "return_date";

    public static final String BOOKINGS_TABLE = "bookings";
    public static final String BOOKINGS_ID = "id";
    public static final String BOOKINGS_CLIENTID = "client_id";
    public static final String BOOKINGS_BIKEID = "bike_id";
    public static final String BOOKINGS_STOREID = "store_id";
    public static final String BOOKINGS_PICKUPDATE = "pickup_date";

    public static final String MODELS_TABLE = "bike_models";
    public static final String MODELS_ID = "id";
    public static final String MODELS_NAME = "name";
    public static final String MODElS_TYPE = "type";
    public static final String MODELS_GEAR = "gear_count";

    public static final String BIKES_TABLE = "bikes";
    public static final String BIKES_ID = "id";
    public static final String BIKES_MODEL = "model_id";

}
