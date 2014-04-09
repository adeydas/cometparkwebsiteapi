package ws.abhis.cometparkapi.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConnect {
	
	private static final Logger log = LogManager.getLogger(DatabaseConnect.class.getName());
	
	private Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(DatabaseConnectionConfiguration.CONNECTION_STRING);
			return connect;
		} catch (ClassNotFoundException e) {
			log.error(e);
			return null;
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
	}
	
	private void closeConnection(Connection connect) {
		try {
			connect.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
	public List runSqlQuery(String query) {
		Connection conn = createConnection();
		try {
			Statement s = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = null;

			int ddlResponse = 9999;
			
			if (query.toLowerCase().contains("insert")
					|| query.toLowerCase().contains("update")
					|| query.toLowerCase().contains("drop")
					|| query.toLowerCase().contains("create")
					|| query.toLowerCase().contains("alter")
					|| query.toLowerCase().contains("delete")) {
				ddlResponse = stmt.executeUpdate();

			} else {
				rs = stmt.executeQuery();
			}

			List<List<String>> result = new ArrayList<>();

			if (rs != null && ddlResponse == 9999) {

				int numcols = rs.getMetaData().getColumnCount();

				while (rs.next()) {
					List<String> row = new ArrayList<>(numcols);
					int i = 1;
					while (i <= numcols) {

						row.add(rs.getString(i++));
					}
					result.add(row);
				}
			} else if (ddlResponse != 9999) {
				List<String> ls = new ArrayList<String>();
				ls.add(Integer.toString(ddlResponse));
				result.add(ls);
			} else {
				result = null;
			}
			
			closeConnection(conn);
			return result;
			
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
	}
}
