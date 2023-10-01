package com.example.filmshakerkotlin.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.filmshakerkotlin.R

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding>(
    private val viewModelClass: Class<VM>,
    private val inflateLayout: (LayoutInflater, ViewGroup?, Boolean) -> DB

) : Fragment() {

    private var _binding: DB? = null
    protected val binding get() = _binding!!

    protected val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    private val progressBar: View? by lazy {
        activity?.findViewById(R.id.pbLoading)
    }


//    protected val navController by lazy {
//        activity?.let {
//            findNavController(it ,R.id.nav_host_fragmentContainerView)
//
//        }
//    }

    protected fun setProgressStatus(isLoading: Boolean) {
        progressBar?.isVisible = isLoading
    }

    open fun onCreateViewInvoke() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateLayout(layoutInflater, container, false)
        onCreateViewInvoke()
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}