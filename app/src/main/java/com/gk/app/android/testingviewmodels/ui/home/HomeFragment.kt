package com.gk.app.android.testingviewmodels.ui.home

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
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var homeViewModel: HomeViewModel? = null

    /**
     * Factory for creating this fragment with an injected ViewModel (otherwise it would be
     * automatically injected by Hilt)
     */
    class Factory(
        private val viewModel: HomeViewModel? = null
    ) : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            if (className == HomeFragment::class.java.name) {
                return HomeFragment().apply {
                    viewModel?.let { this.homeViewModel = it }
                }
            }
            return super.instantiate(classLoader, className)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.v(javaClass.simpleName, "onCreateView()")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onDestroy() {
        Log.v(javaClass.simpleName, "onDestroy()")
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.v(javaClass.simpleName, "onActivityCreated() savedInstanceState=$savedInstanceState")
        super.onActivityCreated(savedInstanceState)

        // If we do not have a constructor injected view model, obtain it from property delegate
        if (homeViewModel == null) {
            val vm: HomeViewModelImpl by viewModels()
            homeViewModel = vm
        }

        Log.i(javaClass.simpleName, "viewModel=$homeViewModel")

        mainBtn.setOnClickListener { homeViewModel?.onButtonClicked("someId") }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(javaClass.simpleName, "onSaveInstanceState()")

        //TODO use view model to save instance state
        //outState.putCharSequence("test", "Some saved data")

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d(javaClass.simpleName, "onViewStateRestored() savedInstanceState=$savedInstanceState")

        super.onViewStateRestored(savedInstanceState)
    }

}