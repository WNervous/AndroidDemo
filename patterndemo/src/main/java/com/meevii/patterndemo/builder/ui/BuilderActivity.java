package com.meevii.patterndemo.builder.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.meevii.patterndemo.R;
import com.meevii.patterndemo.builder.HookMethod.ActorBuilder;
import com.meevii.patterndemo.builder.actor.Actor;
import com.meevii.patterndemo.builder.actor.ActorDirector;
import com.meevii.patterndemo.builder.actor.HeroBuilder;
import com.meevii.patterndemo.builder.classic.Builder;
import com.meevii.patterndemo.builder.classic.ConcretBuilder;
import com.meevii.patterndemo.builder.classic.Director;
import com.meevii.patterndemo.builder.classic.Product;
import com.meevii.patterndemo.builder.noDirector.ActorBuilderNoPara;
import com.meevii.patterndemo.builder.noDirector.HeroBuilder1;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuilderActivity extends AppCompatActivity {


    @BindView(R.id.classic)
    Button classic;
    @BindView(R.id.actor)
    Button actor;
    @BindView(R.id.noDirector)
    Button noDirector;
    @BindView(R.id.hookMethod)
    Button hookMethodL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.classic)
    public void Classic() {
        Builder builder = new ConcretBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        Log.d("BuilderActivity", product.toString());
    }

    @OnClick(R.id.actor)
    public void actor() {
        ActorDirector actorDirector = new ActorDirector();
        Actor actor = actorDirector.create(new HeroBuilder());
        Log.d("BuilderActivity", actor.toString());
    }

    @OnClick(R.id.noDirector)
    public void noDirector() {
        //1.
//        Actor builder = ActorBuilder.builder(new com.meevii.patterndemo.builder.noDirector.HeroBuilder());
//        Log.d("BuilderActivity", builder.toString());
        ActorBuilderNoPara actorBuilderNoPara = new HeroBuilder1();
        Actor builder = actorBuilderNoPara.builder();
        Log.d("BuilderActivity", builder.toString());

    }

    @OnClick(R.id.hookMethod)
    public void hookMethod() {
        ActorBuilder actorBuilder = new com.meevii.patterndemo.builder.HookMethod.HeroBuilder();
        com.meevii.patterndemo.builder.HookMethod.ActorDirector actorDirector = new com.meevii.patterndemo.builder.HookMethod.ActorDirector();
        Actor actor = actorDirector.create(actorBuilder);
        Log.d("BuilderActivity", actor.toString());
    }

    /**
     * 省略Director
     */


}
