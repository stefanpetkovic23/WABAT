package com.example.swev2.ui.others;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.swev2.R;
import com.example.swev2.ui.bookflights.BookFlightFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchDestinationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchDestinationsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SearchView search;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;

    public SearchDestinationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchDestinationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchDestinationsFragment newInstance(String param1, String param2) {
        SearchDestinationsFragment fragment = new SearchDestinationsFragment();
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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_destinations, container, false);

        search=view.findViewById(R.id.search);
        listView = view.findViewById(R.id.leaving);
        arrayList = new ArrayList<>();
        arrayList.add("London");
        arrayList.add("Paris");
        arrayList.add("Beograd");
        arrayList.add("Nis");
        arrayList.add("Istanbul");
        arrayList.add("Madrid");
        arrayList.add("Skopje");
        arrayList.add("Amsterdam");
        arrayList.add("Milan");
        arrayList.add("Rim");
        arrayList.add("Sofia");
        arrayList.add("Kairo");
        arrayList.add("Atina");
        arrayList.add("Berlin");
        arrayList.add("Dublin");
        arrayList.add("Minhen");
        arrayList.add("Lisabon");
        arrayList.add("Stokholm");
        arrayList.add("Frankfurt");
        arrayList.add("Podgorica");
        arrayList.add("Verona");

        adapter = new ArrayAdapter<>(SearchDestinationsFragment.this.getContext(), android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);


        String dest_position="";
        Bundle bundle = getArguments();
        if (bundle != null) {
            dest_position = bundle.getString("position");
        }

        String finalDest_position = dest_position;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String data = listView.getAdapter().getItem(position).toString();
                Bundle result = new Bundle();
                result.putString("data",data);
                if(finalDest_position.equals("start"))
                {
                    getParentFragmentManager().setFragmentResult("fragment1",result);
                }
                else if(finalDest_position.equals("final"))
                {
                    getParentFragmentManager().setFragmentResult("fragment2",result);
                }



                Navigation.findNavController(view).navigate(R.id.action_searchDestinationsFragment_to_nav_bookflight);
            }
        });

        return view;
    }
}