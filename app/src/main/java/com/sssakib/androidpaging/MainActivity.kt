package com.sssakib.androidpaging

import CourseModal
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    var count = 0
    var url = "https://jsonkeeper.com/b/WO6S"
    private var courseArrayList: ArrayList<CourseModal>? = null

    private var courseRVAdapter: CourseRVAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()

        courseArrayList = ArrayList()

       val manager = LinearLayoutManager(this)
        idRVCourses.layoutManager = manager

        idNestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                count++

                idPBLoading.visibility = View.VISIBLE
                if (count < 20) {

                    getData()
                }
            }
        })
    }
    private fun getData() {

        val queue = Volley.newRequestQueue(this@MainActivity)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            {
                    response -> idRVCourses.visibility = View.VISIBLE
                for (i in 0 until response.length()) {

                    try {

                        val responseObj = response.getJSONObject(i)

                        val courseName = responseObj.getString("courseName")
                        val courseTracks = responseObj.getString("courseTracks")
                        val courseMode = responseObj.getString("courseMode")
                        val courseImageURL = responseObj.getString("courseimg")
                        courseArrayList!!.add(
                            CourseModal(
                                courseName,
                                courseMode,
                                courseTracks,
                                courseImageURL
                            )
                        )

                        courseRVAdapter = CourseRVAdapter(this@MainActivity, courseArrayList!!)

                        idRVCourses.adapter = courseRVAdapter
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
        ) { Toast.makeText(this@MainActivity, "Fail to get the data..", Toast.LENGTH_SHORT).show() }
        queue.add(jsonArrayRequest)
    }

    }


