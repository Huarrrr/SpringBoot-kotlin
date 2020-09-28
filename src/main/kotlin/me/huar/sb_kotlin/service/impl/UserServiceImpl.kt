package me.huar.sb_kotlin.service.impl

import me.huar.sb_kotlin.domain.Result
import me.huar.sb_kotlin.domain.UsersEntity
import me.huar.sb_kotlin.repository.UserRepository
import me.huar.sb_kotlin.service.UserService
import me.huar.sb_kotlin.utils.DateUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.HashMap

@Service
@Transactional
class UserServiceImpl : UserService {
    @Autowired
    val userRepository: UserRepository? = null

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
                Result(200, "登录成功", true, null)
            } else {
                Result(400, "用户名或密码错误", false, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Result(400, "参数异常", false, null)
    }

}