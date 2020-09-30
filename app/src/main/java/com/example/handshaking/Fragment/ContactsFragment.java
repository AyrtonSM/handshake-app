package com.example.handshaking.Fragment;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.handshaking.Adapter.Contact.ContactAdapter;
import com.example.handshaking.Entity.Contact;
import com.example.handshaking.R;
import com.example.handshaking.ViewModel.ContactsViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public RecyclerView mContacts;
    public RecyclerView.Adapter mContactsAdapter;
    public RecyclerView.LayoutManager mContactsLayoutManager;
    public Observer<ArrayList<Contact>> contacts;

    public ContactsFragment() {

    }

    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS},1);
        }
    }


    /**
     * buildContactList is responsible for building the contact list in the main page, so the current user can
     * add contacts to Hand Shaking app
     */
    private void buildContactList(){
        mContacts =  getActivity().findViewById(R.id.contacts);
        mContacts.setHasFixedSize(true);
        mContacts.setLayoutManager(new LinearLayoutManager(getContext()));

   }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPermissions();
        ContactsViewModel contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        contactsViewModel.setActivity(getActivity());
        contactsViewModel.getContacts().observe(getViewLifecycleOwner(), contacts -> {
            Toast.makeText(getContext(), "waaaa", Toast.LENGTH_LONG).show();
            mContactsAdapter = new ContactAdapter(contacts);
            mContacts.setAdapter(mContactsAdapter);
            mContactsAdapter.notifyDataSetChanged();

        });

        buildContactList();

    }
}
