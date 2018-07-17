package com.redridgeapps.movies.screen.reviews;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.databinding.ActivityReviewsBinding;
import com.redridgeapps.movies.model.tmdb.Reviews;

public class ReviewsActivity extends AppCompatActivity {

    public static final String EXTRA_REVIEWS = "reviews";

    private ActivityReviewsBinding binding;

    public static Intent createIntent(AppCompatActivity callingActivity, Reviews reviews) {
        Intent intent = new Intent(callingActivity, ReviewsActivity.class);
        intent.putExtra(EXTRA_REVIEWS, reviews);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reviews);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Reviews reviews = getIntent().getParcelableExtra(EXTRA_REVIEWS);

        setupRecyclerView(reviews);
    }

    private void setupRecyclerView(Reviews reviews) {
        ReviewsAdapter adapter = new ReviewsAdapter();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

        adapter.submitList(reviews.getResults());
    }

}
