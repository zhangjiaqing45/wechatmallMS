使用Rabbitmq时，解除以下文件内容中的注释!!!
1、消息队列配置：com.fante.framework.rabbitmq.RabbitConfig
2、交换机和队列配置：com.fante.framework.rabbitmq.exConfig.*
3、交换机、队列和路由键枚举配置：com.fante.framework.rabbitmq.enums.*
4、测试生产者：com.fante.project.demo.sender.*
5、测试消费者：com.fante.project.demo.receiver.*
6、测试调用：com.fante.project.demo.controller.DemoRabbitMQController