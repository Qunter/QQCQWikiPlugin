package dao.daoImp;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExcelUtil implements dao.ExcelUtil {

    @Override
    public List<List<String>> readExcel(File file) {
        try {
        // 创建输入流，读取Excel
        InputStream is = new FileInputStream(file.getAbsolutePath());
        // jxl提供的Workbook类
        Workbook wb = Workbook.getWorkbook(is);
        // 只有一个sheet,直接处理
        //创建一个Sheet对象
        Sheet sheet = wb.getSheet(0);
        // 得到所有的行数
        int rows = sheet.getRows();
        // 所有的数据
        List<List<String>> allData = new ArrayList<>();
        for (int j = 1; j < rows; j++) {
            List<String> oneData = new ArrayList<>();
            // 得到每一行的单元格的数据
            Cell[] cells = sheet.getRow(j);
            Arrays.stream(cells).forEach(x->oneData.add(x.getContents().trim()));
            // 存储每一条数据
            allData.add(oneData);
        }
            return allData;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
