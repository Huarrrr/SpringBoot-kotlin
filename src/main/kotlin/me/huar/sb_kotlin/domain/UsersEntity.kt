package me.huar.sb_kotlin.domain

import lombok.Data
import javax.persistence.*

@Entity
@Data
@Table(name = "users", schema = "spring_kotlin")
open class UsersEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Basic
    @Column(name = "uuid", nullable = true)
    open var uuid: String? = null

    @Column(name = "username", nullable = false)
    open var username: String? = null

    @Column(name = "password", nullable = false)
    open var password: String? = null

    @Column(name = "mobile", nullable = true)
    open var mobile: String? = null

    @Column(name = "birthday", nullable = true)
    open var birthday: java.sql.Timestamp? = null

    @Column(name = "avatar", nullable = true)
    open var avatar: String? = null

    @Column(name = "email", nullable = true)
    open var email: String? = null

    @Column(name = "level", nullable = true)
    open var level: Int? = null

    @Column(name = "gender", nullable = true)
    open var gender: Int? = null

    @Column(name = "avatar_text", nullable = true)
    open var avatar_text: String? = null

    @Column(name = "token", nullable = true)
    open var token: String? = null

    @Column(name = "createtime", nullable = true)
    open var createtime: java.sql.Timestamp? = null


    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "uuid = $uuid " +
                    "username = $username " +
                    "password = $password " +
                    "mobile = $mobile " +
                    "birthday = $birthday " +
                    "avatar = $avatar " +
                    "email = $email " +
                    "level = $level " +
                    "gender = $gender " +
                    "avatarText = $avatar_text " +
                    "token = $token " +
                    "createtime = $createtime " +
                    ")"
}

