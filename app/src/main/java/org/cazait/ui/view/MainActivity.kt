package org.cazait.ui.view

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.R
import org.cazait.databinding.ActivityMainBinding
import org.cazait.ui.base.BaseActivity
import org.cazait.ui.util.extension.replace
import org.cazait.ui.view.cafelist.CafeListFragment
import org.cazait.ui.view.interest.CafeInterestFragment
import org.cazait.ui.view.mypage.MyPageFragment
import org.cazait.ui.view.viewmore.ViewMoreFragment
import org.cazait.ui.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private var backButtonPressedTime = 0L
    private val backButtonThreshold = 2000L

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private val cafeListFragment: CafeListFragment by lazy { CafeListFragment() }
    private val cafeInterestFragment: CafeInterestFragment by lazy { CafeInterestFragment() }
    private val myPageFragment: MyPageFragment by lazy { MyPageFragment() }
    private val viewMoreFragment: ViewMoreFragment by lazy { ViewMoreFragment() }

    override fun initBeforeBinding() {
        setupBackButtonHandler()
    }

    override fun initView() {
        initBottomNavigation()
    }

    override fun initAfterBinding() {
    }

    private fun setupBackButtonHandler() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()
                if (backButtonPressedTime + backButtonThreshold > currentTime) {
                    finish()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.press_back_again_to_exit,
                        Toast.LENGTH_SHORT,
                    ).show()
                }
                backButtonPressedTime = currentTime
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun initBottomNavigation() {
        binding.bnvMain.itemIconTintList = null
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

                R.id.menu_view_more -> {
                    replaceViewMoreFragment()
                    return@setOnItemSelectedListener true
                }

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

    private fun replaceViewMoreFragment() {
        supportFragmentManager.popBackStack()
        replace(R.id.container_main, viewMoreFragment)
    }
}
