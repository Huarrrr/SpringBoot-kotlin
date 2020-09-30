package me.huar.sb_kotlin.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

@Component
class RedisUtil {
    @Autowired
    private val stringRedisTemplate: StringRedisTemplate? = null

    @Resource
    private val redisTemplate: RedisTemplate<String, Any>? = null

    /**
     * string类型
     */
    //删除key
    fun deleteKey(key: String) {
        stringRedisTemplate!!.delete(key)
    }

    //判断key存在
    fun keyIsExists(key: String): Boolean {
        return stringRedisTemplate!!.hasKey(key)
    }

    //设置key失效时间,以秒为单位
    fun setKeyTimeOut(key: String, second: Long) {
        stringRedisTemplate!!.expire(key, second, TimeUnit.SECONDS)
    }

    //设置值
    fun setValue(key: String, `val`: String?) {
        stringRedisTemplate!!.boundValueOps(key).set(`val`!!)
    }

    //获取值
    fun getValue(key: String): String? {
        return stringRedisTemplate!!.boundValueOps(key).get()
    }

    /**
     * object类型
     */
    //存入对象
    fun setObject(key: String, obj: Any) {
        getRedisTemplate()!!.boundValueOps(key).set(obj)
    }

    //获取对象
    fun getObject(key: String): Any? {
        return getRedisTemplate()!!.boundValueOps(key).get()
    }

    //删除对象
    fun delObject(key: String) {
        getRedisTemplate()!!.delete(key)
    }

    //设置对象过期时间
    fun setObjectTimeOut(key: String, second: Long) {
        getRedisTemplate()!!.expire(key, second, TimeUnit.SECONDS)
    }

    //判断对象是否存在
    fun objectIsExists(key: String): Boolean {
        return getRedisTemplate()!!.hasKey(key)
    }

    //获取RedisTemplate,把key进行string序列化,那么在库中就显示的原始的key值，否则就是16进制的值不方便。
    private fun getRedisTemplate(): RedisTemplate<String, Any>? {
        //设置key是string类型的序列
        redisTemplate!!.keySerializer = StringRedisSerializer()
        //设置hashKey是string类型的序列
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        return redisTemplate
    }
}