package ca.myjava.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CredentialsSQL.SqlInfo;

public class QueryTablePreparedStm2 {
	public static void main(String[] args) {

		try {
			Class.forName(SqlInfo.DRIVER_CLASS_MYSQL);

			System.out.println("Driver Connected");

			Connection connection = DriverManager.getConnection(SqlInfo.URL, SqlInfo.U, SqlInfo.P);
			System.out.println("Connection Created");

			PreparedStatement PS = connection
					.prepareStatement("SELECT * FROM COUNTRY WHERE lifeexpectancy BETWEEN 10 AND 80");
			System.out.println("Prepared stm Created");

			ResultSet rs = PS.executeQuery();
			System.out.println("RS Created");

			while (rs.next()) {
				System.out.println("Country: " + rs.getString(1) + " Life Exp: " + rs.getInt(2));
			}

		} catch (Exception ae) {
			System.out.println("Invalid SQL command: " + ae);
		}

	}
}
