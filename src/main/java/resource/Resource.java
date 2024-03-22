package resource;

import resource.exceptions.UserNotLoggedInException;

public class Resource {
    protected void validateUserLogin(String token) {
        if (token == null) throw new UserNotLoggedInException();
    }
}
