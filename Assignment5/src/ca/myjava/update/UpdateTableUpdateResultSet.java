package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import CredentialsSQL.SqlInfo;

public class UpdateTableUpdateResultSet {
	
	public static void main(String[] args) {

		try {
			Class.forName(SqlInfo.DRIVER_CLASS_MYSQL);

			System.out.println("Driver Connected");

			Connection connection = DriverManager.getConnection(SqlInfo.URL, SqlInfo.U, SqlInfo.P);
			System.out.println("Connection Created");

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("INSERT INTO country (countryname, lifeexpectancy)" + "VALUES\n"
				+ "    ('Canada', 44)\n");
			System.out.println("Inserted");
			ResultSet rs1 = stmt.executeQuery("UPDATE country\n" + "SET lifeexpectancy = 33\n" + "WHERE countryname = 'Canada'");
			System.out.println("Updated");
			ResultSet rs2 = stmt.executeQuery("DELETE FROM country\n" + "WHERE countryname = 'okay'");
			System.out.println("Deleted");

			ResultSet rs3 = stmt.executeQuery("SELECT * FROM COUNTRY ");
			System.out.println("RS Created");
			System.out.println("New Record");
			while (rs3.next()) {
				System.out.println(" \n Country " + rs3.getString(1) + " Life Exp: " + rs3.getInt(2));
			}

		} catch (Exception ae) {
			System.out.println(ae);
		}

	}


}
