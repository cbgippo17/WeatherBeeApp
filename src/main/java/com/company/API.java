package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class API {

    private static Statement stmt;
    private static ResultSet results;

    public static void main(String[] args) throws JsonProcessingException {
        //testing API functions

        //testing getAllAccounts
        String allAccountsTest = getAllAccounts();
        System.out.println(allAccountsTest);
    }

    /**
     * getAllAccounts
     *
     * Connects to DB, queries and returns all data within 'accounts' table
     * @return -- String JSONOutput containing all account data in JSON format
     */
    public static String getAllAccounts() {
        String pgSelect = "SELECT * FROM accounts";
        List<UserAccount> allAccounts = new ArrayList<UserAccount>();
        String JSONOutput = "";

        try(Connection conn = DBconnection.connect()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(pgSelect);

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

}
