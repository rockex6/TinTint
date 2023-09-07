package com.rockex6.tintint

import androidx.fragment.app.FragmentManager

class DataListCoordinator(private val fragmentManager: FragmentManager) {

    private val mDataListFragment = DataListFragment()
    fun showDataList() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, mDataListFragment)
        fragmentTransaction.commit()
    }

    fun hideDataList() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.hide(mDataListFragment)
        fragmentTransaction.commit()
    }

    fun isVisible(): Boolean {
        val targetFragment = fragmentManager.findFragmentById(mDataListFragment.id)
        return targetFragment?.isVisible ?: false
    }
}