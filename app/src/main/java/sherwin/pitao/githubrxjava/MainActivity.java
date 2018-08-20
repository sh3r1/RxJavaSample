package sherwin.pitao.githubrxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sherwin.pitao.githubrxjava.Data.GithubAdapter;
import sherwin.pitao.githubrxjava.Model.Github;
import sherwin.pitao.githubrxjava.Network.GitHubClient;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.reactivestreams.Subscription;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private GithubAdapter githubAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        //if(githubAdapter == null){
            getGitHubUsers();
        //}


        final EditText txt = findViewById(R.id.editText);
        Button btnSearch = findViewById(R.id.button);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGitHubUser(txt.getText().toString());
            }
        });


    }


    @Override protected void onDestroy() {

        super.onDestroy();
    }


    private void getGitHubUsers(){

        Observable<List<Github>> git = GitHubClient.getInstance()
                .getGitHubUsers();

        git.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Github>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("WEN","On subcribe");
                    }

                    @Override
                    public void onNext(List<Github> githubs) {
                        githubAdapter = new GithubAdapter(MainActivity.this, githubs);
                        recyclerView.setAdapter(githubAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("WEN","On error :" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("WEN","On Completed");
                    }
                });

    }


    private void getGitHubUser(String name){

        Observable<Github> git = GitHubClient.getInstance()
                .getGitHubUser(name);

        git.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Github>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("WEN","On subcribe");
                    }

                    @Override
                    public void onNext(Github githubs) {
                        githubAdapter = new GithubAdapter(MainActivity.this, githubs);
                        recyclerView.setAdapter(githubAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("WEN","On error :" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("WEN","On Completed");
                    }
                });

    }

}
