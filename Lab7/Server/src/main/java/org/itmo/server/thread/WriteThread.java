package org.itmo.server.thread;

import org.itmo.dto.reply.Reply;
import org.itmo.server.network.Network;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.ReentrantLock;

public class WriteThread implements Runnable{
    private final Reply reply;
    private final SelectionKey key;
    private final ReentrantLock lock;

    public WriteThread(Reply reply, SelectionKey key, ReentrantLock lock){
        this.reply = reply;
        this.key = key;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            //lock.lock();
            Network.write((SocketChannel) key.channel(), reply);
            //key.channel().register(key.selector(), SelectionKey.OP_READ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
