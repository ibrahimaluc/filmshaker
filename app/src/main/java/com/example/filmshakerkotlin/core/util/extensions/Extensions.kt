package com.example.filmshakerkotlin.core.util.extensions

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes



fun View.setAnimation(context: Context, @AnimRes animResId: Int) =
    this.startAnimation(AnimationUtils.loadAnimation(context, animResId))
