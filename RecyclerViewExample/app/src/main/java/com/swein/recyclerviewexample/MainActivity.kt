package com.swein.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.swein.blurmaskloginexample.framework.util.thread.ThreadUtil
import com.swein.recyclerviewexample.adapter.RecyclerViewAdapter
import com.swein.recyclerviewexample.adapter.item.ItemBean

/**
 * Hello, let's build a perfect recycler view
 * no bug, low memory, scroll fast
 *
 * let's start
 *
 * 1. xml and project setting
 * 2. let's build recycler view's item view
 * 3. let's import glide
 * 4. let's build the adapter of recycler view
 * 5. let's finish recycler view and make some dummy data
 * 6. let's add a progress
 * 7. let's run app
 * 8. good, let's see the memory use, good
 * 9. let's click item ok
 *
 * thanks
 *
 * video in youtube: https://youtu.be/I1c-6UgOdM4
 */
class MainActivity : FragmentActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    private lateinit var frameLayoutProgress: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()
        setListener()

        initList()
        reload()
    }

    private fun findView() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.recyclerView)
        frameLayoutProgress = findViewById(R.id.frameLayoutProgress)
    }

    private fun setListener() {
        swipeRefreshLayout.setOnRefreshListener {
            reload()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initList() {

        adapter = RecyclerViewAdapter()

        adapter.delegate = object : RecyclerViewAdapter.RecyclerViewAdapterDelegate {
            override fun onLoadMore() {
                loadMore()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun reload() {

        showProgress()

        // get data from server
        ThreadUtil.startThread {

            Log.d("???", "reload 10 items")
            val list = createDummyData(0, 10)

            ThreadUtil.startUIThread(1000) {
                adapter.reload(list)
                hideProgress()
            }
        }
    }

    private fun loadMore() {
        showProgress()

        // get data from server
        ThreadUtil.startThread {

            Log.d("???", "load more 10 items")
            val list = createDummyData(adapter.itemCount, 10)

            ThreadUtil.startUIThread(500) {
                adapter.loadMore(list)
                hideProgress()
            }
        }
    }

    private fun showProgress() {
        frameLayoutProgress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        frameLayoutProgress.visibility = View.GONE
    }

    /**
     * dummy data same as data from server with paging
     */
    private fun createDummyData(offset: Int, limit: Int): MutableList<ItemBean> {
        val list: MutableList<ItemBean> = mutableListOf()

        var itemBean: ItemBean
        for (i in offset until (offset + limit)) {
            itemBean = ItemBean()
            itemBean.title = "title $i"
            itemBean.content = "content, content, content, content,content, content,content, content,content, content, $i"
            itemBean.imageUrl = "https://cdn.wallpapersafari.com/15/87/kp4wAJ.jpg"
            list.add(itemBean)
        }

        return list
    }
}