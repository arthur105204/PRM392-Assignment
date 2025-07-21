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
import vn.edu.fpt.koreandictionary.network.HistoryApiService;
import vn.edu.fpt.koreandictionary.viewmodel.DictionaryViewModel;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {
    private DictionaryViewModel viewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter();
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);
        viewModel.getHistory().observe(this, history -> {
            adapter.setHistory(history);
        });
        viewModel.loadHistory();
    }

    private class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {
        private List<HistoryApiService.HistoryEntry> history;
        public void setHistory(List<HistoryApiService.HistoryEntry> history) {
            this.history = history;
            notifyDataSetChanged();
        }
        @Override
        public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_search_history, parent, false);
            return new HistoryViewHolder(view);
        }
        @Override
        public void onBindViewHolder(HistoryViewHolder holder, int position) {
            HistoryApiService.HistoryEntry entry = history.get(position);
            holder.bind(entry);
            holder.itemView.findViewById(R.id.buttonRemoveHistory).setOnClickListener(v -> {
                viewModel.removeHistory(entry.word);
                Toast.makeText(HistoryActivity.this, "Removed from history", Toast.LENGTH_SHORT).show();
            });
        }
        @Override
        public int getItemCount() {
            return history == null ? 0 : history.size();
        }
    }

    private class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewWord, textViewTimestamp;
        public HistoryViewHolder(View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.textViewWord);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }
        public void bind(HistoryApiService.HistoryEntry entry) {
            textViewWord.setText(entry.word);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(entry.timestamp));
            textViewTimestamp.setText(date);
        }
    }
} 