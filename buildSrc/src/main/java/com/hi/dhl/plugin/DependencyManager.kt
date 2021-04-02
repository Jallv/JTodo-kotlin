package com.hi.dhl.plugin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/3
 *     desc  : 如果数量少的话，放在一个类里面就可以，如果数量多的话，可以拆分为多个类
 * </pre>
 */

object Versions {
    const val retrofit = "2.9.0"
    const val okHttpLogging = "4.9.0"
    const val appcompat = "1.2.0"
    const val coreKtx = "1.3.2"
    const val constraintLayout = "2.0.4"
    const val paging = "3.0.0-alpha02"
    const val kotlin = "1.4.20"
    const val kotlinCoroutinesCore = "1.3.7"
    const val kotlinCoroutinesAndroid = "1.3.6"
    const val room = "2.2.6"
    const val cardView = "1.0.0"
    const val recyclerview = "1.0.0"
    const val fragment = "1.3.1"
    const val swipeRefreshLayout = "1.1.0"
    const val junit = "4.13.1"
    const val junitExt = "1.1.2"
    const val espressoCore = "3.3.0"
    const val coilRuntime = "1.1.1"
    const val koin = "2.2.2"
    const val hitViewModule = "1.0.0-alpha01"
    const val appStartup = "1.0.0"
    const val material = "1.2.1"
    const val nav_version = "2.3.2"
}

object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"

    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val appStartup = "androidx.startup:startup-runtime:${Versions.appStartup}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
}

object Android {
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Koin {
    const val core = "org.koin:koin-android:${Versions.koin}"
    const val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val ext = "org.koin:koin-androidx-ext:${Versions.koin}"
}

object Coil {
    const val runtime = "io.coil-kt:coil:${Versions.coilRuntime}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Fragment {
    const val runtimeKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
}

object Kt {
    const val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutinesAndroid}"
}

object Retrofit {
    const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Recyclerview {
    const val bindingCollectionAdapter =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:3.1.1"
    const val bindingCollectionAdapterRecyclerview =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:2.2.0"
}

object Util {
    const val statusBarUtil = "com.jaeger.statusbarutil:library:1.5.1"
    const val mmkv = "com.tencent:mmkv:1.0.23"
}
