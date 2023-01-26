package org.cazait.cazait_android.ui.viewmodel

import org.cazait.cazait_android.ui.base.BaseViewModel

class MenuData(
//    private val menuImg:ImageView,
    private val menuName: String,
    private val menuPrice: String
) {
    //    fun getMenuImg():String{
//        return menuImg
//    }
    fun getMenuName(): String {
        return menuName
    }

    fun getMenuPrice(): String {
        return menuPrice
    }
}

class CafeInfoMenuViewModel : BaseViewModel() {
    var menuList: ArrayList<MenuData> = arrayListOf(
        MenuData(
//            "${R.drawable.cafeimg1}",
            "아메리카노",
            "1500원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카페라떼",
            "2000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카페모카",
            "3500원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "바닐라라떼",
            "3000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카라멜 마끼아또",
            "4000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카라멜 마끼아또",
            "4000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카라멜 마끼아또",
            "4000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카라멜 마끼아또",
            "4000원"
        )
    )
}