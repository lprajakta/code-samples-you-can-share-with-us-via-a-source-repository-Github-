/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itrix.dao;

import com.itrix.bean.ItemMastBean;
import com.itrix.database.DbConnection;
import com.itrix.util.Conversion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Tejas Jadhav
 * @since 21 Sep 2014
 * 
 */
public class ItemMastDao 
{
    Conversion conv=new Conversion();
    ResourceBundle dbTable1=ResourceBundle.getBundle("dbTable");
    ResourceBundle colName=ResourceBundle.getBundle("itemCol");
    String tableName=dbTable1.getString("itemTable");
    String id=colName.getString("iId");
    String icode=colName.getString("iCode");
    String iname=colName.getString("iName");
    String itype=colName.getString("iType");
    String icategory=colName.getString("iCategory");
    String ipackage=colName.getString("iPackage");
    String iunit=colName.getString("iUnit");
    String iprice=colName.getString("iPrice");
    String iop_Stock=colName.getString("iOp_stock");
    
    
    
        public DefaultTableModel getItemsDetailsOnModTableDao(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        DefaultTableModel dtm=null;
        try{
        
            con= DbConnection.getConnection();
             //String sql = "Select item_code,name,item_type,catagory,package,unit,price,op_stock from "+tableName+" where item_type<>'"Liqour"'  order by name";
           String sql="Select item_code,name,item_type,catagory,package,unit,price,op_stock from "+tableName+" where item_type<>\"Liquor\"  order by name";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            dtm=(DefaultTableModel) DbUtils.resultSetToTableModel(rs);
        }catch (Exception ex){
           
          ex.printStackTrace();
        }finally {
            try{
		 rs.close();
                 con.close();
            }catch (SQLException e) {
            	e.printStackTrace();
            }
	}
        return dtm;
    }
       public DefaultTableModel getItemsDetailsOnTableDao(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        DefaultTableModel dtm=null;
        try
        {
        
            con= DbConnection.getConnection();
            String sql = "Select item_code,name,item_type,catagory,package,unit,price,op_stock from "+tableName+" where item_type<>\"Liquor\"  order by name ";
        
         System.out.println("value are"+sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            System.out.println("value are"+rs);
           dtm=(DefaultTableModel)DbUtils.resultSetToTableModel(rs);
         //   dtm=DbUtils.

            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally 
	{
            try 
            {
		 rs.close();
                 con.close();
            }
            catch (SQLException e) 
            {
            	e.printStackTrace();
            }
	}
        return dtm;
    }
    
      public ArrayList<ItemMastBean> getAllRecordForDisplay(){
         ArrayList<ItemMastBean> itemBeanList=new ArrayList<ItemMastBean>();
         Connection con=null;
         PreparedStatement pst=null;
         ResultSet rs=null;
         String sql=null;
         try{
             con=DbConnection.getConnection();
             sql="Select * from item_mast";
             pst=con.prepareStatement(sql);
             rs =pst.executeQuery();
             while(rs.next()){
                ItemMastBean bean= new ItemMastBean(); 
                    bean.setCode(rs.getString(icode));
                    bean.setItemName(rs.getString(iname));
                    bean.setItemType(rs.getString(itype));
                    bean.setItemCategary(rs.getString(icategory));
                    bean.setItemPkg(rs.getString(ipackage));
                    bean.setItemUnit(rs.getString(iunit));
                    bean.setItemRate(rs.getString(iprice));
                    bean.setOp_stk(rs.getInt(iop_Stock));
                  
                 itemBeanList.add(bean);
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
         return itemBeanList;   
    }
    
     
    public boolean addNewItem(ArrayList<ItemMastBean> addItemList, ItemMastBean addItem){
        boolean result=false;
        
       // String Item_Mast=tablNames.getString("item_Mast"); 
      //   String tableName=dbTable1.getString("itemTable");
        
            
        String itemName=addItem.getItemName();
        String itemType=addItem.getItemType();
        String itemCategary=addItem.getItemCategary();
        
        
        
        int res =0;
	Connection con = null;
	PreparedStatement pstmt = null;
        for(int i=0;i<addItemList.size();i++)
        {
            ItemMastBean addItemRateBean=addItemList.get(i);
            String pkge=addItemRateBean.getItemPkg();
            String unit=addItemRateBean.getItemUnit();
            String rate=addItemRateBean.getItemRate();
            String code=addItemRateBean.getCode();
            int stk=addItemRateBean.getOp_stk();
            double Rate=0;
            if(rate.equals("") || rate==null)
            {
                Rate=0.0;
            }
            else
            {
                Rate=Double.parseDouble(rate);
            }
            
            if(code==null)
            {
                code="";
            }
            
            
            try
            {
                con= DbConnection.getConnection();
                String qry="insert into "+tableName+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
               
                pstmt=con.prepareStatement(qry);
                pstmt.setInt(1,0);
                pstmt.setString(2,itemName);
                pstmt.setString(3,itemType);
                pstmt.setString(4,itemCategary);
                pstmt.setString(5,pkge);
                pstmt.setString(6,unit);
                pstmt.setDouble(7,Rate);
                pstmt.setString(8,"");
                pstmt.setTimestamp(9,conv.getCurrentJavaSqlTimeStamp());
                pstmt.setString(10,"");
                pstmt.setDate(11,null);
                pstmt.setString(12,"");
                pstmt.setDate(13,null);
                pstmt.setString(14,code);
                pstmt.setString(15,"");
                pstmt.setInt(16,addItemRateBean.getOp_stk());
                 System.out.println(qry);
                res =  pstmt.executeUpdate();     
                if(res==1)
                {
                    result=true;
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(ItemMastDao.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            finally{
                try{
                    pstmt.close();
                    con.close();
                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
        
           
        
        return result;
    }
  
    public boolean updateItem(ArrayList<ItemMastBean> updateItemList) {
        boolean result=false;
     //**************************Item mast columns******************************
 
        int res =0;
	Connection con = null;
	PreparedStatement pstmt = null;
        for(int i=0;i<updateItemList.size();i++){
            ItemMastBean updateItemBean=updateItemList.get(i);
            String itemName=updateItemBean.getItemName();
            String pkge=updateItemBean.getItemPkg();
            String unit=updateItemBean.getItemUnit();
            String rate=updateItemBean.getItemRate();
            String code=updateItemBean.getCode();
            String mod_by="";
          double Rate=0;
           // int Rate=0;
            if(rate.equals("") || rate==null){
             Rate=0;
                // Rate=0;
            }else{
                Rate=Double.parseDouble(rate);
            }
            try{        
                con= DbConnection.getConnection();
                String qry="update "+tableName+" set "+iprice+"=? ,mod_by=?, mod_dt = ?,"+icode+"=? where "+iname+"='"+itemName+"' and "
                            +iunit+"='"+unit+"' and "+ipackage+"=?";
                pstmt=con.prepareStatement(qry);
                pstmt.setDouble(1,Rate);
                pstmt.setString(2,mod_by);
                pstmt.setTimestamp(3,conv.getCurrentJavaSqlTimeStamp());
                pstmt.setString(4,code);
                pstmt.setString(5,pkge);
                System.out.println("query"+pstmt);
                res =  pstmt.executeUpdate();     
                if(res>0){
                    result=true;
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(ItemMastDao.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(null, ex.getMessage());
            }finally{
                try{
                    pstmt.close();
                    con.close();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
        return result;
    }

    public void deleteItem(ItemMastBean itemMastBean) {
     
        Connection con = null;
	PreparedStatement pstmt = null;
        try{
              String qry=null;
            String itemName1=itemMastBean.getItemName();
            String category1=itemMastBean.getItemCategary();
            String Package1=itemMastBean.getItemPkg();
            String unit1=itemMastBean.getItemUnit();
            con= DbConnection.getConnection();
              qry="delete from "+tableName+" where "+iname+"=? and "+icategory+"=? and "+ipackage+"=? and "+iunit+"=?";
               pstmt = con.prepareStatement(qry);
                pstmt.setString(1,itemMastBean.getItemName());
                 pstmt.setString(2,itemMastBean.getItemCategary());
                 pstmt.setString(3,itemMastBean.getItemPkg());
                 pstmt.setString(4,itemMastBean.getItemUnit());
                 
                 
              pstmt.executeUpdate();
        }
         catch(Exception ex)
         {
             ex.printStackTrace();
               JOptionPane.showMessageDialog(null, ex.getMessage());
         }
        
       // for(int i=0;i<DeleteList.size();i++){
       //     ItemMastBean deleteItemBean=DeleteList.get(i);
           
            }
        
     
    
      public boolean checkCodeInDBDao(ItemMastBean itemMastBean){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        boolean result=false;
        try{
            con= DbConnection.getConnection();
            String sql = "Select "+icode+" from "+tableName+" where "+iname+" !=? and "+ipackage+" !=? and "+iunit+" !=? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, itemMastBean.getItemName());
            pstmt.setString(2, itemMastBean.getItemPkg());
            pstmt.setString(3, itemMastBean.getItemUnit());
            rs=pstmt.executeQuery();
            while(rs.next()){
                if(rs.getString(icode)!=null){
                    if(rs.getString(icode).equals(itemMastBean.getCode())){
                        result=true;
                    }
                }
            }
          }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                pstmt.close();
                con.close();
            }catch(Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        return result;
    }
      public DefaultTableModel updateItemTableByItemNameDao(String itmName){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        DefaultTableModel dtm=null;
        try{
            con= DbConnection.getConnection();
            String sql = "Select "+icode+" as 'Item code',"+iname+" as 'Item name',"
                    +itype+" as 'Item type',"+icategory+" as 'Category',"+ipackage+" as 'Package',"
                   +iunit+" as 'Unit',"+iprice+" as 'Rate' from "
                    +tableName+" where del_by='' and Del_dt is null  and "+iprice+" != '0' and "+iname+" like ? order by "+iname; 
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, itmName+"%");
            rs = pstmt.executeQuery();
            dtm=(DefaultTableModel) DbUtils.resultSetToTableModel(rs);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
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
       public ArrayList<ItemMastBean> getItemDetailFrmNameDao(String itmName,String pkg,String unit,int a){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        ArrayList<ItemMastBean> arrayList=new ArrayList<ItemMastBean>();
        try{
            con= DbConnection.getConnection();
            String sql = "Select "+icode+","+id+","+iname+","
                        +itype+","+icategory+","+ipackage+","
                        +iunit+","+iprice+" from "
                        +tableName+" where "+iname+" ='"+itmName+"'";
            if(a==1){
                sql = "Select "+icode+",item_id,"+iname+","
                        +itype+","+icategory+","+ipackage+","
                        +iunit+","+iprice+" from "
                        +tableName+" where "+iname+" ='"+itmName+"' and "+ipackage+" ='"+pkg+"' and "+iunit+"  ='"+unit+"'";
            }
            pstmt = con.prepareStatement(sql);
            System.out.print(a+"   "+sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ItemMastBean updateItemBean=new ItemMastBean();
                updateItemBean.setCode(rs.getString(icode));           
                updateItemBean.setItemId(rs.getInt(id));
                updateItemBean.setItemName(rs.getString(iname));
                updateItemBean.setItemType(rs.getString(itype));
                updateItemBean.setItemCategary(rs.getString(icategory));
                updateItemBean.setItemPkg(rs.getString(ipackage));
                updateItemBean.setItemUnit(rs.getString(iunit));
                updateItemBean.setItemRate(rs.getString(iprice));
                arrayList.add(updateItemBean);
             }
        }catch (Exception ex){
           ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }finally{
            try{
                rs.close();
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return arrayList;
    }
    
}
