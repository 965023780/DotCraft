package com.example.dotcraft.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment {

    protected var mView: View? = null
    var mListener: FragmentListener? = null

    constructor(listener: FragmentListener) : super() {
        mListener = listener
    }

    constructor() : super()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(getLayoutResID(), container, false)
        init()
        return mView
    }


    abstract fun getLayoutResID(): Int

    abstract fun init()


    interface FragmentListener {
        fun changeFragment(targetTag: String)
    }
}