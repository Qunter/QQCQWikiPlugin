package cqwiki.cqp.me.dao;

import java.io.File;
import java.util.List;

public interface ExcelUtil {
      List<List<String>> readExcel(File file);
}
