package org.cazait.cazait_android.ui.view.login

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.error.EMAIL_OR_PASSWORD_ERROR
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.databinding.ActivityLoginBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.util.SingleEvent
import org.cazait.cazait_android.ui.util.extension.*
import org.cazait.cazait_android.ui.view.MainActivity
import org.cazait.cazait_android.ui.view.signup.SignUpActivity
import org.cazait.cazait_android.ui.viewmodel.LoginViewModel

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels()

    override fun initBeforeBinding() {
        installSplashScreen()
    }

    override fun initView() {
        initSignUpBtn()
        initLoginBtn()
        doLoginIfLoggedIn()
    }

    override fun initAfterBinding() {
        binding.lifecycleOwner = this
        observeViewModel()
    }

    private fun doLoginIfLoggedIn() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.isLoggedIn().collect {
                if (it) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun observeViewModel() {
        observe(viewModel.loginProcess, ::handleLoginResult)
        observeToast(viewModel.showToast)
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Loading -> binding.pbLoginLoaderView.toVisible()
            is Resource.Success -> status.data.let {
                binding.pbLoginLoaderView.toGone()
                when (status.data.result) {
                    "SUCCESS" -> {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    "FAIL" -> viewModel.showToastMessage(EMAIL_OR_PASSWORD_ERROR)
                }
            }
            is Resource.Error -> {
                binding.pbLoginLoaderView.toGone()
                status.message.let {
                    viewModel.showToastMessage(it)
                }
            }
        }
    }

    private fun initSignUpBtn() {
        binding.tvLoginSignUp.setOnClickListener {
            val next = Intent(this, SignUpActivity::class.java)
            startActivity(next)
        }
    }

    private fun initLoginBtn() {
        binding.tvLoginDoing.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        viewModel.doLogin(
            binding.etLoginUserName.text.toString(), binding.etLoginPassword.text.toString()
        )
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
}
