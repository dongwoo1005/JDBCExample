import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

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

        Connection connection = DriverManager.getConnection("jdbc:postgresql://"
                + host + ":" + port + "/" + databaseName, username, password);

        // setting auto commit to false
        connection.setAutoCommit(false);

        // (a) <b>create tables:</b>
        /*
        should contain all the code necessary (including variable declarations) to perform <b>a single transaction</b>
        to define the tables <i> emp, dept and works </i>.
        The SQL statements for creating these tables are listed below.
        Your code <b> must use </b> a <i> Statement </i> object and make 3 calls to the <i> executeUpdate </i> method.
         */
        connection.getTransactionIsolation();

        connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);



        // (b)
        File file = new File(args[5]);

        // (c)

        // (d)
        int salary = Integer.parseInt(args[6]);

        // closing database connection
        connection.close();
    }
}
