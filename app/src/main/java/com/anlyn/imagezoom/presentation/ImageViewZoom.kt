package com.anlyn.imagezoom.presentation

import android.content.Context
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent


class ImageViewZoom(context:Context,attrs:AttributeSet?,defStyleAttr: Int) :
    ImageViewTouchBase(context,attrs,defStyleAttr){

    var nowMatrix: Matrix = Matrix()
    // 변화없는 MATRIX
    var savedMatrix: Matrix = matrix

    private val TAG = ImageViewZoom::class.simpleName
    private val DEBUG = true
    private var mode : Int = 0
    private val NONE = 0
    private val DRAG = 1
    private val ZOOM = 2

    private val matrixArray = FloatArray(9)
    private var oldDist = -1f

    private val pStart = PointF()
    private val pMid = PointF()

    constructor(context:Context,attrs:AttributeSet?) : this(context,attrs,0) {

    }
    constructor(context:Context) : this(context,null) {

    }

    private fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return Math.sqrt(x * x + y * y.toDouble()).toFloat()
    }

    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point.set(x/2,y/2)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var scale = 0f
        nowMatrix.getValues(matrixArray)
        val x = matrixArray[Matrix.MTRANS_X]
        if(DEBUG)
            Log.d(TAG,"x $x")
        when(event!!.action and MotionEvent.ACTION_MASK)
        {
            MotionEvent.ACTION_UP->{

                if(DEBUG)
                Log.d(TAG,"ACTION_UP")
            }
            MotionEvent.ACTION_POINTER_UP-> {
                mode = NONE
                Log.d(TAG, "ACTION_POINTER_UP")
            }
            MotionEvent.ACTION_DOWN->{
                mode = DRAG
                savedMatrix.set(nowMatrix)
                pStart.set(event.getX(),event.getY())
            }
            MotionEvent.ACTION_POINTER_DOWN->{
                mode = ZOOM
                Log.d(TAG,"ACTION_POINTER_DOWN")
                oldDist = spacing(event)
                if(oldDist>5f){
                    if(DEBUG)
                        Log.d(TAG,"ZOOM")
                    savedMatrix.set(nowMatrix)
                    midPoint(pMid,event)
                }
            }

            MotionEvent.ACTION_MOVE->{

                if(mode == DRAG){
                    nowMatrix.set(savedMatrix)
                    var diffX = event.getX()-pStart.x
                    var diffY = event.getY()-pStart.y
                    if(x>0) {
                        //
                        nowMatrix.postTranslate(diffX, diffY)
                    }

                    if(DEBUG)
                        Log.d(TAG,"DRAG")
                }else if (mode == ZOOM){
                    val newDist = spacing(event)
                    if(DEBUG)
                        Log.d(TAG,"newDist: "+ newDist.toString())
                    if(newDist>5f){
                        nowMatrix.set(savedMatrix)
                        scale = newDist/oldDist
                        nowMatrix.postScale(scale,scale,pMid.x,pMid.y)
                    }
                }

            }

        }
        imageMatrix = nowMatrix
        return true
    }

    fun onUp():Boolean{

        return true
    }


}