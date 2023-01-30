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
import org.cazait.cazait_android.databinding.ActivitySignupBinding
import org.cazait.cazait_android.ui.view.login.LoginActivity

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private var idFlag = false
    private var passwordFlag = false
    private var passwordCheckFlag = false
    private var emailFlag = false
    private var DB: SignUpDBHelper? = null


    private val binding: ActivitySignupBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_signup
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DB = SignUpDBHelper(this)

        binding.btnSignupJoin.setOnClickListener {
            val id = binding.etSignupIdExample.editText?.text.toString()
            val pw = binding.etSignupPasswordInsert.editText?.text.toString()
            val repw = binding.etSignupPasswordInsertMore.editText?.text.toString()
            val email = binding.etSignupEmailExample.editText?.text.toString()
            if (id == "" || pw == "" || repw == "" || email == "")
                Toast.makeText(
                    this@SignUpActivity,
                    "회원정보를 전부 입력하세요",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                if (pw == repw) {
                    val checkUsername = DB!!.checkUsername(id)
                    if (checkUsername == false) {
                        val insert = DB!!.insertData(id, pw)
                        if (insert == true) {
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
        }

        binding.etSignupIdExample.editText?.addTextChangedListener(idListener)
        binding.etSignupPasswordInsert.editText?.addTextChangedListener(passwordListener)
        binding.etSignupPasswordInsertMore.editText?.addTextChangedListener(passwordCheckListener)
        binding.etSignupIdExample.hint = resources.getString(R.string.signup_id_example)
        binding.etSignupEmailExample.editText?.addTextChangedListener(emailListener)
        binding.etSignupIdExample.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etSignupIdExample.hint = ""
            } else {
                binding.etSignupIdExample.hint = resources.getString(R.string.signup_id_example)
            }
        }

    }

    private fun hasSpecialCharacter(string: String): Boolean {
        for (i in string.indices) {
            if (!Character.isLetterOrDigit(string[i])) {
                return true
            }
        }
        return false
    }


    private fun hasAlphabet(string: String): Boolean {
        for (i in string.indices) {
            if (Character.isAlphabetic(string[i].code)) {
                return true
            }
        }
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

    private val idListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.etSignupIdExample.error = "아이디를 입력해주세요."
                        idFlag = false
                    }
                    !idRegex(s.toString()) -> {
                        binding.etSignupIdExample.error = "아이디 양식이 맞지 않습니다"
                        idFlag = false
                    }
                    else -> {
                        binding.etSignupIdExample.error = null
                        idFlag = true
                    }
                }
                flagCheck()
            }
        }
    }
    private val passwordListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.etSignupPasswordInsert.error = "비밀번호를 입력해주세요."
                        passwordFlag = false
                    }
                    !passwordRegex(s.toString()) -> {
                        binding.etSignupPasswordInsert.error = "영문/숫자/특수문자(공백 제외)으로 8~16자로 조합"
                        passwordFlag = false
                    }
                    !passwordCheckRegex(s.toString()) -> {
                        binding.etSignupPasswordInsertMore.error = "영문/숫자/특수문자(공백 제외)으로 8~16자로 조합"
                        passwordCheckFlag = false
                    }

                    s.isNotEmpty() -> {
                        binding.etSignupPasswordInsert.error = null
                        passwordFlag = true
                        when {
                            binding.etSignupPasswordInsertMore.editText?.text.toString() != ""
                                    && binding.etSignupPasswordInsertMore.editText?.text.toString() != binding.etSignupPasswordInsert.editText?.text.toString() -> {
                                binding.etSignupPasswordInsertMore.error = "비밀번호가 일치하지 않습니다"
                                passwordCheckFlag = false
                                passwordFlag = true
                            }
                            else -> {
                                binding.etSignupPasswordInsertMore.error = null
                                passwordCheckFlag = true
                            }
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

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.etSignupPasswordInsertMore.error = "비밀번호를 입력해주세요."
                        passwordFlag = false
                    }
                    !passwordCheckRegex(s.toString()) -> {
                        binding.etSignupPasswordInsertMore.error = "영문/숫자/특수문자(공백 제외)으로 8~16자로 조합"
                        passwordCheckFlag = false
                    }

                    s.isNotEmpty() -> {
                        binding.etSignupPasswordInsertMore.error = null
                        passwordFlag = true
                        when {
                            binding.etSignupPasswordInsertMore.editText?.text.toString() != ""
                                    && binding.etSignupPasswordInsertMore.editText?.text.toString() != binding.etSignupPasswordInsert.editText?.text.toString() -> {
                                binding.etSignupPasswordInsertMore.error = "비밀번호가 일치하지 않습니다"
                                passwordCheckFlag = false
                                passwordFlag = true
                            }
                            else -> {
                                binding.etSignupPasswordInsertMore.error = null
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
                        binding.etSignupEmailExample.error = "이메일을 입력해주세요."
                        emailFlag = false
                    }
                    !emailRegex(s.toString()) -> {
                        binding.etSignupEmailExample.error = "이메일 양식이 맞지 않습니다"
                        emailFlag = false
                    }
                    else -> {
                        binding.etSignupEmailExample.error = null
                        emailFlag = true
                    }
                }
                flagCheck()
            }
        }
    }

    fun flagCheck() {
        binding.btnSignupJoin.isEnabled = idFlag && passwordFlag && passwordCheckFlag && emailFlag
    }
}
