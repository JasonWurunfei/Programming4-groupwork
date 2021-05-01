package com.programming4.messageList.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Represents a SQLite database with lock mechanism to secure concurrent execution.
 * @author Wu Runfei (Jason SE181)
 */
public class ThreadSafeSQLiteDatabase extends SQLiteDatabase {
    final static ReadWriteLock MESSAGE_LOCK = new ReentrantReadWriteLock();
    final static ReadWriteLock USER_LOCK = new ReentrantReadWriteLock();

    /**
     * Constructor of ThreadSafeSQLiteDatabase class used to
     * create a new ThreadSafeSQLiteDatabase object.
     * @param DBPath path to the SQLit database
     */
    public ThreadSafeSQLiteDatabase(String DBPath) throws SQLException {
        super(DBPath);
    }

    /**
     * Saves a Message object to the database in a thread-safe manner.
     * @param message Message object that is about to be save into the database
     * @return boolean value true if the message is saved successfully otherwise false
     */
    @Override
    public boolean saveMessage(Message message) {
        MESSAGE_LOCK.writeLock().lock();
        try {
            return super.saveMessage(message);
        } finally {
            MESSAGE_LOCK.writeLock().unlock();
        }
    }

    /**
     * Reads all the message data from the database in a thread-safe manner.
     * @return ArrayList<Message> object containing all the message data
     */
    @Override
    public ArrayList<Message> readMessages() {
        USER_LOCK.readLock().lock();
        try {
            return super.readMessages();
        } finally {
            USER_LOCK.readLock().unlock();
        }
    }
}
