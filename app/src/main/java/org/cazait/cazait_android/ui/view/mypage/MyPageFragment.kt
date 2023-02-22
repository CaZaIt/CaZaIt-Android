package org.cazait.cazait_android.ui.view.mypage

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.databinding.FragmentMyPageBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.base.BaseViewModel
import org.cazait.cazait_android.ui.util.extension.observe
import org.cazait.cazait_android.ui.view.login.LoginActivity
import org.cazait.cazait_android.ui.view.mypage.recent.CafeRecentActivity
import org.cazait.cazait_android.ui.viewmodel.MyPageViewModel

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding, MyPageViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page

    override val viewModel: MyPageViewModel by viewModels()

    override fun initAfterBinding() {}

    override fun initBeforeBinding() {}


    override fun initView() {
        initButtons()
        observeViewModel()
    }

    private fun observeViewModel() {
        observeLogoutCompleted()
    }

    private fun observeLogoutCompleted() {
        observe(viewModel.logoutCompleted, ::handleLogoutCompleted)
    }

    private fun handleLogoutCompleted(status: Boolean) {
        if (status) {
            val nextScreenIntent = Intent(activity, LoginActivity::class.java)
            nextScreenIntent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(nextScreenIntent)
        }
    }

    private fun initButtons() {
        initLogoutButton()
        initGoRecentButton()
    }

    private fun initGoRecentButton() {
        binding.btnMyPageGoRecent.setOnClickListener {
            val popUpScreenIntent = Intent(activity, CafeRecentActivity::class.java)
            startActivity(popUpScreenIntent)
        }
    }

    private fun initLogoutButton() {
        binding.btnMyPageLogout.setOnClickListener {
            viewModel.logout()
        }
    }
}


