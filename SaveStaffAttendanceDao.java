/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.StaffAttendanceBean;
import com.itrix.bean.StaffAttendenceBeanStatus;
import com.itrix.database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
  * @author abhishek_yewle
 */
public class SaveStaffAttendanceDao {
           public ArrayList getStaffNameInCBDao(){
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList staffList=new ArrayList();
        try{
            con= DbConnection.getConnection();
            String sql ="SELECT sname FROM imess_db1.staff_mast where sactivestatus_flag='A';";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                staffList.add(rs.getString(1));
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
            }catch(Exception ex){
                ex.printStackTrace();
             
            }
        }
        return staffList;
    }
    public ArrayList<StaffAttendanceBean> getStaffAttendanceRecordForDisplay(String month){
         ArrayList<StaffAttendanceBean> staffBeanList=new ArrayList<StaffAttendanceBean>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String sql="";
         String sqlQuery="";
         try{
            con=DbConnection.getConnection();
            sql ="SELECT sm.sid, sm.sname FROM `staff_mast` as sm where sm.sactivestatus_flag='A'";            
            //sql ="SELECT mm.mid, mm.mname,md.for_name FROM `member_mast` as mm inner join member_data as md on md.mid=mm.mid where md.for_name ='"+month+"'";
             sqlQuery= "SELECT sm.sid, sm.sname FROM `staff_mast` as sm";
             pst=con.prepareStatement(sql);
             rs =pst.executeQuery();
             if(rs.next()){
                 do{
                     StaffAttendanceBean staffAttendanceBean= new  StaffAttendanceBean();                   
                    int staffId = rs.getInt("sid");
                  //  String forMon = rs.getString("for_name");
                    staffAttendanceBean.setSid(staffId);
                    staffAttendanceBean.setsName(rs.getString("sname"));
                 //   memberAttendanceBean.setFormonth(forMon);
                    ArrayList<StaffAttendenceBeanStatus> staffAttList = getStaffAttListForMon(staffId,month);
                    staffAttendanceBean.setAttendenceList(staffAttList);                 
                    staffBeanList.add(staffAttendanceBean);
                 } while(rs.next());                                          
             }else{
                sqlQuery= "SELECT sm.sid, sm.sname FROM `staff_mast` as sm where sm.sactivestatus_flag='A'";
                pst=con.prepareStatement(sqlQuery);
                rs =pst.executeQuery();
                while(rs.next()){
                 StaffAttendanceBean staffAttendanceBean= new  StaffAttendanceBean();                 
                  int memId = rs.getInt("mid");                
                    staffAttendanceBean.setSid(memId);
                    staffAttendanceBean.setsName(rs.getString("mname"));
                    staffBeanList.add(staffAttendanceBean);
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
         return staffBeanList;   
    }
    
      private ArrayList<StaffAttendenceBeanStatus> getStaffAttListForMon(int staffId, String forMon) {
         ArrayList<StaffAttendenceBeanStatus> staffBeanStatusList=new ArrayList<StaffAttendenceBeanStatus>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String query=null;
         try{
             con=DbConnection.getConnection();
            query ="SELECT sdd.att_date, sdd.roll_call_status,sdd.att_status FROM `staff_data_det` as sdd \n" +
            "where sdd.for_month ='"+forMon+"' and sdd.sid='"+staffId+"' order by sdd.att_date asc"; 
          
            pst=con.prepareStatement(query);
           // System.out.println("sql 2 "+query);
            rs =pst.executeQuery();
            while(rs.next()){
                // MemberAttendanceBean memberAttendanceBean= new  MemberAttendanceBean(); 
                StaffAttendenceBeanStatus attendenceBeanStatus=new StaffAttendenceBeanStatus();
                int att_date = rs.getInt("att_date");
                String roll_cal_status = rs.getString("roll_call_status");
                String att_status = rs.getString("att_status");
                attendenceBeanStatus.setAttendence_date(att_date);
                attendenceBeanStatus.setRoll_call_status(roll_cal_status);
                attendenceBeanStatus.setStatus(att_status);
                            
                staffBeanStatusList.add(attendenceBeanStatus);
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
         return staffBeanStatusList;
       
       
    }
    
            public DefaultTableModel searchOnStaffName(String staffName){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        DefaultTableModel dtm=null;
        try{
            con= DbConnection.getConnection();
            String sql="select sname from staff_mast where sname like ? order by sname";
//            String sql = "Select "+icode+" as 'Item code',"+iname+" as 'Item name',"
//                    +itype+" as 'Item type',"+icategory+" as 'Category',"+ipackage+" as 'Package',"
//                   +iunit+" as 'Unit',"+iprice+" as 'Rate' from "
//                    +tableName+" where del_by='' and Del_dt is null  and "+iprice+" != '0' and "+iname+" like ? order by "+iname; 
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, staffName+"%");
            rs = pstmt.executeQuery();
            dtm=(DefaultTableModel) DbUtils.resultSetToTableModel(rs);
        }catch (SQLException ex){
           ex.printStackTrace();
        }finally{
            try{
                rs.close();
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return dtm;
    }
}
