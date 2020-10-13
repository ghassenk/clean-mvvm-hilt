package com.gk.app.android.testingviewmodels.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import com.gk.app.android.testingviewmodels.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment(
    private var detailViewModel: DetailViewModel? = null
) : Fragment() {

    class Factory(
        private val detailViewModel: DetailViewModel? = null
    ) : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return DetailFragment(detailViewModel)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.v(javaClass.simpleName, "onCreateView()")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onDestroy() {
        Log.v(javaClass.simpleName, "onDestroy()")
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.v(javaClass.simpleName, "onActivityCreated() savedInstanceState=$savedInstanceState")
        super.onActivityCreated(savedInstanceState)

        // If we do not have a constructor injected view model, obtain it from property delegate
        if (detailViewModel == null) {
            val vm: DetailViewModelImpl by viewModels()
            detailViewModel = vm
        }

        Log.i(javaClass.simpleName, "viewModel=${detailViewModel}")

        detailViewModel?.bindView(this) {
            detailText.text = it
        }
    }

}