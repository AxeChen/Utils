#### 关于实际应用
fragment的懒加载当遇到Viewpager+fragment的情况，这种情况基本上都会用到懒加载的方式来提高性能，这种情况可以封装成基类，当后续遇到需要懒加载的情况可以直接继承基类使用。

#### 实际使用

主要用的方法为fragment的 ```setUserVisibleHint``` 这个方法

```
    private var isPrepare = false
    private var isLazyLoad = false
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepare = true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        doLazyLoad()
    }

    private fun doLazyLoad() {
        if (userVisibleHint && isPrepare && !isLazyLoad) {
            isLazyLoad = true
            // 请求服务器代码
        }
    }


```