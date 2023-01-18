package org.cazait.cazait_android.data

import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.model.domain.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.test_msg1, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg2, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg1, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg2, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg1, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg2, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg1, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg2, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg1, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg2, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg1, R.drawable.image_cafe_ex1),
            Affirmation(R.string.test_msg2, R.drawable.image_cafe_ex1)
        )
    }
}