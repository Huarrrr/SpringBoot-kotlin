package me.huar.sb_kotlin.security


import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JWTUtil {

    @Value("\${jwt.config.secret}")
    private val SIGN: String? = null

    @Value("\${jwt.config.expire}")
    private val expire: Int? = null

    @Value("\${jwt.config.header}")
    private val header: String? = null

    /**
     * 生成token
     * @param map
     * @return
     */
    fun createToken(map: Map<String?, String?>): String {

        //创建jwt构建器
        val builder = JWT.create()

        //设置token的过期时间
        val calendar = Calendar.getInstance()
        //默认是30分钟
        calendar.add(Calendar.MINUTE, expire!!)
        builder.withExpiresAt(calendar.time)

        //设置payload，存储需要的一些参数
        map.forEach { (key: String?, value: String?) -> builder.withClaim(key, value) }

        //加密后生成token
        return builder.sign(Algorithm.HMAC256(SIGN))
    }

    /**
     * 验证token，验证通过返回参数信息
     * @param token
     * @return
     */
    fun verifyToken(token: String?): DecodedJWT {
        val jwtVerifier = JWT.require(Algorithm.HMAC256(SIGN)).build()
        //验证token,如果验证失败会抛出异常
        return jwtVerifier.verify(token)
    }

    /**
     * 获取登录的用户信息
     * @param request
     * @return
     */
    fun getLoginUser(request: HttpServletRequest): Map<String, Any> {
        //获取请求头信息
        val token = request.getHeader(header)
        val decodedJWT = verifyToken(token)
        //获取payload中设置的参数
        val claims = decodedJWT.claims
        val result: MutableMap<String, Any> = HashMap()
        result["username"] = claims["username"]!!.asString()
        return result
    }
}