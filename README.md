# AaComponents

[ ![Download](https://api.bintray.com/packages/ycjiang/ycjiang/AacHttpDataModule/images/download.svg) ](https://bintray.com/ycjiang/ycjiang/AacHttpDataModule/_latestVersion)

基于google Android Architecture Components 封装实现MVP快速开发框架

AaComponents 是一套基于MVP模式的快速开发框架。定义了一套开发规范。
并提供了基于这套规范的Activity，Fragment，Presenter，Model等父类及控件和API等，
完成APP开发过程中大量繁琐工作。

##依赖
  * 1 compile 'com.ycjiang:AacDataModule:2.5.3'
  * 2 compile 'com.ycjiang:AacModule:2.5.3'
  * 3 compile 'com.ycjiang:AacRxDataModule:2.5.3'
  * 4 compile 'com.ycjiang:AacHttpDataModule:2.5.3'
    >>     注意 引用 AaDataModule 默认引用 AacModule 包， 不需要在引用在AaDataModule

```
   repositories {
        jcenter()
          maven { url "https://jitpack.io" }//BaseRecyclerViewAdapterHelper框架需要
          //3.0以下as的版本
          maven { url 'https://maven.google.com' }
         或者 //3.0以上as的版本。下面的
         google();


    }
```
### 使用插件快速生成aac模板代码[戳我](https://github.com/yangchaojiang/AAcHelper)
  
#### More
Find more details about Matisse in [wiki](https://github.com/yangchaojiang/AaComponents/wiki)

##重复依赖
本库已经依赖了下面的库，请注意重复依赖的问题

    compile 'android.arch.lifecycle:extensions:1.0.0'
    annotationProcessor "android.arch.lifecycle:compiler:1.0.0"
    compile "com.android.support:appcompat-v7:${libSversion}"
    compile 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.34'
    compile "com.android.support:recyclerview-v7:${libSversion}"
    compile 'com.ycjiang:loadviewhelper:1.1.0'
    compile 'com.ycjiang:center-toolbar:1.0.0'
    //
      // AacHttpDataModule 内部引用
      compile 'com.lzy.net:okgo:3.0.4'
      compile 'com.lzy.net:okrx2:2.0.2'