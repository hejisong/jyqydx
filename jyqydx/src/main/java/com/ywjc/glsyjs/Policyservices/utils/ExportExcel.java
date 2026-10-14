package com.ywjc.glsyjs.Policyservices.utils;

import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import javafx.beans.property.SimpleMapProperty;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.awt.*;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExportExcel {



    // 导出数据
    public  HSSFWorkbook export(String title, String[] rowName, List<Object[]> dataList,String infotype)  {


        try {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);

        // 设置表格默认列宽度为15个字符
        sheet.setDefaultColumnWidth(15);
        if("pay".equals(infotype)){
            sheet.setColumnWidth(0,9000);
            sheet.setColumnWidth(1,6000);
            sheet.setColumnWidth(2,6000);
            sheet.setColumnWidth(3,10000);
            sheet.setColumnWidth(4,14000);
            sheet.setColumnWidth(5,10000);

        }else if("file".equals(infotype)){
            sheet.setColumnWidth(0,20000);
            sheet.setColumnWidth(1,10000);
            sheet.setColumnWidth(2,8000);


        } else{
            sheet.setColumnWidth(0,11000);
            sheet.setColumnWidth(1,7000);
            sheet.setColumnWidth(2,10000);
            sheet.setColumnWidth(4,12000);
            sheet.setColumnWidth(3,14000);
        }

        // 标题样式
        CellStyle bigTitleStyle = bigTitle(workbook);
        // 内容样式
        CellStyle contentStyle = contentstyle(workbook);

        CellStyle contentstylebig = contentstylebig(workbook);

        // 行高
//        short rowHeight_x = 700;

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTitle = rowm.createCell(0);


            //sheet样式定义【】
            HSSFCellStyle columnTopStyle=this.getColumnTopStyle(workbook);
            HSSFCellStyle style=this.getStyle(workbook);
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            cellTitle.setCellStyle(bigTitleStyle);
            cellTitle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(2);
//            rowRowName.setHeight(rowHeight_x);
            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n);
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);
                cellRowName.setCellStyle(contentstylebig);

            }
            // 将查询到的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);// 遍历每个对象
                HSSFRow row = sheet.createRow(i + 3);// 创建所需的行数
