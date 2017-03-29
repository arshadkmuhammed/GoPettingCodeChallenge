package com.arshad.free.gopettingcodechallenge.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arshad.free.gopettingcodechallenge.R;
import com.arshad.free.gopettingcodechallenge.adapters.CartAdapter;
import com.arshad.free.gopettingcodechallenge.models.UpcomingGuide;

import java.util.ArrayList;

/**
 * Created by arshad on 29/3/17.
 */

public class CartActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ArrayList<UpcomingGuide> cartList;
    private CartAdapter adapter;
    private FloatingActionButton fabCart;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initVariables();
        setupToolbar();
        setupRecyclerView();
    }

    private void initVariables(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fabCart = (FloatingActionButton) findViewById(R.id.fab);
        fabCart.setVisibility(View.GONE);
        cartList = getIntent().getParcelableArrayListExtra("list");
    }

    private void setupToolbar(){
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(cartList, CartActivity.this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CartAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if(v.getId() == R.id.img_close){
                    cartList.remove(cartList.get(position));
                    adapter.notifyDataSetChanged();
                    showSnackbar("Item removed from cart", recyclerView);
                }
            }
        });
    }

   /* @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
