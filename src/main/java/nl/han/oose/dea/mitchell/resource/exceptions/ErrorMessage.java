package nl.han.oose.dea.mitchell.resource.exceptions;

public enum ErrorMessage {
    INVALID_CREDENTIALS_MESSAGE("Invalid credentials. Please check your username and password"),
    USER_NOT_LOGGED_IN_EXCEPTION_MESSAGE("You are currently not logged in. Please log in to use the application"),
    SQL_CONNECTION_EXCEPTION_MESSAGE("An error has occurred while making a connection to the SQL server"),
    SQL_DISCONNECT_EXCEPTION_MESSAGE("An error has occurred while trying to close the connection to the SQL server"),
    SQL_QUERY_EXCEPTION_MESSAGE("An error has occurred while trying to access the database"),
    INVALID_VALUE_EXCEPTION_MESSAGE("The data you are trying to fetch has been corrupted. Please contact a system administrator"),
    PROPERTY_LOAD_EXCEPTION_MESSAGE("The database properties could not be found"),
    DUPLICATE_ENTRY_MESSAGE("The value you are trying to insert is already present in the table.");

    public final String label;

    private ErrorMessage(String message) {
        this.label = message;
    }
}
