package com.example.animalsound;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    Activity activity;
    String[] animals;
    LayoutInflater inflater;

    public ViewPagerAdapter(Activity activity, String[] animals) {
        this.activity = activity;
        this.animals = animals;
    }

    @Override
    public int getCount() {
        return animals.length;
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(View container, int position,Object object) {
        ((ViewGroup)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item,container,false);
        ImageView img =(ImageView)itemView.findViewById(R.id.imageAnimal);
        int animalImageId = activity.getResources().getIdentifier(animals[position],"drawable",activity.getPackageName());
        img.setImageResource(animalImageId);

        container.addView(itemView);
        return itemView;



    }
}
