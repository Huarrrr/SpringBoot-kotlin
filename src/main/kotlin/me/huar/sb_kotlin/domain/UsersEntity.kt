package me.huar.sb_kotlin.domain

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Data
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users", schema = "spring_kotlin")
@Data
open class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    open var id: Int? = null

    @Column(name = "uuid")
    open var uuid: String? = null

    @Column(name = "username")
    open var username: String? = null

    @Column(name = "password")
    open var password: String? = null

    @Column(name = "user_phone")
    open var user_phone: String? = null

    @Column(name = "user_birthday")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    open var user_birthday: Date? = null

    @Column(name = "user_avatar")
    open var user_avatar: String? = null


    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "uuid = $uuid " +
                    "username = $username " +
                    "password = $password " +
                    "user_phone = $user_phone " +
                    "user_birthday = $user_birthday " +
                    "user_avatar = $user_avatar " +
                    ")"


}

