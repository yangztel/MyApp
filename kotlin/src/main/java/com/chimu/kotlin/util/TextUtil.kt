package com.example.chimu.kotlin.util

/**
 * Created by Longwj on 2017/6/28.
 */

object TextUtil {

    @JvmStatic fun isNull() {
        val s: String? = null

        for(i in 0..10){
            println(i)
        }
        println(s?.length)
    }

    @JvmStatic fun isEmpty(str: String): Boolean {
        return "" == str
    }



}

class StringUtils {
    companion object {
        //  静态方法与伴生对象companion object
        fun isEmpty(str: String): Boolean {
            return "" == str
        }
    }
}

//包级别函数，默认在当前源文件中
fun helloKotlin():String {
    val words = mutableListOf<String>()
    words.add("Hello")
    words.add("Kotlin!")
    words.add(java.util.Date().toString())
    return words.joinToString(separator=" ")
}