package org.itmo.dto.request;

import lombok.Getter;

@Getter
public class RegisterRequest extends Request {
    private final String username;
    private final String password;
    public RegisterRequest(String username, String password){
        super("register");
        this.username = username;
        this.password = password;
    }
}
