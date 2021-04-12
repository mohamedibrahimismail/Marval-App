package  com.example.marval.utils

import androidx.fragment.app.Fragment
import com.example.marval.R
import com.example.marval.ui.base.BaseActivity
import com.example.marval.ui.base.BaseFragment


fun BaseActivity.replaceFragmentToActivity(fragment: Fragment, isSaved: Boolean = false) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    if (isSaved) transaction.addToBackStack(null)
    transaction.commit()
}

fun BaseActivity.showAndHideFragmentsToActivity(active: Fragment, newFragment: Fragment) {
    if (!supportFragmentManager.fragments.contains(newFragment)) {
        supportFragmentManager.beginTransaction().add(R.id.container, newFragment).commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().hide(active).show(newFragment).commitAllowingStateLoss()
    } else {
        supportFragmentManager.beginTransaction().hide(active).show(newFragment).commitAllowingStateLoss()
    }
}

fun BaseActivity.addFragmentToActivity(fragment: Fragment, isSaved: Boolean = false) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(R.id.container, fragment)
    if (isSaved) transaction.addToBackStack(null)
    transaction.commit()
}

fun BaseFragment.replaceFragment(fragment: Fragment, isSaved: Boolean = false) {
    val transaction = childFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    if (isSaved) transaction.addToBackStack(null)
    transaction.commit()
}