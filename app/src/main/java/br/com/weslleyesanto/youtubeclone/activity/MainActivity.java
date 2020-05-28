package br.com.weslleyesanto.youtubeclone.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import br.com.weslleyesanto.youtubeclone.R;
import br.com.weslleyesanto.youtubeclone.adapter.AdapterVideo;
import br.com.weslleyesanto.youtubeclone.api.YoutubeService;
import br.com.weslleyesanto.youtubeclone.helper.RetrofitConfig;
import br.com.weslleyesanto.youtubeclone.helper.YoutubeConfig;
import br.com.weslleyesanto.youtubeclone.model.Item;
import br.com.weslleyesanto.youtubeclone.model.Resultado;
import br.com.weslleyesanto.youtubeclone.model.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends Activity {

    //Widgets
    private RecyclerView recyclerView;
    private MaterialSearchView searchView;

    private List<Item> videos = new ArrayList<>();
    private Resultado resultado;
    private AdapterVideo adapterVideo;

    //Retrofit
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componente
        recyclerView = findViewById(R.id.recyclerVideos);
        searchView = findViewById(R.id.searchView);

        //Configuraçeõs iniciais
        retrofit = RetrofitConfig.getRetrofit();

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube Clone");
        setActionBar(toolbar);

        //Recupera Videos
        recuperarVideos();
    }

    private void recuperarVideos(){
        YoutubeService youtubeService = retrofit.create(YoutubeService.class);

        youtubeService.recuperarVideos(
            "snippet",
            "date",
            "20",
                YoutubeConfig.CHAVE_YOUTUBE_API,
                YoutubeConfig.CANAL_ID
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                Log.d("Resultado", "resultado: " + response.toString());
                if (response.isSuccessful()){
                    resultado = response.body();
                    videos = resultado.items;
                    configurarRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });
    }

    public void configurarRecyclerView(){
        adapterVideo = new AdapterVideo(videos, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterVideo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem( item );

        return true;
    }

}
