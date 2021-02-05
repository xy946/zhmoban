package com.zhzh.model.imexdw;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : yzc 
 * @date : 2020/12/29 16:16:40
 **/
@Data
public class Enclosure implements Serializable {

    /**
     * uid
     */
    private String uid;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件路径
     */
    private String url;

    private static final long serialVersionUID = 1L;
}