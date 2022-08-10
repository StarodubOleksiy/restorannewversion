package com.smartparking.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LiquibaseApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LiquibaseApplication.class);
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DATABASE_NAME = "restoran";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    private static final String CHANGE_LOG_FILE = "com/smartparking/liquibase/changelog-master.xml";

    public static void main(String[] args) {
        LOGGER.info("Creating a new database and filling it with data.");
        try {
            registerDriver();
            initializeDatabase();
        } catch (SQLException | ClassNotFoundException | LiquibaseException exception) {
            LOGGER.error("You have done exception.", exception);
        }
    }


    private static void initializeDatabase() throws SQLException, LiquibaseException {
        Connection databaseConnection = DriverManager.getConnection(CONNECTION_URL + DATABASE_NAME, USER, PASSWORD);
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(databaseConnection));
        Liquibase liquibase = new Liquibase(CHANGE_LOG_FILE, new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts(), new LabelExpression());
        databaseConnection.close();
    }

    private static void registerDriver() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
    }


}