//                row.setHeight(rowHeight_x);
                for (int j = 0; j < rowName.length; j++) {
                    HSSFCell cell = null;
                    cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());
                    }else{
                        cell.setCellValue("");
                    }
                    cell.setCellStyle(contentStyle);

                }

            }

            return workbook;

        } catch (Exception e) {

        }

        return null;

    }




    // 导出数据
    public HSSFWorkbook exportfile(String title, String[] rowName, List<Object[]> dataList,String infotype)  {


        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(title);

            // 设置表格默认列宽度为15个字符
            sheet.setDefaultColumnWidth(15);
            if("file".equals(infotype)){
                sheet.setColumnWidth(0,20000);
                sheet.setColumnWidth(1,10000);
                sheet.setColumnWidth(2,8000);
            }

            // 标题样式
            CellStyle bigTitleStyle = bigTitle(workbook);
            // 内容样式
            CellStyle contentStyle = contentstyle(workbook);

            CellStyle contentstylebig = contentstylebig(workbook);

            // 行高
        short rowHeight_x = 600;

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTitle = rowm.createCell(0);


            //sheet样式定义【】
            HSSFCellStyle columnTopStyle=this.getColumnTopStyle(workbook);
            HSSFCellStyle style=this.getStyle(workbook);
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            cellTitle.setCellStyle(bigTitleStyle);
            cellTitle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(2);
            rowRowName.setHeight(rowHeight_x);
            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n);
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);
                cellRowName.setCellStyle(contentstylebig);

            }
            // 将查询到的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);// 遍历每个对象
                HSSFRow row = sheet.createRow(i + 3);// 创建所需的行数
                row.setHeight(rowHeight_x);
                for (int j = 0; j < rowName.length; j++) {
                    HSSFCell cell = null;
                    cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());
                    }else{
                        cell.setCellValue("");
                    }
                    cell.setCellStyle(contentStyle);

                }

            }

            return workbook;

        } catch (Exception e) {

        }

        return null;

    }




    // 导出数据
    public HSSFWorkbook exportdetail(String title, String[] rowName, PolicyFileEntity policyFile, List<Object[]> details)  {


        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(title);

            // 设置表格默认列宽度为15个字符
            sheet.setDefaultColumnWidth(15);
            sheet.setColumnWidth(0,8000);
            sheet.setColumnWidth(1,8000);
            sheet.setColumnWidth(2,15000);
            sheet.setColumnWidth(3,15000);
            sheet.setColumnWidth(8,20000);
            // 标题样式
            CellStyle bigTitleStyle = bigTitle(workbook);
            // 内容样式
            CellStyle contentStyle = contentstyle(workbook);

            CellStyle contentstylebig = contentstylebig(workbook);

            // 行高
            short rowHeight_x = 600;

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTitle = rowm.createCell(0);

            //sheet样式定义【】
            HSSFCellStyle columnTopStyle=this.getColumnTopStyle(workbook);
            HSSFCellStyle style=this.getStyle(workbook);
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            cellTitle.setCellStyle(bigTitleStyle);
            cellTitle.setCellValue(policyFile.getPolicyname());

   /*         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

            String filelevel="";
            String releasetime="";
            filelevel="1".equals(policyFile.getFilelevel())?"市级":"区级";
            if(policyFile.getReleasetime()!=null){
                releasetime=sdf.format(policyFile.getReleasetime());
            }

            HSSFRow rowm1 = sheet.createRow(2);
            HSSFCell cell10 = rowm1.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 3));
            cell10.setCellStyle(contentstylebig);
            cell10.setCellValue("政策级别："+filelevel);

            HSSFCell cell11 = rowm1.createCell(4);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 8));
            cell11.setCellStyle(contentstylebig);
            cell11.setCellValue("生效日期："+releasetime);

            HSSFRow rowm2 = sheet.createRow(3);
            HSSFCell cell20 = rowm2.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 3));
            cell20.setCellStyle(contentstylebig);
            cell20.setCellValue("文号："+policyFile.getFwtitanic());

            HSSFCell cell21 = rowm.createCell(4);
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 4, 8));
            cell21.setCellStyle(contentstylebig);
            cell21.setCellValue("发文单位："+policyFile.getFwunits());*/


            // 定义所需列数
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(2);
//            rowRowName.setHeight(rowHeight_x);
            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n);
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);
                cellRowName.setCellStyle(contentstylebig);

            }
            // 将查询到的数据设置到sheet对应的单元格中
            for (int i = 0; i < details.size(); i++) {
                Object[] obj = details.get(i);// 遍历每个对象
                HSSFRow row = sheet.createRow(i + 3);// 创建所需的行数
//                row.setHeight(rowHeight_x);
                for (int j = 0; j < rowName.length; j++) {
                    HSSFCell cell = null;
                    cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());
                    }else{
                        cell.setCellValue("");
                    }
                    cell.setCellStyle(contentStyle);

                }

            }

            return workbook;

        } catch (Exception e) {

        }

        return null;

    }




    // 导出数据
    public HSSFWorkbook exportfiledetail(String title, String[] rowName, String[] rowNameinfo,String[] rowNamedetail, List<Object[]> dataList)  {


        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(title);

            // 设置表格默认列宽度为15个字符
            sheet.setDefaultColumnWidth(15);
            sheet.setColumnWidth(0,12000);
            sheet.setColumnWidth(6,8000);
            sheet.setColumnWidth(7,8000);
            sheet.setColumnWidth(8,15000);
            sheet.setColumnWidth(9,15000);
            sheet.setColumnWidth(15,20000);
            // 标题样式
            CellStyle bigTitleStyle = bigTitle(workbook);
            // 内容样式
            CellStyle contentStyle = contentstyle(workbook);

            CellStyle contentstylebig = contentstylebig(workbook);

            // 行高
            short rowHeight_x = 500;

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTitle = rowm.createCell(0);


            //sheet样式定义【】
            HSSFCellStyle columnTopStyle=this.getColumnTopStyle(workbook);
            HSSFCellStyle style=this.getStyle(workbook);
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            cellTitle.setCellStyle(bigTitleStyle);
            cellTitle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(2);
            rowRowName.setHeight(rowHeight_x);
            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n);
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);
                cellRowName.setCellStyle(contentstylebig);

            }
            // 将查询到的数据设置到sheet对应的单元格中
            int rownum = 3;
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);// 遍历每个对象
                List<Object[]>  details =(  List<Object[]>  ) obj[6];

                if(details!=null&&details.size()>0){
                    int rowspan = details.size();
                    HSSFRow row = sheet.createRow(rownum);// 创建所需的行数
//                    row.setHeight(rowHeight_x);
                    setValueinfo(row,rowNameinfo,obj,contentStyle,sheet,rownum,rownum + rowspan - 1);

                    int n=0;
                    for ( Object[]  detail:details) {
                        if(n==0){
                            setValue(row,rowNamedetail,detail,contentStyle,rowNameinfo.length,rowName.length);
                        }else{
                            HSSFRow rowx = sheet.createRow(rownum);// 创建所需的行数
//                            rowx.setHeight(rowHeight_x);
                            setValue(rowx,rowNamedetail,detail,contentStyle,rowNameinfo.length,rowName.length);
                        }
                        n++;
                        rownum++;
                    }

                }else{
                    HSSFRow row = sheet.createRow(rownum);// 创建所需的行数
//                    row.setHeight(rowHeight_x);
                    setValue(row,rowNameinfo,obj,contentStyle,0,rowNameinfo.length);
                    rownum++;
                }



            }

            return workbook;

        } catch (Exception e) {

        }

        return null;

    }





    private void setValueinfo( HSSFRow row, String[] rowname,Object[] obj,CellStyle style,HSSFSheet sheet,int startRow,int lastRow){

        for (int j = 0; j < rowname.length; j++) {
            HSSFCell cell = null;
            cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
            if (!"".equals(obj[j]) && obj[j] != null) {
                cell.setCellValue(obj[j].toString());
            }else{
                cell.setCellValue("");
            }
            cell.setCellStyle(style);
            sheet.addMergedRegion(new CellRangeAddress(startRow, lastRow, j, j));
        }
    }


    private void setValue( HSSFRow row, String[] rowname,Object[] obj,CellStyle style,int startint,int endint){
        int k=0;
        for (int j = startint; j < endint; j++) {
            HSSFCell cell = null;
            cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
            if (!"".equals(obj[k]) && obj[k] != null) {
                cell.setCellValue(obj[k].toString());
            }else{
                cell.setCellValue("");
            }
            cell.setCellStyle(style);
            k++;
        }
    }


    /*
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();

        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置低边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置低边框颜色
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置右边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框颜色
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式中应用设置的字体
        style.setFont(font);
        // 设置自动换行
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐；
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;

    }

    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 10);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }


    // 大标题的样式
    public CellStyle bigTitle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字体加粗
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中
        return style;
    }


    public CellStyle contentstylebig(Workbook workbook) {
        // 创建表格样式
        CellStyle style = workbook.createCellStyle();
        // 上下左右四个边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        // 水平和垂直居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 自动换行
        style.setWrapText(true);
        // 生成另一个字体
        Font font = workbook.createFont();
        // 生成另一个字体
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字体加粗
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }


    public CellStyle contentstyle(Workbook workbook) {
        // 创建表格样式
        CellStyle style = workbook.createCellStyle();
        // 上下左右四个边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        // 水平和垂直居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 自动换行
        style.setWrapText(true);
        // 生成另一个字体
        Font font = workbook.createFont();
        // 生成另一个字体
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }



}
