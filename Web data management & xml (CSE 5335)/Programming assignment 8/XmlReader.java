import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlReader {

	public static void main(String[] args) throws Exception {
		new QueryHandler("cs.xml");
	}
}

class QueryHandler extends DefaultHandler {

	//Boolean variables
    private boolean facultyBool = false;
	private boolean staffBool = false;
	private boolean undergradBool = false;
	private boolean gradBool = false;
	private boolean addressBool = false;
	private boolean firstnameBool = false;
	private boolean lastnameBool = false;
	//String Variables for tag
	private String facultyTag = "";
	private String staffTag = "";
	private String undergradTag = "";
	private String gradTag = "";
	private String addressTag = "";
	private String firstnameTag = "";
	private String lastnameTag = "";
	//List variables
	List <grad> gradList;
	List <undergrad> undergradList;
	List <faculty> facultyList;
	List <staff> staffList;
	List <address> addressList;
	//Class objects
	grad Grad;
	undergrad UnderGrad;
	faculty Faculty;
	staff Staff;
	address Address;

    public QueryHandler ( String file ) {
		super();
		gradList = new ArrayList<grad>();
		undergradList = new ArrayList<undergrad>();
		facultyList = new ArrayList<faculty>();
		staffList = new ArrayList<staff>();
		addressList = new ArrayList<address>();
		try {
		    SAXParserFactory factory = SAXParserFactory.newInstance();
		    factory.setValidating(false);
		    factory.setNamespaceAware(false);
		    XMLReader xmlReader = factory.newSAXParser().getXMLReader();
		    xmlReader.setContentHandler(this);
		    xmlReader.parse(file);
		    insertIntoXml();
		} catch (Exception e) {
		    throw new Error(e);
		}
    }
    
    private void insertIntoXml() throws ClassNotFoundException {
    	Class.forName("org.sqlite.JDBC");
    	Connection sqlConnection = null;
    	try
    	{
    		sqlConnection = DriverManager.getConnection("Vatsal");
    		Statement sqlStatement = sqlConnection.createStatement();
    		sqlStatement.executeUpdate("insert into department values(null,'computer science')");
    		for (grad temp : gradList) {
    			String sqlQuery1 = "insert into grad values(null,'" + temp.getFirstname() + "','" + temp.getLastname() + "','" + temp.getPhone() + "','" + temp.getEmail() + "','"
    					//+temp.getCity() + "','" + temp.getState() + "','" + temp.getZip() + "','" + temp.getOffice() + "','" + temp.getUrl() + "','"
    					+ temp.getGpa() + "')";
    			sqlStatement.addBatch(sqlQuery1);	                   
    		}
    		for (undergrad temp : undergradList) {
    			String sqlQuery2 = "insert into undergrad values (null,'" + temp.getLastname() + "','" + temp.getFirstname() + "','" + temp.getPhone() + "','" + temp.getEmail() + "','"
    					//+ temp.getCity() + "','" + temp.getState() + "','" + temp.getZip() + "','"
    					+ temp.getGpa() + "')";
    			sqlStatement.addBatch(sqlQuery2);	                   
    		}
    		for (faculty temp : facultyList) {
    			String sqlQuery3="insert into faculty values (null,'" + temp.getFirstname() + "','" + temp.getLastname() + "','" + temp.getPhone() + "','" + temp.getEmail() + "','" + temp.getOffice() + "')";
    			sqlStatement.addBatch(sqlQuery3);
    		}
    		for (staff temp : staffList) {
    			String sqlQuery4="insert into staff values (null,'" + temp.getLastname() + "','" + temp.getFirstname() + "','" + temp.getPhone() + "','" + temp.getEmail() + "','" + temp.getOffice() + "')";
    			sqlStatement.addBatch(sqlQuery4);
    		}
    		sqlStatement.executeBatch();
    	} catch(SQLException e) {
    		System.err.println(e.getMessage());
    	} finally {
    		try {
    			if(sqlConnection != null)
    				sqlConnection.close();
    		} catch(SQLException e) {
    			//Connection close fail
    			System.err.println(e);
    		}
    	}
    }

    public class faculty {
    	int facultyId;
    	String lastname;
    	String firstname;
    	int phone;
    	String email;
    	int office;
    	int departmentId;
    	public int getFacultyId() {
    		return facultyId;
    	}
    	public void setFacultyId(int facultyId) {
    		this.facultyId = facultyId;
    	}
    	public String getLastname() {
    		return lastname;
    	}
    	public void setLastname(String lastname) {
    		this.lastname = lastname;
    	}
    	public String getFirstname() {
    		return firstname;
    	}
    	public void setFirstname(String firstname) {
    		this.firstname = firstname;
    	}
    	public int getPhone() {
    		return phone;
    	}
    	public void setPhone(int phone) {
    		this.phone = phone;
    	}
    	public String getEmail() {
    		return email;
    	}
    	public void setEmail(String email) {
    		this.email = email;
    	}
    	public int getOffice() {
    		return office;
    	}
    	public void setOffice(int office) {
    		this.office = office;
    	}
    	public int getDepartment_ID() {
    		return departmentId;
    	}
    	public void setDepartmentId(int departmentId) {
    		this.departmentId = departmentId;
    	}
    }

