package me.everything.overscrolldemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer_nav);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
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

    @SuppressWarnings("StatementWithEmptyBody")
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
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.fade_in_slow, R.animator.fade_out_quick)
                .replace(R.id.fragment_placeholder, fragment)
                .commit();
        getSupportActionBar().setTitle(titleResId);
    }
}
