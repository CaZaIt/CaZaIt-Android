package org.cazait.cazait_android.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.cazait.cazait_android.R
import org.cazait.cazait_android.ui.view.signup.SignupActivity
import org.cazait.cazait_android.databinding.ActivityLoginBinding
import org.cazait.cazait_android.ui.view.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.textView7.setOnClickListener {
            val next = Intent(this, SignupActivity::class.java)
            startActivity(next)
        }
    }
}