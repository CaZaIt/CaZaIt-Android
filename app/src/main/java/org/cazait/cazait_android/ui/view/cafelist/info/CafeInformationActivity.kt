package org.cazait.cazait_android.ui.view.cafelist.info

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.CAFE_ITEM_KEY
import org.cazait.cazait_android.R
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

//    private lateinit var name:String
//    private lateinit var address:String
//    private lateinit var bundle: Bundle

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

    override fun initBeforeBinding() {
//        binding.lifecycleOwner = this
    }
    override fun initAfterBinding() {

    }
    override fun initView() {
        val cafe = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(CAFE_ITEM_KEY, Cafe::class.java)
        } else {
            intent.getParcelableExtra(CAFE_ITEM_KEY)
        }
        require(cafe!=null)
        viewModel.setCafe(cafe)
//        val menuFrag = CafeMenuFragment()
//        menuFrag.arguments = bundle

        viewModel.makeCafeImgList(cafe)
        val menuFrag = CafeMenuFragment()
        menuFrag.arguments = viewModel.bundle
        //-----------------

        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.vpImg
        viewPager.adapter = CafeImgAdapter(this, viewModel.cafeImgList)
        dotsIndicator.attachTo(viewPager)

        binding.tvCafeInfoName.text = viewModel.name
        binding.tvCafeInfoAdd.text = viewModel.address

        binding.fabReviewWrite.hide()
        binding.btnCafeMenu.isSelected = true
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.cafe_info_frag_con, menuFrag)
            .commit()

        showFragment(
            binding.btnCafeMenu,
            binding.btnCafeLocTrans,
            binding.btnCafeRatingReview,
            CafeMenuFragment(),
            binding.fabReviewWrite
        )
        showFragment(
            binding.btnCafeLocTrans,
            binding.btnCafeRatingReview,
            binding.btnCafeMenu,
            CafeLocTransFragment(),
            binding.fabReviewWrite
        )
        showFragment(
            binding.btnCafeRatingReview,
            binding.btnCafeMenu,
            binding.btnCafeLocTrans,
            CafeRatingReviewFragment(),
            binding.fabReviewWrite
        )

        binding.fabReviewWrite.setOnClickListener {
            val intent = Intent(this, CafeRatingReviewEditActivity::class.java)
            intent.putExtra("cafeId", viewModel.cafeId)
            startActivity(intent)
        }

    }

    private fun showFragment(
        btn1: Button,
        btn2: Button,
        btn3: Button,
        frag: Fragment,
        fab: FloatingActionButton
    ) {
        btn1.setOnClickListener {
            btn1.isSelected = true
            btn2.isSelected = false
            btn3.isSelected = false
            if (binding.btnCafeMenu.isSelected || binding.btnCafeLocTrans.isSelected) {
                val frag = frag
                frag.arguments = viewModel.bundle
                fab.hide()
            } else {
                val frag = frag
                frag.arguments = viewModel.bundle
                fab.show()
            }
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.cafe_info_frag_con, frag)
                .commit()
        }
    }
}