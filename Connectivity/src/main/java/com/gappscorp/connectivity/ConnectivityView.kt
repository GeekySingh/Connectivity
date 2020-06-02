package com.gappscorp.connectivity

import android.content.Context
import android.transition.Fade
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.transition.doOnEnd
import androidx.core.transition.doOnStart
import androidx.lifecycle.LifecycleOwner

class ConnectivityView : TextView, ConnectionListener {

    private var lifecycleOwner: LifecycleOwner? = null

    private var offlineMessage: String = ""
    private var onlineMessage: String = ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : super(
            context,
            attrs,
            defStyle
    ) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            setPadding(4, 8, 4, 8)
            gravity = Gravity.CENTER
        }

        setTextColor(ContextCompat.getColor(context, android.R.color.white))
        visibility = if (Connectivity.isConnected) View.GONE else View.VISIBLE

        if (context is LifecycleOwner) {
            lifecycleOwner = context
        }

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ConnectivityView)
            offlineMessage = a.getString(R.styleable.ConnectivityView_offlineText)
                    ?: context.getString(R.string.label_offline)
            onlineMessage = a.getString(R.styleable.ConnectivityView_onlineText)
                    ?: context.getString(R.string.label_online)
            a.recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Connectivity.addConnectionListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Connectivity.removeConnectionListener(this)
    }

    override fun onConnectivityChanged(connected: Boolean) {
        setOnlineStatus(connected)
    }

    private fun setOnlineStatus(online: Boolean) {
        when (online) {
            true -> {
                text = onlineMessage
                setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
                val transition = Fade().apply {
                    duration = 300
                    doOnEnd {
                        postDelayed({
                            visibility = View.GONE
                        }, 1500)
                    }
                }
                TransitionManager.beginDelayedTransition(parent as ViewGroup, transition)
            }
            false -> {
                text = offlineMessage
                setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
                val transition = Fade().apply {
                    duration = 300
                    startDelay = 300
                    doOnStart {
                        visibility = View.VISIBLE
                    }
                }
                TransitionManager.beginDelayedTransition(parent as ViewGroup, transition)
            }
        }
    }
}