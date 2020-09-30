package me.huar.sb_kotlin.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class InterceptorConfig : WebMvcConfigurationSupport() {
    @Autowired
    private val interceptor: JWTInterceptor? = null

    //从配置文件读取忽略的url,不拦截这些请求
    @Value("\${system.IgnoreUrl}")
    private val ignoreUrl: String? = null

    //添加拦截器
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interceptor!!).addPathPatterns("/**")//拦截所有请求
                .excludePathPatterns("/**$ignoreUrl/**")//不拦截的请求
    }
}