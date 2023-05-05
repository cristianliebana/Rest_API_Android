package com.example.juegodsarest.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import com.example.juegodsarest.R;
import com.example.juegodsarest.models.Track;

public class ReciclerViewAdapter extends RecyclerView.Adapter<ReciclerViewAdapter.ViewHolder>{
    public List<Track> tracks;
    public ViewHolder viewHolder;

    private static ReciclerViewClickListener itemListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView id, title, singer;
        ImageView photoTrack;
        ImageView deleteImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.idTrack);
            title=(TextView)itemView.findViewById(R.id.titleTrack);
            singer=(TextView)itemView.findViewById(R.id.singerTrack);
            photoTrack=(ImageView) itemView.findViewById(R.id.imgTrack);
            deleteImage=(ImageView) itemView.findViewById(R.id.delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(this.getLayoutPosition());
        }
    }

    public ReciclerViewAdapter(List<Track> tracks, ReciclerViewClickListener itemListener) throws IOException {
        this.tracks = tracks;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_track_item,parent,false);
        this.viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(tracks.get(position).getId());
        holder.title.setText(tracks.get(position).getTitle());
        holder.singer.setText(tracks.get(position).getSinger());
        holder.photoTrack.setImageResource(R.drawable.track);
        holder.deleteImage.setTag(tracks.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void deleteTrack(String id) {
        for(int i = 0; i < tracks.size(); i++){
            if(tracks.get(i).getId().equals(id)) {
                tracks.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, tracks.size());
            }
        }
    }
}

