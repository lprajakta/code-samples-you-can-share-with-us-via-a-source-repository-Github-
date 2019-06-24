/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.MemberAttendenceBeanStatus;
import com.itrix.bean.MemberAttendanceBean;
import com.itrix.database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
  * @author abhishek_yewle
 */
public class SaveMemberAttendanceDao {
    ResourceBundle dbTable1=ResourceBundle.getBundle("dbTable");
    ResourceBundle colName=ResourceBundle.getBundle("memberCol");
    String tableName=dbTable1.getString("memberTable");
    String mid=colName.getString("mId");
    String mname=colName.getString("mName");
    
        public ArrayList getMemberNameInCBDao(){
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList memberList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT mname FROM imess_db1.member_mast;";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                memberList.add(rs.getString(1));
            }            
        }catch (SQLException ex){
            ex.printStackTrace();
            Logger.getLogger(MemberBillDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                rs.close();
                pstmt.close();
                con.close();
            }catch(Exception e){
                e.printStackTrace();
                //JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return memberList;
    }
          public ArrayList getMemberNameInCBDaoLike(String itemName)
          {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList memberList = new ArrayList();
        try {
            con = DbConnection.getConnection();
            // String sql ="SELECT mname FROM imess_db1.member_mast;";
            String sql = "SELECT mname\n"
                    + "FROM  `member_mast` \n"
                    + "WHERE mname LIKE  '"+itemName+"%' and mactivestatus_flag='A'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                memberList.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(MemberBillDao.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                rs.close();
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                //JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return memberList;
    }
    public ArrayList<MemberAttendanceBean> getMemberAttendanceRecordForDisplay(String mbatch,String month){
         ArrayList<MemberAttendanceBean> memberBeanList=new ArrayList<MemberAttendanceBean>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String sql="";
         String sqlQuery="";
         try{
            con=DbConnection.getConnection();
            sql ="SELECT mm.mid, mm.mname FROM `member_mast` as mm where mbatch='"+mbatch+"' and mactivestatus_flag='A' ";            
            //sql ="SELECT mm.mid, mm.mname,md.for_name FROM `member_mast` as mm inner join member_data as md on md.mid=mm.mid where md.for_name ='"+month+"'";
             sqlQuery= "SELECT mm.mid, mm.mname FROM `member_mast` as mm where mbatch='"+mbatch+"'";
             pst=con.prepareStatement(sql);
             System.out.println("sql 19 "+pst);
             rs =pst.executeQuery();
             if(rs.next()){
                 do{
                     MemberAttendanceBean memberAttendanceBean= new  MemberAttendanceBean();                   
                    int memId = rs.getInt("mid");
                 
                    memberAttendanceBean.setMid(memId);
                    memberAttendanceBean.setmName(rs.getString("mname"));
                
                    ArrayList<MemberAttendenceBeanStatus> memAttList = getMemberAttListForMon(memId,month);
                    memberAttendanceBean.setAttendenceList(memAttList);                 
                    memberBeanList.add(memberAttendanceBean);
                 } while(rs.next());                                          
             }else{
                sqlQuery= "SELECT mm.mid, mm.mname FROM `member_mast` as mm where mbatch='"+mbatch+"' and  mactivestatus_flag='A'";
                pst=con.prepareStatement(sqlQuery);
                rs =pst.executeQuery();
                while(rs.next()){
                 MemberAttendanceBean memberAttendanceBean= new  MemberAttendanceBean();                 
                  int memId = rs.getInt("mid");                
                    memberAttendanceBean.setMid(memId);
                    memberAttendanceBean.setmName(rs.getString("mname"));
                    memberBeanList.add(memberAttendanceBean);
                }                
             
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

    private ArrayList<MemberAttendenceBeanStatus> getMemberAttListForMon(int memId, String forMon) {
         ArrayList<MemberAttendenceBeanStatus> memberBeanStatusList=new ArrayList<MemberAttendenceBeanStatus>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String query=null;
         try{
             con=DbConnection.getConnection();
            query ="SELECT mdd.att_date, mdd.roll_call_status,mdd.att_status FROM `member_data_det` as mdd \n" +
            "where mdd.for_month ='"+forMon+"' and mdd.mid='"+memId+"' order by mdd.att_date asc"; 
          
            pst=con.prepareStatement(query);
           // System.out.println("sql 2 "+query);
            rs =pst.executeQuery();
            while(rs.next()){
                // MemberAttendanceBean memberAttendanceBean= new  MemberAttendanceBean(); 
                MemberAttendenceBeanStatus attendenceBeanStatus=new MemberAttendenceBeanStatus();
                int att_date = rs.getInt("att_date");
                String roll_cal_status = rs.getString("roll_call_status");
                String att_status = rs.getString("att_status");
                attendenceBeanStatus.setAttendence_date(att_date);
                attendenceBeanStatus.setRoll_call_status(roll_cal_status);
                attendenceBeanStatus.setStatus(att_status);
                            
                memberBeanStatusList.add(attendenceBeanStatus);
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
         return memberBeanStatusList;
       
       
    }
    
}
