package cn.jiawei.workflow.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    static class Template{

        private String id;

        private String name;

        private String icon;

        private String background;
    }


}
