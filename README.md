# AaComponents
基于google Android Architecture Components 封装实现MVP快速开发框架

AaComponents 是一套基于MVP模式的快速开发框架。定义了一套开发规范。
并提供了基于这套规范的Activity，Fragment，Presenter，Model等父类及控件和API等，
完成APP开发过程中大量繁琐工作。

##依赖
  * 1 compile 'com.ycjiang:AaDataModule:1.0.7'
  * 2 compile 'com.ycjiang:AacModule:1.0.7'

    >>     注意 引用 AaDataModule 默认引用 AacModule 包， 不需要在引用在AaDataModule

```
   repositories {
        jcenter()
        maven { url "https://jitpack.io" }
        maven { url 'https://maven.google.com' }


    }
```

#### More
Find more details about Matisse in [wiki](https://github.com/yangchaojiang/AaComponents/wiki)
