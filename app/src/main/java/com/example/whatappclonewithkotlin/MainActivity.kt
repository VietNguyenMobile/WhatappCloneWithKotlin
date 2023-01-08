package com.example.whatappclonewithkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import android.widget.Toolbar
import com.example.whatappclonewithkotlin.databinding.ActivityMainBinding
import com.example.whatappclonewithkotlin.fragments.ChatsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionPagerAdapter? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
        binding = ActivityMainBinding.inflate(layoutInflater) // new way
//        toolbar = findViewById(R.id.toolbar);
//        fab = findViewById(R.id.fab)

//        setSupportActionBar(toolbar)
//        mSectionsPagerAdapter = SectionPagerAdapter(supportFragmentManager)

//        container.adapter = mSectionsPagerAdapter
//        fab
//        binding.container.adapter = mSectionsPagerAdapter
//        fab.setOnClickListener {view ->
//            Snackbar.make(view, "Replace with action", Snackbar.LENGTH_SHORT).setAction("Action", null).show()
//        }

        val adapter = ViewPagerAdapter(supportFragmentManager) // new way

        adapter.addFragment(ChatsFragment(), "Hello world from section")

        binding.container.adapter = adapter
//        binding.tbLayout.setupWithViewPager(binding.viewPager)

    }

    fun onNewChat(v: View) {
        println("REQUEST_NEW_CHAT")
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            // Permission not granted
//            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
//                AlertDialog.Builder(this)
//                    .setTitle("Contacts permission")
//                    .setMessage("This app requires access to your contacts to initiate a concersation.")
//                    .setPositiveButton("Ask me") { dialog, which -> requestContactsPermission() }
//                    .setNegativeButton("No") { dialog, which ->  }
//                    .show()
//            } else {
//                requestContactsPermission()
//            }
//        } else {
//            // Permission granted
//            startNewActivity(REQUEST_NEW_CHAT)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId

        if (id == R.id.action_profile) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    inner class SectionPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

        private val mFrgmentList = ArrayList<Fragment>()
        private val mFrgmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newIntent(position+1)
        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int) = mFrgmentTitleList[position]
    }

    class PlaceholderFragment: Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            rootView.findViewById<TextView>(R.id.section_label).text = "Hello world from section ${arguments?.getInt(ARG_SECTION_NUMBER)}"
//            return super.onCreateView(inflater, container, savedInstanceState)
            return rootView
        }

        companion object {
            private val ARG_SECTION_NUMBER = "Section number"

            fun newIntent(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

}