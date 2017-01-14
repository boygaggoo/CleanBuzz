package co.zsmb.example.cleanbuzz._1_presentation

import android.graphics.Color
import android.os.Bundle
import android.os.Debug
import android.view.Menu
import android.view.MenuItem
import co.zsmb.example.cleanbuzz.BuildConfig
import co.zsmb.example.cleanbuzz.R
import co.zsmb.example.cleanbuzz._1_presentation.base.BaseView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor

class BuzzActivity : BaseView<BuzzPresenter>(), BuzzView {

    companion object {
        val warningColor = Color.RED
        val resultColor = Color.GRAY
    }

    override fun createPresenter() = activityComponent.makeBuzzPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.bind(this)

        btnBuzz.onClick {
            presenter.requestNumber(etNumber.text.toString())
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(BuildConfig.DEBUG) {
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(BuildConfig.DEBUG) {
            if (item.itemId == R.id.action_new_activity) {
                startActivity(intentFor<BuzzActivity>())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun showResult(result: PresentableResult) {
        tvResult.text = result.result
        tvResult.textColor = if (result.isError) warningColor else resultColor
    }

}