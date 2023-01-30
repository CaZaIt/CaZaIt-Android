package org.cazait.cazait_android.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.SignUpDBHelper
import org.cazait.cazait_android.ui.view.signup.SignUpActivity
import org.cazait.cazait_android.databinding.ActivityLoginBinding
import org.cazait.cazait_android.ui.view.MainActivity

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var DB: SignUpDBHelper? = null
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DB = SignUpDBHelper(this)

        binding.tvLoginSignUp.setOnClickListener {
            val next = Intent(this, SignUpActivity::class.java)
            startActivity(next)
        }

        binding.tvLoginDoing.setOnClickListener {
            val loginEmail = binding.etLoginUserName.text.toString()
            val loginPw = binding.etLoginPassword.text.toString()
            if (loginEmail == "" || loginPw == "")
                Toast.makeText(
                    this@LoginActivity,
                    "회원정보를 전부 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                val checkUserPass = DB!!.checkUserPass(loginEmail, loginPw)
                if (checkUserPass) {
                    Toast.makeText(
                        this@LoginActivity,
                        "로그인 되었습니다",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "회원정보가 존재하지 않습니다",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}