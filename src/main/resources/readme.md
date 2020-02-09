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
        
   流程:
     根据请求 查找对应的RequestMappingHandleMapping以及拦截器链
     
