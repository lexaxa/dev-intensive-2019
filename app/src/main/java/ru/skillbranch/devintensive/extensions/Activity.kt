package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.roundToInt

fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            this.resources.displayMetrics
    )
}

fun Activity.isKeyboardOpen( rootView: View ):Boolean{
    val visibleBounds = Rect()
    rootView.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = rootView.height - visibleBounds.height()
    val marginOfError = convertDpToPx(50F).roundToInt()

    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed( rootView: View) : Boolean = !isKeyboardOpen(rootView)

fun Activity.hideKeyboard(){
    if(currentFocus != null){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}