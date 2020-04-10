package com.example.animalsound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] animal = {
      "ayam","kucing","angsa","burung","kenari","gajah","kuda","kambing"
    };
    ViewPager pager;
    ImageButton btnprev,btnnext,btnspeak;
    TextView txtName;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Collections.shuffle(Arrays.asList(animal));
        txtName=(TextView)findViewById(R.id.txtname);
        txtName.setText(upperCaseFirstLetter(animal[0]));

        pager = (ViewPager)findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this,animal);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                txtName.setText(upperCaseFirstLetter(animal[position]));
                if(player != null && player.isPlaying())
                    player.stop();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnprev = (ImageButton)findViewById(R.id.prev);
        btnnext = (ImageButton)findViewById(R.id.next);
        btnspeak = (ImageButton)findViewById(R.id.speak);

        btnnext.setOnClickListener(this);
        btnspeak.setOnClickListener(this);
        btnprev.setOnClickListener(this);
    }

    private String upperCaseFirstLetter(String animal) {
        StringBuilder str = new StringBuilder(animal);
        return str.substring(0,1).toUpperCase()+str.substring(1).toLowerCase();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.prev:{
                if(pager.getCurrentItem()==0)
                    pager.setCurrentItem(animal.length-1,true);
                else
                    pager.setCurrentItem(pager.getCurrentItem()-1,true);
            }
            break;
            case R.id.next: {
                if (pager.getCurrentItem() == animal.length - 1)
                    pager.setCurrentItem(0, true);
                else
                    pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            }
            break;
                case R.id.speak:
                {
                    int animalSound = this.getResources().getIdentifier(txtName.getText().toString().toLowerCase()
                    ,"raw",this.getPackageName());
                    player = MediaPlayer.create(this,animalSound);
                    player.setLooping(false);
                    player.setVolume(1.0f,1.0f);
                    player.start();
                }
                    break;

            default:
                break;
            }
        }
    }
