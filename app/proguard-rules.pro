# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/Android Studio.app/Contents/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
#webView js调用不混淆，否则取不到数据
-keepclassmembers class com.zyp.ui.WebActivity$* {
    <methods>;
}

#shrink，测试后发现会将一些无效代码给移除，即没有被显示调用的代码，该选项 表示 不启用 shrink。
#-dontshrink
#指定重新打包,所有包重命名,这个选项会进一步模糊包名，将包里的类混淆成n个再重新打包到一个个的package中
#-flattenpackagehierarchy
#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification
#不跳过(混淆) jars中的 非public classes   默认选项
-dontskipnonpubliclibraryclassmembers
#忽略警告
-ignorewarnings
#指定代码的压缩级别
-optimizationpasses 5
#不使用大小写混合类名
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
#不启用优化  不优化输入的类文件
-dontoptimize
#不预校验
-dontpreverify
#混淆时是否记录日志
-verbose
#混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保护注解
-renamesourcefileattribute SourceFile
#保持源文件和行号的信息,用于混淆后定位错误位置
-keepattributes SourceFile,LineNumberTable
#保持含有Annotation字符串的 attributes
-keepattributes *Annotation*
#过滤泛型
-keepattributes Signature
-keepattributes Exceptions,InnerClasses

-dontwarn org.apache.**
-dontwarn android.support.**

#基础配置
# 保持哪些类不被混淆
# 系统组件
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
#如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment
#自定义View
-keep public class * extends android.view.View
# V4,V7
-keep class android.support.constraint.**{ *; }
-keep class android.support.v4.**{ *; }
-keep class android.support.v7.**{ *; }
-keep class android.webkit.**{*;}
-keep interface android.support.v4.app.** { *; }
#保持 本化方法及其类声明
-keepclasseswithmembers class * {
    native <methods>;
}
#保持view的子类成员： getter setter
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}
#保持Activity的子类成员：参数为一个View类型的方法   如setContentView(View v)
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
#保持枚举类的成员：values方法和valueOf  (每个enum 类都默认有这两个方法)
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#保持Parcelable的实现类和它的成员：类型为android.os.Parcelable$Creator 名字任意的 属性
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#保持 任意包名.R类的类成员属性。  即保护R文件中的属性名不变
-keepclassmembers class **.R$* {
    public static <fields>;
}

# 记录生成的日志数据，在 proguard 目录下

-dump class_files.txt

-printseeds seeds.txt

-printusage unused.txt

-printmapping mapping.txt


-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-dontwarn java.util.**
-keep class java.util.** {*; }

-dontwarn org.apache.http.**
-keep class org.apache.http.** {*; }

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public class * implements java.io.Serializable {*;}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# ======================= 以上是混淆的基础配置================================
# ======================= 以下是混淆的第三方包的配置================================


# ======================= Gson 混淆================================
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.idea.fifaalarmclock.entity.***
-keep class com.google.gson.stream.** { *; }

# ======================= Umeng 混淆================================
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
#这是由于SDK中的部分代码使用反射来调用构造函数， 如果被混淆掉， 在运行时会提示"NoSuchMethod"错误。
#另外，由于SDK需要引用导入工程的资源文件，通过了反射机制得到资源引用文件R.java，但是在开发者通过proguard等混淆/优化工具处理apk时，proguard可能会将R.java删除，如果遇到这个问题，请在proguard配置文件中添加keep命令如：
-keep public class com.zyp.R$*{
    public static final int *;
}
-keep class com.umeng.**
-keep public class com.idea.fifaalarmclock.app.R$*{
    public static final int *;
}

-keep public class com.umeng.fb.ui.ThreadView {
}
-dontwarn com.umeng.**
-dontwarn org.apache.commons.**
-keep public class * extends com.umeng.**
-keep class com.umeng.** {*; }


# ======================= pinying4j 混淆================================
-dontwarn com.hp.hpl.sparta.**
-keep class com.hp.hpl.sparta.**{*;}
-dontwarn net.sourceforge.pinyin4j.**
-keep class net.sourceforge.pinyin4j.**{*;}
-dontwarn demo.**
-keep class demo.**{*;}

# ======================= volley 混淆================================
-dontwarn com.android.volley.**
-keep class com.android.volley.**{*;}

# ======================= fastjson 混淆================================
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
# ======================= alipay 混淆================================
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.mobilesecuritysdk.*
-keep class com.ut.*

# ======================= weixin 混淆================================
-keep class com.tencent.mm.** { *; }

# ======================= okio ================================
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}


# ======================= activation.jar ================================
-dontwarn javax.activation.**
-keep class javax.activation.**{*;}
-dontwarn com.sun.activation.registries.**
-keep class com.sun.activation.registries.**{*;}

# ======================= additionnal.jar ================================
-dontwarn myjava.awt.datatransfer.**
-keep class myjava.awt.datatransfer.**{*;}
-dontwarn org.apache.harmony.**
-keep class org.apache.harmony.**{*;}


# ======================= RxAndroid,RxJava ================================
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#======================= httpmime =======================
-keep class org.apache.http.entity.mime.** {*;}
#======================= zxing =======================
-keep class com.google.zxing.** { *; }

#======================= simplelatlng =======================
-keep class com.javadocmd.simplelatlng.** {*;}

# =======================universal-image-loader ================================
-keep class com.nostra13.universalimageloader.** {*;}

# ======================= 百度地图  ================================
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

# ======================= 高德地图  ================================
# 3D 地图
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.amap.mapcore.*{*;}
-keep   class com.amap.api.trace.**{*;}

# 定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#搜索
-keep   class com.amap.api.services.**{*;}

#2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}

#导航
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}

#讯飞
-keep class com.iflytek.**{*;}


# ======================= app javabean 混淆================================
-keep class com.zyp.bean.**{*;}


##---------------Begin:retrofit+rxjava----------------------
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-keep interface okhttp3.**{*;}
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okhttp3.**
-keep class okhttp3.** { *;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}


-dontnote android.webkit.**
-dontnote java.util.**

##---------------Inmobi----------------------

-keepattributes SourceFile,LineNumberTable -keep class com.inmobi.** { *; }
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-dontwarn com.squareup.picasso.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{
public *;
}
# skip the Picasso library classes
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**
# skip Moat classes
-keep class com.moat.** {*;}
-dontwarn com.moat.**


