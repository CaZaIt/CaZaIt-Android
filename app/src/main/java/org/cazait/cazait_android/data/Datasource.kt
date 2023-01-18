package org.cazait.cazait_android.data

import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.model.domain.Affirmation
import org.cazait.cazait_android.data.model.domain.CafeState

class Datasource {
    fun loadAffirmations(): List<CafeState> {
        return listOf(
            CafeState("카페 범쿤","100", "서울시 광진구 능동 239 - 26", "보통"),
            CafeState("눈물","200", "서울시 광진구 xxxx - xxxxxx", "혼잡"),
            CafeState("눙물","300", "서울시 광진구 xxxx - xxxxxx", "보통"),
            CafeState("국물","400", "서울시 광진구 xxxx - xxxxxx", "여유"),
            CafeState("물논","1200", "서울시 광진구 xxxx - xxxxxx", "보통"),
            CafeState("물론","400", "서울시 광진구 xxxx - xxxxxx", "보통"),
            CafeState("스타벅스 광진 능동점","500", "서울시 광진구 xxxx - xxxxxx", "혼잡"),
            CafeState("골목","600", "서울시 광진구 xxxx - xxxxxx", "보통"),
            CafeState("눔룬","700", "서울시 광진구 xxxx - xxxxxx", "보통"),
            CafeState("엔제리너스카페24시","800", "서울시 광진구 xxxx - xxxxxx", "보통"),
            CafeState("제주몰빵","900", "서울시 광진구 xxxx - xxxxxx", "보통"),
            CafeState("카페 딕셔너리","1000", "서울시 xxxx - xxxxxx", "보통"),
            CafeState("카페베네","1100", "서울시 광진구 xxxx - xxxxxx", "보통")

        )
    }
}