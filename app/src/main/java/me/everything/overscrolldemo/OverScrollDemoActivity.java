package me.everything.overscrolldemo;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import me.everything.overscrolldemo.view.GridViewDemoFragment;
import me.everything.overscrolldemo.view.ListViewDemoFragment;
import me.everything.overscrolldemo.view.MiscViewsDemoFragment;
import me.everything.overscrolldemo.view.NestedScrollViewDemoFragment;
import me.everything.overscrolldemo.view.RecyclerViewDemoFragment;
import me.everything.overscrolldemo.view.RecyclerViewStaggeredGridDemoFragment;
import me.everything.overscrolldemo.view.ScrollViewDemoFragment;
import me.everything.overscrolldemo.view.ViewPagerDemoFragment;

public class OverScrollDemoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overscroll_demo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.recycler_view_demo_title);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer_nav);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_placeholder, new RecyclerViewDemoFragment())
                    .commit();
        }
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
    public boolean onNavigationItemSelected(MenuItem item) {
        final int id = item.getItemId();
        item.setChecked(true);

        switch (id) {
            case R.id.drawer_item_recyclerview_demo:
                replaceMainFragment(new RecyclerViewDemoFragment(), R.string.recycler_view_demo_title);
                break;
            case R.id.drawer_item_gridview_demo:
                replaceMainFragment(new GridViewDemoFragment(), R.string.grid_view_demo_title);
                break;
            case R.id.drawer_item_listview_demo:
                replaceMainFragment(new ListViewDemoFragment(), R.string.list_view_demo_title);
                break;
            case R.id.drawer_item_recyclerview_staggered_grid_demo:
                replaceMainFragment(new RecyclerViewStaggeredGridDemoFragment(), R.string.recycler_view_staggered_grid_demo_title);
                break;
            case R.id.drawer_item_scrollview_demo:
                replaceMainFragment(new ScrollViewDemoFragment(), R.string.scroll_view_demo_title);
                break;
            case R.id.drawer_item_viewpager_demo:
                replaceMainFragment(new ViewPagerDemoFragment(),R.string.viewpager_demo_title);
                break;
            case R.id.drawer_item_nested_scrollview_demo:
                replaceMainFragment(new NestedScrollViewDemoFragment(), R.string.nested_scrollview_demo_title);
                break;
            case R.id.drawer_item_misc_demo:
                replaceMainFragment(new MiscViewsDemoFragment(), R.string.misc_views_demo_title);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceMainFragment(Fragment fragment, int titleResId) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.fade_in_slow, R.animator.fade_out_quick)
                .replace(R.id.fragment_placeholder, fragment)
                .commit();
        getSupportActionBar().setTitle(titleResId);
    }
}
