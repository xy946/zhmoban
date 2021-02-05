package com.zhzh.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author : zhang sq
 * @date : 2020/12/3 15:49
 **/

/**
 *  //@Document配置文档 indexName->索引 type->类型
 *  indexName:用来指定这个对象的转为json文档存入哪个索引中 ,要求Es服务器中之前不能存在此索引
 *  type :    用来指定在当前这个索引下创建的类型名称
 */
@Data
@Document(indexName = "myels",type = "member")
public class Member implements Serializable {
    private static final long serialVersionUID =1L;

    //Es使用 创建文档-->索引-->类型-->id
    @Id  //version的作用
    private String id;

    //Field用在属性上 ,代表mapping中的一个属性 一个字段 type:属性 用来指定字段的类型
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String address;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String phone;

    @Field(type = FieldType.Text)
    private String bir;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String six;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String home;
}
