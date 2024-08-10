package abled.semina.hilt

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import jakarta.inject.Inject
import java.util.Random


//Hilt를 사용하는 앱에는 Application 클래스가 강제로 포함된다.
//의존성 주입의 시작점
//Manifest name에 추가
@HiltAndroidApp
class MyApplication: Application()


// AndroidEntryPoint 어노테이션이 있는 모든 클래스에 의존성을 제공할 수 있다.
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //주입 대상 지정
    @Inject
    lateinit var numberRepository: NumberRepository

    @Inject
    lateinit var charRepository: CharRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "mainActivity: ${numberRepository.text}")
        Log.d("TAG", "mainActivity: ${charRepository.text}")

        startActivity(Intent(this,MainActivity2::class.java))

    }
}


// 주입할 의존성을 셋팅하는 곳
@Module
@InstallIn(ActivityComponent::class)
class RepositoryModule{

    @Provides
    @ActivityScoped
    fun provideNumberRepository(): NumberRepository = NumberRepository()

    @Provides
    @ActivityScoped
    fun provideCharRepository(): CharRepository = CharRepository()

}

//랜덤한 숫자를 text 변수에 갖고있는 클래스
class NumberRepository {
    var text = run{ Random().nextInt(100).toString()}
}

//랜덤한 문자를 text 변수에 갖고 있는 클래스
class CharRepository {
    var text = run{ Random().nextInt(100).toChar().toString()}
}


