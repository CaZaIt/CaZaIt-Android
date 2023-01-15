package org.cazait.cazait_android.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityMainBinding
import org.cazait.cazait_android.ui.view.cafelist.CafeListFragment
import org.cazait.cazait_android.ui.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ViewModel::class.java)

        transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.listFrame, CafeListFragment())
        transaction.commitAllowingStateLoss()
    }
}