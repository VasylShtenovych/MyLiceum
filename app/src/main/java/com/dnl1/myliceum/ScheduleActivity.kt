package com.dnl1.myliceum

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.dnl1.myliceum.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_schedule.*

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var sch_type = intent.getStringExtra(SCHEDULE_TYPE)
        var sch_id = intent.getIntExtra(SCHEDULE_ID, 1)
        var sch_title = intent.getStringExtra(FRAGMENT_TITLE)

        frag_title.text = sch_title

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, sch_type!!, sch_id)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }

    companion object{
        const val SCHEDULE_TYPE = "schedule_type"
        const val SCHEDULE_ID = "schedule_id"
        const val FRAGMENT_TITLE = "fragment_title"
    }
}