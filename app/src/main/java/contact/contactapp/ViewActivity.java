package contact.contactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    TextView textViewname,textViewnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        textViewname= findViewById(R.id.textViewName);
        textViewnumber=findViewById(R.id.textViewNumber);

        String name=getIntent().getStringExtra("Name");
        String number=getIntent().getStringExtra("number");

        textViewname.setText(name);
        textViewnumber.setText(number);

    }
}
