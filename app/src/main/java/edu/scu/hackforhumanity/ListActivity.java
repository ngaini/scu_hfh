package edu.scu.hackforhumanity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class ListActivity extends android.app.ListActivity {

    private List<String[]> list_elements = new LinkedList<String[]>();




    //    private String[] phone_string = {"1237894506", "4561237890", "1594561230","7844567890"};
//    private String[] need_string = {"roti maaangta","kapda maangta","makaan maangta","item maangta"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);




//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        list_elements.add(new String[] {"1234657890","roti maangta"});
        list_elements.add(new String[] {"1597534560","kapda maangta"});
        list_elements.add(new String[] {"9517536210","makaan maangta"});
        list_elements.add(new String[]{"1023456789", "item maangta"});
        list_elements.add(new String[] {"9087456321","roti maangta"});
//        ArrayAdapter<String[]> listView = new ArrayAdapter<String[]>(this, android.R.layout.simple_expandable_list_item_2,android.R.id.text1, list_elements)
//        {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view= super.getView(position, convertView, parent);
//                String[] entry = list_elements.get(position);
//                TextView text1 = (TextView)findViewById(android.R.id.text1);
//                TextView text2 = (TextView)findViewById(android.R.id.text2);
//                text1.setText(entry[0]);
//                text2.setText(entry[1]);
//                return convertView;
//            }
//        };


//        final List<String[]> colorList = new LinkedList<String[]>();
//        colorList.add(new String[] { "Red", "the color red" });
//        colorList.add(new String[] { "Green", "the color green" });
//        colorList.add(new String[] { "Blue", "the color blue" });

        // Note - we're specifying android.R.id.text1 as a param, but it's ignored
        // because we override getView(). That param usually tells ArrayAdapter
        // where to find the one TextView entity in a complex layout.
        // If our layout was a simple TextView (like android.R.layout.simple_list_item_1),
        // we wouldn't need that param.
        setListAdapter(new ArrayAdapter<String[]>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                list_elements) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // Must always return just a View.
                View view = super.getView(position, convertView, parent);

                // If you look at the android.R.layout.simple_list_item_2 source, you'll see
                // it's a TwoLineListItem with 2 TextViews - text1 and text2.
                //TwoLineListItem listItem = (TwoLineListItem) view;
                String[] entry = list_elements.get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(entry[0]);
                text2.setText(entry[1]);
                return view;
            }
        });







    }

}
