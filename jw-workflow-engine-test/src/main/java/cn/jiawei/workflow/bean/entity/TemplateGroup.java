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
 * (TemplateGroup)实体类
 *
 * @author makejava
 * @since 2020-09-21 22:50:41
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TemplateGroup")
public class TemplateGroup implements Serializable {
    private static final long serialVersionUID = 296474043591142508L;
    
    @Id
    private Integer id;
    
    private String templateId;
    
    private Integer groupId;
    
    private Integer sortNum;
    
    private Date created;
}