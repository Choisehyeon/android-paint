package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class CircleDrawble(
    private val path: Path = Path(),
) : CanvasDrawble {

    private var prevX: Float = 0F
    private var prevY: Float = 0F

    override fun movePath(x: Float, y: Float) {
        prevX = x
        prevY = y
    }

    override fun initPath(x: Float, y: Float) {
        path.reset()
        path.addOval(
            prevX,
            prevY,
            x,
            y,
            Path.Direction.CW,
        )
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawPath(path, paint)
    }

    override fun newPainting(): CanvasDrawble {
        return CircleDrawble()
    }
}