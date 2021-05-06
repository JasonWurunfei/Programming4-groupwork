package com.programming4.messageList.data;

import java.sql.*;
import java.util.ArrayList;

/**
 * Represents a SQLite database.
 * @author Wu Runfei (Jason SE181)
 */
public class SQLiteDatabase {
    private Connection connection;

    public SQLiteDatabase(String DBPath) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
        this.createMessageTable();
        this.addDefaultMessages();
    }

    private void createMessageTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS message (" +
                            "id         integer PRIMARY KEY," +
                            "title      text NOT NULL," +
                            "content    text NOT NULL," +
                            "sender     text NOT NULL," +
                            "URLAddress text NOT NULL" +
                        ");";
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    private void addDefaultMessages() {
        if (this.readMessages().size() == 0) {
            for (int i = 0; i < 4; i++) {
                this.saveMessage(new Message(
                        "Message"+i,
                        "This is message"+i,
                        "Jason",
                        "https://www.oulu.fi/en"
                ));
            }
        }
    }

    /**
     * Saves a Message object to the database in a thread-safe manner.
     * @param message Message object that is about to be save into the database
     * @return boolean value true if the message is saved successfully otherwise false
     */
    public boolean saveMessage(Message message) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO message(title, content, sender, URLAddress) VALUES(?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, message.getTitle());
            statement.setString(2, message.getContent());
            statement.setString(3, message.getSender());
            statement.setString(4, message.getURLAddress());

            isSuccess = statement.executeUpdate() == 1;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * Reads all the message data from the database in a thread-safe manner.
     * @return ArrayList<Message> object containing all the message data
     */
    public ArrayList<Message> readMessages() {
        ArrayList<Message> messageList = new ArrayList<>();
        String query = "SELECT * FROM message;";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                messageList.add(new Message(
                        result.getString("title"),
                        result.getString("content"),
                        result.getString("sender"),
                        result.getString("URLAddress")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messageList;
    }

    public void close() throws SQLException {
        this.connection.close();
    }
}
