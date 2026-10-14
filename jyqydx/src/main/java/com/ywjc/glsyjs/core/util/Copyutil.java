package com.ywjc.glsyjs.core.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;


public class Copyutil {
	/**
	 *制作人：吴老师   献给现在还看不懂的自己！！！
	 */
//	public static void main(String[] args) {
//		Field[] fields = Evalactrecordhis.class.getDeclaredFields();
//		List<String> columnlist = new ArrayList<String>();
//		Column a = null;
//		for (Field f : fields) {
//			a = f.getAnnotation(Column.class);
//			if(a!=null){
//				columnlist.add(a.name());
//			}
//		}
//		String s = null;
//		for(String column : columnlist){
//			s = column.substring(0,1).toUpperCase() + column.substring(1,column.length());
////			System.out.println("if(oldobject.get"+ s + "()!=null){"
////					+ "this.set"+s+"(oldobject.get"+s+"()}");
//		}
//	}
	public static void copyBeanNotNull2Bean(Object databean,Object tobean)throws Exception
	  {
	 PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(databean);
	      for (int i = 0; i < origDescriptors.length; i++) {
	          String name = origDescriptors[i].getName();
//	          String type = origDescriptors[i].getPropertyType().toString();
	          if ("class".equals(name)) {
	              continue; // No point in trying to set an object's class
	          }
	          if (PropertyUtils.isReadable(databean, name) &&PropertyUtils.isWriteable(tobean, name)) {
	              try {
	                  Object value = PropertyUtils.getSimpleProperty(databean, name);
	                  if(value!=null && !"".equals(value)){
	                	  PropertyUtils.setSimpleProperty(tobean, name, value);	                     
	                  }
	              }
	              catch (java.lang.IllegalArgumentException ie) {
	            	  System.err.println("IllegalArgumentException Occured");
	              }

	          }
	      }
	  }
}
