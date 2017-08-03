package com.example.viet.demoalarmclockmvp.view.main.evenbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.viet.demoalarmclockmvp.R;
import com.example.viet.demoalarmclockmvp.model.Message;
import com.example.viet.demoalarmclockmvp.presenter.main.changetitle.ChangeTitlePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChildActivity extends AppCompatActivity implements ChildView {
    private ChangeTitlePresenter changeTitlePresenter;

    @BindView(R.id.edtTitle)
    EditText edtTitle;
    @BindView(R.id.btnOk)
    Button btnOk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        ButterKnife.bind(this);
        initPresenter();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTitlePresenter.onChangeTitle();
            }
        });
    }

    private void initPresenter() {
        changeTitlePresenter = new ChangeTitlePresenter(this);
    }

    @Override
    public void displayResult() {
        String event = edtTitle.getText().toString();
        Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new Message(event));
    }
}
