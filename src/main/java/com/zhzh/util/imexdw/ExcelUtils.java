package com.zhzh.util.imexdw;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.zhzh.constants.Constants;
import com.zhzh.util.exception.GlobalException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.out;

/**
 * @author : zhang sq
 * @date : 2020/10/10 9:43
 **/
public class ExcelUtils {

    /**
     * 导出execl
     * @param fileName execl文件名
     * @param response
     * @param workbook
     */
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            //文件以流形式返回前端下载
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/x-download");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
            workbook.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据接收的Excel文件来导入Excel,并封装成实体类

     * @param file 上传的文件
     * @param titleRows 表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass Excel实体类
     * @return
     */
    @SneakyThrows
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows,
                                          Class<T> pojoClass, String [] columnName) {
        List<T> list = null;
        if (file == null) {
            throw new GlobalException(Constants.PARAM_ERROR,"未获取到文件信息");
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        //文件后缀名校验
        if (!(fileName.endsWith("xls") || fileName.endsWith("xlsx"))) {
            throw new GlobalException(Constants.PARAM_ERROR,"文件格式不正确");
        }
        Workbook wb = fileName.endsWith("xls")==true?new HSSFWorkbook(file.getInputStream())
                    :new XSSFWorkbook(file.getInputStream());
            verificationExcelHeadLine(wb, columnName ,titleRows);
            ImportParams params = new ImportParams();
            params.setTitleRows(titleRows);
            params.setHeadRows(headerRows);
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        return list;
    }

    /**
     * list根据字段去重
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 表头效验
     * @param wb
     * @param columnName  表头字符串数组
     * @param headerRows   表头在哪行
     * @return
     */
    @SneakyThrows
    public static Boolean verificationExcelHeadLine(Workbook wb, String[] columnName,Integer headerRows){

            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(headerRows.intValue());
            if (row != null && row.getLastCellNum() >= columnName.length) {
                for (int idx = 0; idx < row.getLastCellNum(); idx++) {
                    String value = row.getCell(idx).getStringCellValue().replaceAll(" ","");
                    if (idx < columnName.length) {
                        if (StringUtils.isBlank(value) || !columnName[idx].equals(value)) {
                            throw new GlobalException(Constants.PARAM_ERROR,"标题行第" + (idx + 1) + "列名称错误！");
                        }
                    }
                }
            } else {
                throw new GlobalException(Constants.PARAM_ERROR,"上传文件首行不能为空或与模板的表格表头不一致！");
            }
        return true;
    }

    /**
     * 模板下载
     * @param fileName   文件名
     * @param response
     * @param pojoClass  实体
     * @param t
     */
    public static void downLoadExcel(String fileName, HttpServletResponse response, Class<?> pojoClass,
                                     List<?> t) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            Workbook workbook = ExcelExportUtil.exportExcel(
                    new ExportParams(null,null,
                            fileName.endsWith("xls")? ExcelType.HSSF:ExcelType.XSSF),
                    pojoClass, t);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出
     * @param fileName   文件名
     * @param response
     * @param pojoClass  实体
     * @param t          实体集合
     */
    public static void exportZhExcel(String fileName, HttpServletResponse response, Class<?> pojoClass,
                                     List<?> t) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            Workbook workbook = ExcelExportUtil.exportExcel(
                    new ExportParams(null,null,
                            fileName.endsWith("xls")?ExcelType.HSSF:ExcelType.XSSF),
                    pojoClass, t);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
