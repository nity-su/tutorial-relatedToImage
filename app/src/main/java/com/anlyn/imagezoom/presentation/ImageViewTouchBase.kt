package com.anlyn.imagezoom.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView



abstract class ImageViewTouchBase(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatImageView(context, attrs, defStyleAttr) {

    init{
       scaleType = ScaleType.MATRIX
        val m: Matrix = Matrix()

        val w = drawable.intrinsicWidth
        val h = drawable.intrinsicHeight

        val drawableRect = RectF(0f, 0f, w.toFloat(), h.toFloat())
        val viewRect = RectF(0f, 0f, 333f, 333f)

        m.setRectToRect(drawableRect,viewRect,Matrix.ScaleToFit.CENTER)
        imageMatrix = m
    }

    private val TAG = ImageViewTouchBase::class.simpleName

    protected var mViewPort = RectF()
    protected var mViewPortOld = RectF()

    constructor(context: Context) : this(context,null){

    }

    constructor(context: Context,attributes: AttributeSet?) : this(context,attributes,0){

    }


    fun viewPortSet(left: Int, top: Int, right: Int, bottom: Int){
        mViewPort.set(left.toFloat(),top.toFloat(),right.toFloat(),bottom.toFloat())

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        if(scaleType == ScaleType.FIT_XY) {
            if (drawable != null) {
                Log.d(TAG,"drawable.draw")
                drawable.draw(canvas!!)
            }
        }else {
            //default fit center 
            super.onDraw(canvas)
        }
    }
}