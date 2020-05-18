package com.hunanyihong.htk.util.luban

import android.content.Context
import android.text.TextUtils
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File

/**
 *  图片压缩
 *  主要是使用鲁班压缩框架
 *  https://github.com/Curzibn/Luban
 */
object CompressUtil {

    /**
     * inputPath: 需要压缩的图片路径
     * outputPath：压缩后的图片路劲
     */
    fun compressImage(context: Context, inputPath: String, outputPath: String, callBack: CompressUtilCallBack) {
        Luban.with(context)
                .load(inputPath)
                .ignoreBy(100)
                .setTargetDir(outputPath)
                .filter { path ->
                    !(TextUtils.isEmpty(path) || path!!.toLowerCase().endsWith(".gif"));
                }.setCompressListener(object : OnCompressListener {
                    override fun onSuccess(file: File?) {
                        if (file == null) {
                            callBack.compressFail()
                        } else {
                            callBack.getCompressPath(file.absolutePath)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        callBack.compressFail()
                    }

                    override fun onStart() {
                        callBack.startCompress()
                    }

                }).launch()
    }

    interface CompressUtilCallBack {
        fun getCompressPath(path: String)
        fun compressFail()
        fun startCompress()
    }
}