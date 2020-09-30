package me.huar.sb_kotlin.service.impl

import me.huar.sb_kotlin.domain.Result
import me.huar.sb_kotlin.domain.UsersEntity
import me.huar.sb_kotlin.repository.UserRepository
import me.huar.sb_kotlin.security.JWTUtil
import me.huar.sb_kotlin.security.RedisUtil
import me.huar.sb_kotlin.service.UserService
import me.huar.sb_kotlin.utils.DateUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserServiceImpl : UserService {
    @Autowired
    val userRepository: UserRepository? = null

    @Autowired
    val jwtUtil:JWTUtil? = null

    @Autowired
    val redisUtil:RedisUtil? = null

    override fun addUser(usersEntity: UsersEntity?): Result {
        try {
            usersEntity?.uuid = DateUtil.getCurrentDate("yyyyMMddHHmmss")
            val result = userRepository?.save(usersEntity!!) //插入单个对象
            if (result != null) {
                return Result(200, "注册成功", true, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Result(400, "参数异常", false, null)
    }

    override fun findUser(reqMap: HashMap<String, Any>?): Result {
        try {
            val result = userRepository?.userLogin(reqMap)
            return if (result!!.size == 1) {
                val username = result[0].username
                val userId = result[0].id.toString()
                //登录成功，签发token
                val token = jwtUtil!!.createToken(mapOf("username" to username))
                //存入redis缓存
                redisUtil!!.setValue(username!!,token)
                result[0].token = token
                Result(200, "登录成功", true, result[0])
            } else {
                Result(400, "用户名或密码错误", false, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Result(400, "参数异常", false, null)
    }

}