package com.yashovardhan99.bobblebigtext

import androidx.ui.tooling.preview.PreviewParameterProvider

class BooleanPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true)

}