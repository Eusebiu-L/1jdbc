package ro.emanuel.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Main {

	public static void main(String[] args) throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user",  "root");
		connectionProps.put("password", "");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tema1jdbc",connectionProps);
		
		//insert cars
		String sqlInsertCars = "Insert into cars (brand, model,fabrication) values (?,?,?)";
		PreparedStatement psInsertCars = conn.prepareStatement(sqlInsertCars);
		psInsertCars.setString(1,"Audi");
		psInsertCars.setString(2,"A4");
		psInsertCars.setDouble(3, 2001);
		psInsertCars.executeUpdate();
		
		//READ CARS
		Statement stmtCars = conn.createStatement();
		ResultSet rsCars = stmtCars.executeQuery("SELECT *from cars");
		while(rsCars.next()) {
			int id = rsCars.getInt("id");
				String brand = rsCars.getString("brand");
				String model = rsCars.getString("model");
				double fabrication = rsCars.getDouble("fabrication");
				System.out.println(id + "," + brand + "," + model + "," + fabrication);
		}
		//UPDATE
		String updateCars = "UPDATE cars set brand=? where model=?"; 
		PreparedStatement psUpdateCars = conn.prepareStatement(updateCars);
		psUpdateCars.setString(1,"BMW" );
		psUpdateCars.setString(2, "seria2");
		int rowsUpdateCars = psUpdateCars.executeUpdate();
		
		//DELETE
		String deleteCars = "DELETE from cars where fabrication= ?";
		PreparedStatement psDeleteCars = conn.prepareStatement(deleteCars);
		psDeleteCars.setDouble(1, 2001);
		int rowsDeletedCars = psDeleteCars.executeUpdate();
		
		
		
		//insert engines
		String sqlInsertEngines = "Insert into engines (engine_code,cylinder, horse_power,wheel_drive) values (?,?,?,?)";
		PreparedStatement psInsertEngines = conn.prepareStatement(sqlInsertEngines);
		psInsertCars.setString(1,"M57");
		psInsertCars.setInt(2,1987);
		psInsertCars.setInt(3, 150);
		psInsertCars.setString(4, "rear");
		psInsertCars.executeUpdate();
		
		//READ engines
		Statement stmtEngines = conn.createStatement();
		ResultSet rsEngines = stmtEngines.executeQuery("SELECT *from engines");
		while(rsEngines.next()) {
				String engine_code = rsEngines.getString("engine_code");
				int cylinder = rsEngines.getInt("cylinder");
				int horse_power = rsEngines.getInt("horse_power");
				String wheel_drive = rsEngines.getString("wheel_drive");

				System.out.println(engine_code + "," + cylinder + "," + horse_power + "," + wheel_drive);
		}
		//UPDATE
		String updateEngines = "UPDATE engines set wheel_drive=? where horse_power=?"; 
		PreparedStatement psUpdateEngines = conn.prepareStatement(updateEngines);
		psUpdateEngines.setString(1,"rear" );
		psUpdateCars.setInt(2, 150);
		int rowsUpdateEngines = psUpdateEngines.executeUpdate();
		
		//DELETE
		String deleteEngines = "DELETE from engines where engine_code= ?";
		PreparedStatement psDeleteEngines = conn.prepareStatement(deleteEngines);
		psDeleteEngines.setString(1, "M57");
		int rowsDeletedEngines = psDeleteEngines.executeUpdate();
		
		conn.close();
	}
	
	
}
