package org.cazait.cazait_android.ui.view.cafelist.info

import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityCafeInformationBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.view.cafelist.info.inner.*
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel

@AndroidEntryPoint
class CafeInformationActivity : BaseActivity<ActivityCafeInformationBinding, CafeInfoViewModel>(),
    OnMapTouchListener {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_information

    override val viewModel: CafeInfoViewModel by viewModels()

    override fun initAfterBinding() {

    }

    override fun initBeforeBinding() {
    }

    override fun initView() {
        binding.tvCafeInfoName.text = resources.getString(R.string.cafelist1_name)
        binding.tvCafeInfoAdd.text = resources.getString(R.string.cafelist1_add)
        binding.fabReviewWrite.hide()

        binding.btnCafeMenu.setSelected(true)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.cafe_info_frag_con, CafeMenuFragment())
            .commit()

        setFragment1(
            binding.btnCafeMenu,
            binding.btnCafeLocTrans,
            binding.btnCafeRatingReview,
            CafeMenuFragment(),
            binding.fabReviewWrite
        )
        setFragment1(
            binding.btnCafeLocTrans,
            binding.btnCafeRatingReview,
            binding.btnCafeMenu,
            CafeLocTransFragment(),
            binding.fabReviewWrite
        )
        setFragment2(
            binding.btnCafeRatingReview,
            binding.btnCafeMenu,
            binding.btnCafeLocTrans,
            CafeRatingReviewFragment(),
            binding.fabReviewWrite
        )

        binding.fabReviewWrite.setOnClickListener {
            val intent = Intent(this, CafeRatingReviewEditActivity::class.java)
            startActivity(intent)
        }

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        binding.vpImg.adapter = pagerAdapter
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = Int.MAX_VALUE

        override fun createFragment(position: Int): Fragment {
            return when (position % 3) {
                0 -> ImageSlideFragment(R.drawable.image_cafe_ex1)
                1 -> ImageSlideFragment(R.drawable.image_cafe_ex1)
                else -> ImageSlideFragment(R.drawable.image_cafe_ex1)
            }
        }
    }

    private fun setFragment1(
        btn1: Button,
        btn2: Button,
        btn3: Button,
        frag: Fragment,
        fab: FloatingActionButton
    ) {
        btn1.setOnClickListener {
            btn1.setSelected(true)
            btn2.setSelected(false)
            btn3.setSelected(false)
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.cafe_info_frag_con, frag)
                .commit()
            fab.hide()
        }
    }

    private fun setFragment2(
        btn1: Button,
        btn2: Button,
        btn3: Button,
        frag: Fragment,
        fab: FloatingActionButton
    ) {
        btn1.setOnClickListener {
            btn1.setSelected(true)
            btn2.setSelected(false)
            btn3.setSelected(false)
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.cafe_info_frag_con, frag)
                .commit()
            fab.show()
        }
    }

    override fun onTouch() {
        binding.cafeInfoFragCon.requestDisallowInterceptTouchEvent(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("Toolbar_item", "뒤로가기 클릭")
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}