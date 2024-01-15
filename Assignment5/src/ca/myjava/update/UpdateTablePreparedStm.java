package ca.myjava.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import CredentialsSQL.SqlInfo;

public class UpdateTablePreparedStm {

	public static void main(String[] args) {

		try {
			Class.forName(SqlInfo.DRIVER_CLASS_MYSQL);

			System.out.println("Driver Connected");

			Connection connection = DriverManager.getConnection(SqlInfo.URL, SqlInfo.U, SqlInfo.P);
			System.out.println("Connection Created");

			// insert
			PreparedStatement sqlInsert = connection.prepareStatement(
					"INSERT INTO country (countryname, lifeexpectancy)" + "VALUES\n" + "    ('Brazil', 77)\n");
			System.out.println("SQL1");

			// update
			PreparedStatement sqlUpdate = connection
					.prepareStatement("UPDATE country\n" + "SET lifeexpectancy = 100\n" + "WHERE countryname = 'USA'");
			System.out.println("SQL2");

			// delete
			PreparedStatement sqlDelete = connection
					.prepareStatement("DELETE FROM country\n" + "WHERE countryname = 'Canada'");
			System.out.println("SQL3");

			// show table
			PreparedStatement sqlShowTable = connection.prepareStatement("SELECT * FROM COUNTRY ");
			System.out.println("SQL4");

			sqlInsert.executeQuery();
			System.out.println("Inserted");
		    sqlUpdate.executeQuery();
			System.out.println("Updated");
			sqlDelete.executeQuery();
			System.out.println("Deleted");

			ResultSet rs = sqlShowTable.executeQuery();
			System.out.println("RS Created");

			while (rs.next()) {
				System.out.println("New Record \n Country " + rs.getString(1) + " Life Exp: " + rs.getInt(2));
			}


		} catch (Exception ae) {
			System.out.println(ae);
		}

	}
}
