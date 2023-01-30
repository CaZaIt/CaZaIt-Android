package org.cazait.cazait_android.ui.view

import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.CafeInterestFragment
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityMainBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.util.extension.replace
import org.cazait.cazait_android.ui.view.cafelist.CafeListFragment
import org.cazait.cazait_android.ui.view.mypage.MyPageFragment
import org.cazait.cazait_android.ui.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private val cafeListFragment: CafeListFragment by lazy { CafeListFragment() }
    private val cafeInterestFragment: CafeInterestFragment by lazy { CafeInterestFragment() }
    private val myPageFragment: MyPageFragment by lazy { MyPageFragment() }
//    private val moreFragment: PlusFragment by lazy { PlusFragment() }

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }

    override fun initView() {
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_cafe_list -> {
                    replaceCafeListFragment()
                    return@setOnItemSelectedListener true
                }

                R.id.menu_cafe_interest -> {
                    replaceCafeInterestFragment()
                    return@setOnItemSelectedListener true
                }

                R.id.menu_my_page -> {
                    replaceMyPageFragment()
                    return@setOnItemSelectedListener true
                }
//                R.id.menu_plus -> {
//                    replacePlusFragment()
//                    return@setOnItemSelectedListener true
//                }


                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun replaceCafeListFragment() {
        supportFragmentManager.popBackStack()
        replace(R.id.container_main, cafeListFragment)
    }

    private fun replaceCafeInterestFragment() {
        supportFragmentManager.popBackStack()
        replace(R.id.container_main, cafeInterestFragment)
    }

    private fun replaceMyPageFragment() {
        supportFragmentManager.popBackStack()
        replace(R.id.container_main, myPageFragment)
    }

}