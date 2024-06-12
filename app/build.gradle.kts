import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.dicoding.furniscan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.furniscan"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()

        val localPropertiesFile = project.rootProject.file("local.properties")

        properties.load(localPropertiesFile.inputStream())
        val baseUrl:String = properties.getProperty("BASE_URL")

        buildConfigField(
            "String",
            "BASE_URL",
            "\"$baseUrl\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("androidx.appcompat:appcompat:1.3.0-beta01")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("com.android.support:cardview-v7:27.1.0")

    //lottie
    implementation(libs.lottie)

    //Material Design
    implementation(libs.material)
    implementation("com.google.android.material:material:1.3.0-alpha03")

    //Camera X
    implementation(libs.androidx.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converterGson)
    implementation(libs.loggingInterceptor)
    implementation(libs.lifecycleRuntimeKtx)

    //coroutines
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.coroutinesCore)
    implementation(libs.lifecycleLivedataKtx)
    implementation(libs.coroutinesAndroid)

    //Datastore
    implementation(libs.datastorePreferences)

    //Tensorflow Lite
    implementation(libs.tensorflowLiteTaskVision)
    implementation(libs.tensorflowLiteMetadata)
    implementation(libs.tensorflowLiteGpu)
    implementation(libs.tensorflowLite)

}