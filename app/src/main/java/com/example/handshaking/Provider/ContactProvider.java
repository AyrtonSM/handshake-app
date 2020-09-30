package com.example.handshaking.Provider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;


public class ContactProvider implements LoaderManager.LoaderCallbacks<Cursor> {


    private Activity context;

    public ContactProvider(Activity context){
        this.context = context;
    }

    @SuppressLint("InlinedApi")
    private static final String[] PROJECTION =
            {
                    Contacts._ID,
                    Contacts.LOOKUP_KEY,
                    Build.VERSION.SDK_INT
                            >= Build.VERSION_CODES.HONEYCOMB ?
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY :
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    CommonDataKinds.Phone.NUMBER


            };

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(
                this.context,
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                null,
                 null,
                null
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
//        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
