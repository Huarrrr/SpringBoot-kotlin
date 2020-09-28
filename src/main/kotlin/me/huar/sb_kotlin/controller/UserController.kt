package me.huar.sb_kotlin.controller

import me.huar.sb_kotlin.domain.Result
import me.huar.sb_kotlin.domain.UsersEntity
import me.huar.sb_kotlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    val userService: UserService? = null

    @PostMapping("userLogin")
    @ResponseBody
    fun userLogin(@RequestBody reqMap: HashMap<String, Any>?): Result {
        return userService?.findUser(reqMap)!!

    }


    @PostMapping("userRegister")
    @ResponseBody
    fun userLogin(@RequestBody usersEntity: UsersEntity?): Result {
        return userService?.addUser(usersEntity)!!

    }
}