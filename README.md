# 小型 Spring 框架

## 06 
BeanFactoryPostProcessor: 允许自定义修改 BeanDefinition 属性信息(创建 Bean对象之前)
BeanPostProcessor: 用于修改实例 Bean 对象的扩展点(初始化前后都提供了钩子函数)

上下文刷新流程: ConfigurableApplicationContext#refresh: 
> (1) 创建 BeanFactory, 并加载 BeanDefinition
> 
> (2) 获取 BeanFactory
> 
> (3) 在 Bean 实例化之前, 执行 BeanFactoryPostProcessor[调用 context 注册 beanFactoryProcessor]> 
> 
> (4) BeanPostProcessor 需要提前于其他 Bean 对象实例化之前进行注册
> 
> (5) 提前实例化单例 Bean 对象

创建 bean 对象: AbstractBeanFactory#createBean
> (1) 创建 bean 实例
> 
> (2) 给 bean 填充属性
> 
> (3) 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置发放
### 设计原则
可以参考: ClassPathXmlApplicationContext, 顶层接口定义了一个 refresh 方法, 
整个流程都抽象出来给抽象类, 最后只需要一个 configLocations, 构造最终子类, 调用顶层接口 refresh(公共流程+configLocations参数).