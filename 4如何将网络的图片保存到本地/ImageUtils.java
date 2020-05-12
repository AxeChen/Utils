package com.hunanyihong.htk.util;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.hunanyihong.htk.ui.widget.GlideApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by malk on 16/11/28.
 */

public class ImageUtils {

    public static Disposable saveImgToGallery(final Context context, final String imgUrl) {
        return Observable.create((ObservableOnSubscribe<File>) emitter -> {
            File file = Glide.with(context)
                    .download(imgUrl)
                    .submit()
                    .get();
            String fileParentPath =
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/XingTu/Image";
            File appDir = new File(fileParentPath);
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            //获得原文件流
            FileInputStream fis = new FileInputStream(file);
            //保存的文件名
            String fileName = "xt" + System.currentTimeMillis() + ".jpg";
            //目标文件
            File targetFile = new File(appDir, fileName);
            //输出文件流
            FileOutputStream fos = new FileOutputStream(targetFile);
            // 缓冲数组
            byte[] b = new byte[1024 * 8];
            while (fis.read(b) != -1) {
                fos.write(b);
            }
            fos.flush();
            fis.close();
            fos.close();
            //扫描媒体库
            String extension = MimeTypeMap.getFileExtensionFromUrl(targetFile.getAbsolutePath());
            String mimeTypes = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            MediaScannerConnection.scanFile(context, new String[]{targetFile.getAbsolutePath()},
                    new String[]{mimeTypes}, null);
            emitter.onNext(targetFile);
        })
                .subscribeOn(Schedulers.io()) //发送事件在io线程
                .observeOn(AndroidSchedulers.mainThread())//最后切换主线程提示结果
                .subscribe(file -> ToastUtils.showLongToastSafe(context, "保存图片成功"),
                        throwable -> ToastUtils.showLongToastSafe(context, "保存图片成功"));
    }

}
