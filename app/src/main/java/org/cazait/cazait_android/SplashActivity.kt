package org.cazait.cazait_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import org.cazait.cazait_android.databinding.ActivityLoginBinding
import org.cazait.cazait_android.databinding.ActivitySplashBinding
import org.cazait.cazait_android.ui.view.MainActivity
import org.cazait.cazait_android.ui.view.login.LoginActivity

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