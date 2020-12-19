package com.utachiwana.athena.ui.menu.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.utachiwana.athena.R;
import com.utachiwana.athena.data.Post;

public class PostDetailsActivity extends AppCompatActivity {

    TextView mForm, mType, mName, mSubject, mTime, mText, mPrice, mDuration;
    Button mSignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        Post post = (Post) getIntent().getSerializableExtra("data");
        mType = findViewById(R.id.details_type);
        mTime = findViewById(R.id.details_time);
        mName = findViewById(R.id.details_author);
        mDuration = findViewById(R.id.details_duration);
        mForm = findViewById(R.id.details_form);
        mPrice = findViewById(R.id.details_price);
        mSubject = findViewById(R.id.details_subject);
        mText = findViewById(R.id.details_text);
        mSignUpBtn = findViewById(R.id.details_signup_button);

        mType.setText(getResources().getString(R.string.type) + ": " + post.getType());
        mTime.setText(getResources().getString(R.string.freetime) + ":\n" + post.getTime());
        mName.setText(getResources().getString(R.string.textName) + ": " + post.getAuthor());
        mDuration.setText(getResources().getString(R.string.duration) + ": " + post.getDuration());
        mForm.setText(getResources().getString(R.string.form) + ": " + post.getForm());
        mPrice.setText(getResources().getString(R.string.price) + ": " + post.getPrice());
        mSubject.setText(getResources().getString(R.string.subject) + ": " + post.getSubject());
        mText.setText(getResources().getString(R.string.description) + ": " + post.getText());
    }
}