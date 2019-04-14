@file:Suppress("MemberVisibilityCanBePrivate")

package studio.forface.materialbottombar.panels.holders

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.graphics.drawable.DrawableCompat
import studio.forface.materialbottombar.utils.getColorCompat

class ColorHolder internal constructor(
    @ColorRes   val colorRes:   Int? =      null,
                val colorHex:   String? =   null,
    @ColorInt   val color:      Int? =      null
) {

    fun applyToBackground( view: View ) {
        val color = resolveColor( view.context )
        color?.let {
            try {
                DrawableCompat.setTint( view.background, color )
            } catch ( e: NullPointerException ) {
                view.setBackgroundColor( color )
            }
        }
    }

    fun applyToDrawable( context: Context, drawable: Drawable ) {
        val color = resolveColor( context )
        color?.let { DrawableCompat.setTint( drawable, color ) }
    }

    fun applyToDrawable( button: ImageButton ) {
        val color = resolveColor( button.context )
        color?.let { DrawableCompat.setTint( button.drawable, color ) }
    }

    /** Apply `this` [ColorHolder] to the given [ImageView] */
    fun applyToImageView(imageView: ImageView) {
        val color = resolveColor( imageView.context )
        // Set color or defaultColorFilter if not null
        if ( color != null ) imageView.setColorFilter( color )
        else imageView.clearColorFilter()
    }

    /**
     * Apply `this` [ColorHolder] to the given [TextView]
     * @param defaultColor an OPTIONAL [ColorInt] to use al fallback
     */
    fun applyToTextView( textView: TextView, @ColorInt defaultColor: Int? = null ) {
        val color = resolveColor( textView.context )
        // Set color or defaultColor if not null
        if ( color != null ) textView.setTextColor( color )
        else if ( defaultColor != null ) textView.setTextColor( defaultColor )
    }

    fun resolveColor( context: Context ) = when {
        colorRes    != null -> context.getColorCompat( colorRes )
        colorHex    != null -> Color.parseColor( colorHex )
        color       != null -> color
        else -> null
    }

}