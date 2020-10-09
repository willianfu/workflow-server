package cn.jiawei.workflow.core.bean.template;

import cn.jiawei.workflow.core.enums.NodeTypeEnum;
import lombok.Data;

/**
 * @author : willian fu
 * @version : 1.0
 */
@Data
public class Node {

    protected NodeTypeEnum type;

    protected String name;

    protected String id;

    protected NodeProps props;
}
