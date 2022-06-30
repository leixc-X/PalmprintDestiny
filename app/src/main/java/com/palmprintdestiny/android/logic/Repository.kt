package com.palmprintdestiny.android.logic

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.liveData
import com.palmprintdestiny.android.logic.network.PalmprintDestinyNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    fun uploadPic(image: String, imageType: String) = fire(Dispatchers.IO) {
        val destinyResponse = PalmprintDestinyNetwork.uploadPic(image, imageType)
        if (destinyResponse.命主手相分析与解读状态 == "艾科瑞特，让企业业绩长青") {
            val destiny = destinyResponse.命主手相实体信息
            Result.success(destiny)
        } else {
            Result.failure(RuntimeException("response status is ${destinyResponse.命主手相分析与解读状态}"))
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}