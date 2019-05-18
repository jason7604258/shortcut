package com.example.shortcut;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button addButton;
    ListView listView;
    List<String> favorites=new ArrayList<>();
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.button);
        listView = findViewById(R.id.listview);
        favorites.add("this is my favorite");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,favorites);
        listView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            int i=1;
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ((ArrayAdapter) adapter).add(text);
                ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

                ShortcutInfo shortcut = new ShortcutInfo.Builder(MainActivity.this, i+"")
                        .setShortLabel("Website")
                        .setLongLabel("Open the website")
                        .setIcon(Icon.createWithResource(MainActivity.this, R.drawable.ic_ohhhh))
                        .setIntent(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(text)))
                        .build();
                i++;
                shortcutManager.addDynamicShortcuts(Arrays.asList(shortcut));
            }
        });

}
}
