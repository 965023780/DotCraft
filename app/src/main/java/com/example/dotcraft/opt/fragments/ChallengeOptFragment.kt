package com.example.dotcraft.opt.fragments

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.example.dotcraft.App
import com.example.dotcraft.Constants
import com.example.dotcraft.R
import com.example.dotcraft.base.BaseFragment
import com.example.dotcraft.challenge.ChallengeActivity
import com.example.dotcraft.challenge.strategy.DifficultStrategy
import com.example.dotcraft.challenge.strategy.MediumStrategy
import com.example.dotcraft.challenge.strategy.SimpleStrategy

class ChallengeOptFragment(listener: FragmentListener) : BaseFragment(listener),
    View.OnClickListener {

    override fun getLayoutResID(): Int = R.layout.fragment_challenge_opt

    override fun init() {
        mView!!.findViewById<AppCompatButton>(R.id.btn_simple).setOnClickListener(this)
        mView!!.findViewById<AppCompatButton>(R.id.btn_medium).setOnClickListener(this)
        mView!!.findViewById<AppCompatButton>(R.id.btn_difficult).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(activity, ChallengeActivity::class.java)
        when (v?.id) {
            R.id.btn_simple -> {
                intent.putExtra(Constants.Opt.CHALLENGE, SimpleStrategy.instance)
            }
            R.id.btn_medium -> {
                intent.putExtra(Constants.Opt.CHALLENGE, MediumStrategy.instance)
            }
            R.id.btn_difficult -> {
                intent.putExtra(Constants.Opt.CHALLENGE, DifficultStrategy.instance)
            }
        }
        activity?.let {
            startActivity(intent)
            it.finish()
        }
    }
}