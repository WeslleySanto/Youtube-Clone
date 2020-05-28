package br.com.weslleyesanto.youtubeclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.weslleyesanto.youtubeclone.R;
import br.com.weslleyesanto.youtubeclone.model.Item;
import br.com.weslleyesanto.youtubeclone.model.Video;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.MyViewHolder> {

    private List<Item> videos = new ArrayList<>();
    private Context context;

    public AdapterVideo(List<Item> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_video, parent, false);
        return new AdapterVideo.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item video = videos.get(position);
        holder.textViewTitulo.setText(video.snippet.title);
//        holder.textViewTitulo.setText(video.id.videoId);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitulo;
        ImageView imageViewCapa;

        public MyViewHolder(View itemView){
            super(itemView);

            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            imageViewCapa = itemView.findViewById(R.id.imageViewCapa);
        }
    }

}


