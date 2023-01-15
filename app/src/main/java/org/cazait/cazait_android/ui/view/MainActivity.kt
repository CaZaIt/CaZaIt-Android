package org.cazait.cazait_android.ui.view

import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityMainBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.view.cafelist.CafeListFragment
import org.cazait.cazait_android.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private val transaction: FragmentTransaction by lazy {
        supportFragmentManager.beginTransaction()
    }

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }

    override fun initView() {
        transaction.replace(R.id.listFrame, CafeListFragment())
        transaction.commitAllowingStateLoss()
    }

    private fun initBottomNavigation() {

    }
}