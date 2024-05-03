package org.itmo.dto.request;

import lombok.Getter;
import org.itmo.entity.Route;

@Getter
public class AddRequest extends Request {
    public final Route route;
    public final String username;
    public final String password;
    public AddRequest(Route route, String username, String password){
        super("add");
        this.route = route;
        this.username = username;
        this.password = password;
    }

    protected AddRequest(String name, Route route, String username, String password){
        super(name);
        this.route = route;
        this.username = username;
        this.password = password;
    }

}
