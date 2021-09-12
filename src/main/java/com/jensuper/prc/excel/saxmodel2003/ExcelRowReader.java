package com.jensuper.prc.excel.saxmodel2003;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
 

/**
 * 名称: ExcelRowReader.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年7月5日 上午10:30:11<br>
 * 
 * @since 2016年7月5日
 * @author “”
 */
public class ExcelRowReader implements IExcelRowReader {
 
    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowlist) {
//        System.out.print(curRow+" ");
        for (int i = 0; i < rowlist.size(); i++) {  
            System.out.print(rowlist.get(i)==""?"*":rowlist.get(i) + " ");
        }  
        System.out.println();  
    }
    
    public static void main(String[] args) {
        String s = "2019-1-1";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(dateFormat.parse(s));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
}
