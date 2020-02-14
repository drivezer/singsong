package com.androdocs.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_add extends Fragment implements View.OnClickListener {


    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button Buntonclear;
    Button Buttonadd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.content_add,container,false);

            Buntonclear = view.findViewById(R.id.btndel);
            textView1 = view.findViewById(R.id.txtnamesong);
            textView2 = view.findViewById(R.id.txtlinkchord);
            textView3 = view.findViewById(R.id.txttextsong);
            textView4 = view.findViewById(R.id.txtlinksong);

            Buttonadd = view.findViewById(R.id.btnedit);


            Buntonclear.setOnClickListener(this);
            Buttonadd.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case  R.id.btndel:
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                break;

            case  R.id.btnedit:
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                break;
        }
    }

}

