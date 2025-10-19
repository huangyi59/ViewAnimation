package com.hy.view.animation

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.hy.view.animation.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var animation:Animation
    private lateinit var animationSet:AnimationSet
    private val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onViewClick(v: View) {
        when(v) {
            // 淡入、淡出
            viewBinding.btnAnimationAlpha -> {
                lifecycleScope.launch {
                    startAnim(animId = R.anim.fade_in)
                    delay(1500)
                    startAnim(animId = R.anim.fade_out)
                }
                logD(TAG, "fade in or fade out.")
            }

            // 缩小、放大
            viewBinding.btnAnimationScale -> {
                lifecycleScope.launch {
                    startAnim(animId = R.anim.scale_in)
                    delay(1500)
                    startAnim(animId = R.anim.scale_out)
                }
                logD(TAG, "scale in or scale out.")
            }

            // 左移、右移
            viewBinding.btnAnimationTranslate -> startAnim(animId = R.anim.traslation_enter_out)

            // 中心旋转
            viewBinding.btnAnimationRotate -> startAnim(animId = R.anim.rotate_in_center)

            // 组合1
            viewBinding.btnAnimationSet1 -> startAnimSet(R.anim.animation_set1)

            // 组合2
            viewBinding.btnAnimationSet2 -> startAnimSet(R.anim.animation_set2)
        }
    }

    private fun startAnim(animId: Int) {
        animation = AnimationUtils.loadAnimation(this, animId).apply {
            viewBinding.ivAnimation.startAnimation(this)
            // 监听动画的过程
            setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    logD(TAG, "onAnimationStart")
                }

                override fun onAnimationEnd(animation: Animation?) {
                    logD(TAG, "onAnimationEnd")
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    logD(TAG, "onAnimationRepeat")
                }

            })
        }
    }

    private fun startAnimSet(setAnimId: Int) {
        animationSet = AnimationUtils.loadAnimation(this, setAnimId) as AnimationSet
        viewBinding.ivAnimation.startAnimation(animationSet)
    }

    override fun onPause() {
        super.onPause()
        animation.cancel()
        animationSet.cancel()
    }

}