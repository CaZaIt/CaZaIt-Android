package org.cazait.cazait_android.ui.view.signup

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Single
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.error.EMAIL_OR_PASSWORD_ERROR
import org.cazait.cazait_android.ui.viewmodel.SignUpViewModel
import org.cazait.cazait_android.data.model.local.SignUpDBHelper
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import org.cazait.cazait_android.databinding.ActivitySignUpBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.util.SingleEvent
import org.cazait.cazait_android.ui.util.extension.observe
import org.cazait.cazait_android.ui.util.extension.showToast
import org.cazait.cazait_android.ui.util.extension.toGone
import org.cazait.cazait_android.ui.util.extension.toVisible
import org.cazait.cazait_android.ui.view.login.LoginActivity

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    override val viewModel: SignUpViewModel by viewModels()

    private var nickNameFlag = false
    private var passwordFlag = false
    private var passwordCheckFlag = false
    private var emailFlag = false
    private var DB: SignUpDBHelper? = null

    private val nickNameListener = object : CheckTextWatcher() {
        override fun checkFlag() {
            binding.btnSignUpJoin.isEnabled =
                nickNameFlag && passwordFlag && passwordCheckFlag && emailFlag
        }

        override fun checkText(text: String) = checkNickName(text)
    }

    private val passwordListener = object : CheckTextWatcher() {
        override fun checkFlag() {
            binding.btnSignUpJoin.isEnabled =
                nickNameFlag && passwordFlag && passwordCheckFlag && emailFlag
        }

        override fun checkText(text: String) = checkPassword(text)
    }

    private val passwordAgainListener = object : CheckTextWatcher() {
        override fun checkFlag() {
            binding.btnSignUpJoin.isEnabled =
                nickNameFlag && passwordFlag && passwordCheckFlag && emailFlag
        }

        override fun checkText(text: String) = checkPasswordAgain(text)
    }

    private val emailListener = object : CheckTextWatcher() {
        override fun checkFlag() {
            binding.btnSignUpJoin.isEnabled =
                nickNameFlag && passwordFlag && passwordCheckFlag && emailFlag
        }

        override fun checkText(text: String) = checkEmail(text)
    }

    override fun initBeforeBinding() {}
    override fun initAfterBinding() {
        binding.lifecycleOwner = this
        observeViewModel()
    }

    override fun initView() {
        initSignUpBtn()
        initEditTextListener()
    }

    private fun observeViewModel() {
        observe(viewModel.signUpProcess, ::handleSignUpResult)
        observeToast(viewModel.showToast)
    }

    private fun handleSignUpResult(status: Resource<SignUpResponse>) {
        when (status) {
            is Resource.Loading -> binding.pbSignUpLoaderView.toVisible()
            is Resource.Success -> status.data.let {
                binding.pbSignUpLoaderView.toGone()
                when (status.data.result) {
                    "SUCCESS" -> {
                        val nextScreenIntent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(nextScreenIntent)
                        finish()
                    }
                    "FAIL" -> viewModel.showToastMessage(status.data.message)
                }
            }
            is Resource.Error -> {
                binding.pbSignUpLoaderView.toGone()
                viewModel.showToastMessage(status.message)
            }
        }
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun initEditTextListener() {
        binding.etSignUpEmailExample.editText?.addTextChangedListener(emailListener)
        binding.etSignUpPasswordInsert.editText?.addTextChangedListener(passwordListener)
        binding.etSignUpPasswordInsertMore.editText?.addTextChangedListener(passwordAgainListener)
        binding.etSignUpNickNameExample.editText?.addTextChangedListener(nickNameListener)
    }

    private fun initSignUpBtn() {
        binding.btnSignUpJoin.setOnClickListener {
            val email = binding.etSignUpEmailExample.editText?.text.toString()
            val pw = binding.etSignUpPasswordInsert.editText?.text.toString()
            val repw = binding.etSignUpPasswordInsertMore.editText?.text.toString()
            val nickname = binding.etSignUpNickNameExample.editText?.text.toString()

            if (email == "" || pw == "" || repw == "" || nickname == "")
                viewModel.showToastMessage("??????????????? ?????? ???????????????")
            else if (pw == repw) {
                viewModel.showToastMessage("?????????????????????")
                viewModel.signUp(email, pw, nickname)
            } else
                viewModel.showToastMessage("??????????????? ???????????? ????????????")
        }
    }

    private fun nicknameRegex(id: String): Boolean =
        id.matches("^[???-???a-zA-Z|d]{3,15}$".toRegex())


    private fun passwordRegex(password: String): Boolean =
        password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{8,16}\$".toRegex())

    private fun passwordCheckRegex(passwordCheck: String): Boolean =
        passwordCheck.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{8,16}\$".toRegex())


    private fun emailRegex(email: String): Boolean {
        val regexEmail =
            """^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})${'$'}""".toRegex()
        return regexEmail.matches(email)
    }

    private fun isInvalidEditTextPassword(): Boolean {
        val etInsert = binding.etSignUpPasswordInsert.editText?.text.toString()
        val etInsertMore =
            binding.etSignUpPasswordInsertMore.editText?.text.toString()

        if (etInsertMore != "" && etInsertMore != etInsert) return false
        return true
    }

    private fun checkEmail(email: String) {
        when {
            email.isEmpty() -> {
                binding.etSignUpEmailExample.error = "???????????? ??????????????????."
                emailFlag = false
            }
            !emailRegex(email) -> {
                binding.etSignUpEmailExample.error = "????????? ????????? ?????? ????????????"
                emailFlag = false
            }
            else -> {
                binding.etSignUpEmailExample.error = null
                emailFlag = true
            }
        }
    }

    private fun checkNickName(nickName: String) {
        when {
            nickName.isEmpty() -> {
                binding.etSignUpNickNameExample.error = "???????????? ??????????????????."
                nickNameFlag = false
            }
            !nicknameRegex(nickName) -> {
                binding.etSignUpNickNameExample.error = "?????? ?????? ????????? 3~15?????? ????????? ?????????"
                nickNameFlag = false
            }
            else -> {
                binding.etSignUpNickNameExample.error = null
                nickNameFlag = true
            }
        }
    }

    private fun checkPassword(password: String) {
        when {
            password.isEmpty() -> {
                binding.etSignUpPasswordInsert.error = "??????????????? ??????????????????."
                passwordFlag = false
            }
            !passwordRegex(password) -> {
                binding.etSignUpPasswordInsert.error = "??????/??????/????????????(?????? ??????)?????? 8~16?????? ??????"
                passwordFlag = false
            }
            !passwordCheckRegex(password) -> {
                binding.etSignUpPasswordInsertMore.error = "??????/??????/????????????(?????? ??????)?????? 8~16?????? ??????"
                passwordCheckFlag = false
            }
            password.isNotEmpty() -> {
                binding.etSignUpPasswordInsert.error = null
                passwordFlag = true

                if (!isInvalidEditTextPassword()) {
                    binding.etSignUpPasswordInsertMore.error = "??????????????? ???????????? ????????????"
                    passwordCheckFlag = false
                    passwordFlag = true
                } else {
                    binding.etSignUpPasswordInsertMore.error = null
                    passwordCheckFlag = true
                }
            }
        }
    }

    private fun checkPasswordAgain(password: String) {
        when {
            password.isEmpty() -> {
                binding.etSignUpPasswordInsertMore.error = "??????????????? ??????????????????."
                passwordFlag = false
            }
            !passwordCheckRegex(password) -> {
                binding.etSignUpPasswordInsertMore.error = "??????/??????/????????????(?????? ??????)?????? 8~16?????? ??????"
                passwordCheckFlag = false
            }
            password.isNotEmpty() -> {
                binding.etSignUpPasswordInsertMore.error = null
                passwordFlag = true
                when {
                    binding.etSignUpPasswordInsertMore.editText?.text.toString() != ""
                            && binding.etSignUpPasswordInsertMore.editText?.text.toString() != binding.etSignUpPasswordInsert.editText?.text.toString() -> {
                        binding.etSignUpPasswordInsertMore.error = "??????????????? ???????????? ????????????"
                        passwordCheckFlag = false
                        passwordFlag = true
                    }
                    else -> {
                        binding.etSignUpPasswordInsertMore.error = null
                        passwordCheckFlag = true
                    }
                }
            }
        }
    }
}
