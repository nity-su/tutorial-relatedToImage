package com.anlyn.emotionalfaceview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context:Context,attributeSet: AttributeSet)  : View(context,attributeSet){
    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // Some colors for the face background, eyes and mouth.
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK

    // Face border width in pixels
    private var borderWidth = 4.0f
    // View size in pixels
    private var size = 320
    private var mouthPath = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFaceBackground(canvas!!)
        drawEyes(canvas)
        drawMouth(canvas)
    }
    private fun drawFaceBackground(canvas: Canvas) {

        paint.color = faceColor
        paint.style = Paint.Style.FILL

        val radius = size / 2f


        canvas.drawCircle(size / 2f, size / 2f, radius, paint)


        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth


        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {

        paint.color = eyesColor
        paint.style = Paint.Style.FILL


        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)


        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)

    }

    private fun drawMouth(canvas: Canvas) {
        // 시작점 설정
        mouthPath.moveTo(size * 0.22f, size * 0.7f)
        // 커브 구간
        mouthPath.quadTo(size * 0.50f, size * 0.50f, size * 0.78f, size * 0.70f)

        mouthPath.quadTo(size * 0.50f, size * 0.6f, size * 0.22f, size * 0.70f)

        paint.color = mouthColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        canvas.drawPath(mouthPath, paint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        size = Math.min(widthMeasureSpec,heightMeasureSpec)
        setMeasuredDimension(size,size)
    }
}