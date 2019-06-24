/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.StaffRegBean;
import com.itrix.database.DbConnection;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 * @author abhishek_yewle
 */
public class SaveStaffDao {
    
     ResourceBundle dbTable1=ResourceBundle.getBundle("dbTable");
    ResourceBundle colName=ResourceBundle.getBundle("staffCol");
    String tableName=dbTable1.getString("staffTable");
    String sid=colName.getString("sId");
    String sname=colName.getString("sName");
    String saddress=colName.getString("sAddress");
    String smobile_no=colName.getString("sMobile_no");
    String sremark=colName.getString("sRemark");
    String sactivestatus_flag=colName.getString("sActiveStatus_Flag");
    
     public int saveDetail(StaffRegBean bean){
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
            String staffStatusFlag=bean.getsStatus();
        try{
           con=DbConnection.getConnection();
           query="insert into "+tableName+"("+sid+","+sname+","+saddress+","+smobile_no+","+sremark+","+sactivestatus_flag+",image) values(?,?,?,?,?,?,?)";
         
           pst = con.prepareStatement(query);
           pst.setInt(1,0);
           pst.setString(2,bean.getsName());
           pst.setString(3,bean.getsAddress());
           pst.setString(4,bean.getsMobileno());
           pst.setString(5,bean.getsRemark());
          if(staffStatusFlag.equals("active")){
           pst.setString(6,String.valueOf('A'));
           }else{
               pst.setString(6,String.valueOf('I'));
           }
           FileInputStream fileInputStream=new FileInputStream(bean.getImagePath1());
           byte b[]=new byte[fileInputStream.available()];
           fileInputStream.read(b);
           pst.setBytes(7, b);
        
           int result=pst.executeUpdate();
           if(result!=0){
               return 1;
           }else{
               return 2;
           }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{
                con.close();
                pst.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return 1;
    } 
     
     public int updateDetail(StaffRegBean bean) {
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
            int sid1=bean.getSid();
            String userStatusFlag=bean.getsStatus();
        try{
           con=DbConnection.getConnection();
           query="update "+tableName+" set "+sname+"=?,"+saddress+"=?,"+smobile_no+"=?,"+sremark+"=?,"+sactivestatus_flag+"=? where "+sid+"="+sid1;
           pst = con.prepareStatement(query);
           pst.setString(1,bean.getsName());
           pst.setString(2,bean.getsAddress());
           pst.setString(3,bean.getsMobileno());
           pst.setString(4,bean.getsRemark());
          if(userStatusFlag.equals("active")){
           pst.setString(5,String.valueOf('A'));
           }else{
               pst.setString(5,String.valueOf('I'));
           }
          int result=pst.executeUpdate();
           if(result!=0){
               return 1;
           }else{
               return 2;
           }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                con.close();
                pst.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        return 1;
     }
        
     
       public int deleteDetail(final int sId){
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
        try{
           con=DbConnection.getConnection();
           query="delete from "+tableName+" where "+sid+"="+sId;
           pst = con.prepareStatement(query);
           int result=pst.executeUpdate();
           if(result!=0){
               return 1;
           }else{
               return 2;
           }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try{
                con.close();
                pst.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return 1;
    }        
     
      public ArrayList<StaffRegBean> getAllRecordForDisplay(){
         ArrayList<StaffRegBean> staffBeanList=new ArrayList<StaffRegBean>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String sql=null;
         try{
             con=DbConnection.getConnection();
             sql="Select sid,sname,saddress,smobile_no,sremark,sactivestatus_flag from staff_mast";
             
             pst=con.prepareStatement(sql);
             rs =pst.executeQuery();
             while(rs.next()){
                 StaffRegBean bean= new StaffRegBean(); 
                    bean.setSid(rs.getInt("sid"));
                    bean.setsName(rs.getString("sname"));
                    bean.setsAddress(rs.getString("saddress"));
                    bean.setsMobileno(rs.getString("smobile_no"));
                    bean.setsRemark(rs.getString("sremark"));
                    bean.setsStatus(rs.getString("sactivestatus_flag"));
                 staffBeanList.add(bean);
             }
                
            }catch(Exception e){
                e.printStackTrace();
            }finally{
            try{
                con.close();
                pst.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
         return staffBeanList;   
    }

    public boolean uploadImageOnIdDao(StaffRegBean bean) {
    boolean result=false;
    Connection con=null;
    PreparedStatement pst=null;
    String query=null;
    int rs=0;
        try {
            con=DbConnection.getConnection();
            query = " update " + tableName + " set image = ? where " + sid + "=? ";
            pst=con.prepareStatement(query);
           
            FileInputStream fileInputStream = new FileInputStream(bean.getImagePath1());
            byte b[] = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            pst.setBytes(1, b);
             pst.setInt(2,bean.getSid());
            
           rs= pst.executeUpdate();
           if(rs!=0)
           {
               result=true;
               return result;
           }
           else{
               result=false;
               return result;
           }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
}
