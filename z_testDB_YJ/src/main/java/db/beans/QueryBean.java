package db.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.Query;

	public class QueryBean
{
		Connection conn;
		Statement stmt;
		ResultSet rs;
	
	
	public QueryBean() {
		conn=null;
		stmt=null;
		rs=null;
		
	}

	public void getConnection() {
		try {
			conn=DBConnection.getConnection();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			stmt=conn.createStatement();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		if(stmt !=null) {
			try {
				stmt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		
		}
		if(conn !=null) {
			try {
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
// 1. select
	public ArrayList selectUserInfo(String user_id) throws Exception {
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("SELECT");
		sb.append("   U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME ");
		sb.append(" FROM ");
		sb.append("   USER_INFO_SAMPLE");
		sb.append(" WHERE ");
		sb.append("   U_ID LIKE '%"+user_id+"%' ");
		sb.append(" ORDER BY");
		sb.append("     WRITE_TIME");
		
		
		rs = stmt.executeQuery(sb.toString());
		
		ArrayList res = new ArrayList();
		while(rs.next()) {

			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
			
		}
		
		System.out.println(sb.toString());
		return res;
		
	}
	
	
//	2.insert
	
	public int insertUserInfo(String user_id, String user_name, String user_phone, String user_grade)
	{
		String query = "";
		PreparedStatement pstmt=null;
		
		int result = 0;
		
		query = "insert into";
		query+= "USER_INFO_SAMPLE (U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME)";
		query+= "values";
		query+= "(?,?,?,?, sysdayte)";
		
		System.out.println(query);
		
		try
		{
			pstmt=conn.prepareStatement(query);
			
			pstmt.clearParameters();
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_name);
			pstmt.setString(3, user_phone);
			pstmt.setString(4, user_grade);
			
			result=pstmt.executeUpdate();
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return result;
		
	}
	
	//3. uptdate
	
	public int updateUserInfo(String user_id, String user_name, String user_phone, String user_grade)
	{
		StringBuffer query1= new StringBuffer();
		PreparedStatement pstmt=null;
		
		
		int result = 0;
		
		query1.append(" update ");
		query1.append("        USER_INFO_SAMPLE ");
		query1.append(" set ");
//		query1.append("        U_NAME = '"+user_name+"', U_PHONE = '"+user_phone+"', U_GRADE = "+user_grade+", WRITE_TIME = sysdate ");
		query1.append("        U_NAME = ?, U_PHONE = ?, U_GRADE = ?, WRITE_TIME = SYSDATE ");
		query1.append(" where ");
//		query1.append("        U_ID = '"+user_id+"' ");
		query1.append("        U_ID = ? ");
		
		
	System.out.println(query1.toString());
		
		try
		{
			pstmt=conn.prepareStatement(query1.toString());
			
			pstmt.clearParameters();
			
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_phone);
			pstmt.setString(3, user_grade);
			pstmt.setString(4, user_id);
			
			result=pstmt.executeUpdate();
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return result;
		
	}
		//4.delete
		public int deleteUserInfo(String user_id)//�떆媛꾨뭡怨� �떎 諛쏆븘�샂, db�떆媛꾩쓣 �벐湲곗쐞�빐�꽌
		{
			StringBuffer query1 = new StringBuffer();
			
			//String query = "";
			PreparedStatement pstmt = null;
			
			int result = 0;//1:�꽦怨�, 0:�떎�뙣
			
			query1.append(" delete ");
			query1.append("       from ");
			query1.append("              USER_INFO_SAMPLE ");
			query1.append("       where");
//			query1.append("              U_ID = '"+user_id+"'");
			query1.append("              U_ID = ?");
			
			
			System.out.println(query1.toString());
			
			try 
			{
				pstmt = conn.prepareStatement(query1.toString());
				
				pstmt.clearParameters();
				
				pstmt.setString(1, user_id);
				
				result = pstmt.executeUpdate();//荑쇰━瑜� db�뿉 �궇由곕떎 理쒖쥌�쟻�쑝濡�!
				//executeUpdate()�븿�닔�뒗 insert, update, delete�뿉留� �벖�떎! select�뿉�뒗 �븞�벖�떎!
				//�솢 洹몃윭�깘硫�, 諛섑솚媛믪씠 �떎瑜대떎~! int�씠�떎~! cf)select�뒗 ResultSet�쑝濡� 諛섑솚�븳�떎.
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			finally
			{
				try 
				{
					pstmt.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}	
			}
			return result;
		}
		
}