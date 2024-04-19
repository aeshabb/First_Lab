package org.itmo.client.command;

import lombok.Getter;

import java.net.Socket;

@Getter
public class Receiver {
    private final Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }


}