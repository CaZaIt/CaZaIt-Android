package org.cazait.cazait_android.ui.view.cafelist.info.inner

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentImageSlideBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInfoImgViewModel

@AndroidEntryPoint
class ImageSlideFragment(val image: Int) :
    BaseFragment<FragmentImageSlideBinding, CafeInfoImgViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_image_slide

    override val viewModel: CafeInfoImgViewModel by viewModels()
    override fun initView() {
        binding.imgSlide.setImageResource(image)
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}