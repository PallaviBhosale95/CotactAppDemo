package contact.contactapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddcontactActivity extends AppCompatActivity {
     EditText editTextName,editTextNumber;
     Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);
        editTextName=findViewById(R.id.edittextname);
        editTextNumber=findViewById(R.id.edittextnumber);
        buttonAdd=findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editTextName.getText().toString();
                String number=editTextNumber.getText().toString();
                Intent intent1=new Intent();

                intent1.putExtra("Name",name);
                intent1.putExtra("number",number);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });




    }
}
