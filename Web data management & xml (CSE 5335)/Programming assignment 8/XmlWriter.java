import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class XmlWriter {

	public static void main(String args[]) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection sqlConnection = null;
		try {
			sqlConnection = DriverManager.getConnection("jdbc:sqlite:Vatsal");

			Statement sqlStatement1 = sqlConnection.createStatement();
			Statement sqlStatement2 = sqlConnection.createStatement();
			Statement sqlStatement3 = sqlConnection.createStatement();
			Statement sqlStatement4 = sqlConnection.createStatement();
			Statement sqlStatement5 = sqlConnection.createStatement();

			ResultSet sqlQuery1 = sqlStatement1.executeQuery("select * from grad;");
			ResultSet sqlQuery2 = sqlStatement2.executeQuery("select * from staff;");
			ResultSet sqlQuery3 = sqlStatement3.executeQuery("select * from faculty;");
			ResultSet sqlQuery4 = sqlStatement4.executeQuery("select * from undergrad;");
			ResultSet sqlQuery5 = sqlStatement5.executeQuery("select * from department;");

			while(sqlQuery1.next()) {
				String lastName = sqlQuery1.getString("lastname");
				String firstName = sqlQuery1.getString("firstname");
				int phoneNo = sqlQuery1.getInt("phone");
				String emailId = sqlQuery1.getString("email");
				String cityName = sqlQuery1.getString("City");
				String stateAbbr = sqlQuery1.getString("State");
				int zipCode = sqlQuery1.getInt("Zip");
				String office = sqlQuery1.getString("Office");
				String url = sqlQuery1.getString("Url");
				String gpa = sqlQuery1.getString("GPA");
				System.out.println("<gradstudent>");
				System.out.println("<name>");
				System.out.println("\t<lastname>"+lastName+"</lastname>");
				System.out.println("\t<firstname>"+firstName+"</firststname>");
				System.out.println("</name>");
				System.out.println("<phone>"+phoneNo+"</phone>");
				System.out.println("<email>"+emailId+"</email>");
				System.out.println("<address>");
				System.out.println("\t<city>"+cityName+"</city>");
				System.out.println("\t<state>"+stateAbbr+"</state>");
				System.out.println("\t<zip>"+zipCode+"</zip>");
				System.out.println("</address>");
				System.out.println("<office>"+office+"</office>");
				System.out.println("<url>"+url+"</url>");
				System.out.println("<gpa>"+gpa+"</gpa>");
				System.out.println("</gradstudent>");
			}
			while(sqlQuery2.next()) {
				String lastName = sqlQuery2.getString("lastname");
				String firstName = sqlQuery2.getString("firstname");
				int phoneNo = sqlQuery2.getInt("phone");
				String emailId = sqlQuery2.getString("email");
				int office = sqlQuery2.getInt("office");
				System.out.println("<staff>");
				System.out.println("<name>");
				System.out.println("\t<lastname>"+lastName+"</lastname>");
				System.out.println("\t<firstname>"+firstName+"</firststname>");
				System.out.println("</name>");
				System.out.println("<phone>"+phoneNo+"</phone>");
				System.out.println("<email>"+emailId+"</email>");
				System.out.println("<office>"+office+"</office>");
				System.out.println("</staff>");
			}
			while(sqlQuery3.next()) {
				String lastName = sqlQuery3.getString("lastname");
				String firstName = sqlQuery3.getString("firstname");
				int phoneNo = sqlQuery3.getInt("phone");
				String emailId = sqlQuery3.getString("email");
				int office = sqlQuery3.getInt("office");
				System.out.println("<faculty>");
				System.out.println("<name>");
				System.out.println("\t<lastname>"+lastName+"</lastname>");
				System.out.println("\t<firstname>"+firstName+"</firststname>");
				System.out.println("</name>");
				System.out.println("<phone>"+phoneNo+"</phone>");
				System.out.println("<email>"+emailId+"</email>");
				System.out.println("<office>"+office+"</office>");
				System.out.println("</faculty>");
			}
			while(sqlQuery4.next()) {
				String lastName = sqlQuery4.getString("lastname");
				String firstName = sqlQuery4.getString("firstname");
				int phoneNo = sqlQuery4.getInt("phone");
				String emailId = sqlQuery4.getString("email");
				String cityName = sqlQuery4.getString("City");
				String stateAbbr = sqlQuery4.getString("State");
				int zipCode = sqlQuery4.getInt("Zip");
				String gpa = sqlQuery4.getString("GPA");
				System.out.println("<undergradstudent>");
				System.out.println("<name>");
				System.out.println("\t<lastname>"+lastName+"</lastname>");
				System.out.println("\t<firstname>"+firstName+"</firststname>");
				System.out.println("</name>");
				System.out.println("<phone>"+phoneNo+"</phone>");
				System.out.println("<email>"+emailId+"</email>");
				System.out.println("<address>");
				System.out.println("\t<city>"+cityName+"</city>");
				System.out.println("\t<state>"+stateAbbr+"</state>");
				System.out.println("\t<zip>"+zipCode+"</zip>");
				System.out.println("</address>");
				System.out.println("<gpa>"+gpa+"</gpa>");
				System.out.println("</undergradstudent>");
			}
			while(sqlQuery5.next()) {
				String deptName=sqlQuery5.getString("Department_Name");
				System.out.println("<department>");
				System.out.println("\t<deptname>"+deptName+"</deptname>");
			}
			System.out.println("</department>");
			sqlQuery1.close();
			sqlQuery2.close();
			sqlQuery3.close();
			sqlQuery4.close();
			sqlQuery5.close();
			//Queries closed
			sqlStatement1.close();
			sqlStatement2.close();
			sqlStatement3.close();
			sqlStatement4.close();
			sqlStatement5.close();
			//Statements closed
			sqlConnection.close();
			//Connection Closed
		} catch(SQLException e) {
			//If error is "out of memory",it means no db file found
			System.err.println(e.getMessage());
		} finally {
			try {
				if(sqlConnection != null)
					sqlConnection.close();
			} catch(SQLException e) {
				System.err.println(e);
			}
		}
	}
}