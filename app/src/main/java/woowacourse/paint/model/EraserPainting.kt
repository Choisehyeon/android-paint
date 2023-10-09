package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.annotation.ColorInt

class EraserPainting(
    private val path: Path = Path(),
    private val _paint: Paint,
) : Painting {

    override val paint: Paint
        get() = _paint

    init {
        _paint.apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.SQUARE
        }
    }

    override fun movePath(x: Float, y: Float): Painting {
        return EraserPainting(path = Path(), _paint = _paint).apply {
            this.path.moveTo(x, y)
        }
    }

    override fun initPath(prevX: Float, prevY: Float, x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, _paint)
    }

    override fun setStroke(value: Float): Painting = EraserPainting(
        path = Path(),
        _paint = Paint(_paint).apply {
            this.strokeWidth = value
        },
    )

    override fun setColor(@ColorInt color: Int): Painting = EraserPainting(
        path = Path(),
        _paint = Paint(_paint).apply {
            this.color = color
        },
    )

    override fun getNewPainting(): Painting {
        return EraserPainting(_paint = _paint)
    }
}