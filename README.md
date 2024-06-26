# 小型 Spring 框架

## 06 
 * BeanFactoryPostProcessor: 允许自定义修改 BeanDefinition 属性信息(创建 Bean对象之前)

 * BeanPostProcessor: 用于修改实例 Bean 对象的扩展点(初始化前后都提供了钩子函数)

调用法方: 

(1) BeanFactoryPostProcessor.postProcessBeanFactory(beanFactory)

(2) BeanFactory.addBeanPostProcessor(beanPostProcessor)

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

## 07 
本节主要实现 Bean对象从初始化钩子函数和销毁钩子函数

两个核心接口: 
* InitializingBean: 在 bean 属性赋值完之后调用
* DisposableBean: 在 bean 销毁之前调用, 类似于 Object#finalize()

实现的方式有两种:

> (1) 是通过实现者两个接口的方法 
>
> (2) xml文件中配置, 并指定调用的方法(注解本质原理也是这个, 只是简化了xml配置)

它们两个的实现是完全不同的:

(1) init-method是通过 `createBean` 时, 将其加在 `initializeBean` 方法中, bean 初始化前置处理器和后置处理器之间调用;

(2) destroy-method 是通过钩子函数实现的, **注册**在 `createBean` 时, 创建好 bean, 在 bean 被加入单例注册表之前, 在注册一个 destroy的钩子函数; 
**调用** 实在 context 执行 `close` 方法时候, 会调用 `destroySingletons` 方法, 执行单例注册表中的销毁方法.

总结: 两个方法实现不同的原因, bean 对象创建是自发性的, 容器是为了管理这个 Bean 对象的生命周期, 在创建好对象后执行 init-method 方法; 
销毁 Bean对象只能是通过钩子函数的方式, 在对象创建完成后(对于容器创建Bean没有结束), 去注册一个钩子函数, 当对象要销毁时(容器销毁对象), 
去注册表中获取到钩子函数进行调用; 用途: 销毁这里可以做一些该类对象需要做的销毁前的操作, 比如链接资源的释放...

## 08
本节主要实现 BeanFactory、Bean、BeanClassLoader、ApplicationContext 的标记
* Aware: 标记接口

作用: 标记 Bean对象, 可以给 Bean 对象重命名(指定BeanName)
* BeanFactoryAware: 标记bean工厂

用途: 事件机制使用到了

## 09 
本节主要实现 对象作用域和FactoryBean
对象作用域(scope): bean 对象是有单例(singleton)和原型(prototype)两种模式的

FactoryBean: 这是一个工厂Bean, 本质上还是一个Bean对象, 给外部暴露提供创建对象的接口.
场景: AOP 代理对象的创建、Web工厂

## 10
本节主要实现 事件机制

设计模式: 发布订阅模型

* 场景: 在设计业务流程时, 我们可以使用事件机制实现业务的解耦, 代码的封装. 
* 功能: 还提供了注解 @EventListener 将其代码段标注为监听器, 实现接受事件的handler处理逻辑, 并提供了condition条件方法, 过滤相同事件不同的业务状态(业务但支付完成触发事件, 对于创建这个handler是不会执行的)
* 比如: 业务处理完需要发消息通知下游, 发消息的逻辑就可以做成一种事件通知

## 11
Q&A

1. aop uml图? 每个类的介绍?
2. jdk和cglib都是动态代理区别是啥? spring默认使用的哪个? 

## 12
本节主要实现将 AOP 结合到Bean对象的创建

1.代理对象的创建com.knight.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation();

调用方: createBean的时候先去检查
实现方: DefaultAdvisorAutoProxyCreator#postProcessBeforeInstantiation

* 流程: 
> (1) 从 BeanFactory 中获取切点表达式通知者AspectJExpressionPointcutAdvisor遍历;
> 
> (2) match 到切面;
> 
> (3) 进行代理信息的构建, 然后使用动态代理构建代理对象(使用JDK动态代理给目标对象创建代理对象)
* 安全性: 只有类型是 通知-Advice/通知者-Advisor/切点-Pointcut 才可以进行代理

代理对象的创建:
> 1. 从 beanFactory 获取切点表达式类型的对象
> 
> 2. 遍历 切点表达式类型对象集合
> 
> (1) 判断此切点是否match住这个beanClass
> 
> (2) 如果匹配, 需要构建通知&通知器管理者 AdvisedSupport 去构建代理工厂 ProxyFactory
> 
> AdvisedSupport构建信息: 创建代理对象-TargetSource; 方法拦截器-MethodInterceptor; 方法匹配器-MethodMatcher; 是否需要代理对象-false


Q&A

Advice、Advisor、Advised都是什么接口?
> Advice 是 Aspect 在特定切点采取的操作, 表示"通知"; 
> 
> Advisor 是 Spring AOP的基础接口, 表示"通知者";
> 
> Advised 是 AOP 代理工厂配置类接口. 提供了操作和管理 Advice 和 Advisor 的能力.


### uml图
![事件机制](./uml/事件机制.png)


### 重点类
FactoryBeanRegistrySupport: FactoryBean 的注册支持. 
提供了一个Cache(Map)保存单例模式下FactoryBean创建的对象, 并整合了 DefaultSingletonBeanRegistry(单例管理者)

## 特殊
cglib 生成的类: className 中会包含 "$$"