package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        if (properties != null) {
            String driverClass = properties.getProperty("driver_class");
            String url = properties.getProperty("url");
            String login = properties.getProperty("username");
            String password = properties.getProperty("password");
            Class.forName(driverClass);
            this.connection = DriverManager.getConnection(url, login, password);

        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "create table if not exists %s ();",
                tableName
        );
        statementExecute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        statementExecute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "alter table if exists %s add column if not exists %s %s;",
                tableName, columnName, type
        );
        statementExecute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "alter table if exists %s drop column if exists %s;",
                tableName, columnName
        );
        statementExecute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "alter table if exists %s rename column %s to %s;",
                tableName, columnName, newColumnName
        );
        statementExecute(sql);
    }

    private void statementExecute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception  {
        Properties config = new Properties();
        try (InputStream in = new FileInputStream("src/main/resources/app.properties")) {
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {
                tableEditor.dropTable("animals");
                tableEditor.createTable("animals");
                System.out.println(tableEditor.getTableScheme("animals"));
                tableEditor.addColumn("animals", "name", "varchar");
                System.out.println(tableEditor.getTableScheme("animals"));
                tableEditor.addColumn("animals", "family", "varchar");
                System.out.println(tableEditor.getTableScheme("animals"));
                tableEditor.renameColumn("animals", "family", "kind");
                System.out.println(tableEditor.getTableScheme("animals"));
                tableEditor.dropColumn("animals", "kind");
                System.out.println(tableEditor.getTableScheme("animals"));
                tableEditor.dropTable("animals");
            }
        }
    }
}