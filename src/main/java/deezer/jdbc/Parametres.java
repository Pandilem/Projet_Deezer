package deezer.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

public class Parametres {
	private static String password = "34apviSw*";
    private static String user = "root";
    private static String url = "jdbc:mysql://localhost:3306/deezer?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; // 

    public static String getPassword() {
        return password;
    }
    public static String getUrl() {
        return url;
    }
    public static String getUser() {
        return user;
}
    public static Connection getConnexion()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Parametres.getUrl(), Parametres.getUser(),
					Parametres.getPassword());

			

		} catch (Exception e) {
			System.out.println("Erreur " + e);
		}
		return con;
	
}
    public static void afficherTable(String tableName) throws SQLException{
		try {
			
			
			Statement stmt = Parametres.getConnexion().createStatement();
			String query ="SELECT * from "+tableName;
			
			ResultSet rs = stmt.executeQuery(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = rs.getString(i);
					String columnName = rsmd.getColumnName(i);
					System.out.print(MessageFormat.format("<{0}:{1}>\t\t", columnName, columnValue));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Erreur " + e);
		}
	}

}
