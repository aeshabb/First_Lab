package org.itmo.server.thread;

import org.itmo.dto.request.Request;
import org.itmo.server.command.Command;
import org.itmo.server.network.Network;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ReadThread implements Runnable {

    private final SelectionKey key;
    private final ReentrantLock lock;
    private final Map<String, Command> commandMap;

    public ReadThread(SelectionKey key, ReentrantLock lock, Map<String, Command> commandMap) {
        this.key = key;
        this.lock = lock;
        this.commandMap = commandMap;
    }

    @Override
    public void run() {
        try {
            //lock.lock();
            Request req = Network.read(key);
            var attachment = key.attachment();
            key.channel().register(key.selector(), SelectionKey.OP_READ, attachment);
            ProcessThread processThread = new ProcessThread(req, key, lock, commandMap);
            Thread thread = new Thread(processThread);
            thread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //lock.unlock();
        }
    }

}