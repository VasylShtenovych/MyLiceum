package com.dnl1.myliceum.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dnl1.myliceum.R
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class ScheduleFragment : Fragment() {

    private var day: Int = 1
    private var scheduleId: Int = 1
    private var scheduleType: String = "students_schedule"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        day = arguments?.getInt(ScheduleFragment.ARG_DAY) ?: 1
        scheduleId = arguments?.getInt(ScheduleFragment.ARG_SCHEDULE_ID) ?: 1
        scheduleType = arguments?.getString(ScheduleFragment.ARG_SCHEDULE_TYPE) ?: "students_schedule"


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_schedule, container, false)


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        day = arguments?.getInt(ScheduleFragment.ARG_DAY) ?: 1
        scheduleId = arguments?.getInt(ScheduleFragment.ARG_SCHEDULE_ID) ?: 1
        scheduleType = arguments?.getString(ScheduleFragment.ARG_SCHEDULE_TYPE) ?: "students_schedule"



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isNetworkAvailable())
        {
            Toast.makeText(activity, "Не вдалось завантажити дані. Перевірте підключення до інтернету", Toast.LENGTH_LONG).show()
        }
        else
        {

            if(scheduleType == "students_schedule")
            {
                webView.loadUrl("http://www.dnl1.if.ua/schedule/?type=$scheduleType&class=$scheduleId&day=$day")
            }
            if(scheduleType == "teachers_schedule")
            {
                webView.loadUrl("http://www.dnl1.if.ua/schedule/?type=$scheduleType&teacher=$scheduleId&day=$day")
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkAvailable(): Boolean {
        val connectionManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    companion object {

        private const val ARG_DAY = "section_number"
        private const val ARG_SCHEDULE_TYPE = "schedule_type"
        private const val ARG_SCHEDULE_ID = "class_id"


        @JvmStatic
        fun newInstance(sectionNumber: Int, scheduleType: String, scheduleId: Int): ScheduleFragment {
            return ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ScheduleFragment.ARG_DAY, sectionNumber)
                    putString(ScheduleFragment.ARG_SCHEDULE_TYPE, scheduleType)
                    putInt(ScheduleFragment.ARG_SCHEDULE_ID, scheduleId)
                }
            }
        }
    }
}