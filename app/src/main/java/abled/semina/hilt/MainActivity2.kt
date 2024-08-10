package abled.semina.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {

    @Inject
    lateinit var numberRepository: NumberRepository

    @Inject
    lateinit var charRepository: CharRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Log.d("TAG", "mainActivity2: ${numberRepository.text}")
        Log.d("TAG", "mainActivity2: ${charRepository.text}")
    }
}