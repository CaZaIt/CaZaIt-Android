package org.cazait.cazait_android.ui.view.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivitySignupBinding
import org.cazait.cazait_android.ui.view.login.LoginActivity

class SignupActivity : AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_signup
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding.tv23.setOnClickListener {
            val back = Intent(this, LoginActivity::class.java)
            startActivity(back)
        }
    }
}