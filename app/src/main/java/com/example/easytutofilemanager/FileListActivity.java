package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class FileListActivity extends AppCompatActivity {

    private String path;
    private File root;
    private  File[] filesAndFolders;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        TextView noFilesText = findViewById(R.id.nofiles_textview);

        path = getIntent().getStringExtra("path");
        root = new File(path);
        Log.d("Path", path);
        filesAndFolders = root.listFiles();
        Log.d("Files", Arrays.toString(root.listFiles()));

        if(filesAndFolders==null || filesAndFolders.length ==0){
            noFilesText.setVisibility(View.VISIBLE);
            return;
        }

        noFilesText.setVisibility(View.INVISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(getApplicationContext(),root,filesAndFolders);
        recyclerView.setAdapter(adapter);

    }

    public void option1Clicked(View view) {
    }

    public void CreateFolder(View view) {
        File file;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            file = new File (getIntent().getStringExtra("path")+"/Nouveau dossier");
        } else {
            file = new File(getIntent().getStringExtra("path")+"/Nouveau dossier");
        }
        if (!file.exists()) {
            file.mkdirs();
            adapter.updateFileList(root.listFiles());
        }
    }
}