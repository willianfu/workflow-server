package cn.jiawei.workflow.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author : willian fu
 * @date : 2020/9/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateGroupVo {

    private Integer id;

    private String name;

    private List<Template> items;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Template{

        private String id;

        private Integer tgId;

        private String name;

        private String icon;

        private Boolean isStop;

        private String remark;

        private String background;

        private Date updated;
    }


}
