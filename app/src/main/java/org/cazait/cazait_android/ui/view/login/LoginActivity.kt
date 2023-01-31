package org.cazait.cazait_android.ui.view.login

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
<<<<<<< Updated upstream
import org.cazait.cazait_android.data.model.local.SignUpDBHelper
import org.cazait.cazait_android.ui.view.signup.SignUpActivity
=======
>>>>>>> Stashed changes
import org.cazait.cazait_android.databinding.ActivityLoginBinding
import org.cazait.cazait_android.LoginViewModel
import org.cazait.cazait_android.SignUpDBHelper
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.view.MainActivity
import org.cazait.cazait_android.ui.view.signup.SignUpActivity


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels()
    private var DB: SignUpDBHelper? = null
    override fun initBeforeBinding() {
        installSplashScreen()
    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        DB = SignUpDBHelper(this)

        binding.tvLoginSignUp.setOnClickListener {
            val next = Intent(this, SignUpActivity::class.java)
            startActivity(next)
        }

        binding.tvLoginDoing.setOnClickListener {
            val loginEmail = binding.etLoginUserName.text.toString()
            val loginPw = binding.etLoginPassword.text.toString()
            if (loginEmail == "" || loginPw == "") Toast.makeText(
                this@LoginActivity, "회원정보를 전부 입력해주세요", Toast.LENGTH_SHORT
            ).show()
            else {
                val checkUserPass = DB!!.checkUserPass(loginEmail, loginPw)
                if (checkUserPass) {
                    Toast.makeText(
                        this@LoginActivity, "로그인 되었습니다", Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity, "회원정보가 존재하지 않습니다", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
