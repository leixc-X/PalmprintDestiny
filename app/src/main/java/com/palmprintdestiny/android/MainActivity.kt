package com.palmprintdestiny.android

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.Image
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import com.palmprintdestiny.android.logic.Repository
import com.palmprintdestiny.android.logic.model.DestinyResponse
import com.palmprintdestiny.android.logic.model.DestinyService
import com.palmprintdestiny.android.logic.network.ServiceCreator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    val takePhote = 1
    val fromAlbum = 2
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takePhotoBtn.setOnClickListener {
            // 创建File对象，存储照片
            outputImage = File(externalCacheDir, "output_image.jpg")
            if (outputImage.exists()) {
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(
                    this,
                    "com.palmprintdestiny.android.fileprovider",
                    outputImage
                )

            } else {
                Uri.fromFile(outputImage)
            }
            //启动相机
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, takePhote)
        }

        // 相册按钮
        fromAlbumBtn.setOnClickListener {
            // 打开文件选择器
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            // 指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent, fromAlbum)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            takePhote -> {
                if (resultCode == Activity.RESULT_OK) {
                    // 拍摄照片显示出来
                    val bitmap =
                        BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    Log.d("MainActivity", "bitmap is ${bitmap}")
                    imageView.setImageBitmap(rotateIfRequired(bitmap))

                    val imageString = imageUri.toString()
                    Log.d("MainActivity", "imageString is ${imageString}")
                }
            }

            fromAlbum -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
                        Log.d("MainActivity", "uri is ${uri}")
                        // 想选择的图片显示
                        val bitmap = getBitmapFromUri(uri)
                        Log.d("MainActivity", "bitmap is ${bitmap}")
                        imageView.setImageBitmap((bitmap))

                        hidden_Components()

                        val imageString1 = Base64Util.getBitmapStrBase64(bitmap).toString()
                        Log.d("MainActivity", "imageString is ${imageString1}")
                        sendDataBtn.setOnClickListener {
                            val desService = ServiceCreator.create(DestinyService::class.java)
                            val image = "https://i.postimg.cc/c4WYYVxD/9d4abd46b8e4b21758be22422d8bb31.jpg"
                            val imageType = "0"
                            val intent = Intent(this, SecondActivity::class.java)
                            desService.uploadPic(imageString1, imageType).enqueue(object :Callback<DestinyResponse>{
                                override fun onFailure(call: Call<DestinyResponse>, t: Throwable) {
                                    Toast.makeText(PalmprintDestinyApplication.context, "请求超时，请重新点击按钮", Toast.LENGTH_SHORT).show()
                                    t.printStackTrace()
                                }

                                override fun onResponse(call: Call<DestinyResponse>, response: Response<DestinyResponse>) {
                                    val des = response.body()
                                    val bundle = Bundle()
                                    if (des != null) {
                                        if ( des.命主手相分析与解读状态 == "艾科瑞特，让企业业绩长青"){
                                            bundle.putString("palmName", des.命主手相实体信息.命主手相掌型实体信息.掌型名称)
                                            bundle.putString("wuxing", des.命主手相实体信息.命主手相掌型实体信息.掌型五行名称)
                                            bundle.putString("palmInterpretation", des.命主手相实体信息.命主手相掌型实体信息.掌型解读)

                                            bundle.putString("xueye_geju", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.学业.格局)
                                            bundle.putString("xueye_weixie", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.学业.威胁与挑战)
                                            bundle.putString("xueye_yicongshi", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.学业.宜从事)
                                            bundle.putString("xueye_youliyu", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.学业.有利于)

                                            bundle.putString("shiye_geju", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.事业.格局)
                                            bundle.putString("shiye_weixie", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.事业.威胁与挑战)
                                            bundle.putString("shiye_yicongshi", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.事业.宜从事)
                                            bundle.putString("shiye_youliyu", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.事业.有利于)

                                            bundle.putString("caiyun_geju", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.财运.格局)
                                            bundle.putString("caiyun_weixie", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.财运.威胁与挑战)
                                            bundle.putString("caiyun_yicongshi", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.财运.宜从事)
                                            bundle.putString("caiyun_youliyu", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.财运.有利于)

                                            bundle.putString("jiankang_geju", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.健康.格局)
                                            bundle.putString("jiankang_weixie", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.健康.威胁与挑战)
                                            bundle.putString("jiankang_yicongshi", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.健康.宜从事)
                                            bundle.putString("jiankang_youliyu", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.健康.有利于)

                                            bundle.putString("jiating_geju", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.家庭.格局)
                                            bundle.putString("jiating_weixie", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.家庭.威胁与挑战)
                                            bundle.putString("jiating_yicongshi", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.家庭.宜从事)
                                            bundle.putString("jiating_youliyu", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.家庭.有利于)

                                            bundle.putString("hunyin_geju", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.婚姻.格局)
                                            bundle.putString("hunyin_weixie", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.婚姻.威胁与挑战)
                                            bundle.putString("hunyin_yicongshi", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.婚姻.宜从事)
                                            bundle.putString("hunyin_youliyu", des.命主手相实体信息.命主手相掌型实体信息.掌型命理解读.婚姻.有利于)

                                            intent.putExtras(bundle)

                                            startActivity(intent)
                                        }else{
                                            Toast.makeText(PalmprintDestinyApplication.context, "请求失败，请重新点击一下按钮", Toast.LENGTH_SHORT).show()
                                        }

//                        infoTv.text = "${tel.result.province}   ${tel.result.city}"
//                                        Log.d("MainActivity", "data is ${des.命主手相分析与解读状态}")
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }
    }

    private fun hidden_Components(){
        takePhotoBtn.visibility = View.GONE
        fromAlbumBtn.visibility = View.GONE
        titleView1.visibility = View.GONE
        titleView2.visibility = View.GONE
        titleView3.visibility = View.GONE
        simpleView.visibility = View.GONE
        sendDataBtn.visibility = View.VISIBLE

    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        val orientation =
            exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotateBitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle() // 回收不需要的Bitmap对象
        return rotateBitmap
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }

}
