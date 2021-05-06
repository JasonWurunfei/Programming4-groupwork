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
     * Saves a Message object to the database
     * @param message Message object that is about to be save into the database
     * @return boolean value true if the message is saved successfully otherwise false
     */
    public boolean saveMessage(Message message) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO message(id, title, content, sender, URLAddress) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, this.lastId()+1);
            statement.setString(2, message.getTitle());
            statement.setString(3, message.getContent());
            statement.setString(4, message.getSender());
            statement.setString(5, message.getURLAddress());

            isSuccess = statement.executeUpdate() == 1;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * Remove a Message object from the database
     * @param id the id of the Message object that is about to be save into the database
     * @return boolean value true if the message is saved successfully otherwise false
     */
    public boolean removeMessage(int id) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM message WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            isSuccess = statement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * Reads all the message data from the database
     * @return ArrayList<Message> object containing all the message data
     */
    public ArrayList<Message> readMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM message;";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                messages.add(new Message(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("content"),
                        result.getString("sender"),
                        result.getString("URLAddress")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    /**
     * get the biggest message ID in the message table of the SQLite database.
     * @return the last ID or 0 if the database is empty.
     */
    public int lastId() {
        int maxId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT max(id) FROM message;");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            maxId = resultSet.getInt(1);
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return maxId;
    }

    public void close() throws SQLException {
        this.connection.close();
    }
}
