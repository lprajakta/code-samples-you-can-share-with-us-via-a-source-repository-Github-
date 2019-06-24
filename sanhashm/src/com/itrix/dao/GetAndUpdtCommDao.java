package com.itrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.itrix.bean.CanCurAddressInfoBean;
import com.itrix.bean.CanPerAddressInfoBean;

public class GetAndUpdtCommDao {
	Logger logger=Logger.getLogger(GetAndUpdtCommDao.class);
	
	/**
	*
	* @author chirag
	* @param CanPerAddressInfoBean,CanCurAddressInfoBean model class instance
	* @return boolean
	* Description Method get updated Address Detail
	* 
	*/
	
	
	public boolean getUpdateDao(CanPerAddressInfoBean perAdrBean,CanCurAddressInfoBean curAdrBean)
	{
		logger.info("GetAndUpdtCommDao/getUpdateDao Strat");
		Boolean result=false;
		ResourceBundle dbTables= ResourceBundle.getBundle("dbTables");
		String addressMast=dbTables.getString("addrPerMast");
		String addressCurMast=dbTables.getString("addrCurMast");
		ResourceBundle currentAddressDetail=ResourceBundle.getBundle("CurrentAddressMast");
		ResourceBundle addressPerMast=ResourceBundle.getBundle("AddressMast");
		String pUserIdint=addressPerMast.getString("user_id");
		
		String flatNo=addressPerMast.getString("flat");
		String bulidingNo=addressPerMast.getString("building");
		String roadName=addressPerMast.getString("road");
		String areaName=addressPerMast.getString("area");
		String cityName=addressPerMast.getString("city");
		String countryName=addressPerMast.getString("country");
		String stateName=addressPerMast.getString("state");
		String picCode=addressPerMast.getString("pin");
		String disrtictname=addressPerMast.getString("district");
		
		String cUserIdint=currentAddressDetail.getString("user_id");
		String curFlatNo=currentAddressDetail.getString("flat");
		String curBulidingNo=currentAddressDetail.getString("building");
		String curRoadName=currentAddressDetail.getString("road");
		String curAreaName=currentAddressDetail.getString("area");
		String curCityName=currentAddressDetail.getString("city");
		String curCountryName=currentAddressDetail.getString("country");
		String curStateName=currentAddressDetail.getString("state");
		String curPinCode=currentAddressDetail.getString("pin");
		String curDisrtictname=currentAddressDetail.getString("district");
		
		Connection conn=null;
		PreparedStatement pst=null;
		String sql="";
		int userId=perAdrBean.getUserId();
		try{
			conn=DBConnection.getConnection();
			sql="update "+addressMast+" as pm ,"+addressCurMast+" as cm set pm."+flatNo+"=?,pm."+bulidingNo+"=?" +
				",pm."+roadName+"=?,pm."+areaName+"=?,pm."+cityName+"=?," +
				"pm."+countryName+"=?,pm."+stateName+"=?,pm."+picCode+"=?,pm."+disrtictname+"=?,cm."+curFlatNo+"=?,cm."+curBulidingNo+"=?" +
				",cm."+curRoadName+"=?,cm."+curAreaName+"=?,cm."+curCityName+"=?," +
				"cm."+curCountryName+"=?,cm."+curStateName+"=?,cm."+curPinCode+"=?,cm."+curDisrtictname+"=? where pm."+pUserIdint+"=cm."+cUserIdint+" and pm."+pUserIdint+"="+userId+"";
			pst=conn.prepareStatement(sql);
			
			pst.setString(1,perAdrBean.getFlatNo());
			pst.setString(2,perAdrBean.getBuildingNo());
			pst.setString(3,perAdrBean.getRoad());
			pst.setString(4,perAdrBean.getArea());
			pst.setString(5,perAdrBean.getCity());
			pst.setInt(6,perAdrBean.getCountry());
			pst.setInt(7,perAdrBean.getState());
			pst.setInt(8,perAdrBean.getPinCode());
			pst.setString(9,perAdrBean.getDistrict());
			pst.setString(10,curAdrBean.getCurFlatNo());
			pst.setString(11,curAdrBean.getCurBuildingNo());
			pst.setString(12,curAdrBean.getCurRoad());
			pst.setString(13,curAdrBean.getCurArea());
			pst.setString(14,curAdrBean.getCurCity());
			pst.setInt(15,curAdrBean.getCurCountry());
			pst.setInt(16,curAdrBean.getCurState());
			pst.setInt(17,curAdrBean.getCurPinCode());
			pst.setString(18,curAdrBean.getCurDistrict());
			
			int rs=pst.executeUpdate();
			if(rs!=0){
				return true;
			}
		}catch (Exception e) {
			logger.error("GetAndUpdtCommDao/getUpdateDao"+e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				try{
					pst.close();
					conn.close();
				} 
				catch (SQLException s) 
				{
					logger.error("GetAndUpdtCommDao/getUpdateDao"+s.getMessage());
					s.printStackTrace();
				}
			}
		}
		logger.info("GetAndUpdtCommDao/getUpdateDao End");
		return result;
	}
	
