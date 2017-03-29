package com.arshad.free.gopettingcodechallenge.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arshad.free.gopettingcodechallenge.R;
import com.arshad.free.gopettingcodechallenge.adapters.UpcomingGuideAdapter;
import com.arshad.free.gopettingcodechallenge.models.UpcomingGuide;
import com.arshad.free.gopettingcodechallenge.models.UpcomingGuideResponse;
import com.arshad.free.gopettingcodechallenge.network.ServiceClass;
import com.arshad.free.gopettingcodechallenge.network.ServiceFactory;
import com.arshad.free.gopettingcodechallenge.utils.AppUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arshad on 29/3/17.
 */

public class ListActivity  extends BaseActivity{

    private RecyclerView recyclerView;
    private UpcomingGuideAdapter adapter;
    private ArrayList<UpcomingGuide> upcomingGuides;
    public ArrayList<UpcomingGuide> cartList;
    private ProgressDialog progressDialog;
    private FloatingActionButton fabCart;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initVariables();
        setupToolbar();
        setupClicks();
        setupRecyclerView();
        callApi();
    }

    private void initVariables() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        upcomingGuides = new ArrayList<>();
        cartList = new ArrayList<>();
        fabCart = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void setupToolbar(){
        toolbar.setTitle("Guide List");
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

    private void setupClicks(){
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartList!=null && cartList.size()>0) {
                    Intent cartIntent = new Intent(ListActivity.this, CartActivity.class);
                    cartIntent.putParcelableArrayListExtra("list", cartList);
                    startActivity(cartIntent);
                }else {
                    showSnackbar("your cart is empty. Click to add item to cart", recyclerView);
                }
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UpcomingGuideAdapter(upcomingGuides, ListActivity.this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new UpcomingGuideAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                addToCart(upcomingGuides.get(position));
            }
        });
    }

    private void callApi(){

        if(AppUtils.isNetworkAvailable(ListActivity.this)) {
            showProgressDialog();
            ServiceClass service = ServiceFactory.getInstance(ListActivity.this);
            Call<UpcomingGuideResponse> call = service.getUpcomingGuides();
            call.enqueue(new Callback<UpcomingGuideResponse>() {
                @Override
                public void onResponse(Call<UpcomingGuideResponse> call, Response<UpcomingGuideResponse> response) {
                    hideProgressDialog();
                    if(response.body()!=null) {
                        upcomingGuides = response.body().getData();
                        adapter.updateData(upcomingGuides);
                    }else {
                        showSnackbar("Internal server error", recyclerView);
                    }
                }

                @Override
                public void onFailure(Call<UpcomingGuideResponse> call, Throwable t) {
                    hideProgressDialog();
                    showSnackbar(t.getMessage(), recyclerView);
                }
            });
        }else {
            showSnackbar("No network available", recyclerView);
        }
    }

    private void showProgressDialog(){
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(true);
        }

        progressDialog.show();
    }

    private void hideProgressDialog(){

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    public void addToCart(UpcomingGuide item){
        if(cartList == null){
            cartList = new ArrayList<>();
        }
        if(cartList.contains(item)){
            showSnackbar("Item already added to your cart", recyclerView);
        }else {
            cartList.add(item);
            showSnackbar("Item added to your cart", recyclerView);
        }

    }

    /*@Override
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
