package me.huar.sb_kotlin.security

import com.auth0.jwt.exceptions.AlgorithmMismatchException
import com.auth0.jwt.exceptions.InvalidClaimException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.servlet.HandlerInterceptor
import java.io.IOException
import java.io.PrintWriter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JWTInterceptor : HandlerInterceptor {
    @Value("\${jwt.config.header}")
    private val header: String? = null

    @Autowired
    private val jwtUtil: JWTUtil? = null

    @Autowired
    private val redisUtil: RedisUtil? = null

    //在请求之前进行拦截
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        //获取请求头信息
        var token = request.getHeader(header)

        //解析token
        if (!StringUtils.isEmpty(token)) {
            val userInfo = jwtUtil!!.getLoginUser(request)
            val tempToken = redisUtil!!.keyIsExists("${userInfo["username"]}")

            //从redis获取token副本，解决退出问题。当用户退出后，清空redis的token

            if (tempToken) {
                val redisToken = redisUtil.getValue("${userInfo["username"]}")
                //判断副本和原token是否相同
                if (redisToken != token) {
                    token = null
                }
            } else {
                token = null
            }
        }

        //如果token为空直接返回错误信息
        if (StringUtils.isEmpty(token)) {
            returnJson(response, "{\"code\":403,\"message\":\"请求未授权，无法访问资源！\"}")
        } else {
            try {
                //验证token
                jwtUtil!!.verifyToken(token)
                //验证通过就放行
                return true
            } catch (e: SignatureVerificationException) {
                //签名不一致
                returnJson(response, "{\"code\":403,\"message\":\"请求失败，签名不一致！\"}")
            } catch (e: TokenExpiredException) {
                //token过期
                e.printStackTrace()
                returnJson(response, "{\"code\":403,\"message\":\"请求失败，token已过期！\"}")
            } catch (e: AlgorithmMismatchException) {
                //验证算法不一致
                e.printStackTrace()
                returnJson(response, "{\"code\":403,\"message\":\"请求失败，验证算法不一致！\"}")
            } catch (e: InvalidClaimException) {
                //payload失效
                e.printStackTrace()
                returnJson(response, "{\"code\":403,\"message\":\"请求失败，token已失效！\"}")
            } catch (e: Exception) {
                //其他异常
                e.printStackTrace()
                returnJson(response, "{\"code\":403,\"message\":\"认证失败，无法访问资源！\"}")
            }
        }
        return false
    }

    private fun returnJson(response: HttpServletResponse, json: String) {
        var writer: PrintWriter? = null
        response.characterEncoding = "UTF-8"
        response.contentType = "application/json; charset=utf-8"
        try {
            writer = response.writer
            writer.print(json)
        } catch (e: IOException) {
        } finally {
            writer?.close()
        }
    }
}