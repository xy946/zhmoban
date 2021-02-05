package com.zhzh.util.imexdw;


import com.alibaba.fastjson.JSONArray;
import com.zhzh.constants.common.Constants;
import com.zhzh.model.imexdw.Enclosure;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author : zhang sq
 * @date : 2020/12/10 16:37
 **/
public class ZipUtil {

    /**
     * 附件压缩文件下载
     * @param response
     * @param jsonArray
     * @throws Exception
     */
    public static void downZip(HttpServletResponse response,String jsonArray){

        FileInputStream fin = null;
        ZipOutputStream zos = null;
        try {
            List<Enclosure> fjList = JSONArray.parseArray(jsonArray, Enclosure.class);
            response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''"
                    + URLEncoder.encode("附件.zip", "UTF-8"));
            response.setContentType("application/zip;charset=utf-8");
            response.setCharacterEncoding("utf-8");

            zos = new ZipOutputStream(response.getOutputStream());
            for (Enclosure enclosure : fjList) {
                fin = new FileInputStream(Constants.ZIP_PATH + enclosure.getName());
                ZipEntry zipEntry = new ZipEntry(enclosure.getName());
                zos.putNextEntry(zipEntry);
                int length = 0;
                byte[] buffer = new byte[1024];
                while ((length = fin.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(zos != null){
                    zos.close();
                }
                if(fin != null){
                    fin.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
