package contact.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    ArrayList<ContactItem>list;
    Context context;
    public ContactAdapter(Context context,ArrayList<ContactItem>l){
        this.context=context;
        list=l;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);

    }

    @Override
    public long getItemId(int i) {
    return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ContactItem contactItem=list.get(i);
        LayoutInflater inflater=LayoutInflater.from(context);
        View contactView=inflater.inflate(R.layout.contact_row_item,viewGroup,false);
        TextView textViewName=contactView.findViewById(R.id.textViewname);
        TextView textViewNumber=contactView.findViewById(R.id.textViewnumber);

        textViewName.setText(contactItem.getName());
        textViewNumber.setText(contactItem.getMobileNo());

        return  contactView;





    }

    public void addItem(ContactItem newItem){
        list.add(newItem);
    }
    public  void delete(int position){
        list.remove(position);
    }
}
