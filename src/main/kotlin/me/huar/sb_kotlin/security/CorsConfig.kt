package me.huar.sb_kotlin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

/**
 * 跨域配置，允许跨域
 */
@Configuration
class CorsConfig {
    private fun buildConfig(): CorsConfiguration {
        val corsConfiguration = CorsConfiguration()
        // 1允许服务端访问
        corsConfiguration.addAllowedOrigin("*")
        // 1.1允许本地访问
        corsConfiguration.addAllowedOrigin("*")
        // 2允许任何头
        corsConfiguration.addAllowedHeader("*")
        // 3允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*")
        // 4 允许withCredentials报文头
        corsConfiguration.allowCredentials = true
        return corsConfiguration
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", buildConfig())
        return CorsFilter(source)
    }
}