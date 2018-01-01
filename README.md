# AaComponents
基于google Android Architecture Components 封装实现MVP快速开发框架

AaComponents 是一套基于MVP模式的快速开发框架。定义了一套开发规范。
并提供了基于这套规范的Activity，Fragment，Presenter，Model等父类及控件和API等，
完成APP开发过程中大量繁琐工作。

##依赖
  * 1 compile 'com.ycjiang:AacDataModule:2.1.5'
  * 2 compile 'com.ycjiang:AacModule:2.1.5'
  * 3  compile 'com.ycjiang:AacRxDataModule:2.1.5'
    >>     注意 引用 AaDataModule 默认引用 AacModule 包， 不需要在引用在AaDataModule

```
   repositories {
        jcenter()
        maven { url 'https://maven.google.com' }


    }
```
### 使用插件快速生成aac模板代码[戳我](https://github.com/yangchaojiang/AAcHelper)
  
#### More
Find more details about Matisse in [wiki](https://github.com/yangchaojiang/AaComponents/wiki)
