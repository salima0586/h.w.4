package com.example.mokerapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mokerapi.R;
import com.example.mokerapi.databinding.ActivitySecondBinding;
import com.example.mokerapi.model.MokerModel;
import com.example.mokerapi.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class SecondActivity extends AppCompatActivity {

    private
    ActivitySecondBinding binding;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot() );

        String title = getIntent().getStringExtra( "title" );
        String content = getIntent().getStringExtra( "content" );
        String user = getIntent().getStringExtra( "user" );
        String group = getIntent().getStringExtra( "group" );
        String getId = getIntent().getStringExtra( "id" );

        binding.editTitle.setText( title );
        binding.editContent.setText( content );
        binding.editUser.setText( user );
        binding.editGroup.setText( group );


        binding.btnCreate.setOnClickListener( new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                RetrofitBuilder.getInstance().createMokerModel( new MokerModel(
                        binding.editTitle.getText().toString(),
                        binding.editContent.getText().toString(),
                        Integer.valueOf( binding.editUser.getText().toString().trim()),
                                Integer.valueOf( binding.editGroup.getText().toString().trim() ) ) ).enqueue( new Callback<MokerModel>() {
                    @Override
                    public
                    void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

                    }

                    @Override
                    public
                    void onFailure(Call<MokerModel> call, Throwable t) {

                    }
                } );
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity( intent );
            }
        } );

        binding.btnUpgrade.setOnClickListener( new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                RetrofitBuilder.getInstance().upgrade( getId,new MokerModel( binding.editTitle.getText().toString(),
                        binding.editContent.getText().toString(),
                        Integer.valueOf( binding.editUser.getText().toString().trim()),
                        Integer.valueOf( binding.editGroup.getText().toString().trim() )  ) ).enqueue( new Callback<MokerModel>() {
                    @Override
                    public
                    void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

                    }

                    @Override
                    public
                    void onFailure(Call<MokerModel> call, Throwable t) {

                    }
                } );
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity( intent );
            }
        } );
    }
}