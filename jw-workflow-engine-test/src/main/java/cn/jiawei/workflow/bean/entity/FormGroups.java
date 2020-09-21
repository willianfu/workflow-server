package cn.jiawei.workflow.bean.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * (FormGroups)实体类
 *
 * @author makejava
 * @since 2020-09-21 22:49:33
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "form_groups")
public class FormGroups implements Serializable {
    private static final long serialVersionUID = -50696449296875480L;
    
    @Id
    /**
    * id
    */
    private Integer groupId;
    /**
    * 组名
    */
    private String groupName;
    /**
    * 排序号
    */
    private Integer sortNum;
    /**
    * 创建时间
    */
    private Date created;
    /**
    * 更新时间
    */
    private Date updated;

}