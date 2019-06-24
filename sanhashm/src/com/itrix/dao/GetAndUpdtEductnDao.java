package com.itrix.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.itrix.bean.EducationBean;

public class GetAndUpdtEductnDao {
	Logger logger=Logger.getLogger(GetAndUpdtEductnDao.class);
	
	/**
	*
	* @author chirag
	* @param education Id eduId
	* @return ArrayList<EducationBean>
	* Description Method use for Get all Detail About Education. 
	* 
	*/
	public ArrayList<EducationBean> getEducationRecord(final int eduId)
	{
		logger.info("GetAndUpdtEductnDao/getEducationRecord Start");
		ResourceBundle dbTable=ResourceBundle.getBundle("dbTables");
		String educationTableMast=dbTable.getString("educationMast");
		ResourceBundle educationMast=ResourceBundle.getBundle("EducationMast");
		String eduIdInt=educationMast.getString("eduId");
		String type=educationMast.getString("type");
		String degree=educationMast.getString("degree");
		String institute=educationMast.getString("institute");
		String duration=educationMast.getString("duration");
		String passYear=educationMast.getString("passYear");
		String fromDate=educationMast.getString("fromDate");
		String specialisation=educationMast.getString("specialisation");
		String other=educationMast.getString("other");
		ArrayList<EducationBean> educationBeanList=new ArrayList<EducationBean>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			conn=DBConnection.getConnection();
			String sql="select * from "+educationTableMast+" where "+eduIdInt+" = "+eduId+" ";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				EducationBean educationBean=new EducationBean();
				educationBean.setEduId(rs.getInt(eduIdInt));
				educationBean.setEduType(rs.getString(type));
				educationBean.setDegree(rs.getString(degree));
				educationBean.setInstitute(rs.getString(institute));
				educationBean.setEduDuration(rs.getString(duration));
				Date sqlDate=rs.getDate(fromDate);
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");  
				String startDateStr = df.format(sqlDate);
				educationBean.setStrDate(startDateStr);
				
				Date sqlEndDate=rs.getDate(passYear);
				DateFormat dff = new SimpleDateFormat("dd-MM-yyyy");  
				String endDateStr = dff.format(sqlEndDate);
				educationBean.setEndDate(endDateStr);
				educationBean.setSpecialisation(rs.getString(specialisation));
				educationBean.setOther(rs.getString(other));
				
				educationBean.setEduType(rs.getString(type));
				educationBeanList.add(educationBean);
			}
		}catch (Exception e) {
			logger.error("GetAndUpdtEductnDao/getEducationRecord"+e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				try{
					rs.close();
					pst.close();
					conn.close();
				} 
				catch (SQLException s){
					logger.error("GetAndUpdtEductnDao/getEducationRecord"+s.getMessage());
					s.printStackTrace();
				}
			}
		}
		logger.info("GetAndUpdtEductnDao/getEducationRecord End");
		return educationBeanList;
	}
	
	
	/**
	*
	* @author chirag
	* @param education Id eduId, User Id 
	* @return boolean
	* Description Method use for delete Record Match With edu Id and User Id. 
	* 
	*/
	
	public boolean deleteRecord(final int eduId,final int userId)
	{
		logger.info("GetAndUpdtEductnDao/deleteRecord Start");
		boolean result=false;
		ResourceBundle dbTable=ResourceBundle.getBundle("dbTables");
		String educationTableMast=dbTable.getString("educationMast");
		ResourceBundle educationMast=ResourceBundle.getBundle("EducationMast");
		String eduIdInt=educationMast.getString("eduId");
		String userIdInt=educationMast.getString("userId");
		Connection conn=null;
		PreparedStatement pst=null;
		int rs=0;
		try{
			conn=DBConnection.getConnection();
			String sql="delete from "+educationTableMast+" where "+eduIdInt+" = "+eduId+" and "+userIdInt+"="+userId+" ";
			pst=conn.prepareStatement(sql);
			rs=pst.executeUpdate();
			if(rs!=0){
				return true;
			}
			else{
				return false;
			}
		}catch (Exception e) {
			logger.error("GetAndUpdtEductnDao/deleteRecord"+e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				try{
					pst.close();
					conn.close();
				} 
				catch (SQLException s){
					logger.error("GetAndUpdtEductnDao/deleteRecord"+s.getMessage());
					s.printStackTrace();
				}
			}
		}
		logger.info("GetAndUpdtEductnDao/deleteRecord End");
		return result;
	}
}
