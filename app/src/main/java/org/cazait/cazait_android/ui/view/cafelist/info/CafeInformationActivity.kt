package org.cazait.cazait_android.ui.view.cafelist.info

import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityCafeInformationBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.view.cafelist.info.inner.CafeLocTransFragment
import org.cazait.cazait_android.ui.view.cafelist.info.inner.CafeMenuFragment
import org.cazait.cazait_android.ui.view.cafelist.info.inner.CafeRatingReviewFragment
import org.cazait.cazait_android.ui.view.cafelist.info.inner.OnMapTouchListener
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
        val toolbar = binding.toolbar
        toolbar.title = "롬곡"
        binding.tvCafeInfoAdd.text = "서울특별시 광진구 광나루로 375-1 2층(군자동)"
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
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
}