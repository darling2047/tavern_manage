package com.manage.tavern.po.sys;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Table: TAVERN_HTTP_LOG
 */
@Data
public class TavernHttpLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String url;

    private String params;

    private String res;

    private Date createTime;

    private String busId;

    private static final long serialVersionUID = 1L;
}