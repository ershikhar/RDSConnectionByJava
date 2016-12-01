package mysqlpkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class insert {

    private String jdbcDriverStr;
    private String jdbcURL;
 
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    enum TestTableColumns{
        id,TEXT;
    }
 
    public void MySQLJava(String jdbcDriverStr, String jdbcURL){
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }
 
    public void readData() throws Exception {
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from javaTestDB.test_table;");
            getResultSet(resultSet);
            preparedStatement = connection.prepareStatement("insert into javaTestDB.test_table values (default,?)");
            preparedStatement.setString(1,"insert test from java");
            preparedStatement.executeUpdate();
        }finally{
            close();
        }
    }
 
    private void getResultSet(ResultSet resultSet) throws Exception {
        while(resultSet.next()){
            Integer id = resultSet.getInt(TestTableColumns.id.toString());
            String text = resultSet.getString(TestTableColumns.TEXT.toString());
            System.out.println("id: "+id);
            System.out.println("text: "+text);
        }
    }
 
    private void close(){
        try {
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        } catch(Exception e){}
    }

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://autotestdata.cmbns0deulin.us-east-1.rds.amazonaws.com:3306/AutoDB","ShikharAdmin","skisgr8est");
        statement = connection.createStatement();
//        resultSet = statement.executeQuery("select * from AutoDB.MASTER_EXECUTION_LOG;");
//        getResultSet(resultSet);
        preparedStatement = connection.prepareStatement("insert into MASTER_EXECUTION_LOG VALUES(2,'SHIKHAR','D',1,'SHIKHAR','D','D','D','D','D','D','D')");
//        preparedStatement.setString(1,"insert test from java");
        preparedStatement.executeUpdate();
		 
		 		
		
	}

}
