package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제1) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다 Lprod_id값이 큰 자료들을 출력하시오.
public class JdbcTest02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Lprod_id값 입력 >> ");
		//int id = scan.nextInt();
		String id = scan.nextLine();
		
		// DB작업에 필요한 변수 선언
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"sem", 
					"java");
			/*
			String sql = "select * from lprod where lprod_id > " + id;
			System.out.println("실행한 SQL문 : " + sql);
			System.out.println();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			*/
			String sql = "select * from lprod where lprod_id > ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//if(id < rs.getInt("LPROD_ID")) {
				System.out.println("Lprod_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("Lprod_GU : " + rs.getString("LPROD_GU"));
				System.out.println("Lprod_NM : " + rs.getString("LPROD_NM"));
				System.out.println("-------------------------------------");
				//}
			}
			System.out.println("출력 끝...");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close();  }catch(SQLException e) {}
			if(stmt!=null) try { stmt.close();  }catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close();  }catch(SQLException e) {}
			if(conn!=null) try { conn.close();  }catch(SQLException e) {}
		}
		

	}

}








