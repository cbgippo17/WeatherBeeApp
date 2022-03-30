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
        List<UserAccount> allAccounts = new ArrayList<UserAccount>();
        allAccounts = getAllAccounts();
        String JSONOutput = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            JSONOutput = mapper.writeValueAsString(allAccounts);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(JSONOutput);
    }

    /**
     * getAllAccounts
     *
     * Select query to database and returns all data within 'accounts' table
     * @return -- allAccounts array list of UserAccount class
     */
    public static List<UserAccount> getAllAccounts() {
        String pgSelect = "SELECT * FROM accounts";
        List<UserAccount> allAccounts = new ArrayList<UserAccount>();

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAccounts;
    }

}
