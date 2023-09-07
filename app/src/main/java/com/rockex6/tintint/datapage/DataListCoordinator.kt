package com.rockex6.tintint.datapage

import androidx.fragment.app.FragmentManager
import com.rockex6.tintint.R

class DataListCoordinator(private val fragmentManager: FragmentManager) {

    private val mDataListFragment = DataListFragment()
    fun showDataList() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (mDataListFragment.isAdded) {
            fragmentTransaction.show(mDataListFragment)
        } else {
            fragmentTransaction.replace(R.id.container, mDataListFragment)
        }
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