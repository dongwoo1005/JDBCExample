import java.io.*;
import java.sql.*;

/**
 * Created by Dongwoo on 26/02/2016.
 */
public class JDBCExample {

    public static void main(String[] args) throws Exception{

        // loading the PostgreSQL driver
        Class.forName("org.postgresql.Driver");

        // connection to the database
        String host = args[0];
        String port = args[1];
        String databaseName = args[2];
        String username = args[3];
        String password = args[4];

        System.out.println("Connecting to DB...");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://"
                + host + ":" + port + "/" + databaseName, username, password);
        System.out.println("Connected to DB!");

        // setting auto commit to false
        connection.setAutoCommit(false);

        // (a) <b>create tables:</b>
        /*
        should contain all the code necessary (including variable declarations) to perform <b>a single transaction</b>
        to define the tables <i> emp, dept and works </i>.
        The SQL statements for creating these tables are listed below.
        Your code <b> must use </b> a <i> Statement </i> object and make 3 calls to the <i> executeUpdate </i> method.
         */

        System.out.println("Creating Tables...");

        Statement stmt = connection.createStatement();
        String sql;

        String createEmpTable =
                "CREATE TABLE emp (" +
                "eid NUMERIC(9, 0) PRIMARY KEY," +
                "ename VARCHAR(30)," +
                "age NUMERIC(3, 0)," +
                "salary NUMERIC(10, 2))";
        stmt.executeUpdate(createEmpTable);
        System.out.println("Created Table emp");

        String createDeptTable =
                "CREATE TABLE dept (" +
                "did NUMERIC(2, 0) PRIMARY KEY," +
                "dname VARCHAR(20)," +
                "budget NUMERIC(10, 2)," +
                "managerid NUMERIC(9,0) REFERENCES emp(eid))";
        stmt.executeUpdate(createDeptTable);
        System.out.println("Created Table dept");

        String createWorksTable =
                "CREATE TABLE works (" +
                "eid NUMERIC(9,0) REFERENCES emp," +
                "did NUMERIC(2,0) REFERENCES dept," +
                "pct_time NUMERIC(3,0)," +
                "PRIMARY KEY(eid, did))";
        stmt.executeUpdate(createWorksTable);
        System.out.println("Created Table works");

        connection.commit();
        System.out.println("Commited a Transaction");

        // (b) <b> populate emp </b>:
        /*
        should contain all the code necessary (including variable declarations) to perform
        <b> a single transaction </b> to populate the <i> emp </i> table with the tuples given in the file
        <b> emp.csv </b> (available on Learn).
        The file will be given as a command-line argument to the class (args[5]). Each tuple in the file
        must be inserted as a separate insert statement (i.e. your SQL insert statement must use placeholders
        in order to perform batch insertions). Your code <b> must use </b> a PreparedStatement object.
         */
        File file = new File(args[5]);

        String populateEmp = "INSERT INTO emp VALUES(?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(populateEmp);

        BufferedReader br = new BufferedReader(new FileReader(file));
        String tuple = "";
        String comma = ",";
        while ((tuple = br.readLine()) != null){
            String[] attributes = tuple.split(comma);
            pstmt.clearParameters();
            pstmt.setInt(1, Integer.parseInt(attributes[0])); // eid
            pstmt.setString(2, attributes[1]); // ename
            pstmt.setInt(3, Integer.parseInt(attributes[2])); // age
            pstmt.setInt(4, Integer.parseInt(attributes[3])); // salary
            pstmt.addBatch();
        }
        br.close();
        pstmt.executeBatch();
        pstmt.close();
        connection.commit();


        // (c)

        // (d)
//        int salary = Integer.parseInt(args[6]);

        // closing database connection
        connection.close();
    }
}
