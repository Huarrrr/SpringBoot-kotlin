package me.huar.sb_kotlin.controller

import me.huar.sb_kotlin.domain.Result
import me.huar.sb_kotlin.domain.UsersEntity
import me.huar.sb_kotlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    val userService: UserService? = null

    @PostMapping("/userLogin")
    @ResponseBody
    fun userLogin(@RequestBody reqMap: HashMap<String, Any>?): Result {
        return userService?.findUser(reqMap)!!
    }


    @PostMapping("/userRegister")
    @ResponseBody
    fun userRegister(@RequestBody usersEntity: UsersEntity?): Result {
        return userService?.addUser(usersEntity)!!

    }

    @GetMapping("/getAd")
    @ResponseBody
    fun getAd(): Result {
        return Result(200,"获取成功",true, "http://119.29.104.217:8087/image/24115f36-08f3-44e6-9cad-c61941324468.jpg")
    }
}