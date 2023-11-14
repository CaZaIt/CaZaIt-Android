package org.cazait.ui.view.cafelist.info

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
import org.cazait.CAFE_ITEM_KEY
import org.cazait.R
import org.cazait.databinding.ActivityCafeInformationBinding
import org.cazait.ui.view.cafelist.info.inner.*
import org.cazait.data.model.Cafe
import org.cazait.ui.adapter.CafeImgAdapter
import org.cazait.ui.base.BaseActivity
import org.cazait.ui.util.extension.observe
import org.cazait.ui.view.cafelist.info.inner.CafeLocTransFragment
import org.cazait.ui.view.cafelist.info.inner.CafeMenuFragment
import org.cazait.ui.view.cafelist.info.inner.CafeRatingReviewEditActivity
import org.cazait.ui.view.cafelist.info.inner.CafeRatingReviewFragment
import org.cazait.ui.view.cafelist.info.util.OnMapTouchListener
import org.cazait.ui.viewmodel.CafeInfoViewModel

@AndroidEntryPoint
class CafeInformationActivity :
    BaseActivity<ActivityCafeInformationBinding, CafeInfoViewModel>(),
    OnMapTouchListener {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_information

    override val viewModel: CafeInfoViewModel by viewModels()

    private lateinit var bundle: Bundle

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
    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.cafeIdData, ::handleCafeId)
        observe(viewModel.locationData, ::handleLocation)
    }

    override fun initView() {
        val cafe = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(CAFE_ITEM_KEY, Cafe::class.java)
        } else {
            intent.getParcelableExtra(CAFE_ITEM_KEY)
        }

        require(cafe != null)
        viewModel.setCafe(cafe)

        viewModel.makeCafeImgList(cafe)
        val menuFrag = CafeMenuFragment()
        makeBundleData()
        menuFrag.arguments = bundle
        // -----------------

        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.vpImg
        viewPager.adapter = CafeImgAdapter(this, viewModel.cafeImgList)
        dotsIndicator.attachTo(viewPager)

        binding.tvCafeInfoName.text = viewModel.name
        binding.tvCafeInfoAdd.text = viewModel.address

        initDefaultFrag(menuFrag)

        showFragment(
            binding.btnCafeMenu,
            binding.btnCafeLocTrans,
            binding.btnCafeRatingReview,
            CafeMenuFragment(),
            binding.fabReviewWrite,
        )
        showFragment(
            binding.btnCafeLocTrans,
            binding.btnCafeRatingReview,
            binding.btnCafeMenu,
            CafeLocTransFragment(),
            binding.fabReviewWrite,
        )
        showFragment(
            binding.btnCafeRatingReview,
            binding.btnCafeMenu,
            binding.btnCafeLocTrans,
            CafeRatingReviewFragment(),
            binding.fabReviewWrite,
        )

        binding.fabReviewWrite.setOnClickListener {
            val intent = Intent(this, CafeRatingReviewEditActivity::class.java)
            intent.putExtra("cafeId", viewModel.cafeId)
            startActivity(intent)
        }
    }

    private fun initDefaultFrag(menuFrag: Fragment) {
        binding.fabReviewWrite.hide()
        binding.btnCafeMenu.isSelected = true
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.cafe_info_frag_con, menuFrag)
            .commit()
    }

    private fun showFragment(
        btn1: Button,
        btn2: Button,
        btn3: Button,
        fragment: Fragment,
        fab: FloatingActionButton,
    ) {
        var frag: Fragment
        btn1.setOnClickListener {
            btn1.isSelected = true
            btn2.isSelected = false
            btn3.isSelected = false
            if (binding.btnCafeMenu.isSelected || binding.btnCafeLocTrans.isSelected) {
                frag = fragment
                frag.arguments = bundle
                fab.hide()
            } else {
                frag = fragment
                frag.arguments = bundle
                fab.show()
            }
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.cafe_info_frag_con, frag)
                .commit()
        }
    }

    private fun makeBundleData() {
        bundle = Bundle()
        Log.d("cafeId", "${viewModel.cafeIdData.value!!}")
        Log.d("cafeLat", viewModel.locationData.value!![0])
        Log.d("cafeLong", viewModel.locationData.value!![1])
        bundle.putLong("cafeId", viewModel.cafeIdData.value!!)
        bundle.putString("cafeLat", viewModel.locationData.value!![0])
        bundle.putString("cafeLong", viewModel.locationData.value!![1])
    }

    private fun handleCafeId(status: Long) {
        return
    }

    private fun handleLocation(status: List<String>) {
        return
    }
}
