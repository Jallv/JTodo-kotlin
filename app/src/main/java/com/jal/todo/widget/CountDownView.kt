package com.jal.todo.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import androidx.databinding.BindingAdapter
import com.jal.todo.R


class CountDownView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    /**
     * 圆环宽度,默认半径的1／5
     */
    private var mRingWidth = 0f

    /**
     * 圆环颜色,默认 #CBCBCB
     */
    private var mRingColor = 0

    /**
     * 圆环半径,默认：Math.min(getHeight()/2,getWidth()/2)
     */
    private var mRadius = 0f

    /**
     * 进度颜色
     */
    private var mProgressRingColor = 0

    /**
     * 文字颜色
     */
    private var mTextColor = 0

    /**
     * 文字大小
     */
    private var mTextSize = 0f

    /**
     * 底环画笔
     */
    private lateinit var mRingPaint: Paint

    /**
     * 内环画笔
     */
    private lateinit var mProgressRingPaint: Paint

    /**
     * 文字画笔
     */
    private lateinit var mTextPaint: Paint

    /**
     * Text的基线
     */
    private var mTextBaseLine = 0f

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CountDownView)
        mRingWidth = typedArray.getDimension(R.styleable.CountDownView_ring_width, 0f)
        mRingColor = typedArray.getColor(
            R.styleable.CountDownView_ring_color,
            Color.parseColor("#CBCBCB")
        )
        mRadius = typedArray.getDimension(R.styleable.CountDownView_radius, 0f)
        mProgressRingColor = typedArray.getColor(
            R.styleable.CountDownView_progress_ring_color,
            Color.parseColor("#000000")
        )
        mTextColor = typedArray.getColor(
            R.styleable.CountDownView_text_color,
            Color.parseColor("#000000")
        )
        mTextSize = typedArray.getDimension(R.styleable.CountDownView_text_size, 0f)
        init()
    }

    private fun init() {
        mRingPaint = Paint()
        mRingPaint.isAntiAlias = true // 抗锯齿效果
        mRingPaint.style = Paint.Style.STROKE
        mRingPaint.color = mRingColor // 背景
        mRingPaint.strokeWidth = mRingWidth
        mProgressRingPaint = Paint()
        mProgressRingPaint.isAntiAlias = true // 抗锯齿效果
        mProgressRingPaint.style = Paint.Style.STROKE
        mProgressRingPaint.strokeCap = Paint.Cap.ROUND // 圆形笔头
        mProgressRingPaint.strokeWidth = mRingWidth
        mProgressRingPaint.color = mProgressRingColor
        mTextPaint = Paint()
        mTextPaint.textSize = mTextSize
        mTextPaint.color = mTextColor
        mTextPaint.isAntiAlias = true
    }

    private var mCountDownTimer: CountDownTimer? = null
    private var mResetMillis: Long = 0
    private var mTotalMillis: Long = 0
    private var mMillisUntilFinished: Long = 0

    fun setTime(millis: Long) {
        reset()
        mResetMillis = millis
        mTotalMillis = millis
        mMillisUntilFinished = mTotalMillis
        invalidate()
    }

    fun start() {
        mCountDownTimer = object : CountDownTimer(mTotalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mMillisUntilFinished = millisUntilFinished
                invalidate()
            }

            override fun onFinish() {
                reset()
            }
        }
        mCountDownTimer!!.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mCountDownTimer?.cancel()
    }

    fun reset() {
        mCountDownTimer?.let {
            it.cancel()
            mCountDownTimer = null
            mTotalMillis = mResetMillis
            mMillisUntilFinished = mTotalMillis
            invalidate()
        }
    }

    private val rect = RectF()
    override fun onDraw(canvas: Canvas) {
        if (mTextBaseLine == 0f) {
            val metrics = mTextPaint.fontMetrics
            mTextBaseLine = height / 2f - metrics.descent + (metrics.bottom - metrics.top) / 2
        }
        // 圆心坐标,当前View的中心
        val x = width / 2.toFloat()
        val y = height / 2.toFloat()
        // 底环
        canvas.drawCircle(x, y, mRadius, mRingPaint)
        val paddingX = ((width - mRadius * 2) / 2).toInt()
        val paddingY = ((height - mRadius * 2) / 2).toInt()
        val angle = (360f * (mTotalMillis - mMillisUntilFinished) / mTotalMillis).toInt()
        val minute = (mMillisUntilFinished / 1000 / 60).toInt()
        val second = (mMillisUntilFinished / 1000 % 60).toInt()
        val text =
            if (minute < 10) "0$minute" else "$minute:${if (second < 10) "0$second".toInt() else second}"

        rect.set(
            paddingX.toFloat(),
            paddingY.toFloat(),
            (width - paddingX).toFloat(),
            (height - paddingY).toFloat()
        )
        canvas.drawArc(
            rect,
            -90f,
            angle.toFloat(),
            false,
            mProgressRingPaint
        )
        mTextPaint.measureText(text)
        val textWidth = mTextPaint.measureText(text)
        canvas.drawText(text, (width - textWidth) / 2, mTextBaseLine, mTextPaint)
    }

}