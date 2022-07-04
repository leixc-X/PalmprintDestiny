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

        val xueye_geju = bundle?.get("xueye_geju")
        val xueye_weixie = bundle?.get("xueye_weixie")
        val xueye_yicongshi = bundle?.get("xueye_yicongshi")
        val xueye_youliyu = bundle?.get("xueye_youliyu")


        Log.d("SecondActivity", "status data is $statusData")
        val Data: TextView = findViewById(R.id.showData)
        val detailData :TextView = findViewById(R.id.detailData)

        Data.append("掌纹名称：\b" + palmName + "\n")
        Data.append("掌纹五行：\b" + wuxing + "\n")
        Data.append("掌纹解读：\b" + palmInterpretation + "\n")
        detailData.append("学业：\n" + xueye_geju + "\n")
        detailData.append("威胁与挑战：\n" + xueye_weixie + "\n")
        detailData.append("有利于：\n" + xueye_yicongshi + "\n")
        detailData.append("宜从事：\n" + xueye_youliyu + "\n")
//        Data.text = shiye
//        Data.text = xueye

    }
}