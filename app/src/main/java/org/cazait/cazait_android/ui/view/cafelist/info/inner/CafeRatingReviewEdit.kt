package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityCafeRatingReviewEditBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewViewModel

class CafeRatingReviewEdit :
    BaseActivity<ActivityCafeRatingReviewEditBinding, CafeInfoReviewViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_rating_review_edit
    override val viewModel: CafeInfoReviewViewModel by viewModels()

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

    @SuppressLint("ResourceAsColor")
    override fun initView() {
        var imageView1Clicked: Boolean = false
        var imageView2Clicked: Boolean = false
        var imageView3Clicked: Boolean = false
        var imageView4Clicked: Boolean = false
        var imageView5Clicked: Boolean = false
//        binding.imgvStarPress1.setOnClickListener {
//            when (imageView1Clicked) {
//                false -> {
//                    binding.imgvStarPress1.setImageResource(R.drawable.cafe_info_rating_review_edit_star_full)
//                    imageView1Clicked = !imageView1Clicked
//                }
//                true -> {
//                    binding.imgvStarPress1.setImageResource(R.drawable.cafe_info_rating_review_edit_star_empty)
//                    imageView1Clicked = !imageView1Clicked
//                }
//            }
//        }
//        binding.imgvStarPress2.setOnClickListener {
//            when(imageView2Clicked){
//                false ->{
//                    binding.imgvStarPress2.setImageResource(R.drawable.cafe_info_rating_review_edit_star_full)
//                    imageView2Clicked = !imageView2Clicked
//                }
//                true -> {
//                    binding.imgvStarPress2.setImageResource(R.drawable.cafe_info_rating_review_edit_star_empty)
//                    imageView2Clicked = !imageView2Clicked
//                }
//            }
//        }
//        binding.imgvStarPress3.setOnClickListener {
//            when(imageView3Clicked){
//                false ->{
//                    binding.imgvStarPress3.setImageResource(R.drawable.cafe_info_rating_review_edit_star_full)
//                    imageView3Clicked = !imageView3Clicked
//                }
//                true -> {
//                    binding.imgvStarPress3.setImageResource(R.drawable.cafe_info_rating_review_edit_star_empty)
//                    imageView3Clicked = !imageView3Clicked
//                }
//            }
//        }
//        binding.imgvStarPress4.setOnClickListener {
//            when(imageView4Clicked){
//                false ->{
//                    binding.imgvStarPress4.setImageResource(R.drawable.cafe_info_rating_review_edit_star_full)
//                    imageView4Clicked = !imageView4Clicked
//                }
//                true -> {
//                    binding.imgvStarPress4.setImageResource(R.drawable.cafe_info_rating_review_edit_star_empty)
//                    imageView4Clicked = !imageView4Clicked
//                }
//            }
//        }
//        binding.imgvStarPress5.setOnClickListener {
//            when(imageView5Clicked){
//                false ->{
//                    binding.imgvStarPress5.setImageResource(R.drawable.cafe_info_rating_review_edit_star_full)
//                    imageView5Clicked = !imageView5Clicked
//                }
//                true -> {
//                    binding.imgvStarPress5.setImageResource(R.drawable.cafe_info_rating_review_edit_star_empty)
//                    imageView5Clicked = !imageView5Clicked
//                }
//            }
//        }

    }
}
