package com.zhzh.util.imexdw;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.zhzh.constants.common.Constants;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.boot.system.ApplicationHome;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

public class WordUtil {

    public static void exportWord(HttpServletResponse response,Map<String,Object> map){

        OutputStream out = null;
        XWPFDocument doc = null;
        try{
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-word");
            response.setHeader("Content-Disposition",
                    "attachment;filename=export.docx");
            ApplicationHome wurl = new ApplicationHome(WordUtil.class);
            String mobanUrl = wurl.getSource().getParentFile().toString()+Constants.WORDMB_PATH;
            doc = WordExportUtil.exportWord07(mobanUrl , map);
            out = response.getOutputStream();
            doc.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (doc != null) {
                    doc.close();
                }
                if (out != null) {
                    out.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
