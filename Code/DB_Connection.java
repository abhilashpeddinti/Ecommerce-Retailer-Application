
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class DB_Connection {
	public String dbDriver;
	public String dbHost;
	public String dbName;
	public String dbUser;
	public String dbPwd;
	
	public void getDbInfo() {
		Properties pro=new Properties();
		InputStream input=null;
		try {
			input= DB_Connection.class.getClassLoader().getResourceAsStream("Database.properties");
			pro.load(input);
			dbDriver=pro.getProperty("DB_DRIVER");
			dbHost=pro.getProperty("DB_HOST");
			dbPwd=pro.getProperty("DB_PWD");
			dbUser=pro.getProperty("DB_USER");
			dbName=pro.getProperty("DB_NAME");
			if (dbPwd.isEmpty() || dbPwd==null) {
				dbPwd="";			}
		
	
		input.close();
		input=null;
		pro=null;
		}
		catch(Exception ex) {ex.printStackTrace();}
		//return null;
	}
		public static void main (String[] args) {
			DB_Connection obj_db_connection=new DB_Connection();
			Connection connection=null;
			
			connection=obj_db_connection.get_connection();
			}
			public Connection get_connection() {
				Connection connection=null;
				String dbHostUrl =null;
				try {
					getDbInfo();
					dbHostUrl=dbHost+ "/" +dbName;
					//input= DB_Connection.class.getClassLoader().getResourceAsStream("");
					//Class.forName(dbHost);
					Class.forName(dbDriver).newInstance();
					connection=DriverManager.getConnection(dbHostUrl,dbUser,dbPwd);
					//connection=DriverManager.getConnection("jdbc:mysql://10.182.112.87/minions","wbadmin","aongpg1");
					
			}
				catch (Exception e)
				{
					System.out.println(e);
				}
				return connection;
		}
}
