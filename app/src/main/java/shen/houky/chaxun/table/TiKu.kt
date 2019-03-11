package shen.houky.chaxun.table

import org.litepal.crud.LitePalSupport

class TiKu : LitePalSupport() {//这是一个表格类，每个实例是一行数据
//    var id: Int? = null
    var question: String? = null
    var option1: String? = null
    var option2: String? = null
    var option3: String? = null
    var option4: String? = null
    var answer: String? = null

    fun toStringHouky(){

    }
}