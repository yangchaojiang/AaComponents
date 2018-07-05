package com.example.aac.java.coroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aac.data.coroutines.CoroutinesDataAdapter
import com.example.aac.R
import com.lzy.okgo.OkGo
import com.yutils.YUtils

class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        OkGo.get<DataBen>("http://www.kuaidi.com/index-ajaxselectcourierinfo-1202247993797-yunda.html")
                .converter(BeanConverter<DataBen>("-1", DataBen::class.java))
                .adapt(CoroutinesDataAdapter<DataBen>())
                .subscribe({
                     YUtils.Toast(it.timeused+"222222222222")
                },{
                    Log.d("CoroutinesActivity",it.message)
                })
    }
}
