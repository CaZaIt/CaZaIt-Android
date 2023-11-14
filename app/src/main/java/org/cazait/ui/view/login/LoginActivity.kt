package org.cazait.ui.view.login

import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.cazait.R
import org.cazait.databinding.ActivityLoginBinding
import org.cazait.ui.util.extension.*
import org.cazait.data.Resource
import org.cazait.data.error.EMAIL_OR_PASSWORD_ERROR
import org.cazait.data.model.remote.response.LoginResponse
import org.cazait.ui.base.BaseActivity
import org.cazait.ui.util.SingleEvent
import org.cazait.ui.util.extension.observe
import org.cazait.ui.view.MainActivity
import org.cazait.ui.view.signup.SignUpActivity
import org.cazait.ui.viewmodel.LoginViewModel

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_login
    override lateinit var viewModel: LoginViewModel

    override fun initBeforeBinding() {
        installSplashScreen()
    }

    override fun initView() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        initSignUpBtn()
        initLoginBtn()
        doLoginIfLoggedIn()
    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    private fun doLoginIfLoggedIn() {
        CoroutineScope(Dispatchers.IO).launch {
            val hasLoggedIn = viewModel.isLoggedIn().first()
            if (hasLoggedIn) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
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
                        val intent = Intent(this, MainActivity::class.java)
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
            binding.etLoginUserName.text.toString(),
            binding.etLoginPassword.text.toString(),
        )
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
}
