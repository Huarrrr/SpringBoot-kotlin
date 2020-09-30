package me.huar.sb_kotlin.repository

import me.huar.sb_kotlin.domain.UsersEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UsersEntity, Long> {
    @Query(value = "select * from users where mobile= :#{#map[\"mobile\"]} and password= :#{#map[\"password\"]}", nativeQuery = true)
    fun userLogin(@Param("map") map: HashMap<String, Any>?): MutableList<UsersEntity>?

}