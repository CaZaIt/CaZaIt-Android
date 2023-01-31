package org.cazait.cazait_android.ui.view.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.SignUpDBHelper
import org.cazait.cazait_android.databinding.ActivitySignUpBinding
import org.cazait.cazait_android.ui.view.login.LoginActivity

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private var nickNameFlag = false
    private var passwordFlag = false
    private var passwordCheckFlag = false
    private var emailFlag = false
    private var DB: SignUpDBHelper? = null


    private val binding: ActivitySignUpBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_sign_up
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DB = SignUpDBHelper(this)

        binding.btnSignUpJoin.setOnClickListener {
            val email = binding.etSignUpEmailExample.editText?.text.toString()
            val pw = binding.etSignUpPasswordInsert.editText?.text.toString()
            val repw = binding.etSignUpPasswordInsertMore.editText?.text.toString()
            val nickname = binding.etSignUpNickNameExample.editText?.text.toString()
            if (email == "" || pw == "" || repw == "" || nickname == "")
                Toast.makeText(
                    this@SignUpActivity,
                    "회원정보를 전부 입력하세요",
                    Toast.LENGTH_SHORT
                ).show()
            else if (pw == repw) {
                val checkUsername = DB!!.checkUserName(email)
                if (!checkUsername) {
                    val insert = DB!!.insertData(email, pw)
                    if (insert) {
                        Toast.makeText(
                            this@SignUpActivity,
                            "가입되었습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                        val back = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(back)
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            "비밀번호가 일치하지 않습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.etSignUpEmailExample.editText?.addTextChangedListener(emailListener)
        binding.etSignUpPasswordInsert.editText?.addTextChangedListener(passwordListener)
        binding.etSignUpPasswordInsertMore.editText?.addTextChangedListener(passwordCheckListener)
        binding.etSignUpNickNameExample.editText?.addTextChangedListener(nickNameListener)
    }

    private fun hasSpecialCharacter(string: String): Boolean {
        for (i in string.indices) if (!Character.isLetterOrDigit(string[i])) return true
        return false
    }


    private fun hasAlphabet(string: String): Boolean {
        for (i in string.indices) if (Character.isAlphabetic(string[i].code)) return true
        return false
    }



    fun idRegex(id: String): Boolean {
        if ((!hasSpecialCharacter(id)) and (hasAlphabet(id)) and (id.length >= 6)) {
            return true
        }
        return false
    }

    fun passwordRegex(password: String): Boolean {
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,16}$".toRegex())
    }

    fun passwordCheckRegex(passwordCheck: String): Boolean {
        return passwordCheck.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,16}$".toRegex())
    }

    fun emailRegex(email: String): Boolean {
        val regexEmail =
            """^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})${'$'}""".toRegex()
        return regexEmail.matches(email)
    }

    private val nickNameListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editText: Editable?) {
            if (editText == null) return
            when {
                editText.isEmpty() -> {
                    binding.etSignUpNickNameExample.error = "닉네임을 입력해주세요."
                    nickNameFlag = false
                }
                !idRegex(editText.toString()) -> {
                    binding.etSignUpNickNameExample.error = "닉네임 양식이 맞지 않습니다"
                    nickNameFlag = false
                }
                else -> {
                    binding.etSignUpNickNameExample.error = null
                    nickNameFlag = true
                }
            }
            flagCheck()
        }
    }

    private val passwordListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editText: Editable?) {
            if (editText != null) {
                when {
                    editText.isEmpty() -> {
                        binding.etSignUpPasswordInsert.error = "비밀번호를 입력해주세요."
                        passwordFlag = false
                    }
                    !passwordRegex(editText.toString()) -> {
                        binding.etSignUpPasswordInsert.error = "영문/숫자/특수문자(공백 제외)으로 8~16자로 조합"
                        passwordFlag = false
                    }
                    !passwordCheckRegex(editText.toString()) -> {
                        binding.etSignUpPasswordInsertMore.error = "영문/숫자/특수문자(공백 제외)으로 8~16자로 조합"
                        passwordCheckFlag = false
                    }
                    editText.isNotEmpty() -> {
                        binding.etSignUpPasswordInsert.error = null
                        passwordFlag = true

                        if (!isInvalidEditTextPassword()) {
                            binding.etSignUpPasswordInsertMore.error = "비밀번호가 일치하지 않습니다"
                            passwordCheckFlag = false
                            passwordFlag = true
                        } else {
                            binding.etSignUpPasswordInsertMore.error = null
                            passwordCheckFlag = true
                        }
                    }
                }
                flagCheck()
            }
        }
    }

    private val passwordCheckListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editText: Editable?) {
            if (editText != null) {
                when {
                    editText.isEmpty() -> {
                        binding.etSignUpPasswordInsertMore.error = "비밀번호를 입력해주세요."
                        passwordFlag = false
                    }
                    !passwordCheckRegex(editText.toString()) -> {
                        binding.etSignUpPasswordInsertMore.error = "영문/숫자/특수문자(공백 제외)으로 8~16자로 조합"
                        passwordCheckFlag = false
                    }
                    editText.isNotEmpty() -> {
                        binding.etSignUpPasswordInsertMore.error = null
                        passwordFlag = true
                        when {
                            binding.etSignUpPasswordInsertMore.editText?.text.toString() != ""
                                    && binding.etSignUpPasswordInsertMore.editText?.text.toString() != binding.etSignUpPasswordInsert.editText?.text.toString() -> {
                                binding.etSignUpPasswordInsertMore.error = "비밀번호가 일치하지 않습니다"
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
                flagCheck()
            }
        }
    }

    private val emailListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.etSignUpEmailExample.error = "이메일을 입력해주세요."
                        emailFlag = false
                    }
                    !emailRegex(s.toString()) -> {
                        binding.etSignUpEmailExample.error = "이메일 양식이 맞지 않습니다"
                        emailFlag = false
                    }
                    else -> {
                        binding.etSignUpEmailExample.error = null
                        emailFlag = true
                    }
                }
                flagCheck()
            }
        }
    }

    fun flagCheck() {
        binding.btnSignUpJoin.isEnabled = nickNameFlag && passwordFlag && passwordCheckFlag && emailFlag
    }

    private fun isInvalidEditTextPassword(): Boolean {
        val etInsert = binding.etSignUpPasswordInsert.editText?.text.toString()
        val etInsertMore =
            binding.etSignUpPasswordInsertMore.editText?.text.toString()

        if (etInsertMore != "" && etInsertMore != etInsert) return false
        return true
    }
}
