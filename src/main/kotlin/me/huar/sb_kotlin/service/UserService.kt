package me.huar.sb_kotlin.service

import me.huar.sb_kotlin.domain.Result
import me.huar.sb_kotlin.domain.UsersEntity
import java.util.*

interface UserService {

    fun findUser(reqMap: HashMap<String, Any>?): Result

    fun addUser(usersEntity: UsersEntity?): Result
}