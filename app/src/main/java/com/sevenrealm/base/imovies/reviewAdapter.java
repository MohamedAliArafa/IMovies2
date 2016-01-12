package com.sevenrealm.base.imovies;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.sevenrealm.base.imovies.provider.Contract;

public class reviewAdapter extends ResourceCursorAdapter {

    public reviewAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView author = (TextView) view.findViewById(R.id.text1);
        author.setText(cursor.getString(cursor.getColumnIndex(Contract.ReviewEntry.COLUMN_AUTHOR)));
        TextView review = (TextView) view.findViewById(R.id.text2);
        review.setText(cursor.getString(cursor.getColumnIndex(Contract.ReviewEntry.COLUMN_CONTENT)));

    }
}
