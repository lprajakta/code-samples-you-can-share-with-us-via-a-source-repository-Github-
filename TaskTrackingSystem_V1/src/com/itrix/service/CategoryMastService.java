package com.itrix.service;

import java.util.List;

import com.itrix.dao.CategoryMastDao;
import com.itrix.model.CategoryBean;

public class CategoryMastService 
   { 
public boolean addCategorySer(CategoryBean catgBeanObj){	
      boolean status=false;
      CategoryMastDao CatgDaoObj=new CategoryMastDao();
      status=CatgDaoObj.addCategoryDao(catgBeanObj);
      return status;
   }
 
public List<CategoryBean> getCategorySer(int startPageIndex, int numRecordsPerPage)
   {

	CategoryMastDao CatgDaoObj=new CategoryMastDao();
	List <CategoryBean>list=CatgDaoObj.getCategoriesDao(startPageIndex, numRecordsPerPage);	
    return list; 
}
public boolean updateCategorySer(CategoryBean catgBeanObj) {
	boolean status=false;
	// TODO Auto-generated method stub
	CategoryMastDao CatgDaoObj=new CategoryMastDao();
	CatgDaoObj.updateCategoryDao(catgBeanObj);
    return status;
 }

public boolean deleteCategorySer(int catgId) {
   boolean status=false;
   CategoryMastDao CatgDaoObj=new CategoryMastDao();   
   status=CatgDaoObj.deleteCategoryDao(catgId);	
   return status;
  }

public int getCategoryCountSer() {
	// TODO Auto-generated method stub
	CategoryMastDao CatgDaoObj=new CategoryMastDao();
	int categoryCount=CatgDaoObj.getCategoryCountDao();
	return categoryCount;
}    

   
   
}
