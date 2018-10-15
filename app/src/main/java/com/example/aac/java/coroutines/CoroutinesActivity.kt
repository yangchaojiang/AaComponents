package com.example.aac.java.coroutines

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.aac.data.http.converter.BeanConverter
import com.aac.data.http.utils.httpLiveGet
import com.aac.data.http.utils.httpLivePost
import com.aac.data.http.utils.httpRxGet
import com.aac.data.http.utils.httpRxPost
import com.example.aac.R
import com.lzy.okgo.model.HttpParams
import com.yutils.YUtils
import io.reactivex.Flowable

class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        YUtils.initialize(application)
        val data = HttpParams()

        data.put("test", "1233")
        setContentView(R.layout.activity_coroutines)
        httpRxGet<DataBen>("http://www.kuaidi.com/index-ajaxselectcourierinfo-1202247993797-yunda.html", data,
                BeanConverter<DataBen>("-1", DataBen::class.java))
                .subscribe({
                    YUtils.Toast(it.timeused + "222222222222")
                }, {
                    Log.d("CoroutinesActivity", it.message)
                })

        /*  OkGo.get<DataBen>("http://www.kuaidi.com/index-ajaxselectcourierinfo-1202247993797-yunda.html")
                  .converter(BeanConverter<DataBen>("-1", DataBen::class.java))
                  .adapt(CoroutinesDataAdapter<DataBen>())
                  .subscribe({
                       YUtils.Toast(it.timeused+"222222222222")
                  },{
                      Log.d("CoroutinesActivity",it.message)
                  })*/
    }

    fun ssss(): Flowable<DataBen> {
        val data = HttpParams()
        return httpRxGet<DataBen>("http://www.kuaidi.com/index-ajaxselectcourierinfo-1202247993797-yunda.html",
                data,
                BeanConverter<DataBen>("-1", DataBen::class.java))
    }

    fun sssss(): LiveData<DataBen> {
        val data = HttpParams()
        return httpLivePost<DataBen>("http://www.kuaidi.com/index-ajaxselectcourierinfo-1202247993797-yunda.html",
                data,
                BeanConverter<DataBen>("-1", DataBen::class.java))
    }

    fun DataBean(string: String): Flowable<DataBen> {
        val params = HttpParams()
        params.put("string", string)
        return httpRxPost<DataBen>("sss"
                , params,
                BeanConverter("-1", DataBen::class.java))

    }

}
