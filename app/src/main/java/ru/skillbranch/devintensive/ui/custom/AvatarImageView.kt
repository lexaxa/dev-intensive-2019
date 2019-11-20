package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import ru.skillbranch.devintensive.R

class AvatarImageView @JvmOverloads constructor(
    context : Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): CircleImageView(context, attrs, defStyleAttr){
    private lateinit var paint: Paint
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas

    fun setInitials(initials: String) {
        paint = Paint()
        with(paint){
            isAntiAlias = true
            textSize = layoutParams.height / 2.33f
            color = Color.WHITE
            textAlign = Paint.Align.CENTER
        }

        val textBounds = Rect()
        paint.getTextBounds(initials, 0, initials.length, textBounds)
        val backgroundBounds = RectF()
        backgroundBounds.set(0f, 0f, layoutParams.width.toFloat(), layoutParams.height.toFloat())
        val textBottom = backgroundBounds.centerY() - textBounds.exactCenterY()

        bitmap = Bitmap.createBitmap(layoutParams.width, layoutParams.height, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(resources.getColor(R.color.color_accent))
        canvas = Canvas(bitmap)
        canvas.drawText(initials, backgroundBounds.centerX(), textBottom , paint)
        setImageBitmap(bitmap)
        invalidate()
    }

}