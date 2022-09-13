package com.quanticheart.yugiho

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    private var animation1: Animation? = null
    private var animation2: Animation? = null
    private var isBackOfCardShowing = true
    private var ivCarta: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animation1 = AnimationUtils.loadAnimation(this, R.anim.fechar)
        animation1?.setAnimationListener(this)
        animation2 = AnimationUtils.loadAnimation(this, R.anim.abrir)
        animation2?.setAnimationListener(this)
        ivCarta = findViewById<View>(R.id.ivCarta) as ImageView
    }

    fun virarCarta(v: View) {
        v.isEnabled = false
        ivCarta!!.clearAnimation()
        ivCarta!!.animation = animation1
        ivCarta!!.startAnimation(animation1)
    }

    override fun onAnimationEnd(animation: Animation) {
        if (animation === animation1) {
            if (isBackOfCardShowing) {
                ivCarta!!.setImageResource(R.drawable.carta_frente)
            } else {
                ivCarta!!.setImageResource(R.drawable.carta_tras)
            }
            ivCarta!!.clearAnimation()
            ivCarta!!.animation = animation2
            ivCarta!!.startAnimation(animation2)
        } else {
            isBackOfCardShowing = !isBackOfCardShowing
            ivCarta!!.isEnabled = true
        }
    }

    override fun onAnimationRepeat(animation: Animation) {}
    override fun onAnimationStart(animation: Animation) {}
}