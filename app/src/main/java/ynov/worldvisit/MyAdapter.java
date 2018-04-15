package ynov.worldvisit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Erwann on 15/04/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[][] countries;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[][] myDataset) {
        countries = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        /*TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;*/

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_text_view, parent, false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        /*TextView name;
        TextView capital;
        TextView region;


        name = holder.mTextView.findViewById(R.id.name);
        name.setText(countries[position]);
        capital = holder.mTextView.findViewById(R.id.capital);
        capital.setText(countries[capital]);
        region = holder.mTextView.findViewById(R.id.region);
        region.setText(countries[region]);
        name = ((TextView) holder.findViewById(R.id.flag));

        holder.mTextView.setText(countries[position]);*/
        String[] country = new String[1];
        country[0] = countries[position][0];
        country[1] = countries[position][1];
        country[2] = countries[position][2];
        country[3] = countries[position][3];

        holder.display(country);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return countries.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView capital;
        TextView region;
        ImageView flag;

        /*private Pair<String, String> currentPair;*/

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            capital = (TextView) itemView.findViewById(R.id.capital);
            region = (TextView) itemView.findViewById(R.id.region);;
            flag = (ImageView) itemView.findViewById(R.id.flag);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(countries[1])
                            .setMessage(currentPair.second)
                            .show();
                }
            });*/
        }

        public void display(String[] country) {
            name.setText(country[0]);
            capital.setText("Capitale : "+country[1]);
            region.setText("Continent : "+country[2]);
            String myurl = "http://www.geognos.com/api/en/countries/flag/"+country[3]+".png";
            downloadImage(myurl);
        }

        private void downloadImage(String myurl) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(myurl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                flag.setImageBitmap(bitmap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}


