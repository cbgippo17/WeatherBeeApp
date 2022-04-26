package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class API {

    private static Statement stmt;
    private static ResultSet results;

    public static void main(String[] args) {
//        // Connect to server
//        Undertow server = Undertow.builder()
//                .addHttpListener(8080, "localhost")
//                .setHandler(new HttpHandler() {
//                    @Override
//                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
//                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
//                        exchange.getResponseSender().send("Hello World");
//                    }
//                }).build();
//
//        // Boot the web server
//        server.start();
//
//        // Testing API functions

        // Testing getAllAccounts
        String allAccountsTest = getAllAccounts();
        System.out.println(allAccountsTest);

        // Testing postAccount()
//        UserAccount userObject = new UserAccount();
//
//        userObject.setUsername("claregippo");
//        userObject.setPassword("password");
//        userObject.setEmail("clareg@aup.edu");
//        userObject.setNumOfHives(3);
//
//        postAccount(userObject);

    }

    /**
     * getAllAccounts
     *
     * Connects to DB, queries and returns all data within 'accounts' table
     * @return -- String JSONOutput containing all account data in JSON format
     */
    public static String getAllAccounts() {
        String selectAllAccounts = "SELECT * FROM accounts";
        List<UserAccount> allAccounts = new ArrayList<UserAccount>();
        String JSONOutput = "";

        try(Connection conn = DBconnection.connect()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(selectAllAccounts);

            while (results.next()) {

                UserAccount userObject = new UserAccount();

                userObject.setId(Integer.valueOf(results.getString("user_id")));
                userObject.setUsername(results.getString("username"));
                userObject.setPassword(results.getString("password"));
                userObject.setEmail(results.getString("email"));
                userObject.setNumOfHives(Integer.valueOf(results.getString("num_of_hives")));

                allAccounts.add(userObject);
            }

            ObjectMapper mapper = new ObjectMapper();
            JSONOutput = mapper.writeValueAsString(allAccounts);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return JSONOutput;
    }

    public static String getAccountWithId(int id) {
        String selectAllAccounts = "SELECT * FROM accounts WHERE user_id = " + Integer.toString(id);
        List<UserAccount> allAccounts = new ArrayList<UserAccount>();
        String JSONOutput = "";

        try(Connection conn = DBconnection.connect()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(selectAllAccounts);

            while (results.next()) {

                UserAccount userObject = new UserAccount();

                userObject.setId(Integer.valueOf(results.getString("user_id")));
                userObject.setUsername(results.getString("username"));
                userObject.setPassword(results.getString("password"));
                userObject.setEmail(results.getString("email"));
                userObject.setNumOfHives(Integer.valueOf(results.getString("num_of_hives")));

                allAccounts.add(userObject);
            }

            ObjectMapper mapper = new ObjectMapper();
            JSONOutput = mapper.writeValueAsString(allAccounts);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return JSONOutput;
    }

    /**
     * postAccount
     *
     * @param userObject
     */
    public static void postAccount(UserAccount userObject) {
        String insertAccount = "INSERT INTO accounts (username, password, email, num_of_hives) " +
                "VALUES";

        String username = userObject.getUsername();
        String password = userObject.getPassword();
        String email = userObject.getEmail();
        String numOfHives = Integer.toString(userObject.getNumOfHives());

        insertAccount += " ('" + username + "', '" + password + "', '" + email + "'," + numOfHives
                + ") RETURNING user_id";

        try(Connection conn = DBconnection.connect()) {

            stmt = conn.createStatement();
            results = stmt.executeQuery(insertAccount);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * updateAccount
     *
     * @param userObject
     */
    public static void updateAccount(UserAccount userObject) {
        String updateAccount = "UPDATE accounts SET";

        String id = Integer.toString(userObject.getId());
        String username = userObject.getUsername();
        String password = userObject.getPassword();
        String email = userObject.getEmail();
        String numOfHives = Integer.toString(userObject.getNumOfHives());

        updateAccount += "username = " + username + ", password = " + password + ", email = " +
                email + ", num_of_hives = " + numOfHives + " WHERE user_id = " + id;

        try(Connection conn = DBconnection.connect()) {

            stmt = conn.createStatement();
            results = stmt.executeQuery(updateAccount);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deleteAccount
     *
     * @param userObject
     */
    public static void deleteAccount(UserAccount userObject) {
        String id = Integer.toString(userObject.getId());
        String deleteAccount = "DELETE from accounts WHERE user_id = " + id;

        try(Connection conn = DBconnection.connect()) {

            stmt = conn.createStatement();
            results = stmt.executeQuery(deleteAccount);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
