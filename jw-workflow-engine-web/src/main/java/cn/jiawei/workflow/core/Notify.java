package cn.jiawei.workflow.core;

import java.util.List;

/**
 * @author : willian fu
 * @date : 2020/10/2
 * 流程消息通知接口，自定义抄送通知
 */
public interface Notify {
    /**
     * 普通消息
     * @param userId 通知谁
     * @param msg 通知谁
     * @return 通知发送结果
     */
    default boolean info(String userId, String msg){
        throw new RuntimeException("请实现 Notify.info 方法");
    }

    /**
     * 成功消息
     * @param userId 通知谁
     * @return 通知发送结果
     */
    default boolean success(String userId, String msg){
        throw new RuntimeException("请先实现 Notify.success 方法");
    }

    /**
     * 警告消息
     * @param userId 通知谁
     * @return 通知发送结果
     */
    default boolean warning(String userId, String msg){
        throw new RuntimeException("请先实现 Notify.warning 方法");
    }

    /**
     * 失败/错误消息
     * @param userId 通知谁
     * @return 通知发送结果
     */
    default boolean fail(String userId, String msg){
        throw new RuntimeException("请先实现 Notify.fail 方法");
    }

}
