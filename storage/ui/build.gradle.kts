plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
}

android {
    namespace = "com.gt.storage"
    compileSdk = 35
    defaultConfig {
        manifestPlaceholders["secureSearchPermission"] =
            project.findProperty("SECURE_SEARCH_PERMISSION")
                ?.toString()
                ?: throw GradleException("SECURE_SEARCH_PERMISSION not defined")
        applicationId = "com.gt.storage"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = "appsync-alias"
            keyPassword = "appsync123"
            storeFile = file("appsync.keystore")
            storePassword = "appsync123"
        }
        getByName("debug").apply {
            keyAlias = "appsync-debug-alias"
            keyPassword = "debug123"
            storeFile = rootProject.file("appsync.debug.keystore")
            storePassword = "debug123"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs["release"]
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            signingConfig = signingConfigs["debug"]
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.coroutines)
    implementation(libs.converter.gson)
    implementation(libs.retrofit)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.roomRuntime)
    ksp(libs.roomCompiler)
    implementation(libs.roomKtx)

    implementation(project(":storage:domain"))
    implementation(project(":storage:data"))
    implementation(project(":common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}