package cn.jiawei.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author : willian fu
 * @version : 1.0
 */
@MapperScan("cn.jiawei.workflow.mapper")
@SpringBootApplication
public class WorkflowEngineStater {
    public static void main(String[] args) {
        SpringApplication.run(WorkflowEngineStater.class, args);
    }
}
