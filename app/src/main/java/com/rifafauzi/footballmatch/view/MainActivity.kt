package com.rifafauzi.footballmatch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.adapter.ClubsAdapter
import com.rifafauzi.footballmatch.model.Clubs
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        private val listClubs: MutableList<Clubs> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        initData()
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                padding = dip(16)
                lparams(matchParent, matchParent)

                /**
                 * bug in anko with using androidx
                 */
//                recyclerView {
//                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
//                    adapter = ClubsAdapter(context, listClubs)
//                }.lparams(matchParent, matchParent)
            }
        }
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_desc)
        listClubs.clear()
        for (i in name.indices) {
            listClubs.add(Clubs(image.getResourceId(i, 0), name[i], desc[i]))
        }
        //Recycle the typed array
        image.recycle()
    }
}
