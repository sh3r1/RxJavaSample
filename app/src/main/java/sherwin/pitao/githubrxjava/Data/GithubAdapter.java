package sherwin.pitao.githubrxjava.Data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sherwin.pitao.githubrxjava.Model.Github;
import sherwin.pitao.githubrxjava.R;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GitViewHolder>{

    private List<Github> githubList;
    private Github github;
    private Context context;
    public GithubAdapter(Context context, List<Github> github){
        this.context = context;
        this.githubList = github;
    }

    public GithubAdapter(Context context, Github github){
        this.context = context;
        this.github = github;
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.github_item,parent,false);

        return new GitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder holder, int position) {
        Github github = githubList == null ? this.github : githubList.get(position);
        Log.d("WEN",""+github.getLogin());
        holder.login.setText(github.getLogin());
        holder.url.setText(github.getUrl());
        Glide.with(this.context).load(github.getAvatarUrl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return githubList == null ? 1 : githubList.size();
    }

    public static class GitViewHolder extends RecyclerView.ViewHolder {

        private TextView login,url;
        private ImageView imageView;

        public GitViewHolder(View v){
            super(v);

            login = v.findViewById(R.id.textView2);
            url   = v.findViewById(R.id.textView);
            imageView = v.findViewById(R.id.imageView);
        }
    }
}
