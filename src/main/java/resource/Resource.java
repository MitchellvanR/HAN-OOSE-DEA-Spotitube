package resource;

import resource.exceptions.UserNotLoggedInException;

public class Resource {
    protected void validateUserLogin(String token) {
        if (!token.equals("1234-1234-1234")) throw new UserNotLoggedInException();
    }
}
