package me.huar.sb_kotlin.domain

import lombok.Data

@Data
class Result(var code: Int?, var message: String?, var flag: Boolean, var data: Any?)
