package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    /**
     * Resource ID for the background color for this list of words
     */
    private final int mColorResourceId;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context         The current context. Used to inflate the layout file.
     * @param words           A List of Words objects to display in a list
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0, because
        // we are inflating the layout manually
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        // // Get the default translation from the currentWord object and set this text on
        // the default TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok translation from the currentWord object and set this text on
        // he Miwok TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imageResource = listItemView.findViewById(R.id.image);

        // Check if the current word has an image
        if (currentWord.hasImage()) {
            // Set the ImageView to the image resource specified in the current Word
            imageResource.setImageResource(currentWord.getImageResourceId());

            //Make sure the view is visible
            imageResource.setVisibility(View.VISIBLE);
        } else {
            //if it has no image hide the imageView (set visibility to GONE)
            imageResource.setVisibility(View.GONE);
        }

        //Find the Layout ID named text_container which contains the 2 text_views
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the resource color ID for the category
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //Set the background color of the text container
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
