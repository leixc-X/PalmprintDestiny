package com.palmprintdestiny.android.logic.model

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DestinyService {
    //    @POST("data/create")
    //    fun createData(@Body data: Data): Call<ResponseBody>
    //    请求头添加自己的appcode
    @Headers("Authorization: APPCODE df73d9c058e24ef08d08ebe552c46f9d")
    @FormUrlEncoded  //这里用Multipart,不添加的话会引起崩溃反应
    @POST("ai_market/ai_ming_li_xuan_xue_zhi_shi_tu_pu/shou_xiang/v1")//请求方法为POST，里面为你要上传的url
    //注解用@Part，参数类型为List<MultipartBody.Part> 方便上传其它需要的参数或多张图片
//    fun uploadPic(@Part partLis: List<MultipartBody.Part>): Call<DestinyResponse>
    fun uploadPic(@Field("IMAGE") image: String,
                  @Field("IMAGE_TYPE") imageTpye: String): Call<DestinyResponse>
}