	/**
	*
	* @author chirag
	* @param userId 
	* @return ArryList<CanPerAddressInfoBean> 
	* Description Get Selected ADDRESS Record From DataBase
	* 
	*/
	
	public ArrayList<CanPerAddressInfoBean> getInsertedRecord(final int userId)
	{
		logger.info("GetAndUpdtCommDao/getInsertedRecord Start");
		
		ResourceBundle dbTable= ResourceBundle.getBundle("dbTables");
		String perAddressMast=dbTable.getString("addrPerMast");
		String curAddressMast=dbTable.getString("addrCurMast");
		ResourceBundle perAddrMast=ResourceBundle.getBundle("AddressMast");
		ResourceBundle curAddrMast=ResourceBundle.getBundle("CurrentAddressMast");
		
		String paddrId=perAddrMast.getString("paddrId");
		String puserIdint=perAddrMast.getString("user_id");
		String flatNo=perAddrMast.getString("flat");
		String cityName=perAddrMast.getString("city");
		String districtname=perAddrMast.getString("district");
		String bulidingNo=perAddrMast.getString("building");
		String roadName=perAddrMast.getString("road");
		String areaName=perAddrMast.getString("area");
		String countryName=perAddrMast.getString("country");
		String stateName=perAddrMast.getString("state");
		String pinCode=perAddrMast.getString("pin");
	
		String caddrId=curAddrMast.getString("caddrId");
		String cuserIdint=curAddrMast.getString("user_id");
		String curCityName=curAddrMast.getString("city");
		String curFlatNo=curAddrMast.getString("flat");
		String curDistrictName=curAddrMast.getString("district");
		String curBulidingNo=curAddrMast.getString("building");
		String curRoadName=curAddrMast.getString("road");
		String curAreaName=curAddrMast.getString("area");
		String curCountryName=curAddrMast.getString("country");
		String curStateName=curAddrMast.getString("state");
		String curPinCode=curAddrMast.getString("pin");
		ArrayList<CanPerAddressInfoBean> communicationList=new ArrayList<CanPerAddressInfoBean>();
		ArrayList<CanCurAddressInfoBean> currentList=new ArrayList<CanCurAddressInfoBean>();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			conn=DBConnection.getConnection();
			String sql="select pm."+paddrId+",pm."+flatNo+",pm."+cityName+",pm."+districtname+",pm."+bulidingNo+",pm."+roadName+",pm."+areaName+",pm."+countryName+",pm."+pinCode+",pm."+stateName+",cm."+caddrId+",cm."+curFlatNo+",cm."+curCityName+" " +
					",cm."+curBulidingNo+",cm."+curRoadName+",cm."+curAreaName+",cm."+curCountryName+",cm."+curStateName+",cm."+curPinCode+",cm."+curDistrictName+" from "+perAddressMast+" as pm inner join "+curAddressMast+" as cm on ( cm."+cuserIdint+"=pm."+puserIdint+" ) where pm."+puserIdint+"="+userId+"";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			System.out.println("customer addr mast:- "+pst);
			while(rs.next()){
				CanCurAddressInfoBean curAddressBean=new CanCurAddressInfoBean();
				CanPerAddressInfoBean perAddressBean=new CanPerAddressInfoBean();
				
				perAddressBean.setPaddrId(rs.getInt(paddrId));
				perAddressBean.setFlatNo(rs.getString(flatNo));
				perAddressBean.setCity(rs.getString(cityName));
				perAddressBean.setDistrict(rs.getString(districtname));
				perAddressBean.setCountry(rs.getInt(countryName));
				perAddressBean.setRoad(rs.getString(roadName));
				perAddressBean.setState(rs.getInt(stateName));
				perAddressBean.setBuildingNo(rs.getString(bulidingNo));
				perAddressBean.setArea(rs.getString(areaName));
				perAddressBean.setPinCode(rs.getInt(pinCode));
				
				curAddressBean.setCuraddrId(rs.getInt(caddrId));
				curAddressBean.setCurFlatNo(rs.getString(curFlatNo));
				curAddressBean.setCurCity(rs.getString(curCityName));
				curAddressBean.setCurDistrict(rs.getString(curDistrictName));
				curAddressBean.setCurCountry(rs.getInt(curCountryName));
				curAddressBean.setCurRoad(rs.getString(curRoadName));
				curAddressBean.setCurState(rs.getInt(curStateName));
				curAddressBean.setCurBuildingNo(rs.getString(curBulidingNo));
				curAddressBean.setCurArea(rs.getString(curAreaName));
				curAddressBean.setCurPinCode(rs.getInt(curPinCode));
				
				currentList.add(curAddressBean);
				perAddressBean.setCurrentAddres(currentList);
				communicationList.add(perAddressBean);
			}
		}catch (Exception e) {
			logger.error("GetAndUpdtCommDao/getInsertedRecord End"+e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				try{
					rs.close();
					pst.close();
					conn.close();
				} 
				catch (SQLException s) 
				{
					logger.error("GetAndUpdtCommDao/getInsertedRecord End"+s.getMessage());
					s.printStackTrace();
				}
			}
		}
		logger.info("GetAndUpdtCommDao/getInsertedRecord End");
		return communicationList;
	}
}
