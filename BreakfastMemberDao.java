/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.BreakfastMemberBean;
import com.itrix.database.DbConnection;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author itrix_wk1
 */
public class BreakfastMemberDao {
    public int saveBfMemDetail(BreakfastMemberBean breakfastMemberBean){
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;

        try{
           con=DbConnection.getConnection();
       
         query="insert into bf_mem_mast(bfMemName,bfMemMobileno,bfMemAddress,mem_opening_amt,mem_cur_amt,image) values(?,?,?,?,?,?)";
           pst = con.prepareStatement(query);
          // pst.setInt(1,0);
           pst.setString(1,breakfastMemberBean.getBfMemName());
           pst.setString(2,breakfastMemberBean.getBfMemMobileNo());
           pst.setString(3,breakfastMemberBean.getBfMemAddress());
           pst.setDouble(4,breakfastMemberBean.getBfMemOpeningBal());
           pst.setDouble(5,breakfastMemberBean.getBfMemCurrentBal());
          // pst.setString(6,breakfastMemberBean.getImagePath1());
           FileInputStream fileInputStream=new FileInputStream(breakfastMemberBean.getImagePath1());
          byte b[]=new byte[fileInputStream.available()];
          fileInputStream.read(b);
          pst.setBytes(6, b);
           
         
           int result=pst.executeUpdate();
           if(result!=0){
               return 1;
           }else{
               return 2;
           }
        }catch(Exception ex){
          ex.printStackTrace();
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
    
    
      public ArrayList<BreakfastMemberBean> getAllBreakfastMemRecordForDisplay(){
         ArrayList<BreakfastMemberBean> breakfastMemBeanList=new ArrayList<BreakfastMemberBean>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String sql=null;
         try{
             con=DbConnection.getConnection();
            // sql="Select uid,uname,uemail_id,umobile_no,urole,uusername,upassword,uactivestatus_flag from user_mast";
            sql="select * from bf_mem_mast";
             pst=con.prepareStatement(sql);
             rs =pst.executeQuery();
          //  System.out.println(rs);
              while(rs.next()){
                 BreakfastMemberBean breakfastMemberBean= new BreakfastMemberBean(); 
            //     user = new User(rs.getInt("id"),rs.getString("fname"),rs.getString("lname"),rs.getInt("age"));
                    
                    breakfastMemberBean.setBfMemId(rs.getInt("bf_mem_id"));
                    breakfastMemberBean.setBfMemName(rs.getString("bfMemName"));
                    breakfastMemberBean.setBfMemMobileNo(rs.getString("bfMemMobileno"));
                    breakfastMemberBean.setBfMemAddress(rs.getString("bfMemAddress"));
                    breakfastMemberBean.setBfMemOpeningBal(rs.getDouble("mem_opening_amt"));
                    breakfastMemberBean.setBfMemCurrentBal(rs.getDouble("mem_opening_amt"));
                    breakfastMemberBean.setImagePath(rs.getBytes("image"));
                    
                   
                 breakfastMemBeanList.add(breakfastMemberBean);
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
         return breakfastMemBeanList;   
    } 

    public int  updateBfMemDetail(BreakfastMemberBean breakfastMemberBean) {
       Connection con=null;
            PreparedStatement pst=null;
            String query=null;
            int id1=breakfastMemberBean.getBfMemId();
        
        try{
           con=DbConnection.getConnection();
           query="update bf_mem_mast set bfMemName=?, bfMemMobileno=?,bfMemAddress=? where bf_mem_id="+id1;
           pst = con.prepareStatement(query);
           pst.setString(1,breakfastMemberBean.getBfMemName());
           pst.setString(2,breakfastMemberBean.getBfMemMobileNo());
           pst.setString(3,breakfastMemberBean.getBfMemAddress());
           
        
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
    
    public int deleteBfMemDetail(final int uId){
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
        try{
           con=DbConnection.getConnection();
           query="delete from bf_mem_mast where bf_mem_id="+uId+"";
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

    public boolean uploadImageOnIdDao(BreakfastMemberBean breakfastMemberBean) {
        boolean result = false;
        Connection con = null;
        PreparedStatement pst = null;
        String query = null;
        int rs = 0;
        try {
            con = DbConnection.getConnection();
            query = " update bf_mem_mast set image = ? where bf_mem_id=? ";
            pst = con.prepareStatement(query);

            FileInputStream fileInputStream = new FileInputStream(breakfastMemberBean.getImagePath1());
            byte b[] = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            pst.setBytes(1, b);
            pst.setInt(2, breakfastMemberBean.getBfMemId());

            rs = pst.executeUpdate();
            if (rs != 0) {
                result = true;
                return result;
            } else {
                result = false;
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
