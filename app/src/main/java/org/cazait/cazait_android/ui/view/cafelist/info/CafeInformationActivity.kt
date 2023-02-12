package org.cazait.cazait_android.ui.view.cafelist.info

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.CAFE_ITEM_KEY
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Datasource
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.databinding.ActivityCafeInformationBinding
import org.cazait.cazait_android.ui.adapter.CafeImgAdapter
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.view.cafelist.info.inner.*
import org.cazait.cazait_android.ui.view.cafelist.info.util.OnMapTouchListener
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel

@AndroidEntryPoint
class CafeInformationActivity : BaseActivity<ActivityCafeInformationBinding, CafeInfoViewModel>(),
    OnMapTouchListener {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_information

    override val viewModel: CafeInfoViewModel by viewModels()

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

    override fun initAfterBinding() {

    }

    override fun initBeforeBinding() {
    }

    override fun initView() {
        // ------------------ CafeListFrag에서 데이터를 넘겨 받음
        val cafe = if (Build.VERSION.SDK_INT >= 33) {     // cafeId가 담기는 것
            intent.getParcelableExtra(CAFE_ITEM_KEY, Cafe::class.java)
        } else {
            intent.getParcelableExtra(CAFE_ITEM_KEY)
        }
        // 위 데이터를 fragment에 넘겨줌
        val bundle = Bundle()
        if (cafe != null) {
            bundle.putLong("cafeId", cafe.id)
        }
        val menuFrag = CafeMenuFragment()
        menuFrag.arguments = bundle
        //-----------------

        val imgList = Datasource().loadCafeImg()
        addCafeImg(imgList, R.drawable.image_cafe_ex1)

        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.vpImg
        viewPager.adapter = CafeImgAdapter(imgList)
        dotsIndicator.attachTo(viewPager)

        binding.tvCafeInfoName.text = resources.getString(R.string.cafelist1_name)
        binding.tvCafeInfoAdd.text = resources.getString(R.string.cafelist1_add)
        binding.fabReviewWrite.hide()

        binding.btnCafeMenu.setSelected(true)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.cafe_info_frag_con, menuFrag)
            .commit()

        showMenuLocFragment(
            binding.btnCafeMenu,
            binding.btnCafeLocTrans,
            binding.btnCafeRatingReview,
            CafeMenuFragment(),
            binding.fabReviewWrite
        )
        showMenuLocFragment(
            binding.btnCafeLocTrans,
            binding.btnCafeRatingReview,
            binding.btnCafeMenu,
            CafeLocTransFragment(),
            binding.fabReviewWrite
        )
        showReviewFragment(
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

    }

    private fun addCafeImg(list: MutableList<Int>, image: Int) {
        list.add(image)
    }

    private fun showMenuLocFragment(
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

    private fun showReviewFragment(
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
}