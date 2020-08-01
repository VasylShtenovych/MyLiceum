package com.dnl1.myliceum

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnl1.myliceum.Adapter.StudentScheduleAdapter
import com.dnl1.myliceum.Adapter.TeachersScheduleAdapter
import com.dnl1.myliceum.Common.SchoolClassesCommon
import com.dnl1.myliceum.Common.TeachersListCommon
import com.dnl1.myliceum.Interface.SchoolClassesRetrofitService
import com.dnl1.myliceum.Interface.TeachersListRetrofitService
import com.dnl1.myliceum.Model.SchoolClasses
import com.dnl1.myliceum.Model.Teacher
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_student_schedule.*
import kotlinx.android.synthetic.main.fragment_teachers_schedule.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeachersScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeachersScheduleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var mService : TeachersListRetrofitService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter : TeachersScheduleAdapter
    lateinit var dialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teachers_schedule, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        mService = TeachersListCommon.retrofitService

        recyclerTeacherScheduleList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        recyclerTeacherScheduleList.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(activity).build()

        getAllTeachersList()
    }

    private fun getAllTeachersList() {
        dialog.show()

        mService.getNewsList().enqueue(object : Callback<MutableList<Teacher>> {
            override fun onFailure(call: Call<MutableList<Teacher>>, t: Throwable) {
                Toast.makeText(activity!!.baseContext, "Не вдалось завантажити дані. Перевірте підключення до інтернету", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            override fun onResponse(call: Call<MutableList<Teacher>>, response: Response<MutableList<Teacher>>) {
                adapter = TeachersScheduleAdapter(activity!!.baseContext, response.body() as MutableList<Teacher>)
                adapter.notifyDataSetChanged()
                recyclerTeacherScheduleList.adapter = adapter

                dialog.dismiss()
            }

        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeachersScheduleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeachersScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}