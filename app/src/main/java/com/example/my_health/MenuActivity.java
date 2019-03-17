package com.example.my_health;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Call to emergency
        findViewById(R.id.fab).setOnClickListener(v -> dialContactPhone());

        // Navigation slide
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Method call to
    private void dialContactPhone() {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0778317534", null)));
    }

//    // Back method
//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setTitle("Really Exit?")
//                .setMessage("Are you sure you want to exit?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> MenuActivity.super.onBackPressed()).create().show();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_deconnect) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Navigation menu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_documents) {

        } else if (id == R.id.nav_marker) {

            Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_doctorw) {

        } else if (id == R.id.nav_profile) {

            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_settings) {

            Intent intent = new Intent(MenuActivity.this, SettingsMenu.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
