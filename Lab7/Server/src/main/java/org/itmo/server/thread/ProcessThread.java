package org.itmo.server.thread;

import org.itmo.dto.reply.LoginReply;
import org.itmo.dto.reply.RegisterReply;
import org.itmo.dto.reply.Reply;
import org.itmo.dto.request.LoginRequest;
import org.itmo.dto.request.RegisterRequest;
import org.itmo.dto.request.Request;
import org.itmo.server.command.Command;

import java.nio.channels.SelectionKey;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessThread implements Runnable{
    private final Request request;
    private final SelectionKey key;
    private final ReentrantLock lock;

    private final Map<String, Command> commandMap;
    private static boolean login;

    public ProcessThread(Request request, SelectionKey key, ReentrantLock lock, Map<String, Command> commandMap) {
        this.request = request;
        this.key = key;
        this.lock = lock;
        this.commandMap = commandMap;
    }

    @Override
    public void run() {
        Reply reply = commandMap.get(request.name).process(request);
        if (reply != null) {
            if (!login) {
                if (reply.isSuccess() && ((reply.getClass() == LoginReply.class) || (reply.getClass() == RegisterReply.class))) {
                    login = true;
                } else {
                    reply.setSuccess(false);
                }
            }
        }

        var cachedPool = Executors.newCachedThreadPool();
        cachedPool.submit(new WriteThread(reply, key, lock));
    }
}
