package com.palmprintdestiny.android.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class DestinyResponse(var 命主手相分析与解读状态: String, val 命主手相实体信息: Result) {

    data class Result(val 命主手相掌型实体信息: EntityInformation)
    data class EntityInformation(val 掌型名称: String, val 掌型解读:String, val 掌型五行属性: String)

}