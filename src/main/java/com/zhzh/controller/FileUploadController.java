package com.zhzh.controller;


import com.zhzh.service.FileUploadService;
import com.zhzh.util.model.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/common/upfile")
@Api(tags = "文件上传" ,description = "FileUploadController")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * @api {post}  /common/upfile/fileUpload  单个上传
     * @apiName fileUpload
     * @apiGroup 文件上传
     * @apiVersion 0.1.0
     * @apiDescription 单个上传
     *
     * @apiParam {MultipartFile}      file   文件(必填)
     *
     * @apiSuccess {String}  imgUrl         文件访问地址
     * @apiSuccess {String}  picName        文件新名称
     * @apiSuccess {String}  oldFileName    文件旧名称
     * @apiSuccess {String}  resultCode 结果码
     * @apiSuccess {String}  resultMsg  消息说明
     * @apiSuccess {String}  data 数据组合
     * @apiSuccessExample Success-Response:
     *  HTTP/1.1 200 OK
     *	    {
     *          "resultCode": "00000000",
     *          "resultMsg": "SUCCESS",
     *          "data": {
     *              "imgUrl": "http://192.168.1.13:8999/zhly/882ee86715cb4fb695df73b094fe5cc1.jpg",
     *              "picName": "882ee86715cb4fb695df73b094fe5cc1.jpg",
     *              "oldFileName": "000.jpg"
     *          }
     *      }
     *
     *  @apiErrorExample {json} Error-Response:
     *  HTTP/1.1 404 Not Found
     *    {
     *		"resultCode": "99999999",
     *		"data": "",
     *		"resultMsg": "操作失败，异常信息为：参数id不能为空"
     *	  }
     */
    /**
     * {"code":"0000","imgUrl":"http://101.201.211.163:89/web/9f9034.jpg","msg":"文件上传成功","oldFileName":"timg.jpg","picName":"9f9034.jpg"}
     * @param file
     * @return
     */
    @PostMapping(value = "/fileUpload")
    @ApiOperation("单个上传")
    public ResultVO fileUpload(MultipartFile file){
        return fileUploadService.handleFileUpload(file);
    }

}
