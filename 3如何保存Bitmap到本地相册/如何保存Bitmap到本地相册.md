这里分为两部分，第一部分是保存文件 第二部分是扫描媒体库，会将文件显示在相册中。

```
 fun saveBitmapFile(bitmap: Bitmap, url: String) {
        try {
            val file = File(url)//将要保存图片的路径
            if (!file.exists()) {
                file.parentFile.mkdirs()
                file.createNewFile()
            }
            val bos = BufferedOutputStream(FileOutputStream(file))
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            bos.flush()
            bos.close()
            bitmap.recycle()
            ToastUtils.showShortToastSafe(this, "保存成功！")
            //扫描媒体库
            //扫描媒体库
            val extension = MimeTypeMap.getFileExtensionFromUrl(file.getAbsolutePath())
            val mimeTypes = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            MediaScannerConnection.scanFile(this, arrayOf(file.getAbsolutePath()), arrayOf(mimeTypes), null)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
```