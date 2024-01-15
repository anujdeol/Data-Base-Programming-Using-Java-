package ca.myjava.unknown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import CredentialsSQL.SqlInfo;

public class AnySQL {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName(SqlInfo.DRIVER_CLASS_MYSQL);

			System.out.println("Driver Connected");

			Connection connection = DriverManager.getConnection(SqlInfo.URL, SqlInfo.U, SqlInfo.P);
			System.out.println("Connection Created");
			
			Statement stmt = connection.createStatement();

		//String sql = "SELECT * FROM COUNTRY WHERE lifeexpectancy BETWEEN 10 AND 80";
			System.out.println("Please enter any SQL command you want to execute (without ; ath the end)");
			String sqlcmd = sc.nextLine();
			//System.out.println(sqlcmd);
			

			ResultSet rs = stmt.executeQuery(sqlcmd);
			System.out.println("RS Created");

			while (rs.next()) {
			    System.out.println("Country " + rs.getString(1) + " Life Exp: " + rs.getInt(2));
			}


		} catch (Exception ae) {
			System.out.println("Invalid SQL command" +ae);
		}

	}


}
