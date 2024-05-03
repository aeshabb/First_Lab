package org.itmo.dto.request;

import lombok.Getter;
@Getter
public class LoginRequest extends Request {
    private final String username;
    private final String password;
    public LoginRequest(String username, String password){
        super("login");
        this.username = username;
        this.password = password;
    }

}
