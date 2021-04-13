package com.swein.recyclerviewexample

import android.content.Intent
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
import com.swein.recyclerviewexample.detail.DetailActivity

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
 *
 * Hello, in this video, let's add click listener and pass the item content to main activity
 * then, change to next screen
 *
 * if you don't know how to build a recycler view check the video on screen first, thanks
 *
 * 1. create detail activity
 * 2. add interface
 * 3. prepare random image source
 * 4. let's run app, not bad, but I want to the image in detail keep center
 * 5. good, but, I want to different height list item that can wrap item content
 * 6. let's run app, cool
 *
 * thanks
 *
 * video in youtube: https://youtu.be/guo22kLrAFM
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

            override fun onItemViewClick(itemBean: ItemBean) {

                moveToDetail(itemBean)
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

    private fun moveToDetail(itemBean: ItemBean) {
        val intent = Intent(this, DetailActivity::class.java)

        // we pass title and image url to the detail activity
        val title = itemBean.title
        val imageUrl = itemBean.imageUrl

        val bundle = Bundle()
        bundle.putString("content", title)
        bundle.putString("imageUrl", imageUrl)
        intent.putExtra("bundle", bundle)

        startActivity(intent)
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

            val randoms = (0 until 6).random()
            when (randoms) {
                0 -> {
                    itemBean.imageUrl = "https://images.template.net/wp-content/uploads/2016/01/26124651/Cool-Art.jpg"
                }
                1 -> {
                    itemBean.imageUrl = "https://fiverr-res.cloudinary.com/images/q_auto,f_auto/gigs/172054218/original/290e9ed5fbed30bfb914678a7143818e49144f8f/create-patterns-and-cool-designs-for-tshirts-pajamas-etc.png"
                }
                2 -> {
                    itemBean.imageUrl = "https://retrohelix.com/en/wp-content/uploads/2020/02/Tsunami_by_hokusai_19th_century.jpg"
                }
                3 -> {
                    itemBean.imageUrl = "https://i0.wp.com/www.designer-daily.com/wp-content/uploads/2014/12/Ritot-%E2%80%94-Projection-Watch.jpg"
                }
                4 -> {
                    itemBean.imageUrl = "https://static.boredpanda.com/blog/wp-content/uploads/2014/11/creative-t-shirts-20__605.jpg"
                }
                5 -> {
                    itemBean.imageUrl = "https://cdn.wallpapersafari.com/15/87/kp4wAJ.jpg"
                }
            }

            list.add(itemBean)
        }

        return list
    }
}