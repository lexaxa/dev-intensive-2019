package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.utils.Utils
import kotlin.math.roundToInt

fun Activity.getRootView(): View {
    return findViewById<View>(android.R.id.content)
}
fun Activity.isKeyboardOpen():Boolean{
    val visibleBounds = Rect()
    this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = getRootView().height - visibleBounds.height()
    val marginOfError = Utils.convertDpToPx(this,50F).roundToInt()

    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed() : Boolean = !isKeyboardOpen()

fun Activity.hideKeyboard(){
    if(currentFocus != null){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}