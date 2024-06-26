plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.gms.google-services")
    id ("kotlin-parcelize")
    id ("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "kr.co.lion.mungnolza"
    compileSdk = 34

    defaultConfig {
        applicationId = "kr.co.lion.mungnolza"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
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


    viewBinding {
        enable = true
    }

    buildFeatures{
        dataBinding = true
    }


}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.2")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.room:room-common:2.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.28")

    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.2")

    implementation ("com.airbnb.android:lottie:3.4.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")

    // 이미지 원형 적용 라이브리러
    implementation ("de.hdodenhof:circleimageview:3.1.0") //Circle ImageView

    implementation ("com.aminography:primedatepicker:3.6.0")
    implementation ("com.aminography:primecalendar:1.7.0")

    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore:24.11.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.11.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")
    implementation ("com.google.firebase:firebase-database-ktx")

    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.2.0")

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("com.kakao.sdk:v2-user:2.20.1")
    implementation("com.naver.maps:map-sdk:3.17.0")

    // ROOM
    val roomVersion = "2.6.1"

    implementation ("androidx.room:room-runtime:$roomVersion")
    kapt ("androidx.room:room-compiler:$roomVersion")

    implementation ("androidx.room:room-ktx:$roomVersion")

}