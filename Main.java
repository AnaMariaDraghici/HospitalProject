package org.example;

import org.example.domain.validator.consultatieValidator;
import org.example.repository.consultatieRepository;
import org.example.service.Service;
import org.example.ui.Console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/MAP";
        String username = "postgres";
        String password = "0rleans";

        Connection connection = DriverManager.getConnection(url, username, password);

        consultatieRepository consultatieRepository = new consultatieRepository(connection, new consultatieValidator());
        Service service = new Service(consultatieRepository);
        Console console = new Console(service);
        console.run();
    }
}