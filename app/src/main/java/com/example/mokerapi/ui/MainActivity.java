package com.example.mokerapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mokerapi.R;
import com.example.mokerapi.adapter.MokerAdapter;
import com.example.mokerapi.databinding.ActivityMainBinding;
import com.example.mokerapi.model.MokerModel;
import com.example.mokerapi.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class MainActivity extends AppCompatActivity implements MokerAdapter.Callback{

    private ActivityMainBinding binding;
    private final MokerAdapter adapter = new MokerAdapter(this);

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );

        binding.recyclerview.setAdapter( adapter );

        MokerModel mokerModel = new MokerModel( "e","content",2 ,2);
        getPosts();
       // createPosts(mokerModel);
        openSecondActivity();
    }

    private void openSecondActivity(){
        binding.btnCreate.setOnClickListener( new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity( intent );
            }
        } );
    }

    private void createPosts(MokerModel mokerModel){
        mokerModel = new MokerModel( "post","second",1,2 );
        RetrofitBuilder.getInstance().createMokerModel( mokerModel ).enqueue( new Callback<MokerModel>() {
            @Override
            public
            void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

            }

            @Override
            public
            void onFailure(Call<MokerModel> call, Throwable t) {

            }
        } );
    }

    private void getPosts(){

        RetrofitBuilder.getInstance().getPost().enqueue( new Callback<List<MokerModel>>() {

            @Override
            public
            void onResponse(Call<List<MokerModel>> call, Response<List<MokerModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    adapter.addItems(response.body());

                }
                else {
                    Log.d("tag","error"+response.code());
                }
            }

            @Override
            public
            void onFailure(Call<List<MokerModel>> call, Throwable t) {
                Log.d("tag","failure" + t.getLocalizedMessage());
            }
        } );
    }


    @Override
    public
    void clickListener(MokerModel mokerModel) {

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra( "title",mokerModel.getTitle());
        intent.putExtra( "content", mokerModel.getContent());
        intent.putExtra( "user", ""+ mokerModel.getUser());
        intent.putExtra( "group", ""+ mokerModel.getGroup());
        intent.putExtra( "id", ""+ mokerModel.getId());
        startActivity( intent );
    }
}