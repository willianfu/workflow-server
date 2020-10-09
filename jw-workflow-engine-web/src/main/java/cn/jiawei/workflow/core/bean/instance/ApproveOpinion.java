package cn.jiawei.workflow.core.bean.instance;

import lombok.Data;

import java.util.List;

/**
 * @author : willian fu
 * @date : 2020/9/29
 * 审批意见/评论
 */
@Data
public class ApproveOpinion {

    //文字评论/意见
    private String content;

    //图片
    private List<String> images;

    //文件
    private List<Annex> annexes;

    //签名
    private String sign;

    @Data
    public static class Annex {

        //文件地址
        private String url;

        //文件全名
        private String name;
    }
}
