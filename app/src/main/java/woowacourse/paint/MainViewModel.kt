package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.model.BrushTool
import woowacourse.paint.model.ColorBox
import woowacourse.paint.model.PaintBrush
import woowacourse.paint.model.PaintColor
import woowacourse.paint.model.Painting
import woowacourse.paint.model.PaintingHistory
import woowacourse.paint.model.PenPainting

class MainViewModel : ViewModel() {
    private val _colors = MutableLiveData<List<ColorBox>>()
    val colors: LiveData<List<ColorBox>>
        get() = _colors

    private val _paintBrush = MutableLiveData<List<PaintBrush>>()
    val paintBrush: LiveData<List<PaintBrush>>
        get() = _paintBrush

    val paletteHistory = PaintingHistory()
    private var _painting: Painting = PenPainting()
    val painting
        get() = _painting

    init {
        setColors()
        setPaintBrushes()
    }

    private fun setColors() {
        _colors.value = PaintColor.getColorBoxes(R.color.red)
    }

    private fun setPaintBrushes() {
        _paintBrush.value = BrushTool.getPaintBrushes(BrushTool.PEN)
    }

    fun setColorsSelected(colorBox: ColorBox) {
        _colors.value = _colors.value?.map {
            it.copy(isSelected = it == colorBox)
        }
    }

    fun setBrushesSelected(brush: PaintBrush) {
        _paintBrush.value = _paintBrush.value?.map {
            it.copy(isSelected = it == brush)
        }
    }

    fun updatePaintingElement(updateFunction: (Painting) -> Painting) {
        _painting = updateFunction(_painting)
    }
}
