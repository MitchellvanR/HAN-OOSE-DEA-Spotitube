package resource.exceptions;

public enum ErrorMessage {
    INVALID_CREDENTIALS_MESSAGE("Invalid credentials. Please check your username and password"),
    SQL_CONNECTION_EXCEPTION_MESSAGE("An error has occurred while making a connection to the SQL server"),
    SQL_DISCONNECT_EXCEPTION_MESSAGE("An error has occurred while trying to close the connection to the SQL server"),
    SQL_QUERY_EXCEPTION_MESSAGE("An error has occurred while trying to access the database"),
    INVALID_VALUE_EXCEPTION_MESSAGE("The data you are trying to fetch has been corrupted. Please contact a system administrator"),
    PROPERTY_LOAD_EXCEPTION_MESSAGE("The database properties could not be found");

    public final String label;

    private ErrorMessage(String message) {
        this.label = message;
    }
}
