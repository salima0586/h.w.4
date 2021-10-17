package com.example.mokerapi.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mokerapi.databinding.ListPostBinding;
import com.example.mokerapi.model.MokerModel;
import com.example.mokerapi.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public
class MokerAdapter extends RecyclerView.Adapter<MokerAdapter.ViewHolder> {

    private List<MokerModel> mokerList= new ArrayList<>();
    private Callback callback;

    public
    MokerAdapter(Callback callback) {
        this.callback = callback;
    }

    @Override
    public
    ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListPostBinding binding = ListPostBinding.inflate( LayoutInflater.from( parent.getContext() ) );
        return new ViewHolder( binding,callback );
    }

    @Override
    public
    void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mokerList.get(position));
    }

    @Override
    public
    int getItemCount() {
        return mokerList.size();
    }

    public
    void addItems(List<MokerModel> body) {
        this.mokerList = body;
        notifyDataSetChanged();
    }

    public
    class ViewHolder extends RecyclerView.ViewHolder {

        private ListPostBinding binding;
        private Callback callback;

        public
        ViewHolder(ListPostBinding binding,Callback callback) {
            super( binding.getRoot() );
            this.binding = binding;
            this.callback = callback;
        }

        public
        void onBind(MokerModel mokerModel) {
            binding.title.setText( mokerModel.getTitle() );
            binding.content.setText( mokerModel.getContent() );
            binding.group.setText( ""+mokerModel.getGroup() );
            binding.user.setText( ""+mokerModel.getUser() );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public
                void onClick(View v) {
                    callback.clickListener( mokerModel);
                }
            } );

            itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public
                boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    builder.setMessage("Do you want to delete?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Toast.makeText(v.getRootView().getContext(), "clicked", Toast.LENGTH_SHORT).show();

                                    RetrofitBuilder.getInstance().deletePostById(mokerModel.getId()).enqueue( new retrofit2.Callback<MokerModel>() {
                                        @Override
                                        public void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<MokerModel> call, Throwable t) {

                                        }
                                    });
                                    mokerList.remove( getAdapterPosition() );
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                    return true;
                }
            } );
        }
    }
    public interface Callback{
        void clickListener(MokerModel mokerModel);
    }
}
