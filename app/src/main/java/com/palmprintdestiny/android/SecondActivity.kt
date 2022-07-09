package com.palmprintdestiny.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.palmprintdestiny.android.logic.model.DestinyResponse
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val statusData = intent.getStringExtra("status")
        var bundle = this.intent.extras

        val palmName = bundle?.get("palmName")
        val wuxing = bundle?.get("wuxing")
        val palmInterpretation = bundle?.get("palmInterpretation")

        // 学业分析
        val xueye_geju = bundle?.get("xueye_geju")
        val xueye_weixie = bundle?.get("xueye_weixie")
        val xueye_yicongshi = bundle?.get("xueye_yicongshi")
        val xueye_youliyu = bundle?.get("xueye_youliyu")

        // 事业分析
        val shiye_geju = bundle?.get("shiye_geju")
        val shiye_weixie = bundle?.get("shiye_weixie")
        val shiye_yicongshi = bundle?.get("shiye_yicongshi")
        val shiye_youliyu = bundle?.get("shiye_youliyu")

        // 财运分析
        val caiyun_geju = bundle?.get("caiyun_geju")
        val caiyun_weixie = bundle?.get("caiyun_weixie")
        val caiyun_yicongshi = bundle?.get("caiyun_yicongshi")
        val caiyun_youliyu = bundle?.get("caiyun_youliyu")

        // 健康分析
        val jiankang_geju = bundle?.get("jiankang_geju")
        val jiankang_weixie = bundle?.get("jiankang_weixie")
        val jiankang_yicongshi = bundle?.get("jiankang_yicongshi")
        val jiankang_youliyu = bundle?.get("jiankang_youliyu")

        // 家庭分析
        val jiating_geju = bundle?.get("jiating_geju")
        val jiating_weixie = bundle?.get("jiating_weixie")
        val jiating_yicongshi = bundle?.get("jiating_yicongshi")
        val jiating_youliyu = bundle?.get("jiating_youliyu")

        // 婚姻分析
        val hunyin_geju = bundle?.get("hunyin_geju")
        val hunyin_weixie = bundle?.get("hunyin_weixie")
        val hunyin_yicongshi = bundle?.get("hunyin_yicongshi")
        val hunyin_youliyu = bundle?.get("hunyin_youliyu")


        Log.d("SecondActivity", "status data is $statusData")
        val Data: TextView = findViewById(R.id.showData)
        val xueyeData: TextView = findViewById(R.id.xueyeData)
        val shiyeData: TextView = findViewById(R.id.shiyeData)
        val caiyunData: TextView = findViewById(R.id.caiyunData)
        val jiankangData: TextView = findViewById(R.id.jiankangData)
        val jiatingData: TextView = findViewById(R.id.jiatingData)
        val hunyinData: TextView = findViewById(R.id.hunyinData)

        Data.append("掌纹名称：\b" + palmName + "\n")
        Data.append("掌纹五行：\b" + wuxing + "\n")
        Data.append("掌纹解读：\b" + palmInterpretation + "\n")

        xueyeData.append("学业：\n" + xueye_geju + "\n")
        xueyeData.append("威胁与挑战：\n" + xueye_weixie + "\n")
        xueyeData.append("有利于：\n" + xueye_yicongshi + "\n")
        xueyeData.append("宜从事：\n" + xueye_youliyu + "\n")

        shiyeData.append("事业：\n" + shiye_geju + "\n")
        shiyeData.append("威胁与挑战：\n" + shiye_weixie + "\n")
        shiyeData.append("有利于：\n" + shiye_yicongshi + "\n")
        shiyeData.append("宜从事：\n" + shiye_youliyu + "\n")

        caiyunData.append("财运：\n" + caiyun_geju + "\n")
        caiyunData.append("威胁与挑战：\n" + caiyun_weixie + "\n")
        caiyunData.append("有利于：\n" + caiyun_yicongshi + "\n")
        caiyunData.append("宜从事：\n" + caiyun_youliyu + "\n")

        jiankangData.append("健康：\n" + jiankang_geju + "\n")
        jiankangData.append("威胁与挑战：\n" + jiankang_weixie + "\n")
        jiankangData.append("有利于：\n" + jiankang_yicongshi + "\n")
        jiankangData.append("宜从事：\n" + jiankang_youliyu + "\n")

        jiatingData.append("家庭：\n" + jiating_geju + "\n")
        jiatingData.append("威胁与挑战：\n" + jiating_weixie + "\n")
        jiatingData.append("有利于：\n" + jiating_yicongshi + "\n")
        jiatingData.append("宜从事：\n" + jiating_youliyu + "\n")

        hunyinData.append("婚姻：\n" + hunyin_geju + "\n")
        hunyinData.append("威胁与挑战：\n" + hunyin_weixie + "\n")
        hunyinData.append("有利于：\n" + hunyin_yicongshi + "\n")
        hunyinData.append("宜从事：\n" + hunyin_youliyu + "\n")

    }
}