有时候有保存View截图的需求，将View对象传进去即可。

```
    fun createViewBitmap(v: View): Bitmap {
        val bitmap = Bitmap.createBitmap(v.width, v.height,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        v.draw(canvas)
        return bitmap
    }
```

