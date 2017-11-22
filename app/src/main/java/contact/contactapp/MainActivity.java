package contact.contactapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textViewname, textViewNumber;
    ContactAdapter adapter;
    ArrayList<ContactItem> contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        textViewname = findViewById(R.id.textViewname);
        textViewNumber = findViewById(R.id.textViewnumber);
     /*   ContactItem contact = new ContactItem("amruta", "8655268502");
        ContactItem contact1 = new ContactItem("pallavi", "9404757187");

        ArrayList<ContactItem> contacts = new ArrayList<>();
        contacts.add(contact);
        contacts.add(contact1);*/
        MyDatabase database=new MyDatabase(MainActivity.this);
        ArrayList<ContactItem> contacts=database.getAllContacts();

        adapter = new ContactAdapter(MainActivity.this, contacts);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showPopupMenu(view, i);
            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            ContactItem contact2=new ContactItem("Devika","8652425862");
            adapter.addItem(contact2);
            adapter.notifyDataSetChanged();
            }
        });
*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItemId = item.getItemId();
        if (selectedItemId == R.id.actionAdd) {
            Toast.makeText(MainActivity.this, "add item selected", Toast.LENGTH_SHORT).show();
            addContact();
        } else if (selectedItemId == R.id.actionsettings) {
            Toast.makeText(MainActivity.this, "setting item selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("options");


    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = adapterContextMenuInfo.position;

        ContactItem contactItem= (ContactItem) adapter.getItem(pos);
        MyDatabase database=new MyDatabase(MainActivity.this);


        int selectedItemId = item.getItemId();
        if (selectedItemId == R.id.actionDelete) {


            Toast.makeText(MainActivity.this, "Delete item selected", Toast.LENGTH_SHORT).show();

            adapter.delete(pos);
            adapter.notifyDataSetChanged();






        }
        return super.onContextItemSelected(item);
    }

    public void showPopupMenu(View view, final int pos) {

        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int selectedItemId = menuItem.getItemId();
                if (selectedItemId == R.id.actionEdit) {

                    Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                } else if (selectedItemId == R.id.actionview) {
                    ContactItem contactItem = (ContactItem) adapter.getItem(pos);
                    showview(contactItem);
                    Toast.makeText(MainActivity.this, "view", Toast.LENGTH_SHORT).show();


                } else if (selectedItemId == R.id.actioncall) {
                    Toast.makeText(MainActivity.this, "call", Toast.LENGTH_SHORT).show();
                    ContactItem contactItem = (ContactItem) adapter.getItem(pos);
                    call(contactItem);

                }
                return false;
            }
        });

        popupMenu.show();

    }

    public void showview(ContactItem contactItem) {

        String Name = contactItem.getName();
        String number = contactItem.getMobileNo();

        // String Name=textViewname.getText().toString();
        //String   number=textViewNumber.getText().toString();

        Intent viewIntentname = new Intent(MainActivity.this, ViewActivity.class);
        viewIntentname.putExtra("Name", Name);
        viewIntentname.putExtra("number", number);
        startActivity(viewIntentname);


    }

    public void addContact() {
        Intent addIntent = new Intent(MainActivity.this, AddcontactActivity.class);
        startActivityForResult(addIntent, 1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("Name");
                String number = data.getStringExtra("number");

                ContactItem contactItem=new ContactItem();
                contactItem.setName(name);
                contactItem.setMobileNo(number);


                adapter.addItem(contactItem);
                adapter.notifyDataSetChanged();
                MyDatabase database=new MyDatabase(MainActivity.this);
                database.insertContact(contactItem);


            }
        }

    }

    public void call(ContactItem contact) {

        String number = contact.getMobileNo();

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            String premissions[] = {Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(MainActivity.this, premissions, 123);

        }
        startActivity(intent);

    }




}
