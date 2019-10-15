package pe.com.pedidosya.ui.home;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import pe.com.pedidosya.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @BindView(R.id.text_home) TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);

        //final TextView textView = root.findViewById(R.id.text_home);
        RadioGroup radioGroup = (RadioGroup)root.findViewById(R.id.rgGrupo);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //RadioButton rb=(RadioButton)root.findViewById(checkedId);
                //Toast.makeText(getActivity(), rb.getText(), Toast.LENGTH_SHORT).show();

                switch(checkedId)
                {
                    case R.id.rbUno:
                        Toast.makeText(getActivity(),"UNO", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbDos:
                        Toast.makeText(getActivity(), "DOS", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        textView.setText("HOME");
/*        Button btnUno= root.findViewById(R.id.btnUno);

        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Evento si ButterKnife",Toast.LENGTH_SHORT).show();
            }
        });*/

      /*  homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

  /*  @OnClick(R.id.btnDos)
    public void onClick(View view){
        Toast.makeText(getActivity(),"EVENTO CON BUTTERKNIFE", Toast.LENGTH_SHORT).show();
    }*/

    @OnClick({R.id.btnDos,R.id.btnUno})
    public void submit(View view) {
        String mensaje="Estoy haciendo click en";
        switch (view.getId()){
            case R.id.btnUno:
                mensaje+=" el botton uno";
                break;
            case  R.id.btnDos:
                mensaje+=" el botton dos";
                break;
        }
        Toast.makeText(getActivity(),mensaje, Toast.LENGTH_SHORT).show();
    }
/*
    @OnCheckedChanged({R.id.rbUno, R.id.rbDos})
    public void onRadioButtonCheckChanged(CompoundButton button, boolean checked) {
        String mensaje="Estoy haciendo click en";
        if(checked) {
            switch (button.getId()) {
                case R.id.rbUno:
                    mensaje+=" radio button uno";
                    break;
                case R.id.rbDos:
                    mensaje+=" radio button dos";
                    break;
            }
            Toast.makeText(getActivity(),mensaje, Toast.LENGTH_SHORT).show();
        }
    }*/
}