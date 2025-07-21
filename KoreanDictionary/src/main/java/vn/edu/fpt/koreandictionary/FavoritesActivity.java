package vn.edu.fpt.koreandictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.fpt.koreandictionary.network.FavoritesApiService;
import vn.edu.fpt.koreandictionary.viewmodel.DictionaryViewModel;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FavoritesActivity extends AppCompatActivity {
    private DictionaryViewModel viewModel;
    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        recyclerView = findViewById(R.id.recyclerViewFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoritesAdapter();
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);
        viewModel.getFavorites().observe(this, favorites -> {
            adapter.setFavorites(favorites);
        });
        viewModel.loadFavorites();
    }

    private class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {
        private List<FavoritesApiService.FavoriteInfo> favorites;
        public void setFavorites(List<FavoritesApiService.FavoriteInfo> favorites) {
            this.favorites = favorites;
            notifyDataSetChanged();
        }
        @Override
        public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_favorite, parent, false);
            return new FavoritesViewHolder(view);
        }
        @Override
        public void onBindViewHolder(FavoritesViewHolder holder, int position) {
            FavoritesApiService.FavoriteInfo fav = favorites.get(position);
            holder.bind(fav);
            holder.itemView.findViewById(R.id.buttonRemoveFavorite).setOnClickListener(v -> {
                viewModel.removeFavorite(fav.word);
                Toast.makeText(FavoritesActivity.this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            });
        }
        @Override
        public int getItemCount() {
            return favorites == null ? 0 : favorites.size();
        }
    }

    private class FavoritesViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewWord, textViewPronunciation, textViewDefinition, textViewPos, textViewTimestamp;
        private final ImageButton buttonRemove;
        public FavoritesViewHolder(View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.textViewWord);
            textViewPronunciation = itemView.findViewById(R.id.textViewPronunciation);
            textViewDefinition = itemView.findViewById(R.id.textViewDefinition);
            textViewPos = itemView.findViewById(R.id.textViewPos);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
        public void bind(FavoritesApiService.FavoriteInfo fav) {
            textViewWord.setText(fav.word);
            textViewPronunciation.setText(fav.pronunciation);
            textViewDefinition.setText(fav.definition);
            textViewPos.setText(fav.pos);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(fav.timestamp));
            textViewTimestamp.setText(date);
        }
    }
} 