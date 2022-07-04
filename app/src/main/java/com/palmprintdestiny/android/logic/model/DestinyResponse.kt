package com.palmprintdestiny.android.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class DestinyResponse(var 命主手相分析与解读状态: String, val 命主手相实体信息: Result) {

    data class Result(val 命主手相掌型实体信息: EntityInformation)
    data class EntityInformation(
        val 掌型名称: String, val 掌型解读: String, val 掌型五行名称: String,
        val 掌型命理解读: PalmInterpretation
    )

    data class PalmInterpretation(
        val 学业: DataInterpretation, val 事业: DataInterpretation, val 财运: DataInterpretation,
        val 健康: DataInterpretation, val 家庭: DataInterpretation, val 婚姻: DataInterpretation
    )

    data class DataInterpretation(
        val 格局: String,
        val 威胁与挑战: String,
        val 有利于: String,
        val 宜从事: String
    )

}