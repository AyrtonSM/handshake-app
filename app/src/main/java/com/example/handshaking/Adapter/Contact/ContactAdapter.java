package com.example.handshaking.Adapter.Contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handshaking.Activities.Chat.FriendRequestActivity;
import com.example.handshaking.Entity.Contact;
import com.example.handshaking.Model.FriendCollection;
import com.example.handshaking.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    public ArrayList<Contact> contacts;

    public ContactAdapter(ArrayList<Contact> contacts){
        this.contacts = contacts;
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView contactName;
        public TextView contactTelephone;
        public LinearLayout linearLayout1;
        public LinearLayout linearLayout2;
        public ImageView image;

        public ContactViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.main_contact_card);
            this.contactName = itemView.findViewById(R.id.contact_name);
            this.contactTelephone = itemView.findViewById(R.id.contact_telephone_1);
            this.linearLayout1 = itemView.findViewById(R.id.layout_contact);
            this.linearLayout2 = itemView.findViewById(R.id.layout_contact_info);
            this.image = itemView.findViewById(R.id.contact_image);
        }
    }


    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact_info, parent, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(contactView);

        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(holder.cardView.getContext(), String.valueOf(holder.cardView.getCardElevation()), Toast.LENGTH_LONG).show();

                    holder.cardView.setCardElevation(30);


                Toast.makeText(holder.cardView.getContext(), holder.contactName.getText().toString(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

        holder.contactName.setText(contacts.get(position).getName());
        holder.contactTelephone.setText(contacts.get(position).getTelephone());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact contact = new Contact();

                contact.setName(holder.contactName.getText().toString());
                contact.setTelephone(holder.contactTelephone.getText().toString());

                FriendCollection.addFriend(contact);
                FriendCollection.getContactById(0);

//                Toast.makeText(holder.cardView.getContext(),holder.contactName.getText().toString(),Toast.LENGTH_LONG).show();
                Intent friendRequestActivity = new Intent(holder.cardView.getContext(), FriendRequestActivity.class);
                JSONObject contactInformation = new JSONObject();

                try {
                    contactInformation.put("contact_name", holder.contactName.getText().toString());
                    contactInformation.put("contact_phone", holder.contactTelephone.getText().toString());

                    friendRequestActivity.putExtra("contact_json_data", contactInformation.toString());
                    holder.cardView.getContext().startActivity(friendRequestActivity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
