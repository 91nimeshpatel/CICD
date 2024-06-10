package com.nimeshpatel.cicd.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nimeshpatel.cicd.databinding.ActivityMainBinding
import com.nimeshpatel.cicd.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplash()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initSplash() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                return@setKeepOnScreenCondition homeViewModel.isSplashScreenLoading.value
            }
            setOnExitAnimationListener { splashScreen ->
                // to remove splashscreen with no animation
                splashScreen.remove()
            }
        }
    }
}