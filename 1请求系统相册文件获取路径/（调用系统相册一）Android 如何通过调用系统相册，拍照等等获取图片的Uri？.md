#### 记录这个东西的初衷
每次遇到这样的需求就是各种去找百度了，今天都记录在这个地方。

#### 调起相册和拍照
调起相册
```
            val intent = Intent("android.intent.action.PICK")
            intent.type = "image/*"
            startActivityForResult(intent, 1)
            dialog.dismiss()
```

调起拍照
```
 mFile = File(FileUtils.SAVE_FILE + System.currentTimeMillis() + ".jpg")
            imageFileUri = null
            imageFileUri = Uri.fromFile(mFile)
            if (imageFileUri != null) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri)
                startActivityForResult(intent, 0)
            <!--}-->
```
来自图片浏览
```
 val intent = Intent()
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            if (Build.VERSION.SDK_INT < 19) {
                intent.action = Intent.ACTION_GET_CONTENT
            } else {
                intent.action = Intent.ACTION_OPEN_DOCUMENT
            }
            var requestCode ="1"+(stepInfoBean?.orders!!+1).toString()
            activity.startActivityForResult(intent, requestCode.toInt())
```


获取Uri   
在onActivityResult的方法中去获取uri
```
// 来自相册的
imageFileUri = data!!.data

// 来自拍照的
uri已经获取到了

// 来自图片浏览
imageFileUri = data!!.data
```

如何将这些获取到的uri中获取到真实的路径呢？请看第二篇文章
