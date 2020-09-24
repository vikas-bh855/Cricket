package com.sportsinteractive.cricket

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("showCaptain", "showWicketK")
fun TextView.showStatus(isWicketKeeper: Boolean, isCaptain: Boolean) {
    if (isWicketKeeper && isCaptain)
        text = "(Wicket-Keeper) " + " (Captain)"
    else if (isWicketKeeper)
        text = "(Wicket-Keeper)"
    else if (isCaptain)
        text = "(Captain"
    else
        visibility = View.GONE
}

