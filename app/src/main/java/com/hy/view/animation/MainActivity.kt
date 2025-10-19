package com.hy.view.animation

import android.os.Bundle
import android.view.View
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
            viewBinding.btnAnimationTranslate ->  {
                startAnim(animId = R.anim.traslation_enter_out)
                logD(TAG, "Translate.")
            }

            // 中心旋转
            viewBinding.btnAnimationRotate -> {
                startAnim(animId = R.anim.rotate_in_center)
                logD(TAG, "Rotate.")
            }
        }
    }

    private fun startAnim(animId: Int) {
        AnimationUtils.loadAnimation(this, animId).apply {
            viewBinding.ivAnimation.startAnimation(this)
        }
    }

}