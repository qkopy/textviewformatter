package com.qkopy.textviewformatter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet

import java.util.ArrayList

class QkoEditText : android.support.v7.widget.AppCompatEditText {

    /**
     * List of [TextWatcher] listening to this [QkoEditText]
     */
    private var mListeners: ArrayList<TextWatcher>? = null

    /**
     * [TextWatcher] listener to do the formatting.
     */
    private val mEditTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            sendBeforeTextChanged(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            sendOnTextChanged(s, start, before, count)
        }

        override fun afterTextChanged(s: Editable) {

            postDelayed({ format() }, 10)

        }
    }

    /*
     * Constructor
     */
    constructor(context: Context) : super(context) {
        init()
    }

    /*
     * Constructor
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    /*
     * Constructor
     */
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    /**
     * Initializer method to listen for text change events.
     */
    private fun init() {
        addTextChangedListener(mEditTextWatcher)
    }

    /**
     * Performs formatting.
     */
    private fun format() {

        val text = text
        val formatted = QkoViewCompat.extractFlagsForEditText(text!!)
        removeTextChangedListener(mEditTextWatcher)
        val selectionEnd = selectionEnd
        val selectionStart = selectionStart
        setText(formatted)
        setSelection(selectionStart, selectionEnd)
        val formattedEditableText = getText()
        sendAfterTextChanged(formattedEditableText)
        addTextChangedListener(mEditTextWatcher)
    }

    /**
     * Send an before text change event to child listeners
     *
     *
     */
    private fun sendBeforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        if (mListeners != null) {
            for (i in mListeners!!.indices) {
                mListeners!![i].beforeTextChanged(s, start, count, after)
            }
        }
    }

    /**
     * Send an on text change event to child listeners
     *
     */
    private fun sendOnTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (mListeners != null) {
            for (i in mListeners!!.indices) {
                mListeners!![i].onTextChanged(s, start, before, count)
            }
        }
    }

    /**
     * Send an after text change event to child listeners
     *
     * @see {@link TextWatcher.afterTextChanged
     */
    private fun sendAfterTextChanged(s: Editable?) {
        if (mListeners != null) {
            for (i in mListeners!!.indices) {
                mListeners!![i].afterTextChanged(s)
            }
        }
    }

    /*
     * Overridden method.
     * Registers the callback for text change events.
     */
    override fun addTextChangedListener(watcher: TextWatcher) {

        if (watcher !== mEditTextWatcher) {
            if (mListeners == null) {
                mListeners = ArrayList()
            }

            mListeners!!.add(watcher)
        } else {
            super.addTextChangedListener(watcher)
        }
    }

    /*
     * Overridden method.
     * Unregisters the callback for text change events.
     */
    override fun removeTextChangedListener(watcher: TextWatcher) {
        if (watcher !== mEditTextWatcher) {
            if (mListeners != null) {
                val i = mListeners!!.indexOf(watcher)

                if (i >= 0) {
                    mListeners!!.removeAt(i)
                }
            }
        } else {
            super.removeTextChangedListener(watcher)
        }
    }

}