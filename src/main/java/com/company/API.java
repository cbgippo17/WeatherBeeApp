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

    public static void main(String[] args) {
        String sqlSelect = "SELECT * FROM accounts";

        try(Connection conn = DBconnection.connect()){

            stmt = conn.createStatement();
            results = stmt.executeQuery(sqlSelect);

            List<UserAccount> usersList = new ArrayList<UserAccount>();

            while (results.next()) {

                UserAccount userObject = new UserAccount();

                userObject.setId(Integer.valueOf(results.getString("user_id")));
                userObject.setUsername(results.getString("username"));
                userObject.setPassword(results.getString("password"));
                userObject.setEmail(results.getString("email"));
                userObject.setNumOfHives(Integer.valueOf(results.getString("num_of_hives")));

                usersList.add(userObject);
            }

            ObjectMapper mapper = new ObjectMapper();
            String JSONOutput = mapper.writeValueAsString(usersList);
            System.out.println(JSONOutput);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
