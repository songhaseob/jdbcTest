package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제2) lprod_id값 2개를 차례로 입력 받아서 두 값 중 작은값부터 큰값사이의 자료들을 출력하시오.
public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 Lprod_Id값 입력 >>");
		int num1 = scan.nextInt();
		
		System.out.print("두번째 Lprod_Id값 입력 >>");
		int num2 = scan.nextInt();
		
		// 입력받은 두 값 중 큰값과 작은 값 비교
		int max, min;
		if(num1>num2) {
			max = num1;
			min = num2;
		}else {
			max = num2;
			min = num1;
		}
		
//		max = Math.max(num1, num2);
//		min = Math.min(num1, num2);
		
		// DB작업에 필요한 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"sem", 
					"java");
			
			String sql = "select * from lprod "
					//+ " where lprod_id >= " + min + " and lprod_id <= " + max;
					+ " where lprod_id  between " + min + " and " + max;
			
			
			System.out.println("실행한 SQL문 : " + sql);
			System.out.println();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("Lprod_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("Lprod_GU : " + rs.getString("LPROD_GU"));
				System.out.println("Lprod_NM : " + rs.getString("LPROD_NM"));
				System.out.println("-------------------------------------");
			}
			System.out.println("출력 끝...");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close();  }catch(SQLException e) {}
			if(stmt!=null) try { stmt.close();  }catch(SQLException e) {}
			if(conn!=null) try { conn.close();  }catch(SQLException e) {}
		}

	}

}





