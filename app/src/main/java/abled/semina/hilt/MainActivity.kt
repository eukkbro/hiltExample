package abled.semina.hilt

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

@HiltAndroidApp
class MyApplication: Application()

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var numberRepository: NumberRepository

    @Inject
    lateinit var charRepository: CharRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(numberRepository.text)
        println(charRepository.text)


    }
}


@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule{

    @Provides
    @ActivityScoped
    fun provideNumberRepository(): NumberRepository = NumberRepository()

    @Provides
    @ActivityScoped
    fun provideCharRepository(): CharRepository = CharRepository()

}

class NumberRepository {
    var text = run{ Random().nextInt(100).toString()}
}

class CharRepository {
    var text = run{ Random().nextInt(100).toChar().toString()}
}


