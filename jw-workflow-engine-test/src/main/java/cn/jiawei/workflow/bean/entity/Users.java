package cn.jiawei.workflow.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2020-09-17 14:18:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = 147397842421109939L;
    /**
     * 用户id
     */
    @Id
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String alisa;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private Boolean sex;
    /**
     * 部门id，分隔
     */
    private String departmentIds;
    /**
     * 入职日期
     */
    private Date entryDate;
    /**
     * 离职日期
     */
    private Date leaveDate;
    /**
     * 管理级别 0=主管理员 1=子管理员 2=普通员工
     */
    private Integer admin;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;

}
