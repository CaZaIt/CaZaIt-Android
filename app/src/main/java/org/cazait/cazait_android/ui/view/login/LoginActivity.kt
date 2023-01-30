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
    var DB: SignUpDBHelper? = null
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DB = SignUpDBHelper(this)

        binding.tvLoginSignup.setOnClickListener {
            val next = Intent(this, SignUpActivity::class.java)
            startActivity(next)
        }

        binding.tvLoginDoing!!.setOnClickListener {
            val loginid = binding.etLoginUserName!!.text.toString()
            val loginpw = binding.etLoginPassword!!.text.toString()
            if (loginid == "" || loginpw == "")
                Toast.makeText(
                    this@LoginActivity,
                    "회원정보를 전부 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                val checkUserpass = DB!!.checkUserpass(loginid, loginpw)
                if (checkUserpass == true) {
                    Toast.makeText(
                        this@LoginActivity,
                        "로그인 되었습니다",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
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