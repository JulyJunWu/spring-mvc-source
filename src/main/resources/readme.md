springMvc使用及源码分析:
    spring mvc核心接口组件:
        FrameworkServlet
        DispatcherServlet                       :  servlet实现类,主要是用来转发
        HandlerInterceptor                      :  拦截器
        HandlerInterceptorAdapter               :  拦截器适配器(其实已经不需要了,只是为了老版本兼容)
        HandlerMapping                          :  封装@RequeatMapping属性
        HandlerMethod                           :  封装含有@RequestMapping注解的方法
        HandlerAdapter                          :  适配器,用来执行controller
        MethodParameter                         :  将封装方法的形参
        HandlerMethodArgumentResolver           :  将请求参数转换为所需方法的参数
        HandlerMethodArgumentResolverComposite  :  组合模式,主要是封装多个HandlerMethodArgumentResolver
        RequestResponseBodyMethodProcessor　　　:　处理@RequestBody和@ResponseBody前后处理
            同时实现了HandlerMethodReturnValueHandler 和 HandlerMethodArgumentResolver
        HandlerExecutionChain                   :　封装拦截器，拦截请求
        HandlerExceptionResolver　　　　        :　异常解析

AOP:
    通过AnnotationAwareAspectJAutoProxyCreator类(BeanPostProcessor)对bean进行拦截代理     
    AnnotationAwareAspectJAutoProxyCreator
    AopAutoConfiguration
    AspectJAutoProxyRegistrar
代理源码:
    AnnotationAwareAspectJAutoProxyCreator.postProcessAfterInitialization对需要增强代理的类进行代理
        AbstractAutoProxyCreator.wrapIfNecessary
            AbstractAutoProxyCreator.createProxy
执行代理方法:
    DynamicAdvisedInterceptor.intercept : 
        AdvisedSupport.getInterceptorsAndDynamicInterceptionAdvice: 将增强器转换/包装成MethodInterceptor ,组成一个拦截器链
            DefaultAdvisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice
        CglibMethodInvocation.proceed(); 逐个执行拦截器链上的拦截器,根据索引下标来控制
            ExposeInvocationInterceptor.invoke ; 第一个拦截器
                1.1.设置CglibMethodInvocation到当前ThreadLocal中
                1.2.继续执行CglibMethodInvocation.proceed(),取出第二个拦截器,假设是AspectJAfterThrowingAdvice
            AspectJAfterThrowingAdvice.invoke: 第二个拦截器
                2.1 此拦截器就是捕捉异常,对异常进行处理
                2.2 当无异常时,继续执行CglibMethodInvocation.proceed();出异常时,在catch块中进行处理(执行我们定义的切面函数),最终还是抛出异常
            AspectJAroundAdvice.invoke: 第三个拦截器
                3.1 执行定义的@Around的函数,并在函数中执行目标方法
                3.2 执行目标方法时,重新进入CglibMethodInvocation.proceed(),执行invokeJoinpoint();
            最终拦截器3返回结果给拦截器2,拦截器2返回给拦截器1,最终结果返回
     
