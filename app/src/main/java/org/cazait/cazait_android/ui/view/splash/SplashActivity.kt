package org.cazait.cazait_android.ui.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivitySplashBinding
import org.cazait.cazait_android.ui.view.login.LoginActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_splash
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backgroundImg : ImageView = binding.ivSplashLogo
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_iv)
        backgroundImg.startAnimation(sideAnimation)

        Handler().postDelayed({
            val start = Intent(this, LoginActivity::class.java)
            startActivity(start)

            finish()

        }, 1000)
    }
}