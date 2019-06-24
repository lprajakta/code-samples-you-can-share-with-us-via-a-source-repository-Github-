/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.MemberRegBean;
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
public class SaveMemberDao {
    
    ResourceBundle dbTable1=ResourceBundle.getBundle("dbTable");
    ResourceBundle colName=ResourceBundle.getBundle("memberCol");
    String tableName=dbTable1.getString("memberTable");
    String mid=colName.getString("mId");
    String mname=colName.getString("mName");
    String mbatch=colName.getString("mBatch");
    String maddress=colName.getString("mAddress");
    String mmobile_no=colName.getString("mMobile_no");
    String memail_id=colName.getString("mEmail_id");
    String mmembership_type=colName.getString("mMembership_Type");
    String mcur_amt=colName.getString("mCurrent_Amt");
    String mopening_amt=colName.getString("mOpening_Amt");
    String mactivestatus_flag=colName.getString("mActiveStatus_Flag");
    
    public int saveDetail(MemberRegBean bean){
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
            String memberStatusFlag=bean.getmStatus();
            String memberMembershipType=bean.getmMembershipType();
        try{
           con=DbConnection.getConnection();
           query="insert into "+tableName+"("+mid+","+mname+","+mbatch+","+maddress+","+mmobile_no+","+memail_id+","+mmembership_type+","+mcur_amt+","+ mopening_amt+","+ mactivestatus_flag+",image) values(?,?,?,?,?,?,?,?,?,?,?)";
         
           pst = con.prepareStatement(query);
           pst.setInt(1,0);
           pst.setString(2,bean.getmName());
           pst.setString(3,bean.getmBatch());
           
           pst.setString(4,bean.getmAddress());
           pst.setString(5,bean.getmMobileno());
           pst.setString(6,bean.getmEmailid());
           if(memberMembershipType.equals("both")){
           pst.setString(7,String.valueOf('B'));
           }else{
               pst.setString(7,String.valueOf('S'));
           }
           
           pst.setDouble(8,bean.getmCurrentAmt());
           pst.setDouble(9,bean.getmOpeningAmt());
           if(memberStatusFlag.equals("active")){
           pst.setString(10,String.valueOf('A'));
           }else{
               pst.setString(10,String.valueOf('I'));
           }
           
          FileInputStream fileInputStream=new FileInputStream(bean.getMemberImagePath());
          byte b[]=new byte[fileInputStream.available()];
          fileInputStream.read(b);
          pst.setBytes(11, b);
           
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
    
    public int updateDetail(MemberRegBean bean) {
        Connection con = null;
        PreparedStatement pst = null;
        String query = null;
        int mid1 = bean.getMid();
        String memberStatusFlag = bean.getmStatus();
        String memberMembershipType = bean.getmMembershipType();
        try {
            con = DbConnection.getConnection();
            query = "update " + tableName + " set " + mname + "=?," + mbatch + "=?," + maddress + "=?," + mmobile_no + "=?," + memail_id + "=?," + mmembership_type + "=?," + mcur_amt + "=?," + mopening_amt + "=?," + mactivestatus_flag + "=? where " + mid + "=" + mid1;
            pst = con.prepareStatement(query);
            pst.setString(1, bean.getmName());
            pst.setString(2, bean.getmBatch());
            pst.setString(3, bean.getmAddress());
            pst.setString(4, bean.getmMobileno());
            pst.setString(5, bean.getmEmailid());
            if (memberMembershipType.equals("both")) {
                pst.setString(6, String.valueOf('B'));
            } else {
                pst.setString(6, String.valueOf('S'));
            }

            pst.setDouble(7, bean.getmCurrentAmt());
            pst.setDouble(8, bean.getmOpeningAmt());
            if (memberStatusFlag.equals("active")) {
                pst.setString(9, String.valueOf('A'));
            } else {
                pst.setString(9, String.valueOf('I'));
            }
            
            int result = pst.executeUpdate();
            if (result != 0) {
                return 1;
            } else {
                return 2;
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
        return 1;
    }
        
     public int deleteDetail(final int mId){
            Connection con=null;
            PreparedStatement pst=null;
            String query=null;
        try{
           con=DbConnection.getConnection();
           query="delete from "+tableName+" where "+mid+"="+mId;
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
    
    
    
     public ArrayList<MemberRegBean> getAllRecordForDisplay(){
         ArrayList<MemberRegBean> memberBeanList=new ArrayList<MemberRegBean>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String sql=null;
         try{
             con=DbConnection.getConnection();
             sql="Select * from member_mast ORDER BY mname";
             pst=con.prepareStatement(sql);
             rs =pst.executeQuery();
             while(rs.next()){
                 MemberRegBean bean= new MemberRegBean(); 
                    bean.setMid(rs.getInt("mid"));
                    bean.setmName(rs.getString("mname"));
                    bean.setmBatch(rs.getString("mbatch"));
                    bean.setmAddress(rs.getString("maddress"));
                    bean.setmMobileno(rs.getString("mmobile_no"));
                    bean.setmEmailid(rs.getString("memail_id"));
                    bean.setmMembershipType(rs.getString("mmembership_type"));
                    bean.setmCurrentAmt(rs.getDouble("mcur_amt"));
                    bean.setmOpeningAmt(rs.getDouble("mopening_amt"));
                    bean.setmStatus(rs.getString("mactivestatus_flag"));
                    memberBeanList.add(bean);
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
         return memberBeanList;   
    }

    public boolean uploadImageOnIdDao(MemberRegBean bean) {
        boolean result=false;
    Connection con=null;
    PreparedStatement pst=null;
    String query=null;
    int rs=0;
        try {
            con=DbConnection.getConnection();
            query = " update " + tableName + " set image = ? where " + mid + "=? ";
            pst=con.prepareStatement(query);
           
            FileInputStream fileInputStream = new FileInputStream(bean.getMemberImagePath());
            byte b[] = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            pst.setBytes(1, b);
             pst.setInt(2,bean.getMid());
            
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
