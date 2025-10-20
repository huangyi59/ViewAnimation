package com.hy.view.animation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
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

    // view 动画
    private lateinit var animation:Animation
    private lateinit var animationSet:AnimationSet

    // 属性动画

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onViewClick(v: View) {
        when(v) {
            // 淡入、淡出
            binding.btnAnimationAlpha -> {
                lifecycleScope.launch {
                    startAnim(animId = R.anim.fade_in)
                    delay(1000)
                    startAnim(animId = R.anim.fade_out)
                }
                logD(TAG, "fade in or fade out.")
            }

            // 缩小、放大
            binding.btnAnimationScale -> {
                lifecycleScope.launch {
                    startAnim(animId = R.anim.scale_in)
                    delay(1000)
                    startAnim(animId = R.anim.scale_out)
                }
                logD(TAG, "scale in or scale out.")
            }

            // 左移、右移
            binding.btnAnimationTranslate -> startAnim(animId = R.anim.traslation_enter_out)

            // 中心旋转
            binding.btnAnimationRotate -> startAnim(animId = R.anim.rotate_in_center)

            // 组合1
            binding.btnAnimationSet1 -> startAnimSet(R.anim.animation_set1)

            // 组合2
            binding.btnAnimationSet2 -> startAnimSet(R.anim.animation_set2)

            // 属性动画
            binding.btnObjectAlpha -> startObjectAlpha()
            binding.btnObjectTranslate -> startObjectTranslate()
            binding.btnObjectScale -> startObjectScale()
            binding.btnObjectRotate -> startObjectRotate()
            binding.btnObjectSet1 -> startObjectSet1()
            binding.btnObjectSet2 -> startObjectSet2()
            binding.btnCustomAnim -> startCustomAnim()
        }
    }

    private fun startAnim(animId: Int) {
        animation = AnimationUtils.loadAnimation(this, animId).apply {
            binding.ivAnimation.startAnimation(this)
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
        binding.ivAnimation.startAnimation(animationSet)
    }

    private fun startObjectAlpha() {
        ObjectAnimator.ofFloat(binding.ivObjectAnimation, "alpha", 1.0f, 0f).apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
        }.start()
    }

    private fun startObjectScale() {
        AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(binding.ivObjectAnimation, "scaleX", 1.0f, 0.5f).apply {
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                },
                ObjectAnimator.ofFloat(binding.ivObjectAnimation, "scaleY", 1.0f, 0.5f).apply {
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                }
            )
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }.start()
    }

    private fun startObjectTranslate() {
        ObjectAnimator.ofFloat(binding.ivObjectAnimation, "translationX", 0f, 400f).apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
        }.start()
    }

    private fun startObjectRotate() {
        ObjectAnimator.ofFloat(binding.ivObjectAnimation, "rotation", 0f, 360f).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 1
            repeatMode = ValueAnimator.RESTART
        }.start()
    }

    private fun startObjectSet1() {
        AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(binding.ivObjectAnimation, "scaleX", 1.0f, 0.5f).apply {
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                },
                ObjectAnimator.ofFloat(binding.ivObjectAnimation, "scaleY", 1.0f, 0.5f).apply {
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                },
                ObjectAnimator.ofFloat(binding.ivObjectAnimation, "translationX", 0f, 400f).apply {
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                }
            )
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }.start()
    }

    private fun startObjectSet2() {
        AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(binding.ivObjectAnimation, "scaleX", 1.0f, 0.5f).apply {
                    duration = 1000
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                },
                ObjectAnimator.ofFloat(binding.ivObjectAnimation, "scaleY", 1.0f, 0.5f).apply {
                    duration = 1000
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                },
                ObjectAnimator.ofFloat(binding.ivObjectAnimation, "translationX", 0f, 400f).apply {
                    duration = 1000
                    repeatCount = 1
                    repeatMode = ValueAnimator.REVERSE
                    startDelay = 2000
                }
            )
            addListener(object: Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    logD(TAG, "onAnimationStart")
                }

                override fun onAnimationEnd(animation: Animator) {
                    logD(TAG, "onAnimationEnd")
                }

                override fun onAnimationCancel(animation: Animator) {
                    logD(TAG, "onAnimationCancel")
                }

                override fun onAnimationRepeat(animation: Animator) {
                    logD(TAG, "onAnimationRepeat")
                }

            })
            interpolator = AccelerateDecelerateInterpolator()
        }.start()
    }

    private fun startCustomAnim() {
        ObjectAnimator.ofFloat(binding.viewBottom, "translationY", 0f, binding.viewBottom.height.toFloat()).apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }.start()
    }

    override fun onPause() {
        super.onPause()
        animation.cancel()
        animationSet.cancel()
    }

}