    public class staff {
    	int staffId;
    	String lastname;
    	String firstname;
    	int phone;
    	String email;
    	int office;
    	int departmentId;
    	public int getStaffId() {
    		return staffId;
    	}
    	public void setStaffId(int staffId) {
    		this.staffId = staffId;
    	}
    	public String getLastname() {
    		return lastname;
    	}
    	public void setLastname(String lastname) {
    		this.lastname = lastname;
    	}
    	public String getFirstname() {
    		return firstname;
    	}
    	public void setFirstname(String firstname) {
    		this.firstname = firstname;
    	}
    	public int getPhone() {
    		return phone;
    	}
    	public void setPhone(int phone) {
    		this.phone = phone;
    	}
    	public String getEmail() {
    		return email;
    	}
    	public void setEmail(String email) {
    		this.email = email;
    	}
    	public int getOffice() {
    		return office;
    	}
    	public void setOffice(int office) {
    		this.office = office;
    	}
    	public int getDepartment_ID() {
    		return departmentId;
    	}
    	public void setDepartmentId(int departmentId) {
    		this.departmentId = departmentId;
    	}
    }

    public class undergrad {
    	int undergradId;
    	String lastname;
    	String firstname;
    	int phone;
    	String email;
    	int addressId;
    	float gpa;
    	int departmentId;
    	public int getUndergradId() {
    		return undergradId;
    	}
    	public void setUndergradId(int undergradId) {
    		this.undergradId = undergradId;
    	}
    	public String getLastname() {
    		return lastname;
    	}
    	public void setLastname(String lastname) {
    		this.lastname = lastname;
    	}
    	public String getFirstname() {
    		return firstname;
    	}
    	public void setFirstname(String firstname) {
    		this.firstname = firstname;
    	}
    	public int getPhone() {
    		return phone;
    	}
    	public void setPhone(int phone) {
    		this.phone = phone;
    	}
    	public String getEmail() {
    		return email;
    	}
    	public void setEmail(String email) {
    		this.email = email;
    	}
    	public int getAddressId() {
    		return addressId;
    	}
    	public void setAddressId(int addressId) {
    		this.addressId = addressId;
    	}
    	public float getGpa() {
    		return gpa;
    	}
    	public void setGpa(float gpa) {
    		this.gpa = gpa;
    	}
    	public int getDepartmentId() {
    		return departmentId;
    	}
    	public void setDepartmentId(int departmentId) {
    		this.departmentId = departmentId;
    	}
    }

    public class grad {
    	int gradId;
    	String lastname;
    	String firstname;
    	int phone;
    	String email;
    	int addressId;
    	float gpa;
    	int departmentId;
    	public int getGradId() {
    		return gradId;
    	}
    	public void setGradId(int gradId) {
    		this.gradId = gradId;
    	}
    	public String getLastname() {
    		return lastname;
    	}
    	public void setLastname(String lastname) {
    		this.lastname = lastname;
    	}
    	public String getFirstname() {
    		return firstname;
    	}
    	public void setFirstname(String firstname) {
    		this.firstname = firstname;
    	}
    	public int getPhone() {
    		return phone;
    	}
    	public void setPhone(int phone) {
    		this.phone = phone;
    	}
    	public String getEmail() {
    		return email;
    	}
    	public void setEmail(String email) {
    		this.email = email;
    	}
    	public int getAddressId() {
    		return addressId;
    	}
    	public void setAddressId(int addressId) {
    		this.addressId = addressId;
    	}
    	public float getGpa() {
    		return gpa;
    	}
    	public void setGpa(float gpa) {
    		this.gpa = gpa;
    	}
    	public int getDepartmentId() {
    		return departmentId;
    	}
    	public void setDepartmentId(int departmentId) {
    		this.departmentId = departmentId;
    	}
    }

    public class address {
    	int addressId;
    	String city;
    	String stateAbbr;
    	int zipcode;
		public int getAddressId() {
			return addressId;
		}
		public void setAddressId(int addressId) {
			this.addressId = addressId;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getStateAbbr() {
			return stateAbbr;
		}
		public void setStateAbbr(String stateAbbr) {
			this.stateAbbr = stateAbbr;
		}
		public int getZipcode() {
			return zipcode;
		}
		public void setZipcode(int zipcode) {
			this.zipcode = zipcode;
		}
    }
    
	public void startDocument() throws SAXException {
    }

    public void startElement ( String uri, String name, String tag, Attributes atts ) throws SAXException {
    	if (tag.equals("gradstudent")) {
    		Grad = new grad();
    		gradBool = true;
    	}
    	else if (tag.equals("undergradstudent")) {
    		UnderGrad = new undergrad();
    		undergradBool = true;
    	}
		else if (tag.equals("faculty")) {
			Faculty = new faculty();
			facultyBool = true;
		}
		else if (tag.equals("staff")) {
			Staff = new staff();
			staffBool = true;
		}
		else if (tag.equals("address")) {
			Address = new address();
			addressBool = true;
		}
		else if (tag.equals("firstname"))
			firstnameBool = true;
		else if (tag.equals("lastname"))
			lastnameBool = true;
    }

    public void characters ( char text[], int start, int length ) {
    	if (gradBool == true) {
    		gradTag = new String(text,start,length);
    		if(lastnameBool == true) {
    			Grad.setLastname(gradTag);
    		}
    	}
    	else if (undergradBool == true)
    		undergradTag = new String(text,start,length);
    	else if (facultyBool == true)
    		facultyTag = new String(text,start,length);
		else if (staffBool == true)
			staffTag = new String(text,start,length);
    }
    
    public void endElement ( String uri, String name, String tag ) {
    	if (tag.equals("gradstudent")) {
    		gradList.add(Grad);
    		gradBool = false;
    	}
    	else if (tag.equals("undergradstudent")) {
    		undergradList.add(UnderGrad);
    		undergradBool = false;
    	}
    	else if (tag.equals("faculty")) {
    		facultyList.add(Faculty);
    		facultyBool = false;
    	}
		else if (tag.equals("staff")) {
			staffList.add(Staff);
			staffBool = false;
		}
		else if (tag.equals("address")) {
			addressList.add(Address);
			addressBool = false;
		}
    }
    
    public void endDocument() throws SAXException {
    	
    }
}