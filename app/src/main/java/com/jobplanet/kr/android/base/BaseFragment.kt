package com.jobplanet.kr.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class BaseFragment<T: ViewDataBinding>(
    private val layoutRes: Int
    ) : Fragment() {

    private var _binding: T? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    protected fun binding(action: T.() -> Unit) {
        binding.run(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * 메모리 누수 방지코드입니다.
         */
        _binding = null
    }


}