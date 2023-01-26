package org.cazait.cazait_android.ui.view.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivitySignupBinding
import org.cazait.cazait_android.ui.view.login.LoginActivity
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_signup
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding.tvSignupJoin.setOnClickListener {
            val back = Intent(this, LoginActivity::class.java)
            startActivity(back)
        }
    }
}