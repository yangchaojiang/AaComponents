package com.example.aac.java.data.test.bean
import java.io.Serializable
import com.chad.library.adapter.base.entity.MultiItemEntity;
/**
* Created by yangc on 2018/10/15 18:14:55
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
class Test :Serializable,MultiItemEntity {
var test:String?=null


     override fun getItemType(): Int = 0
}