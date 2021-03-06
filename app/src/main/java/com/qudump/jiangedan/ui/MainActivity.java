package com.qudump.jiangedan.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.ui.fragment.BoringPicListFragment;
import com.qudump.jiangedan.ui.fragment.GirlPicListFragment;
import com.qudump.jiangedan.ui.fragment.JokeListFragment;
import com.qudump.jiangedan.ui.fragment.PostListFragment;
import com.qudump.jiangedan.ui.fragment.VideoListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment currentFragment;
    Toolbar toolbar;
    private long lastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if(null != actionBar) {
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().performIdentifierAction(R.id.nav_post, 0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_post) {
            replaceFragment(new PostListFragment());
            toolbar.setTitle("新鲜事");
        } else if (id == R.id.nav_boring_pic) {
            replaceFragment(new BoringPicListFragment());
            toolbar.setTitle("无聊图");
        } else if (id == R.id.nav_joke) {
            replaceFragment(new JokeListFragment());
            toolbar.setTitle("段子");
        } else if (id == R.id.nav_girls) {
            replaceFragment(new GirlPicListFragment());
            toolbar.setTitle("妹子图");
        } else if (id == R.id.nav_video) {
            replaceFragment(new VideoListFragment());
            toolbar.setTitle("小电影");
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK) {

            long current = System.currentTimeMillis();
            if(current - lastClick < 1000){
                finish();
            } else {
                lastClick = current;
                Toast.makeText(this,"再按一次退出煎蛋",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
