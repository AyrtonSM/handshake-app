package com.example.handshaking.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handshaking.Adapter.ChatAdapter;
import com.example.handshaking.Entity.Chat;
import com.example.handshaking.Entity.ChatMessage;
import com.example.handshaking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ArrayList<Chat> chats;
    public RecyclerView mChatRecycler;
    public RecyclerView.LayoutManager mChatLayoutManager;
    public RecyclerView.Adapter mChatAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_chat, container, false);


        chats = this.populateTestDataset();

        mChatRecycler = view.findViewById(R.id.chatRecycler);
        mChatLayoutManager = new LinearLayoutManager(getContext());
        mChatAdapter = new ChatAdapter(chats);
        mChatRecycler.setLayoutManager(mChatLayoutManager);
        mChatRecycler.setAdapter(mChatAdapter);
        mChatAdapter.notifyDataSetChanged();

        return view;
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

    private ArrayList<Chat> populateTestDataset(){

        ArrayList<Chat> chats = new ArrayList<>();
        ArrayList<ChatMessage> messageList;
        JSONArray jsonArray = new JSONArray();
        String[] names = {"Ayrton Sousa", "Saori Costa", "Einstein", "Luna"};
        String[] messages = {
                            "Hi I'm Ayrton, it's good to be here.",
                            "Today is very sunny, oh man..",
                            "Hi, here is Einstein, the dog that bites you..",
                            "Father, I'm hungry"
                            };
        boolean[] received = {false, true, true, false};
        boolean[] seen = {false, false, true, false};
        String[] messagesDates = {
            "Today 10:00 a.m",
            "Yesterday 08:00 a.m",
            "Today 10:22 a.m",
            "Today 10:30 a.m"
        };

        for(int i = 0; i < 4; i++){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name",names[i]);
                jsonObject.put("message",messages[i]);
                jsonObject.put("received",received[i]);
                jsonObject.put("seen",seen[i]);
                jsonObject.put("messageDate",messagesDates[i]);

                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < 4; i++){
            try {
                messageList = new ArrayList<>();

                String name = jsonArray.getJSONObject(i).getString("name");
                String message = jsonArray.getJSONObject(i).getString("message");
                boolean receivedMessage = jsonArray.getJSONObject(i).getBoolean("received");
                boolean seenMessage = jsonArray.getJSONObject(i).getBoolean("seen");
                String messageDate = jsonArray.getJSONObject(i).getString("messageDate");

                messageList.add(new ChatMessage(name,message, receivedMessage,seenMessage,messageDate));

                Chat chat = new Chat(null, name,messageDate, messageList);
                chats.add(chat);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return chats;
    }
}
